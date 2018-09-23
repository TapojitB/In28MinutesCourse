/**
 * 
 */
package com.in28minutes.rest.webservices.restfulwebservices.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author TapojitBhattacharya
 *
 */
@RestController
public class PersonVersioningController {

	/******* API VERSIONING THROUGH URI *******/
	// In this versioning we are polluting the URI
	@GetMapping("/get-person-V1")
	public PersonV1 getPersonV1() {
		return new PersonV1("Tapojit Bhattacharya");

	}

	@GetMapping("/get-person-V2")
	public PersonV2 getPersonV2() {
		return new PersonV2(new Name("Tapojit", "Bhattacharya"));

	}

	/******* API VERSIONING THROUGH REQUEST PARAMETER *******/
	// In this versioning we are polluting the URI
	@GetMapping(path = "/person/param", params = "version=1")
	public PersonV1 getPersonParamV1() {
		return new PersonV1("Tapojit Bhattacharya");

	}

	@GetMapping(path = "/person/param", params = "version=2")
	public PersonV2 getPersonParamV2() {
		return new PersonV2(new Name("Tapojit", "Bhattacharya"));

	}

	/******* API VERSIONING THROUGH REQUEST HEADER PARAMETER *******/
	// In this versioning we are misusing the HTTP headers, they are never meant for
	// versioning. URI cannot be cached in this method of versioning. Cannot be
	// executed using normal browser.

	@GetMapping(path = "/person/header", headers = "X-API-VERSION=1")
	public PersonV1 getPersonHeaderV1() {
		return new PersonV1("Tapojit Bhattacharya");

	}

	@GetMapping(path = "/person/header", headers = "X-API-VERSION=2")
	public PersonV2 getPersonHeaderV2() {
		return new PersonV2(new Name("Tapojit", "Bhattacharya"));

	}

	/*******
	 * API VERSIONING THROUGH PRODUCES / ACCEPT HEADER VERSIONING / CONTENT
	 * NEGOTIATION / MEDIA TYPE VERSIONING
	 *******/
	// In this versioning we are misusing the HTTP headers, they are never meant for
	// versioning. URI cannot be cached in this method of versioning. Cannot be
	// executed using normal browser.

	@GetMapping(path = "/person/produces", produces = "application/vnd.company.app-v1+json")
	public PersonV1 getPersonProducesV1() {
		return new PersonV1("Tapojit Bhattacharya");

	}

	@GetMapping(path = "/person/produces", produces = "application/vnd.company.app-v2+json")
	public PersonV2 getPersonProducesV2() {
		return new PersonV2(new Name("Tapojit", "Bhattacharya"));

	}

}
