package interfaces;

import java.util.ArrayList;

import pojos.Course;
import pojos.LectureDetail;
import pojos.LectureDashboard;
import pojos.SimpleCourse;
import pojos.SimpleGrade;
import pojos.StudentGrade;
import pojos.StudentGradeView;

public interface CourseDao {
	
	public ArrayList<Course> getAllCourses();
	
	public ArrayList<Course> getCoursesByLecturerID(int schoolID);
	
	public Course getCourseByCourseID(int courseID);
	
	public Course getCourseByLectureID(int lectureID);
	
	public ArrayList<Course> getAllVisibleCourses();
	
	public ArrayList<Course> getAllInVisibleCourses();
	
	public ArrayList<Course> getCourseByStudentID(int schoolID);
	
	public ArrayList<Course> getCourseByDepartmentID(int departmentID);
	
	public ArrayList<Course> getCoursesByCode(String courseCode);

	public ArrayList<SimpleCourse> getCoursesByCodeUsingLike(String courseCode);
	
	public boolean addNewCourse(String code, String lecture, String lecturerEmail);
	
	public LectureDashboard getLectureDashboard(int lectureID);

	public ArrayList<SimpleGrade> getLectureGrades(int lectureID);
	
	public ArrayList<StudentGradeView> getStudentListofGradeByGradeID(int gradeID);
	
	public LectureDetail getLectureDetail(int lectureID);
	
	public ArrayList<Course> getAllCoursesNotTakenByStudent(int schoolID);
}
