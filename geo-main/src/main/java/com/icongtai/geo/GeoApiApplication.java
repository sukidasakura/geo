package com.icongtai.geo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
//@ComponentScan(basePackages = { "com.icongtai.geo" })
public class GeoApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(GeoApiApplication.class, args);
	}
}
