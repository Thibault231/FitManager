package fr.isika.cda.javaee.entity.spaces;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 * Bean to catch user demand on access to a specific space
 * 
 * @author Thibault
 *
 */
@Named
@RequestScoped
public class GoToSpaceView {
	private Long no;

//************************************************
	public Long getNo() {
		return no;
	}

	public void setNo(Long no) {
		this.no = no;
	}

}
