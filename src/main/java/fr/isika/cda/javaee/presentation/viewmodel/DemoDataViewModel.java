package fr.isika.cda.javaee.presentation.viewmodel;

import java.time.LocalDateTime;
import java.util.UUID;

public class DemoDataViewModel {
	
	private UUID id;
	private String name;
	private String description;
	private LocalDateTime creationDate;
	
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public LocalDateTime getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(LocalDateTime creationDate) {
		this.creationDate = creationDate;
	}
	
}
