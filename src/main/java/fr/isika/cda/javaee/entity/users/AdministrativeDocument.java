package fr.isika.cda.javaee.entity.users;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class AdministrativeDocument {
	@Id
	@GeneratedValue
	private Long AdministrativeDocumentId;
	//private Type type;
	private String link;
	
	 //Getters and setters
	
//	public Type getType() {
//		return type;
//	}
	public Long getAdministrativeDocumentId() {
		return AdministrativeDocumentId;
	}
	public void setAdministrativeDocumentId(Long administrativeDocumentId) {
		AdministrativeDocumentId = administrativeDocumentId;
	}
//	public void setType(Type type) {
//		this.type = type;
//	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
//	@Override
//	public String toString() {
//		return "AdministrativeDocument [AdministrativeDocumentId=" + AdministrativeDocumentId + ", type=" + type
//				+ ", link=" + link + ", getType()=" + getType() + ", getAdministrativeDocumentId()="
//				+ getAdministrativeDocumentId() + ", getLink()=" + getLink() + ", getClass()=" + getClass()
//				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
//	}
	

	
}
