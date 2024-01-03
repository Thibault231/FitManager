package fr.isika.cda.javaee.presentation.viewmodel;

public class SpaceForm {

	private String name;
	private String password;
	private String siret;
	private String siren;
	private String logo;

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSiret() {
		return siret;
	}

	public void setSiret(String siret) {
		this.siret = siret;
	}

	public String getSiren() {
		return siren;
	}

	public void setSiren(String siren) {
		this.siren = siren;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SpaceForm [name=");
		builder.append(name);
		builder.append(", password=");
		builder.append(password);
		builder.append(", siret=");
		builder.append(siret);
		builder.append(", siren=");
		builder.append(siren);
		builder.append(", logo=");
		builder.append(logo);
		builder.append("]");
		return builder.toString();
	}

}
