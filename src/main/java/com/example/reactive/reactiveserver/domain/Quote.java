package com.example.reactive.reactiveserver.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright (c) 2018. scicom.com.my - All Rights Reserved
 * Created by kalana.w on 10/30/2019.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Quote
{
	private String id;
	private String book;
	private String content;
}
