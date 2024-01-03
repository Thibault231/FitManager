package fr.isika.cda.javaee.entity.users;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Adress {
	@Id
	@GeneratedValue
	private Long adressId;
	private String street;
	private int zipCode;
	private String city;
	
	//Getters and setters
	
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
	@Override
	public String toString() {
		return "Adress [adressId=" + adressId + ", street=" + street + ", zipCode=" + zipCode + ", city=" + city
				+ ", getStreet()=" + getStreet() + ", getAdressId()=" + getAdressId() + ", getZipCode()=" + getZipCode()
				+ ", getCity()=" + getCity() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}

	
	
}
