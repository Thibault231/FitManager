package fr.isika.cda.javaee.presentation.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;
import fr.isika.cda.javaee.dao.IDaoSpace;
import fr.isika.cda.javaee.entity.spaces.Space;
import fr.isika.cda.javaee.presentation.viewmodel.SpaceViewModel;

@ManagedBean
public class SpaceController {

	@Inject
	private IDaoSpace spaceDao;

	private SpaceViewModel spaceViewModel = new SpaceViewModel();;

	@PostConstruct
	public void init() {
		this.spaceViewModel = new SpaceViewModel();
	}

	public String createSpace() {
		spaceDao.createSpace(spaceViewModel.getSpace());
		return "AccueilSalle";
	}

	public SpaceViewModel getSpaceForm() {
		return spaceViewModel;
	}

	public void setSpaceForm(SpaceViewModel spaceForm) {
		this.spaceViewModel = spaceForm;
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
