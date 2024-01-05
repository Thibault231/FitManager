package fr.isika.cda.javaee.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.isika.cda.javaee.entity.plateform.Machine;
import fr.isika.cda.javaee.presentation.viewmodel.MachineForm;

@Stateless
public class MachineDao {

	@PersistenceContext
	private EntityManager entityManager;

	public Long createMachine(MachineForm machineForm) {
		Machine machine = new Machine();
		machine.setName(machineForm.getName());
		entityManager.persist(machine);
		return machine.getId();
	}

	public Machine getMachineById(Long id) {
		return entityManager.find(Machine.class, id);
	}

	public List<Machine> getAllMachines() {

		return entityManager.createQuery("SELECT a FROM Machine a", Machine.class).getResultList();

	}

	public void deleteMachine(Long id) {

		entityManager.remove(entityManager.find(Machine.class, id));
	}

}
