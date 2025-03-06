package com.alpas.coding_challenge.controller;


import static com.alpas.coding_challenge.util.Constants.PRICE_REQUEST;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alpas.coding_challenge.bo.AveragePriceRecord;
import com.alpas.coding_challenge.bo.Price;
import com.alpas.coding_challenge.bo.PriceRecord;
import com.alpas.coding_challenge.service.PriceService;

import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping
@RequiredArgsConstructor
public class PriceController {

    private final PriceService priceService;

    @PostMapping("/prices")
    public Price recordPrice(@RequestParam(PRICE_REQUEST) double price) {
        return priceService.record( price );
    }

    @GetMapping("/")
    public List<PriceRecord> getPrices() {
        return priceService.getPrices();
    }

    @GetMapping("/average-prices")
    public List<AveragePriceRecord> getAveragePrices() {
        return priceService.averagePrices();
    }
}
