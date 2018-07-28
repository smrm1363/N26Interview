package com.mohammadreza_mirali.n26.service.transactionStatistics;

import com.mohammadreza_mirali.n26.service.transactionStatistics.dto.StatisticDto;
import com.mohammadreza_mirali.n26.service.transactionStatistics.dto.TransactionDto;
import javax.inject.Named;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

@Named("TransactionStatisticsManager")
public class TransactionStatisticsManager {

    private static final int validityTime= 60000;
    private List<TransactionDto> transactionDtoList = Collections.synchronizedList(new ArrayList<TransactionDto>());
    private StatisticDto statisticDto = new StatisticDto();
    private Boolean threadStarted = false;

    public synchronized String transactions(TransactionDto transactionDto)
    {

        if(transactionDto.getTimestamp() >= Instant.now().toEpochMilli() - validityTime)
        {
            transactionDtoList.add(transactionDto);

            if(threadStarted == false) {
                ExecutorService executor = Executors.newSingleThreadExecutor();
                threadStarted = true;
                executor.submit(() -> {gatherStatistics(transactionDtoList);

                });
            }
            return "201";

        }
        return "204";
    }


    public synchronized  StatisticDto statistics()
    {

        return statisticDto;
    }

    private void gatherStatistics(List<TransactionDto> transactionDtoList)
    {

        Comparator<TransactionDto> comparator = Comparator.comparing(TransactionDto::getAmount);
        while(true){
        if(transactionDtoList.size() > 0) {
            List<TransactionDto> transactionDtoFilteredList = transactionDtoList.parallelStream().
                    filter(p -> p.getTimestamp() > Instant.now().toEpochMilli() - validityTime).collect(Collectors.toList());
            if(transactionDtoFilteredList.size()==0)
                return ;
            statisticDto.setMax(transactionDtoFilteredList.stream().max(comparator).get().getAmount());
            statisticDto.setMin(transactionDtoFilteredList.stream().min(comparator).get().getAmount());
            statisticDto.setCount(transactionDtoFilteredList.stream().count());
            statisticDto.setAvg(transactionDtoFilteredList.stream().mapToDouble(o -> o.getAmount()).average().getAsDouble());
            statisticDto.setSum(transactionDtoFilteredList.stream().mapToDouble(o -> o.getAmount()).sum());
            transactionDtoList =  Collections.synchronizedList(new ArrayList<TransactionDto>(transactionDtoFilteredList));
            System.out.println("runn..."+transactionDtoList.size());
        }}

    }


    public List<TransactionDto> getTransactionDtoList() {
        return transactionDtoList;
    }

    public void setTransactionDtoList(List<TransactionDto> transactionDtoList) {
        this.transactionDtoList = transactionDtoList;
    }
}