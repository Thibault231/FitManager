package fr.isika.cda.javaee.entity.users;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Type {
	@Id
	@GeneratedValue
	private Long typeId;
	
	private String diploma;
	private String id;
	private String medicalCertificate;
	private String bankDetails;
	
	//Getters and setters
	
	public String getDiploma() {
		return diploma;
	}
	public Long getTypeId() {
		return typeId;
	}
	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}
	public void setDiploma(String diploma) {
		this.diploma = diploma;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMedicalCertificate() {
		return medicalCertificate;
	}
	public void setMedicalCertificate(String medicalCertificate) {
		this.medicalCertificate = medicalCertificate;
	}
	public String getBankDetails() {
		return bankDetails;
	}
	public void setBankDetails(String bankDetails) {
		this.bankDetails = bankDetails;
	}
	@Override
	public String toString() {
		return "Type [typeId=" + typeId + ", diploma=" + diploma + ", id=" + id + ", medicalCertificate="
				+ medicalCertificate + ", bankDetails=" + bankDetails + ", getDiploma()=" + getDiploma()
				+ ", getTypeId()=" + getTypeId() + ", getId()=" + getId() + ", getMedicalCertificate()="
				+ getMedicalCertificate() + ", getBankDetails()=" + getBankDetails() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}
	
	
}
