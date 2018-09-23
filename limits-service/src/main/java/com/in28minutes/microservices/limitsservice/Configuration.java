/**
 * 
 */
package com.in28minutes.microservices.limitsservice;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author TapojitBhattacharya
 *
 */
@Component
@ConfigurationProperties(prefix = "limits-service")
public class Configuration {
	private int minimum;
	private int maximum;

	/**
	 * 
	 */
	public Configuration() {

	}

	/**
	 * @param maximum
	 * @param minimum
	 */
	public Configuration(int maximum, int minimum) {
		this.maximum = maximum;
		this.minimum = minimum;
	}

	/**
	 * @return the maximum
	 */
	public int getMaximum() {
		return maximum;
	}

	/**
	 * @param maximum
	 *            the maximum to set
	 */
	public void setMaximum(int maximum) {
		this.maximum = maximum;
	}

	/**
	 * @return the minimum
	 */
	public int getMinimum() {
		return minimum;
	}

	/**
	 * @param minimum
	 *            the minimum to set
	 */
	public void setMinimum(int minimum) {
		this.minimum = minimum;
	}

}
