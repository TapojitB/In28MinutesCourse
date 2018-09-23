/**
 * 
 */
package com.in28minutes.rest.webservices.restfulwebservices.versioning;

/**
 * @author TapojitBhattacharya
 *
 */
public class PersonV1 {

	private String name;

	/**
	 * 
	 */
	public PersonV1() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param name
	 */
	public PersonV1(String name) {
		this.name = name;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

}
