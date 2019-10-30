package com.example.reactive.reactiveserver.repository;

import com.example.reactive.reactiveserver.domain.Quote;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Copyright (c) 2018. scicom.com.my - All Rights Reserved
 * Created by kalana.w on 10/30/2019.
 */
@Repository
public interface QuoteMongoBlockingRepository extends CrudRepository<Quote, String>
{
	@Query("{ id: { $exists: true }}")
	List<Quote> retrieveAllQuotesPaged(final Pageable page);
}
