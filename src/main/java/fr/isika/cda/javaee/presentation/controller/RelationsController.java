package fr.isika.cda.javaee.presentation.controller;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;

import fr.isika.cda.javaee.dao.relations.IBookingDao;
import fr.isika.cda.javaee.dao.relations.IPlanningDao;
import fr.isika.cda.javaee.dao.relations.IScheduldeDao;
import fr.isika.cda.javaee.presentation.viewmodel.RelationViewmModel;

@ManagedBean
@RequestScoped
public class RelationsController {
	@Inject
	private IBookingDao bookingDao;
	@Inject
	private IPlanningDao planningDao;
	@Inject
	private IScheduldeDao scheduldeDao;

	private RelationViewmModel relationViewModel;

	@PostConstruct
	public void init() {
		this.relationViewModel = new RelationViewmModel();
	}

	public String createBooking() {
		bookingDao.createBooking(relationViewModel.getBooking());
		return "TestRelation";
	}

	public String createPlanning() {
		planningDao.createPlanning(relationViewModel.getPlanning());
		return "TestRelation";
	}

	public String createSchedulde() {
		scheduldeDao.createSchedulde(relationViewModel.getSchedulde());
		return "TestRelation";
	}

}
