package fr.isika.cda.javaee.presentation.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import fr.isika.cda.javaee.dao.IDaoSpace;
import fr.isika.cda.javaee.entity.spaces.Space;
import fr.isika.cda.javaee.presentation.viewmodel.SpaceViewModel;

@Named
@ViewScoped
public class SpaceController implements Serializable {

	private static final long serialVersionUID = 8496614779097793938L;

	@Inject
	private IDaoSpace spaceDao;

	private SpaceViewModel spaceViewModel = new SpaceViewModel();

	@PostConstruct
	public void init() {
		this.spaceViewModel = new SpaceViewModel();
	}

	public String createSpace() {
		spaceDao.createSpace(spaceViewModel.getSpace());
		return "ManagerDashBoard";
	}

	public SpaceViewModel getSpaceForm() {
		return spaceViewModel;
	}

	public void setSpaceForm(SpaceViewModel spaceForm) {
		this.spaceViewModel = spaceForm;
	}

	public String deleteSpace(Long spaceToDeleteId) {
		spaceDao.deleteSpace(spaceToDeleteId);

		return "index";
	}

	public List<Space> getAllActiveSpace() {
		List<Space> spaceList = spaceDao.getAllSpace();
		return spaceList;
	}

	public Space getSpace(Long spaceId) {
		return spaceDao.getSpaceById(spaceId);
	}

	public Space getSpace(String spaceName) {
		return spaceDao.getSpaceByName(spaceName);
	}

}
