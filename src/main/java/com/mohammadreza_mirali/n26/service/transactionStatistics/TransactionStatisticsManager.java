package com.mohammadreza_mirali.n26.service.transactionStatistics;

import com.mohammadreza_mirali.n26.service.transactionStatistics.dto.StatisticDto;
import com.mohammadreza_mirali.n26.service.transactionStatistics.dto.TransactionDto;
import javax.inject.Named;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

@Named("TransactionStatisticsManager")
public class TransactionStatisticsManager {


    private List<TransactionDto> transactionDtoList = new ArrayList<>();

    public synchronized String transactions(TransactionDto transactionDto)
    {

        if(transactionDto.getTimeStamp() >= Instant.now().toEpochMilli() - 6000000)
        {
            transactionDtoList.add(transactionDto);
            return "201";

        }
        return "204";
    }


    public synchronized  StatisticDto statistics()
    {
        StatisticDto statisticDto = new StatisticDto();
        Comparator<TransactionDto> comparator = Comparator.comparing(TransactionDto::getAmount);
        if(transactionDtoList.size() > 0) {
            List<TransactionDto> transactionDtoFilteredList = transactionDtoList.parallelStream().
                    filter(p -> p.getTimeStamp() > Instant.now().toEpochMilli() - 6000000).collect(Collectors.toList());
            if(transactionDtoFilteredList.size()==0)
                return statisticDto;
            statisticDto.setMax(transactionDtoFilteredList.stream().max(comparator).get().getAmount());
            statisticDto.setMin(transactionDtoFilteredList.stream().min(comparator).get().getAmount());
            statisticDto.setCount(transactionDtoFilteredList.stream().count());
            statisticDto.setAvg(transactionDtoFilteredList.stream().mapToDouble(o -> o.getAmount()).average().getAsDouble());
            statisticDto.setSum(transactionDtoFilteredList.stream().mapToDouble(o -> o.getAmount()).sum());
            transactionDtoList = transactionDtoFilteredList;
        }
        return statisticDto;
    }



}