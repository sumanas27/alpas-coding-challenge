package com.alpas.coding_challenge.service;


import static com.alpas.coding_challenge.util.Constants.DATA_RETENTION_SECONDS;
import static java.time.Instant.now;
import static java.time.temporal.ChronoUnit.SECONDS;
import static java.util.stream.Collectors.averagingDouble;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

import java.time.Instant;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.springframework.stereotype.Service;

import com.alpas.coding_challenge.bo.AveragePriceRecord;
import com.alpas.coding_challenge.bo.Price;
import com.alpas.coding_challenge.bo.PriceRecord;

import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class PriceService {

    private final ConcurrentLinkedQueue<PriceRecord> priceRecordQueue = new ConcurrentLinkedQueue<>();

    public Price record( double price ) {
        PriceRecord record = new PriceRecord( price, now() );
        priceRecordQueue.add( record );

        log.info( "Price {} is recorded successfully", price );
        return new Price( record.price() );
    }

    public List<PriceRecord> getPrices() {
        return priceRecordQueue.stream()
                .sorted( ( a, b ) -> b.timestamp().compareTo( a.timestamp() ) )
                .collect( toList() );
    }

    public List<AveragePriceRecord> averagePrices() {
        log.info( "Starting calculation of average prices..." );

        Instant thirtySecondsAgo = now().minus( DATA_RETENTION_SECONDS, SECONDS );
        log.debug("Filtering records older than: {}", thirtySecondsAgo);

        // Remove old records
        int initialSize = priceRecordQueue.size();
        priceRecordQueue.removeIf( record -> record.timestamp().isBefore( thirtySecondsAgo ) );
        int remainingSize = priceRecordQueue.size();

        log.debug( "Removed {} old records. Remaining records: {}",
                   ( initialSize - remainingSize ), remainingSize );

        // Compute the average prices
        List<AveragePriceRecord> averages = priceRecordQueue.stream()
                .collect( groupingBy( record -> record.timestamp().truncatedTo( SECONDS ),
                                      averagingDouble( PriceRecord::price ) ) )
                .entrySet().stream()
                .map( entry -> new AveragePriceRecord( entry.getKey(), entry.getValue() ) )
                .sorted( ( a, b ) -> b.timestamp().compareTo( a.timestamp() ) )
                .toList();
        log.info( "Computed {} average price records.", averages.size() );
        return averages;
    }
}
