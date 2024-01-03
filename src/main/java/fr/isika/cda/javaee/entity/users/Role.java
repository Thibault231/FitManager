package fr.isika.cda.javaee.entity.users;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public enum Role {

	SuperAdmin(1), Gestionnaire(2), Coach(3), Adherent(4), Visiteur(5);

	@Id
	private final int code;

	private Role(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}
	
}