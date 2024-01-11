package fr.isika.cda.javaee.presentation.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import fr.isika.cda.javaee.dao.relations.IBookingDao;
import fr.isika.cda.javaee.dao.relations.IPlanningDao;
import fr.isika.cda.javaee.entity.plateform.Activity;
import fr.isika.cda.javaee.entity.relations.Planning;
import fr.isika.cda.javaee.presentation.viewmodel.RelationViewModel;

@Named
@ViewScoped
public class RelationsController implements Serializable {
	private static final long serialVersionUID = 8496614779097793938L;

	@Inject
	private IBookingDao bookingDao;
	@Inject
	private IPlanningDao planningDao;

	private RelationViewModel relationViewModel;

//************************************************
	@PostConstruct
	public void init() {
		this.relationViewModel = new RelationViewModel();
	}

	public RelationViewModel getRelationViewModel() {
		return relationViewModel;
	}

	public void setRelationViewModel(RelationViewModel relationViewModel) {
		this.relationViewModel = relationViewModel;
	}

//************************************************
	public String createBooking() {
		bookingDao.createBooking(relationViewModel.getBooking());
		return "TestRelation";
	}

	public String printPlanning() {
		Planning planing = new Planning();
		planing.getActivitiesList().add(new Activity());
		planing.getActivitiesList().add(new Activity());
		planningDao.createPlanning(planing);
		relationViewModel.setPlanning(planningDao.getPlanningById(relationViewModel.getSpaceId()));
		return "TestRelation";
	}
}
