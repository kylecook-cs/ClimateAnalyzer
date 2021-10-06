package climateChange;

import java.io.*;

public class Temperature implements ITemperature, Comparable<Temperature> {

	double temperature;
	int year;
	String month, country, code;

	public Temperature(double temperature, int year, String month, String country, String code) throws IOException {
		this.temperature = temperature;
		this.year = year;
		this.month = month;
		this.country = country;
		this.code = code;
	}

	// This method satisfies the Comparable interface
	public boolean equals(Temperature that) {

		if (this.temperature != that.temperature)
			return false;
		if (this.year != that.year)
			return false;
		if (this.month != that.month)
			return false;
		if (this.country != that.country)
			return false;
		if (this.code != that.code)
			return false;
		return true;
	}

	public int compareTo(Temperature that) {

		if (this.temperature != that.temperature) // compares temperatures
			return (int) Math.signum(this.temperature - that.temperature);
		else if (this.year != that.year) // compares years
			return (int) Math.signum(this.year - that.year);
		else if (this.month.equals(that.month)) // compares months
		{
			return 1;
		} else if (this.country.equals(that.country)) // compares country names
		{
			return 1;
		} else if (this.code.equals(that.code)) // compares country code
		{
			return 1;
		} else // all equal
			return 0;
	}

	public String getCountry() {
		// returns name of country
		return country;
	}

	public String getCountry3LetterCode() {
		// returns name of country code
		return code;
	}

	public String getMonth() {
		// returns name of month
		return month;
	}

	public int getYear() {
		// returns the year of specific data
		return year;
	}

	public double getTemperature(boolean getFahrenheit) {
		// returns temperature in either celsius or fahrenheit
		double celsius = temperature;
		double fahrenheit = 0;
		if (getFahrenheit) {
			fahrenheit = (9.0 / 5.0) * celsius + 32;
			return fahrenheit;
		} else
			return celsius;
	}

	public int hashCode() {
		// returns an unique hashcode
		return this.month.hashCode() + this.country.hashCode() + this.code.hashCode() + Integer.hashCode(year)
				+ Double.hashCode(temperature);

	}
	
}
