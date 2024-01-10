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
	public Course getCourseById(Long courseId) {
		return em.find(Course.class, courseId);
	}

	@Override
	public List<Course> getAllCourses(Long spaceId) {
		return em.createQuery("SELECT c FROM Course c WHERE c.linkedSpaceId = :spaceIdParam", Course.class)
				.setParameter("spaceIdParam", spaceId).getResultList();
	}

	@Override
	public List<Course> getAllCoachCourses(Long currentSpaceId, Long currentUserId) {
		return em.createQuery(
				"SELECT c FROM Course c LEFT JOIN FETCH c.coach u WHERE c.linkedSpaceId = :spaceIdParam AND u.userId = :coachIdParam",
				Course.class).setParameter("spaceIdParam", currentSpaceId).setParameter("coachIdParam", currentUserId)
				.getResultList();
	}

	@Override
	public void deleteCourses(Long courseToDeleteId) {
		em.remove(getCourseById(courseToDeleteId));
	}

	@Override
	public Long saveCourses(Course courseToSave) {
		em.persist(courseToSave);
		return courseToSave.getId();
	}

	@Override
	public void updateCourses(Course courseToUpdate) {
		em.merge(courseToUpdate);
	}

}
