package fr.isika.cda.javaee.entity.users;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

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
	private String sex;

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

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@Override
	public String toString() {
		return "Civility [civilityId=" + civilityId + ", name=" + name + ", forename=" + forename + ", birthday="
				+ birthday + ", sex=" + sex + ", getName()=" + getName() + ", getCivilityId()=" + getCivilityId()
				+ ", getForename()=" + getForename() + ", getBirthday()=" + getBirthday() + ", getSex()=" + getSex()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}

}
