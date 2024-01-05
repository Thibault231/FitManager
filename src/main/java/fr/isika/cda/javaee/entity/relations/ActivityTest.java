package fr.isika.cda.javaee.entity.relations;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class ActivityTest {

	@Id
	@GeneratedValue
	private Long activityId;

}
