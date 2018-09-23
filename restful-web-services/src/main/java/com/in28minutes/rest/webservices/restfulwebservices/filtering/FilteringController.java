/**
 * 
 */
package com.in28minutes.rest.webservices.restfulwebservices.filtering;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

/**
 * @author TapojitBhattacharya
 *
 */
@RestController
public class FilteringController {

	/**
	 * --------Examples of static filtering----------
	 */
	@GetMapping(path = "/filtering")
	public SomeBean retrieveSomeBean() {

		return new SomeBean("value1", "value2", "value3");

	}

	@GetMapping(path = "/filtering-list")
	public List<SomeBean> retrieveListOfSomeBean() {

		return Arrays.asList(new SomeBean("value11", "value12", "value13"),
				new SomeBean("value21", "value22", "value23"));

	}

	/**
	 * --------Examples of dynamic filtering----------
	 */

	// field1,field2
	@GetMapping("/filtering-dynamic")
	public MappingJacksonValue retrieveSomeBeanDynamicFilter() {
		SomeBeanDynamic someBeanDynamic = new SomeBeanDynamic("value1", "value2", "value3");

		// Create a filter that will filter out all properties except field1 & field2.
		// The output will contain field1 & field2 only
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1", "field2");

		// Register the filter in the filter provider with a name
		FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanDynamicFilter", filter);

		// Create a new instance wrapping the given POJO to be serialized.
		MappingJacksonValue mapping = new MappingJacksonValue(someBeanDynamic);

		// Set the Jackson filter provider to serialize the POJO with.
		mapping.setFilters(filters);

		return mapping;
	}

	// field2, field3
	@GetMapping("/filtering-list-dynamic")
	public MappingJacksonValue retrieveListOfSomeBeanDynamicFilter() {
		List<SomeBeanDynamic> list = Arrays.asList(new SomeBeanDynamic("value1", "value2", "value3"),
				new SomeBeanDynamic("value12", "value22", "value32"));

		// Create a filter that will filter out all properties except field2 & field3.
		// The output will contain field2 & field3 only
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field2", "field3");

		// Register the filter in the filter provider with a name
		FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanDynamicFilter", filter);

		// Create a new instance wrapping the given POJO list to be serialized.
		MappingJacksonValue mapping = new MappingJacksonValue(list);

		// Set the Jackson filter provider to serialize the POJO with.
		mapping.setFilters(filters);

		return mapping;
	}
}
