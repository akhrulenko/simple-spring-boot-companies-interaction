package app.model;

import javax.persistence.Embeddable;

@Embeddable
public class Address {

	private String country;
	private String city;
	private String street;
	private String house;

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getHouse() {
		return house;
	}

	public void setHouse(String house) {
		this.house = house;
	}

	public String toString() {
		return String.format("%s, %s, %s st, №%s", country, city, street, house);
	}
}