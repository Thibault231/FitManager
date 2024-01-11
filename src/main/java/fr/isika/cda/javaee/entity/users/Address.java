package fr.isika.cda.javaee.entity.users;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Adress of any entity of the fitness space.
 * 
 * @author Alex
 *
 */
@Entity
public class Address {
	@Id
	@GeneratedValue
	private Long adressId;
	private String street;
	private int zipCode;
	private String city;

//****************************************
	public String getStreet() {
		return street;
	}

	public Long getAdressId() {
		return adressId;
	}

	public void setAdressId(Long adressId) {
		this.adressId = adressId;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public int getZipCode() {
		return zipCode;
	}

	public void setZipCode(int zipCode) {
		this.zipCode = zipCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

}
