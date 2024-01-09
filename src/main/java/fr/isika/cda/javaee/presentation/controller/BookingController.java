package fr.isika.cda.javaee.presentation.controller;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import fr.isika.cda.javaee.dao.IDaoUser;
import fr.isika.cda.javaee.dao.relations.BookingDao;
import fr.isika.cda.javaee.dao.relations.IBookingDao;
import fr.isika.cda.javaee.entity.relations.Booking;
import fr.isika.cda.javaee.entity.users.User;
import fr.isika.cda.javaee.presentation.util.SessionUtils;
import fr.isika.cda.javaee.presentation.viewmodel.BookingForm;

@Named
@ViewScoped
public class BookingController implements Serializable {

	private static final long serialVersionUID = -160397842934902381L;
	
	@Inject
	private IBookingDao iBookingDao;
	
	@Inject
	private IDaoUser userDao;
	
	@Inject
	private CourseController courseController;
	
	private BookingForm bookingForm = new BookingForm();

	public void createBooking() {

		Booking bookingToCreate = new Booking();

		iBookingDao.createBooking(bookingToCreate);
	}
	
	public void reserveCourse() {
		User currentUser = userDao.getUserById(SessionUtils.getUserIdFromSession());
		
		String courseName = courseController.getEvent().getTitle();
		String courseDescritpion = courseController.getEvent().getTitle();
		
		courseController.resetEvent();
	}
}
