package fr.isika.cda.javaee.entity.users;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public enum Type {

	Diploma(1), Id(2), MedicalCertificate(3), BankDetails(4);

	@Id
	private final int code;

	private Type(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}
}
