package fr.isika.cda.javaee.presentation.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import fr.isika.cda.javaee.dao.ActivityDao;
import fr.isika.cda.javaee.dao.RoomDao;
import fr.isika.cda.javaee.entity.plateform.Activity;
import fr.isika.cda.javaee.entity.plateform.Room;
import fr.isika.cda.javaee.presentation.viewmodel.ActivityForm;
import fr.isika.cda.javaee.presentation.viewmodel.RoomForm;

@Named
@ViewScoped
public class RoomController implements Serializable {
	
	private static final long serialVersionUID = -160397842934902381L;
	
	@Inject
	private RoomDao roomDao;
	
	private RoomForm roomForm = new RoomForm();
	
	public void createRoom() {
		roomDao.createRoom(roomForm);
		// reset le formulaire
		roomForm = new RoomForm();
	}
	
	public void deleteRoom(Long id) {
		roomDao.deleteRoom(id);
	}
	
	public List<Room> getAllRooms() {
		return roomDao.getAllRooms();
	}

	public RoomForm getRoomForm() {
		return roomForm;
	}

	public void setRoomForm(RoomForm roomForm) {
		this.roomForm = roomForm;
	}

}
