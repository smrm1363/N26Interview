package com.mohammadreza_mirali.n26.service.transactionStatistics.entity;


import java.io.Serializable;

public class StatisticEntity implements Serializable
{
    private Double sum = Double.valueOf(0);
    private Double avg = Double.valueOf(0);
    private TransactionEntity max;
    private TransactionEntity min;
    private Long count;


    public Double getSum() {
        return sum;
    }

    public void setSum(Double sum) {
        this.sum = sum;
    }

    public Double getAvg() {
        return avg;
    }

    public void setAvg(Double avg) {
        this.avg = avg;
    }

    public TransactionEntity getMax() {
        return max;
    }

    public void setMax(TransactionEntity max) {
        this.max = max;
    }

    public TransactionEntity getMin() {return min;}

    public void setMin(TransactionEntity min) {
        this.min = min;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}
