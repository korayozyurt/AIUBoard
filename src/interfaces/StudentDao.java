package interfaces;

import java.util.ArrayList;

import pojos.*;


public interface StudentDao {

	public ArrayList<Student> getAllStudents();

	public ArrayList<Course> getStudentCourse(int schoolID); 
	
	public ArrayList<StudentGrade> getStudentGradesByLecture(int schoolID,int lectureID); 
	
	public ArrayList<Student> getStudentsByLectureID(int lectureID);
	
	public Student getStudent(int schoolID);
	
	public boolean deleteStudentCourse(int schoolID, int lectureID);
	
	public boolean addStudentCourse(int schoolID, int lectureID);
	
	public ArrayList<StudentCourseGrade> getStudentCourseGrades(int schoolID);

	public ArrayList<StudentCourse> getStudentCourses(int schoolID);

}
