package com.mohammadreza_mirali.n26.service.transactionStatistics;

import com.mohammadreza_mirali.n26.service.transactionStatistics.dto.StatisticDto;
import com.mohammadreza_mirali.n26.service.transactionStatistics.dto.TransactionDto;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Named("TransactionStatisticsService")
@Path("/TransactionStatisticsManager")
public class TransactionStatisticsService {
    @Inject
    TransactionStatisticsManager transactionStatisticsManager;

    @POST
    @Path("/transactions")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String transactions(TransactionDto transactionDto)
    {
        return transactionStatisticsManager.transactions(transactionDto);
    }



    @GET
    @Path("/statistics")
    @Produces(MediaType.APPLICATION_JSON)
    public StatisticDto statistics()
    {
        return transactionStatisticsManager.statistics();
    }


}
