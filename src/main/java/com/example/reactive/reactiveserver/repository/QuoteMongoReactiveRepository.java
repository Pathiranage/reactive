package com.example.reactive.reactiveserver.repository;

import com.example.reactive.reactiveserver.domain.Quote;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;


/**
 * Copyright (c) 2018. scicom.com.my - All Rights Reserved
 * Created by kalana.w on 10/30/2019.
 */
public interface QuoteMongoReactiveRepository extends ReactiveCrudRepository<Quote, String>
{
	@Query("{ id: { $exists: true }}")
	Flux<Quote> retrieveAllQuotesPaged(final Pageable page);

}
