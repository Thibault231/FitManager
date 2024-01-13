package fr.isika.cda.javaee.entity.users;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 * Personal datas of a user.
 * 
 * @author Alex Thibault
 *
 */
@Entity
public class Civility {
	@Id
	@GeneratedValue
	private Long civilityId;
	@NotNull(message = "Le nom est requis")
	@Column(length = 50)
	private String name;
	@NotNull(message = "Le prénom est requis")
	@Column(length = 50)
	private String forename;
	@Temporal(TemporalType.TIMESTAMP)
	private Date birthday;
	@Enumerated(EnumType.STRING)
	private Sex sex;
	private String ProfilePicture;

	// Getters and setters

	public String getName() {
		return name;
	}

	public Long getCivilityId() {
		return civilityId;
	}

	public void setCivilityId(Long civilityId) {
		this.civilityId = civilityId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getForename() {
		return forename;
	}

	public void setForename(String forename) {
		this.forename = forename;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Sex getSex() {
		return sex;
	}

	public void setSex(Sex sex) {
		this.sex = sex;

	}

	public String getProfilePicture() {
		return ProfilePicture;
	}

	public void setProfilePicture(String profilePicture) {
		ProfilePicture = profilePicture;
	}

	@Override
	public String toString() {
		return "Civility [civilityId=" + civilityId + ", name=" + name + ", forename=" + forename + ", birthday="
				+ birthday + ", sex=" + sex + ", ProfilePicture=" + ProfilePicture + ", getName()=" + getName()
				+ ", getCivilityId()=" + getCivilityId() + ", getForename()=" + getForename() + ", getBirthday()="
				+ getBirthday() + ", getSex()=" + getSex() + ", getProfilePicture()=" + getProfilePicture()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}

}
