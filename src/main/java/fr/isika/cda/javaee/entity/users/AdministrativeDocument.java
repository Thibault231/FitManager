package fr.isika.cda.javaee.entity.users;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class AdministrativeDocument {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long AdministrativeDocumentId;
	@Column(unique = true, nullable = false)
	private int registration;
	@Enumerated(EnumType.STRING)
	private Type type;

	private String filePath;

	// Getters and setters

	public Type getType() {
		return type;
	}

	public Long getAdministrativeDocumentId() {
		return AdministrativeDocumentId;
	}

	public void setAdministrativeDocumentId(Long administrativeDocumentId) {
		AdministrativeDocumentId = administrativeDocumentId;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public int getRegistration() {
		return registration;
	}

	public void setRegistration(int registration) {
		this.registration = registration;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	@Override
	public String toString() {
		return "AdministrativeDocument [AdministrativeDocumentId=" + AdministrativeDocumentId + ", link="
				+ ", registration=" + registration + ", type=" + type + ", getType()=" + getType()
				+ ", getAdministrativeDocumentId()=" + getAdministrativeDocumentId() + ", getLink()="
				+ ", getRegistration()=" + getRegistration() + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + ", toString()=" + super.toString() + "]";
	}

}
