package com.ucb.demo;

import org.springframework.web.bind.annotation.RestController;
import java.lang.Exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
// import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.networknt.schema.SpecVersion.VersionFlag;
import com.networknt.schema.ValidationMessage;

import com.ucb.demo.dto.LoginDto;

import io.sentry.Sentry;
// import org.springframework.web.bind.annotation.RequestParam;

import java.util.Set;

import org.springframework.http.ResponseEntity;

@RestController
public class Login  {

    @GetMapping("/")
    public String index() {

        try {
            throw new Exception("This is a test.");
          } catch (Exception e) {
            Sentry.captureException(e);
          }
        return "Greetings from Spring boot";
    }

    @GetMapping( value = "/login/{email}", produces = "application/json")
    public ResponseEntity<LoginDto> obtain(@PathVariable String email) {
        
        var login = new LoginDto("a@gmail.com", "1234");
        return ResponseEntity.ok(login);

    }

    @PostMapping( value = "/login", produces = "application/json")
    public ResponseEntity create(@RequestBody LoginDto login) {

        ObjectMapper mapper = new ObjectMapper();
        String json;
        try {
            json = mapper.writeValueAsString(login);
            JsonSchemaFactory factory = JsonSchemaFactory.getInstance(VersionFlag.V7);
            JsonSchema jsonSchema = factory.getSchema(Login.class.getClassLoader().getResourceAsStream("schemas/login.json"));
            JsonNode jsonNode = mapper.readTree(json);
            Set<ValidationMessage> errors = jsonSchema.validate(jsonNode); 

            String errorsCombined = "";
            for( ValidationMessage error: errors) {
                errorsCombined += error.toString() +  "\n";
            }

            if(errors.size() > 0) {
                return ResponseEntity.badRequest().body("Please fix your JSON!,\n"+errorsCombined);
            }
            return ResponseEntity.ok(login);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return ResponseEntity.ok(login);
        }
    }


}
