package fr.isika.cda.javaee.presentation.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import fr.isika.cda.javaee.dao.MachineDao;
import fr.isika.cda.javaee.entity.plateform.Machine;
import fr.isika.cda.javaee.presentation.viewmodel.MachineForm;

@Named
@ViewScoped
public class MachineController implements Serializable {

	private static final long serialVersionUID = -160397842934902381L;
	
	@Inject
	private MachineDao machineDao;
	private MachineForm machineForm = new MachineForm();
	
	public void createMachine() {
		machineDao.createMachine(machineForm);
		machineForm = new MachineForm();
		
	}
	
	public void deleteMachine(Long id) {
		machineDao.deleteMachine(id);
	}
	
	public List<Machine> getAllMachines(){
		return machineDao.getAllMachines();
	}
	
	public MachineForm getMachineForm() {
		return machineForm;
	}
	
	public void setMachienForm(MachineForm machineForm) {
		this.machineForm = machineForm;
	}
	
}
