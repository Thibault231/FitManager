package fr.isika.cda.javaee.presentation.controller;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;

import fr.isika.cda.javaee.dao.relations.IBookingDao;
import fr.isika.cda.javaee.dao.relations.IPlanningDao;
import fr.isika.cda.javaee.dao.relations.IScheduldeDao;
import fr.isika.cda.javaee.presentation.viewmodel.RelationViewModel;

@ManagedBean
@RequestScoped
public class RelationsController {
	@Inject
	private IBookingDao bookingDao;
	@Inject
	private IPlanningDao planningDao;

	private RelationViewModel relationViewModel;

	@PostConstruct
	public void init() {
		this.relationViewModel = new RelationViewModel();
	}

	public String createBooking() {
		bookingDao.createBooking(relationViewModel.getBooking());
		return "TestRelation";
	}

	public String getPlanning() {
		for (int i = 0; i < 6; i++) {
			planningDao.createPlanning(relationViewModel.getPlanning());
		}
		planningDao.getPlanningById(relationViewModel.getSpaceId());
		return "TestRelation";
	}
}
