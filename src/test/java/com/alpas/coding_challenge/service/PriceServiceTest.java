package com.alpas.coding_challenge.service;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.alpas.coding_challenge.bo.AveragePriceRecord;
import com.alpas.coding_challenge.bo.Price;
import com.alpas.coding_challenge.bo.PriceRecord;

class PriceServiceTest {

    private PriceService priceService;

    @BeforeEach
    void setUp() {
        priceService = new PriceService();
    }

    @Test
    void testRecord() {
        double priceValue = 150.0;
        Price result = priceService.record(priceValue);

        assertNotNull(result);
        assertEquals(priceValue, result.price());
    }

    @Test
    void testGetPrices() {
        priceService.record(100.0);
        priceService.record(200.0);

        List<PriceRecord> prices = priceService.getPrices();

        assertNotNull(prices);
        assertEquals(2, prices.size());
        assertTrue(prices.get(0).price() >= prices.get(1).price()); // Ensuring sorted order
    }

    @Test
    void testAveragePrices() {
        priceService.record(100.0);
        priceService.record(200.0);

        List<AveragePriceRecord> avgPrices = priceService.averagePrices();

        assertNotNull(avgPrices);
        assertFalse(avgPrices.isEmpty());
    }
}
