package com.mohammadreza_mirali.n26.service.transactionStatistics.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

public class TransactionDto implements Serializable
{
    private Double amount;
    private Long timeStamp;

//    public TransactionDto(Double amount, Long timeStamp) {
//        this.amount = amount;
//        this.timeStamp = timeStamp;
//    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Long timeStamp) {
        this.timeStamp = timeStamp;
    }

}
