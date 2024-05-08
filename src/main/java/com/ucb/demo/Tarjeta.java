package com.ucb.demo;

import org.springframework.web.bind.annotation.RestController;

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

import com.ucb.demo.dto.TarjetaDto;

// import io.sentry.Sentry;

// import org.springframework.web.bind.annotation.RequestParam;

import java.util.Set;

import org.springframework.http.ResponseEntity;

@RestController
public class Tarjeta {


    @GetMapping( value = "/tarjeta/{card_number}", produces = "application/json")
    public ResponseEntity<TarjetaDto> obtain(@PathVariable String card_number) {
        
        var tarjeta = new TarjetaDto("123456789", 723, "12/24");
        return ResponseEntity.ok(tarjeta);

    }

    @PostMapping( value = "/tarjeta", produces = "application/json")
    public ResponseEntity create(@RequestBody TarjetaDto tarjeta) {

        ObjectMapper mapper = new ObjectMapper();
        String json;
        try {
            json = mapper.writeValueAsString(tarjeta);
            JsonSchemaFactory factory = JsonSchemaFactory.getInstance(VersionFlag.V7);
            JsonSchema jsonSchema = factory.getSchema(Login.class.getClassLoader().getResourceAsStream("schemas/tarjeta.json"));
            JsonNode jsonNode = mapper.readTree(json);
            Set<ValidationMessage> errors = jsonSchema.validate(jsonNode); 

            String errorsCombined = "";
            for( ValidationMessage error: errors) {
                errorsCombined += error.toString() +  "\n";
            }

            if(errors.size() > 0) {
                return ResponseEntity.badRequest().body("Please fix your JSON!,\n"+errorsCombined);
            }
            return ResponseEntity.ok(tarjeta);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return ResponseEntity.ok(tarjeta);
        }
    }


}
