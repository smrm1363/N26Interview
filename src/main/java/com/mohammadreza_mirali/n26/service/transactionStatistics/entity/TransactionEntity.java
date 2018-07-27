package com.mohammadreza_mirali.n26.service.transactionStatistics.entity;

import java.io.Serializable;
import java.util.UUID;

public class TransactionEntity implements Serializable
{
    private Double amount;
    private Long timeStamp;
    private UUID id;

    public TransactionEntity(UUID id,Double amount, Long timeStamp) {
        this.id = id;
        this.amount = amount;
        this.timeStamp = timeStamp;
    }

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

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
