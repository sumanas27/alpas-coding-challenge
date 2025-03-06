package com.alpas.coding_challenge.controller;


import static com.alpas.coding_challenge.util.Constants.PRICE_REQUEST;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.alpas.coding_challenge.service.PriceService;
import com.alpas.coding_challenge.util.Constants;


@WebMvcTest(PriceController.class)
public class PriceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private PriceService priceService;

    @Test
    public void testRecordPrice() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/prices")
                                .param( PRICE_REQUEST, "100.0")
                                .contentType( APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price").value(100.0));
    }

    @Test
    public void testGetPrices() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/")
                                .contentType( APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetAveragePriceLast30Seconds() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/average-prices")
                                .contentType( APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}