/**
 * 
 */
package com.in28minutes.microservices.currencyexchangeservice;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.in28minutes.microservices.currencyexchangeservice.bean.ExchangeValue;

/**
 * @author TapojitBhattacharya
 *
 */
@RestController
public class CurrencyExchangeController {

	private Logger logger = LoggerFactory.getLogger(CurrencyExchangeController.class);

	@Autowired
	private Environment environment;
	@Autowired
	ExchangeValueRepository repository;

	@GetMapping(path = "/currency-exchange/from/{from}/to/{to}")
	public ExchangeValue retrieveExchangeValue(@PathVariable String from, @PathVariable String to) {
		// ExchangeValue exchangeValue = new ExchangeValue(10L, from, to,
		// BigDecimal.valueOf(85));
		ExchangeValue exchangeValue = repository.findByFromAndTo(from, to);
		String port = environment.getProperty("local.server.port");
		exchangeValue.setPort(Integer.parseInt(port));
		logger.info("CurrencyExchangeController.retrieveExchangeValue.exchangeValue---> {}" + exchangeValue);

		return exchangeValue;

	}

}
