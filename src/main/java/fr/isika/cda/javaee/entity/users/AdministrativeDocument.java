package fr.isika.cda.javaee.entity.users;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class AdministrativeDocument {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long AdministrativeDocumentId;
	private String link;
	@Enumerated(EnumType.STRING)
	private Type type;

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

//	}
	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	@Override
	public String toString() {
		return "AdministrativeDocument [AdministrativeDocumentId=" + AdministrativeDocumentId + ", type=" + type
				+ ", link=" + link + ", getType()=" + getType() + ", getAdministrativeDocumentId()="
				+ getAdministrativeDocumentId() + ", getLink()=" + getLink() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}

}
