/*
 * <p>
 *   Copyright (c) All rights reserved Ideas2IT
 * </p>
 */

package com.hellocabs;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * <p>
 *   A initializer class which extends from {@link SpringBootServletInitializer}
 *   to initialize the dispatcher servlet for the application
 * </p>
 *
 * @author : Pradeep
 * created on 20/10/2022
 * @version 1.0
 *
 */
public class ServletInitializer extends SpringBootServletInitializer {

	/**
	 * <p>
	 *   Used to configure main application with spring
	 *   inorder to initialize dispatcher servlet
	 * </p>
	 *
	 * @param springApplicationBuilder {@link SpringApplicationBuilder}
	 *      a object that used to fetch sources from application
	 *      and initialize dispatcher servlet
	 * @return {@link SpringApplicationBuilder} configuration for
	 *      the spring application
	 *
	 */
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder springApplicationBuilder) {
		return springApplicationBuilder.sources(HelloCabsApplication.class);
	}

}
