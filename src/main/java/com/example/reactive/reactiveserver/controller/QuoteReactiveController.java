package com.example.reactive.reactiveserver.controller;

import com.example.reactive.reactiveserver.domain.Quote;
import com.example.reactive.reactiveserver.repository.QuoteMongoReactiveRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;

/**
 * Copyright (c) 2018. scicom.com.my - All Rights Reserved
 * Created by kalana.w on 10/30/2019.
 */
@RestController
public class QuoteReactiveController
{
	private static final int DELAY_PER_ITEM_MS = 100;

	private QuoteMongoReactiveRepository quoteMongoReactiveRepository;

	public QuoteReactiveController( final QuoteMongoReactiveRepository quoteMongoReactiveRepository )
	{
		this.quoteMongoReactiveRepository = quoteMongoReactiveRepository;
	}

	@GetMapping("/quotes-reactive")
	public Flux<Quote> getQuoteFlux()
	{
		// If you want to use a shorter version of the Flux, use take(100) at the end of the statement below
		return this.quoteMongoReactiveRepository.findAll().delayElements( Duration.ofMillis( DELAY_PER_ITEM_MS ) );
	}

	@GetMapping("/quotes-reactive-paged")
	public Flux<Quote> getQuoteFlux( final @RequestParam(name = "page") int page, final @RequestParam(name = "size") int size )
	{
		return quoteMongoReactiveRepository.retrieveAllQuotesPaged( PageRequest.of( page, size ) ).delayElements( Duration.ofMillis( DELAY_PER_ITEM_MS ) );
	}
}
