package com.bank.csm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

import com.bank.csm.config.JpaConfig;
import com.bank.csm.config.SwaggerConfig;
import com.bank.csm.config.WebConfig;
 

/**
 * This is application boot class to bootstrap the 
 * application.
 *
 * @author kumar-sand
 */
@EnableDiscoveryClient
@SpringBootApplication
@Import({WebConfig.class, JpaConfig.class, SwaggerConfig.class})
@ComponentScan("com.bank.csm")
public class ApplicationBoot {

	/**
	 * Application main method.
	 *
	 * @param args Takes commandline as argument
	 */
	public static void main(String[] args) {
		SpringApplication.run(ApplicationBoot.class, args);
	}
}
