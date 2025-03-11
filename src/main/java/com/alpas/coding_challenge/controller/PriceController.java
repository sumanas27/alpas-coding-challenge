package com.alpas.coding_challenge.controller;


import static com.alpas.coding_challenge.util.Constants.PRICE_REQUEST;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alpas.coding_challenge.bo.AveragePriceRecord;
import com.alpas.coding_challenge.bo.Price;
import com.alpas.coding_challenge.bo.PriceRecord;
import com.alpas.coding_challenge.service.PriceService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping( "/api/v1" )
@RequiredArgsConstructor
@Tag( name = "Price API", description = "API to record and get average price data over time" )
public class PriceController {

    private final PriceService priceService;

    @Operation( summary = "Records price with current timestamp",
                description = "In this endpoint implementation a price will "
                              + "be recorded with the current timestamp on the operation." )
    @ApiResponses( value = {
            @ApiResponse( responseCode = "200",
                          description = "Price with current timestamp is recorded.",
                          content = { @Content( mediaType = "application/json", schema =
                          @Schema( implementation = PriceRecord.class ) )
                          } ),
            @ApiResponse( responseCode = "400",
                          description = "Invalid request parameters",
                          content = @Content )
    } )
    @PostMapping( value = "/prices", produces = "application/json" )
    public Price recordPrice( @RequestParam( PRICE_REQUEST ) double price ) {
        return priceService.record( price );
    }

    @Operation( summary = "Fetches all price recorded lifetime of this service.",
                description = "In this endpoint implementation all the recorded "
                              + "price will be fetched." )
    @ApiResponses( value = {
            @ApiResponse( responseCode = "200",
                          description = "All Price recorded is fetched.",
                          content = { @Content( mediaType = "application/json", array =
                          @ArraySchema( schema = @Schema( implementation = PriceRecord.class ) ) )
                          } ),
            @ApiResponse( responseCode = "503",
                          description = "Service is unavailable",
                          content = @Content )
    } )
    @GetMapping( value = "/", produces = "application/json" )
    public List<PriceRecord> getPrices() {
        return priceService.getPrices();
    }

    @Operation( summary = "Fetches list of average prices recorded for the last 30 seconds.",
                description = "In this endpoint implementation a list of average price will "
                              + "be fetched recorded in the last 30 seconds, "
                              + "Each full second is a data point." )
    @ApiResponses( value = {
            @ApiResponse( responseCode = "200",
                          description = "Average Price recorded for last 30 seconds is fetched",
                          content = { @Content( mediaType = "application/json", array =
                          @ArraySchema( schema = @Schema( implementation = AveragePriceRecord.class ) ) )
                          } ),
            @ApiResponse( responseCode = "503",
                          description = "Service is unavailable",
                          content = @Content ) } )
    @GetMapping( value = "/average-prices", produces = "application/json" )
    public List<AveragePriceRecord> getAveragePrices() {
        return priceService.averagePrices();
    }
}
