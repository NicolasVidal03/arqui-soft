package com.ucb.demo;

import org.springframework.http.ResponseEntity;

import com.ucb.demo.dto.ErrorResponse;
import com.ucb.demo.dto.LoginDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

public interface ILoginApi {

    @Tag(name = "Login", description = "Obtain the login")
    @Operation(summary = "Lista de logins", description = "Loren ipsum")
    @ApiResponses(
        value = {
            @ApiResponse(
                responseCode = "200", description = "loren ipson landa bla"
            ),
            @ApiResponse(
                responseCode = "400", description = "Bad request"
            ),
            @ApiResponse(
                responseCode = "404", description = "No se encontró la solicitud"
            ),
            @ApiResponse(
                responseCode = "500", description = "${api.responseCodes.internalServer.description}",
                 content = {
                    @Content(mediaType = "application/json",
                             schema = @Schema(implementation = ErrorResponse.class))
                 }

            )
        }
    )
    public String index();
    
    @Tag(name = "Login", description = "Obtain one login by email")
    public ResponseEntity<LoginDto> obtain(String email);


    @Tag(name = "Login", description = "Create login")
    public ResponseEntity<LoginDto> create(LoginDto login);

}
