package com.mohammadreza_mirali.n26.service.transactionStatistics.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

public class TransactionDto implements Serializable
{
    private Double amount;
    private Long timestamp;


    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

}
