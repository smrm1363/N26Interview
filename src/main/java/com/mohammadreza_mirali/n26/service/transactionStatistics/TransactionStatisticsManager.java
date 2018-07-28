package com.mohammadreza_mirali.n26.service.transactionStatistics;

import com.mohammadreza_mirali.n26.service.transactionStatistics.dto.StatisticDto;
import com.mohammadreza_mirali.n26.service.transactionStatistics.dto.TransactionDto;
import org.springframework.scheduling.annotation.Scheduled;

import javax.inject.Named;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

/**
 * This class is the logic of Transaction Statistics
 */
@Named("TransactionStatisticsManager")
public class TransactionStatisticsManager {

    private static final int validityTime= 60000;
    private List<TransactionDto> transactionDtoList = Collections.synchronizedList(new ArrayList<TransactionDto>());
    private StatisticDto statisticDto = new StatisticDto();
    private Boolean threadStarted = false;

    /**
     *
     * @param transactionDto is teh input data of each transaction
     * @return 201 if transaction is valid, otherwise return 204
     */
    public synchronized String transactions(TransactionDto transactionDto)
    {

        if(transactionDto.getTimestamp() >= Instant.now().toEpochMilli() - validityTime)
        {
            transactionDtoList.add(transactionDto);
            return "201";

        }
        return "204";
    }

    /**
     * this method return the statistics which has O(1) complexity
     * @return the statistics
     */
    public synchronized  StatisticDto statistics()
    {

        return statisticDto;
    }

    /**
     * this method run priodical and gather information of statistics and keep them update
     */
    @Scheduled(fixedRate = 100)
    public void gatherStatistics()
    {

        Comparator<TransactionDto> comparator = Comparator.comparing(TransactionDto::getAmount);

        if(transactionDtoList.size() > 0) {
            List<TransactionDto> transactionDtoFilteredList = transactionDtoList.parallelStream().
                    filter(p -> p.getTimestamp() > Instant.now().toEpochMilli() - validityTime).collect(Collectors.toList());
            if(transactionDtoFilteredList.size()==0){
                statisticDto = new StatisticDto();
                transactionDtoList =  Collections.synchronizedList(new ArrayList<TransactionDto>());
                return ;
            }

            statisticDto.setMax(transactionDtoFilteredList.stream().max(comparator).get().getAmount());
            statisticDto.setMin(transactionDtoFilteredList.stream().min(comparator).get().getAmount());
            statisticDto.setCount(transactionDtoFilteredList.stream().count());
            statisticDto.setAvg(transactionDtoFilteredList.stream().mapToDouble(o -> o.getAmount()).average().getAsDouble());
            statisticDto.setSum(transactionDtoFilteredList.stream().mapToDouble(o -> o.getAmount()).sum());
            transactionDtoList =  Collections.synchronizedList(new ArrayList<TransactionDto>(transactionDtoFilteredList));
            System.out.println("runn..."+transactionDtoList.size());
        }

    }


    public List<TransactionDto> getTransactionDtoList() {
        return transactionDtoList;
    }

    public void setTransactionDtoList(List<TransactionDto> transactionDtoList) {
        this.transactionDtoList = transactionDtoList;
    }
}