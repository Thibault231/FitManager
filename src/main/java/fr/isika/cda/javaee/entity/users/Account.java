package fr.isika.cda.javaee.entity.users;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Login and administrative datas of a user.
 * 
 * @author Alex Thibault
 *
 */
@Entity
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long accountId;

	@NotNull
	private String login;

	@NotNull
	@Size(min = 5, max = 12)
	private String password;

	@Enumerated(EnumType.STRING)
	private Role role;

	@OneToOne(cascade = CascadeType.ALL)
	private AdministrativeDocument administrativeDocument;

//*******************************************************************	
	public String getLogin() {
		return login;
	}

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public AdministrativeDocument getAdministrativeDocument() {
		return administrativeDocument;
	}

	public void setAdministrativeDocument(AdministrativeDocument administrativeDocument) {
		this.administrativeDocument = administrativeDocument;
	}

}
