package com.mohammadreza_mirali.n26.service.transactionStatistics;

import com.mohammadreza_mirali.n26.service.transactionStatistics.dto.TransactionDto;
import org.junit.Test;

import java.time.Instant;

import static org.junit.Assert.*;

public class TransactionStatisticsManagerTest {
    TransactionStatisticsManager transactionStatisticsManager = new TransactionStatisticsManager();
    @Test
    public void transactions() throws Exception {
        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setAmount((double) 1000);
        transactionDto.setTimeStamp(Instant.now().toEpochMilli());
        transactionStatisticsManager.transactions(transactionDto);
        assertEquals(transactionStatisticsManager.transactions(transactionDto),"201");
        transactionDto.setTimeStamp(Instant.now().toEpochMilli()-60001);
        assertEquals(transactionStatisticsManager.transactions(transactionDto),"204");
//        transactionStatisticsManager.statistics();
    }

    @Test
    public void statistics() throws Exception {
        transactionStatisticsManager.statistics();
    }

}