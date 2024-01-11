package fr.isika.cda.javaee.presentation.controller.plateform;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import fr.isika.cda.javaee.dao.spaces.RoomDao;
import fr.isika.cda.javaee.entity.plateform.Room;
import fr.isika.cda.javaee.presentation.viewmodel.RoomForm;

/**
 * Manage Room objects in views.
 * 
 * @author Charef
 *
 */
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
