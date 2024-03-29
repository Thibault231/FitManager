package fr.isika.cda.javaee.dao.plateform;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.isika.cda.javaee.entity.plateform.Course;

@Stateless
public class CourseDao implements IDaoCourse {

	@PersistenceContext
	private EntityManager em;

	@Override
	public Course getCourseById(Long courseId) {
		return em.createQuery("SELECT c FROM Course c WHERE c.id = :courseIdParam", Course.class)
				.setParameter("courseIdParam", courseId).getSingleResult();
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
		em.createNativeQuery("DELETE FROM booking WHERE linkedCourse_id = :courseToDeleteId")
				.setParameter("courseToDeleteId", courseToDeleteId).executeUpdate();
		em.remove(getCourseById(courseToDeleteId));
	}

	@Override
	public Long saveCourses(Course courseToSave) {
		em.persist(courseToSave);
		return courseToSave.getId();
	}

	@Override
	public void updateCourse(Course courseToUpdate) {
		em.merge(courseToUpdate);
	}

}
