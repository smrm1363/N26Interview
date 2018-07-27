package com.mohammadreza_mirali.n26;

import com.mohammadreza_mirali.n26.service.transactionStatistics.TransactionStatisticsManager;
import com.mohammadreza_mirali.n26.service.transactionStatistics.TransactionStatisticsService;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.Instant;

@SpringBootApplication
public class N26Application extends ResourceConfig{

	public N26Application()
	{
		register(TransactionStatisticsService.class);
		System.out.println("time current "+ Instant.now().toEpochMilli());
	}

	public static void main(String[] args) {
		SpringApplication.run(N26Application.class, args);
	}
}
