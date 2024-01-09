package fr.isika.cda.javaee.dao;

import java.util.List;

import fr.isika.cda.javaee.entity.plateform.Course;
import fr.isika.cda.javaee.presentation.viewmodel.CourseForm;

public interface IDaoCourse {
	/**
	 * 
	 * @param courseForm
	 * @return
	 */
	public Long createCourse(CourseForm courseForm);

	/**
	 * 
	 * @param courseId (:Long)
	 * @return the course to get (:Course)
	 */
	public Course getCourseById(Long courseId);

	/**
	 * 
	 * @param spaceId(:Long)
	 * @return list of courses (:List<Course>)
	 */
	public List<Course> getAllCourses(Long spaceId);

	/**
	 * 
	 * @param currentSpaceId (:Long)
	 * @param currentUserId  (:Long)
	 * @return list of courses (:List<Course>)
	 */
	public List<Course> getAllCoachCourses(Long currentSpaceId, Long currentUserId);

	/**
	 * 
	 * @param courseToDeleteId (:Long)
	 */
	public void deleteCourses(Long courseToDeleteId);

	/**
	 * 
	 * @param courseToSave (:Course)
	 * @return saved course id (:Long)
	 */
	public Long save(Course courseToSave);

	/**
	 * 
	 * @param courseToUpdate (:Long)
	 */
	public void update(Course courseToUpdate);
}
