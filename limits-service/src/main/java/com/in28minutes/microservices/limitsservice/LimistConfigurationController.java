/**
 * 
 */
package com.in28minutes.microservices.limitsservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.in28minutes.microservices.limitsservice.bean.LimitConfiguration;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

/**
 * @author TapojitBhattacharya
 *
 */
@RestController
public class LimistConfigurationController {

	@Autowired
	private Configuration configuration;

	@GetMapping("/limits")
	public LimitConfiguration retrieveLimitsFromConfigurations() {
		return new LimitConfiguration(configuration.getMaximum(), configuration.getMinimum());

	}

	@GetMapping("/limits-fault-tolerance")
	@HystrixCommand(fallbackMethod = "fallbackRetrieveConfigurations")
	public LimitConfiguration retrieveConfigurations() {
		throw new RuntimeException("Runtime Exception Ocured");

	}

	public LimitConfiguration fallbackRetrieveConfigurations() {
		return new LimitConfiguration(999, 9);

	}

}
