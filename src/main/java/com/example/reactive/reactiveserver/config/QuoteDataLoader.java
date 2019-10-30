package com.example.reactive.reactiveserver.config;

import com.example.reactive.reactiveserver.domain.Quote;
import com.example.reactive.reactiveserver.repository.QuoteMongoReactiveRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.function.LongSupplier;

/**
 * Copyright (c) 2018. scicom.com.my - All Rights Reserved
 * Created by kalana.w on 10/30/2019.
 */
@Component
public final class QuoteDataLoader implements CommandLineRunner
{
	private static final Logger log = LoggerFactory.getLogger( QuoteDataLoader.class );

	private QuoteMongoReactiveRepository quoteMongoReactiveRepository;

	QuoteDataLoader( final QuoteMongoReactiveRepository quoteMongoReactiveRepository )
	{
		this.quoteMongoReactiveRepository = quoteMongoReactiveRepository;
	}

	@Override
	public void run( final String... args ) throws Exception
	{
		if ( quoteMongoReactiveRepository.count().block() == 0L )
		{
			final LongSupplier longSupplier = new LongSupplier()
			{
				Long l = 0L;

				@Override
				public long getAsLong()
				{
					return l++;
				}
			};
			BufferedReader bufferedReader = new BufferedReader( new InputStreamReader( getClass().getClassLoader().getResourceAsStream( "pg2000.txt" ) ) );
			Flux.fromStream( bufferedReader.lines().filter( l -> !l.trim().isEmpty() ).map( l -> quoteMongoReactiveRepository.save( new Quote( String.valueOf( longSupplier.getAsLong() ), "El Quijote", l ) ) ) ).subscribe( m -> log.info( "New quote loaded: {}", m.block() ) );
			log.info( "Repository contains now {} entries.", quoteMongoReactiveRepository.count().block() );
		}
	}
}
