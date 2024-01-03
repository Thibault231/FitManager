package fr.isika.cda.javaee.entity.spaces;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Infos {
	
	@Id
	private Long id;
	
	@OneToOne
	Administrative administrative; 
	
	@OneToOne
	Configuration configuration;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Administrative getAdministrative() {
		return administrative;
	}
	public void setAdministrative(Administrative administrative) {
		this.administrative = administrative;
	}
	public Configuration getConfiguration() {
		return configuration;
	}
	public void setConfiguration(Configuration configuration) {
		this.configuration = configuration;
	}
	
	

}
