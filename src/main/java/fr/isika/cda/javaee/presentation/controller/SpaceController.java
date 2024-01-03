package fr.isika.cda.javaee.presentation.controller;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;
import fr.isika.cda.javaee.dao.SpaceDao;
import fr.isika.cda.javaee.entity.spaces.Space;
import fr.isika.cda.javaee.presentation.viewmodel.SpaceForm;

@ManagedBean
public class SpaceController {

	@Inject
	private SpaceDao spaceDao;
	private SpaceForm spaceForm = new SpaceForm();;

//	@PostConstruct
//	public void init() {
//		this.spaceForm = new SpaceForm();
//	}

	public void registerSpace() {

		// Remplir les entités du modèle à partir du form
		// save bdd

		Long idSubscription = spaceDao.createSpace(spaceForm);

		System.out.println("L'espace Fitness a été crée " + idSubscription);

		// reset le form
		spaceForm = new SpaceForm();
	}

	public SpaceForm getSpaceForm() {
		return spaceForm;
	}

	public void setSpaceForm(SpaceForm spaceForm) {
		this.spaceForm = spaceForm;
	}

//	public String createSpace() {
//		Space spaceToCreate = new Space();
//		spaceToCreate.setName(this.spaceForm.getName());
//		spaceToCreate.getAdministrative().setSiren(this.spaceForm.getSiren());
//		spaceDao.createSpace(spaceToCreate);
//		return "index";
//	}

	public String deleteSpace(Long spaceToDeleteIdSubscritption) {
		spaceDao.deleteSpace(spaceToDeleteIdSubscritption);
		return "index";
	}

	public List<Space> getAllActiveSpace() {
		List<Space> spaceList = spaceDao.getAllSpace();
		return spaceList;
	}

	public Space getSpace(Long spaceIdSubscritption) {
		return spaceDao.getSpaceByIdSubscription(spaceIdSubscritption);
	}

	public Space getSpace(String spaceName) {
		return spaceDao.getSpaceByName(spaceName);
	}

}
