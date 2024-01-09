package fr.isika.cda.javaee.presentation.controller;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import fr.isika.cda.javaee.dao.relations.BookingDao;
import fr.isika.cda.javaee.dao.relations.IBookingDao;
import fr.isika.cda.javaee.entity.relations.Booking;
import fr.isika.cda.javaee.presentation.viewmodel.BookingForm;

@Named
@ViewScoped
public class BookingController implements Serializable {

	private static final long serialVersionUID = -1603978429342381L;

	@Inject
	private IBookingDao bookingDao;

	private BookingForm bookingForm = new BookingForm();

	public void createBooking() {

		Booking bookingToCreate = new Booking();

		bookingDao.createBooking(bookingToCreate);
	}
}
