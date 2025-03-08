package com.alpas.coding_challenge.controller;


import static java.time.Instant.now;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.alpas.coding_challenge.bo.AveragePriceRecord;
import com.alpas.coding_challenge.bo.Price;
import com.alpas.coding_challenge.bo.PriceRecord;
import com.alpas.coding_challenge.service.PriceService;

@WebMvcTest(PriceController.class)
@ExtendWith(SpringExtension.class)
class PriceControllerWebMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private PriceService priceService;

    @Test
    void testRecordPrice() throws Exception {
        double priceValue = 100.5;
        Price mockPrice = new Price(priceValue);

        when(priceService.record(priceValue)).thenReturn(mockPrice);

        mockMvc.perform(post("/prices").param("price", String.valueOf(priceValue)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price").value(priceValue));
    }

    @Test
    void testGetPrices() throws Exception {
        List<PriceRecord> mockRecords = Arrays.asList(
                new PriceRecord( 100.0, now()),
                new PriceRecord(200.0, now())
        );

        when(priceService.getPrices()).thenReturn(mockRecords);

        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].price").value(100.0))
                .andExpect(jsonPath("$[1].price").value(200.0));
    }

    @Test
    void testGetAveragePrices() throws Exception {
        List<AveragePriceRecord> mockAvgRecords = Arrays.asList(
                new AveragePriceRecord(now(), 100.0),
                new AveragePriceRecord(now(), 150.0)
        );

        when(priceService.averagePrices()).thenReturn(mockAvgRecords);

        mockMvc.perform(get("/average-prices"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].avg_price").value(100.0))
                .andExpect(jsonPath("$[1].avg_price").value(150.0));
    }
}
