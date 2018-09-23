/**
 * 
 */
package com.in28minutes.rest.webservices.restfulwebservices.versioning;

/**
 * @author TapojitBhattacharya
 *
 */
public class PersonV2 {

	Name name;

	/**
	 * 
	 */
	public PersonV2() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param name
	 */
	public PersonV2(Name name) {
		this.name = name;
	}

	/**
	 * @return the name
	 */
	public Name getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(Name name) {
		this.name = name;
	}

}
