package fr.isika.cda.javaee.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.isika.cda.javaee.entity.plateform.Course;
import fr.isika.cda.javaee.presentation.viewmodel.CourseForm;

@Stateless
public class CourseDao {

	@PersistenceContext
	private EntityManager em;

	public Long createCourse(CourseForm courseForm) {
		Course course = new Course();
		course.setName(courseForm.getName());
		em.persist(course);
		return course.getId();
	}

	public Course getCourseById(Long id) {
		return em.find(Course.class, id);
	}

	public List<Course> getAllCourses() {
		return em.createQuery("SELECT a FROM Course a", Course.class).getResultList();
	}

	public void deleteCourses(Long id) {
		em.remove(getCourseById(id));
	}

	public Long save(Course c) {
		em.persist(c);
		return c.getId();
	}

	public void update(Course c) {
		em.merge(c);
	}

}
