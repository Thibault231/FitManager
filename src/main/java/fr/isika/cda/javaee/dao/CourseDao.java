package fr.isika.cda.javaee.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.isika.cda.javaee.entity.plateform.Course;
import fr.isika.cda.javaee.presentation.viewmodel.CourseForm;

@Stateless
public class CourseDao implements IDaoCourse {

	@PersistenceContext
	private EntityManager em;

	@Override
	public Long createCourse(CourseForm courseForm) {
		Course course = new Course();
		course.setName(courseForm.getName());
		em.persist(course);
		return course.getId();
	}

	@Override
	public Course getCourseById(Long id) {
		return em.find(Course.class, id);
	}

	@Override
	public List<Course> getAllCourses(Long spaceId) {
		return em.createQuery("SELECT c FROM Course c WHERE c.linkedSpaceId = :spaceIdParam", Course.class)
				.setParameter("spaceIdParam", spaceId).getResultList();
	}

	@Override
	public List<Course> getAllCoachCourses(Long currentSpaceId, Long currentUserId) {
		return null;
	}

	@Override
	public void deleteCourses(Long id) {
		em.remove(getCourseById(id));
	}

	@Override
	public Long save(Course c) {
		em.persist(c);
		return c.getId();
	}

	@Override
	public void update(Course c) {
		em.merge(c);
	}

}
