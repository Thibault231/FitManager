package fr.isika.cda.javaee.dao.spaces;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.isika.cda.javaee.entity.plateform.Room;
import fr.isika.cda.javaee.presentation.viewmodel.RoomForm;

/**
 * Manage the persistence of Room objects in MySQL DB.
 * 
 * @author Charef
 *
 */
@Stateless
public class RoomDao {
	@PersistenceContext
	private EntityManager entityManager;

	public Long createRoom(RoomForm roomForm) {
		Room room = new Room();
		room.setName(roomForm.getName());
		entityManager.persist(room);
		return room.getId();
	}

	public Room getroomById(Long id) {
		return entityManager.find(Room.class, id);
	}

	public List<Room> getAllRooms() {
		return entityManager.createQuery("SELECT a FROM Room a", Room.class).getResultList();
	}

	public void deleteRoom(Long id) {
		entityManager.remove(entityManager.find(Room.class, id));
	}

}
