package com.ucb.demo;

import org.springframework.http.ResponseEntity;

import com.ucb.demo.dto.ErrorResponse;
import com.ucb.demo.dto.TarjetaDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

public interface ITarjetaApi {

    @Tag(name = "Tarjeta", description = "Obtain the card")
    @Operation(summary = "Lista de tarjetas", description = "Loren ipsum")
    @ApiResponses(
        value = {
            @ApiResponse(
                responseCode = "200", description = "La solicitud tuvo exito"
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
    
    
    @Tag(name = "Tarjeta", description = "Obtain one card by id")
    public ResponseEntity<TarjetaDto> obtain(String card_number);


    @Tag(name = "Tarjeta", description = "Create card")
    public ResponseEntity<TarjetaDto> create(TarjetaDto tarjeta);

}
