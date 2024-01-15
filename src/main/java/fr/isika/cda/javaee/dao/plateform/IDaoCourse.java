package fr.isika.cda.javaee.dao.plateform;

import java.util.List;

import fr.isika.cda.javaee.entity.plateform.Course;

/**
 * Manage the persistence of Course objects.
 * 
 * @author Thibault Charef
 *
 */
public interface IDaoCourse {
	/**
	 * Get a specific course from the database, using it's Id. Return the course if
	 * it exists, null otherwise.
	 * 
	 * @param courseId (:Long)
	 * @return the course to get (:Course)
	 */
	public Course getCourseById(Long courseId);

	/**
	 * Get all the courses of a specific space, registered in the DB, using the
	 * space Id.
	 * 
	 * @param spaceId(:Long)
	 * @return list of courses (:List<Course>)
	 */
	public List<Course> getAllCourses(Long spaceId);

	/**
	 * Get all the courses of a specific space, linked to a specific coach,
	 * registered in the DB, using the space Id and coach Id.
	 * 
	 * @param currentSpaceId (:Long)
	 * @param currentUserId  (:Long)
	 * @return list of courses (:List<Course>)
	 */
	public List<Course> getAllCoachCourses(Long currentSpaceId, Long currentUserId);

	/**
	 * Hard delete a course from the DB, using the course id.
	 * 
	 * @param courseToDeleteId (:Long)
	 */
	public void deleteCourses(Long courseToDeleteId);

	/**
	 * Create a new course in the database, using a course object.
	 * 
	 * @param courseToSave (:Course)
	 * @return saved course id (:Long)
	 */
	public Long saveCourses(Course courseToSave);

	/**
	 * Update a course in the DB, using the updated course object.
	 * 
	 * @param courseToUpdate (:Long)
	 */
	public void updateCourse(Course courseToUpdate);
}
