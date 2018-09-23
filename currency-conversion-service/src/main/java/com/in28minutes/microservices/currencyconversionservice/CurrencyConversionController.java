/**
 * 
 */
package com.in28minutes.microservices.currencyconversionservice;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.in28minutes.microservices.currencyconversionservice.bean.CurrencyConversionBean;

/**
 * @author TapojitBhattacharya
 *
 */
@RestController
public class CurrencyConversionController {
	private Logger logger = LoggerFactory.getLogger(CurrencyConversionController.class);
	@Autowired
	CurrencyExchangeServiceProxy feignProxy;

	@GetMapping(path = "/currency-converter/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversionBean convertCurrency(@PathVariable String from, @PathVariable String to,
			@PathVariable BigDecimal quantity) {

		CurrencyConversionBean conversionBean = invokeCurrencyExchangeService(from, to);

		CurrencyConversionBean currencyConversionBean = new CurrencyConversionBean(conversionBean.getId(),
				conversionBean.getFrom(), conversionBean.getTo(), conversionBean.getConversionMultiple(),
				conversionBean.getPort(), quantity, quantity.multiply(conversionBean.getConversionMultiple()));

		return currencyConversionBean;

	}

	private CurrencyConversionBean invokeCurrencyExchangeService(String from, String to) {
		RestTemplate restTemplate = new RestTemplate();
		String url = "http://localhost:8000/currency-exchange/from/{from}/to/{to}";
		Map<String, String> uriVariables = new HashMap<>();
		uriVariables.put("from", from);
		uriVariables.put("to", to);
		ResponseEntity<CurrencyConversionBean> responseEntity = restTemplate.getForEntity(url,
				CurrencyConversionBean.class, uriVariables);
		CurrencyConversionBean conversionBean = responseEntity.getBody();
		return conversionBean;
	}

	@GetMapping(path = "/currency-converter-feign/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversionBean convertCurrencyFeign(@PathVariable String from, @PathVariable String to,
			@PathVariable BigDecimal quantity) {

		CurrencyConversionBean conversionBean = feignProxy.retrieveExchangeValue(from, to);
		logger.info("CurrencyConversionController.convertCurrencyFeign.conversionBean---> {}" + conversionBean);

		CurrencyConversionBean currencyConversionBean = new CurrencyConversionBean(conversionBean.getId(),
				conversionBean.getFrom(), conversionBean.getTo(), conversionBean.getConversionMultiple(),
				conversionBean.getPort(), quantity, quantity.multiply(conversionBean.getConversionMultiple()));

		return currencyConversionBean;

	}

}
