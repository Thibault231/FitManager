package fr.isika.cda.javaee.presentation.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import fr.isika.cda.javaee.dao.CourseDao;
import fr.isika.cda.javaee.entity.plateform.Course;
import fr.isika.cda.javaee.presentation.viewmodel.CourseForm;

@Named
@ViewScoped
public class CourseController implements Serializable {
	
	private static final long serialVersionUID = -160397842934902381L;
	
	@Inject
	private CourseDao courseDao;
	private CourseForm courseForm = new CourseForm();
	
	public void createCourse() {
		courseDao.createCourse(courseForm);
		courseForm = new CourseForm();
		
	}
	
	public void deleteCourse(Long id) {
		courseDao.deleteCourses(id);
	}
	
	public List<Course> getAllCourses() {
		return courseDao.getAllCourses();
	}
	
	public CourseForm getCourseForm() {
		
		return courseForm;
	}
	
	public void setCourseForm(CourseForm courseForm) {
		
		this.courseForm = courseForm;
	}

}
