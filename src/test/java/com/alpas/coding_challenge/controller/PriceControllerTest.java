package com.alpas.coding_challenge.controller;


import static java.time.Instant.now;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.alpas.coding_challenge.bo.AveragePriceRecord;
import com.alpas.coding_challenge.bo.Price;
import com.alpas.coding_challenge.bo.PriceRecord;
import com.alpas.coding_challenge.service.PriceService;

@ExtendWith(MockitoExtension.class)
class PriceControllerTest {

    @Mock
    private PriceService priceService;

    @InjectMocks
    private PriceController priceController;

    @BeforeEach
    void setUp() {
        // Setup is handled by @ExtendWith(MockitoExtension.class)
    }

    @Test
    void testRecordPrice() {
        double priceValue = 100.5;
        Price mockPrice = new Price(priceValue);

        when(priceService.record(priceValue)).thenReturn(mockPrice);

        Price result = priceController.recordPrice(priceValue);

        assertNotNull(result);
        assertEquals(priceValue, result.price());
        verify(priceService, times(1)).record(priceValue);
    }

    @Test
    void testGetPrices() {
        List<PriceRecord> mockRecords = Arrays.asList(
                new PriceRecord( 100.0, now()),
                new PriceRecord(200.0, now())
        );

        when(priceService.getPrices()).thenReturn(mockRecords);

        List<PriceRecord> result = priceController.getPrices();

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(priceService, times(1)).getPrices();
    }

    @Test
    void testGetAveragePrices() {
        List<AveragePriceRecord> mockAvgRecords = Arrays.asList(
                new AveragePriceRecord( now(), 100.0),
                new AveragePriceRecord( now(), 150.0)
        );

        when(priceService.averagePrices()).thenReturn(mockAvgRecords);

        List<AveragePriceRecord> result = priceController.getAveragePrices();

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(priceService, times(1)).averagePrices();
    }
}