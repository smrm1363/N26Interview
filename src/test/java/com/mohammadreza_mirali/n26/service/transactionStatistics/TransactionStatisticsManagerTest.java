package com.mohammadreza_mirali.n26.service.transactionStatistics;

import com.mohammadreza_mirali.n26.service.transactionStatistics.dto.StatisticDto;
import com.mohammadreza_mirali.n26.service.transactionStatistics.dto.TransactionDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
public class TransactionStatisticsManagerTest {
    TransactionStatisticsManager transactionStatisticsManager = new TransactionStatisticsManager();


    List<TransactionDto> transactionDtoListMock;


    @Test
    public void transactions() throws Exception {
        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setAmount((double) 1000);
        transactionDto.setTimestamp(Instant.now().toEpochMilli());
        transactionStatisticsManager.transactions(transactionDto);
        assertEquals(transactionStatisticsManager.transactions(transactionDto),"201");
        transactionDto.setTimestamp(Instant.now().toEpochMilli()-60001);
        assertEquals(transactionStatisticsManager.transactions(transactionDto),"204");

    }

    @Test
    public void statistics() throws Exception {
        transactionDtoListMock= new ArrayList<>();
        TransactionDto transactionDto1 = new TransactionDto();
        transactionDto1.setTimestamp(Instant.now().toEpochMilli() - 60001);
        transactionDto1.setAmount((double) 1000);
        TransactionDto transactionDto2 = new TransactionDto();
        transactionDto2.setTimestamp(Instant.now().toEpochMilli() - 6);
        transactionDto2.setAmount((double) 1200);
        TransactionDto transactionDto3 = new TransactionDto();
        transactionDto3.setTimestamp(Instant.now().toEpochMilli() - 20);
        transactionDto3.setAmount((double) 1100);
        transactionDtoListMock.add(transactionDto1);
        transactionDtoListMock.add(transactionDto2);
        transactionDtoListMock.add(transactionDto3);
        transactionStatisticsManager.setTransactionDtoList(transactionDtoListMock);
        transactionStatisticsManager.gatherStatistics();
        StatisticDto statisticDto = transactionStatisticsManager.statistics();
        assertEquals(statisticDto.getAvg(),Double.valueOf(1150));
        assertEquals(statisticDto.getCount(),Long.valueOf(2));
        assertEquals(statisticDto.getMax(),Double.valueOf(1200));
        assertEquals(statisticDto.getMin(),Double.valueOf(1100));
        assertEquals(statisticDto.getMax(),Double.valueOf(1200));
        assertEquals(statisticDto.getSum(),Double.valueOf(2300));
    }

}