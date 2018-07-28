package com.mohammadreza_mirali.n26.service.transactionStatistics;

import com.mohammadreza_mirali.n26.service.transactionStatistics.dto.StatisticDto;
import com.mohammadreza_mirali.n26.service.transactionStatistics.dto.TransactionDto;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * this class is the Service layer of our application which call our logic from manager class
 */
@Named("TransactionStatisticsService")
@Path("/")
public class TransactionStatisticsService {
    @Inject
    TransactionStatisticsManager transactionStatisticsManager;

    /**
     *
     * @param transactionDto is input of each transaction, it is JSON input
     * @return the result from logic layer(the manager)
     */
    @POST
    @Path("/transactions")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String transactions(TransactionDto transactionDto)
    {
        return transactionStatisticsManager.transactions(transactionDto);
    }


    /**
     *
     * @return statistics from logic layer
     */
    @GET
    @Path("/statistics")
    @Produces(MediaType.APPLICATION_JSON)
    public StatisticDto statistics()
    {
        return transactionStatisticsManager.statistics();
    }


}
