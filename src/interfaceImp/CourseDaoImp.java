package interfaceImp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.omg.CORBA.StringHolder;

import database.Database;
import interfaces.CourseDao;
import pojos.Anouncment;
import pojos.Course;
import pojos.LectureDetail;
import pojos.LectureDashboard;
import pojos.SimpleCourse;
import pojos.SimpleGrade;
import pojos.StudentGrade;
import pojos.StudentGradeView;

public class CourseDaoImp extends Database implements CourseDao {
	
	
	private Course getCourse(String query) {
		Course course = null;
		
		Connection connection = null;
		
		
		try {
			connection = super.getConnection();
			PreparedStatement sqlStatement = connection.prepareStatement(query);
			ResultSet resultSet = sqlStatement.executeQuery();
			
			if(resultSet.next()) {
				int courseID = resultSet.getInt("CourseID");
				int departmentID = resultSet.getInt("DepartmentID");
				String code = resultSet.getString("Code");
				String name = resultSet.getString("Name");
				int semesterID = resultSet.getInt("SemesterID");
				boolean visible = resultSet.getBoolean("Visible");
				int lecturerID = resultSet.getInt("SchoolID");
				String lecturerName = resultSet.getString("LecturerName");
				String lecturerSurname = resultSet.getString("LecturerSurname");
				int lectureID = resultSet.getInt("LectureID");
				String lectureName = resultSet.getString("LectureName");
				
				course = new Course(courseID, departmentID, code, name, semesterID, visible, lecturerID, lecturerName, lecturerSurname, lectureID, lectureName);
				
			}
			
			
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
		
		
		return course;
		
	}
	
	private ArrayList<Course> getCourses(String query){
		ArrayList<Course> courses = new ArrayList<>();

		
		Course course = null;
		
		Connection connection = null;
		
		
		try {
			connection = super.getConnection();
			PreparedStatement sqlStatement = connection.prepareStatement(query);
			ResultSet resultSet = sqlStatement.executeQuery();
			
			while(resultSet.next()) {
				int courseID = resultSet.getInt("CourseID");
				int departmentID = resultSet.getInt("DepartmentID");
				String code = resultSet.getString("Code");
				String name = resultSet.getString("Name");
				int semesterID = resultSet.getInt("SemesterID");
				boolean visible = resultSet.getBoolean("Visible");
				int lecturerID = resultSet.getInt("SchoolID");
				String lecturerName = resultSet.getString("LecturerName");
				String lecturerSurname = resultSet.getString("LecturerSurname");
				int lectureID = resultSet.getInt("LectureID");
				String lectureName = resultSet.getString("LectureName");
				
				course = new Course(courseID, departmentID, code, name, semesterID, visible, lecturerID, lecturerName, lecturerSurname, lectureID, lectureName);
				courses.add(course);
				
			}
			
			
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
		
		
		return courses;
		
	}
	
	
	private ArrayList<SimpleCourse> getSimpleCourses(String query){
		ArrayList<SimpleCourse> courses = new ArrayList<>();

		
		SimpleCourse course = null;
		
		Connection connection = null;
		
		
		try {
			connection = super.getConnection();
			PreparedStatement sqlStatement = connection.prepareStatement(query);
			ResultSet resultSet = sqlStatement.executeQuery();
			
			while(resultSet.next()) {
				int courseID = resultSet.getInt("CourseID");
				String code = resultSet.getString("Code");
				String name = resultSet.getString("Name");

				
				course = new SimpleCourse(courseID, code, name);
				courses.add(course);
				
			}
			
			
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
		
		
		return courses;
		
	}
	

	@Override
	public ArrayList<Course> getAllCourses() {
		String query = "SELECT c.*, u.schoolID, u.Name AS LecturerName, u.Surname AS LecturerSurname, l.LectureID, l.Name AS LectureName "
				 + "FROM Course c, User u, CourseOfLecturer cl, Lecture l "
				 + "WHERE cl.LectureID = l.LectureID "
				 + "AND l.CourseID = c.CourseID "
				 + "AND cl.SchoolID = u.SchoolID;";	
		
		return this.getCourses(query);
	}

	@Override
	public ArrayList<Course> getCoursesByLecturerID(int schoolID) {
		
		String query = "SELECT c.*, u.schoolID, u.Name AS LecturerName, u.Surname AS LecturerSurname, l.LectureID, l.Name AS LectureName "
				+ "FROM Course c, User u, CourseOfLecturer cl, Lecture l " 
				+ "WHERE c.CourseID =  l.CourseID "  
				+ "AND l.LectureID = cl.LectureID " 
				+ "AND u.SchoolID = cl.SchoolID "  
				+ "AND u.Role = 3 " 
				+ "AND cl.SchoolID = " + schoolID +  " "  
				+ "AND cl.Visible = true;";
		
		return this.getCourses(query);
	}

	@Override
	public Course getCourseByCourseID(int courseID) {
		String query = "SELECT c.*, u.schoolID, u.Name AS LecturerName, u.Surn-+ame AS LecturerSurname, l.LectureID, l.Name AS LectureName "
				 + "FROM Course c, User u, CourseOfLecturer cl, Lecture l "
				 + "WHERE cl.LectureID = l.LectureID "
				 + "AND l.CourseID = c.CourseID "
				 + "AND cl.SchoolID = u.SchoolID "
				 + "AND c.CourseID = " + courseID;
		return this.getCourse(query);
	}

	@Override
	public Course getCourseByLectureID(int lectureID) {
		String query = "SELECT c.*, u.schoolID, u.Name AS LecturerName, u.Surname AS LecturerSurname, l.LectureID, l.Name AS LectureName "
				 + "FROM Course c, User u, CourseOfLecturer cl, Lecture l "
				 + "WHERE cl.LectureID = l.LectureID "
				 + "AND l.CourseID = c.CourseID "
				 + "AND cl.SchoolID = u.SchoolID "
				 + "AND cl.LectureID = " + lectureID;
		
		return this.getCourse(query);
	}

	@Override
	public ArrayList<Course> getAllVisibleCourses() {
		String query = "SELECT c.*, u.schoolID, u.Name AS LecturerName, u.Surname AS LecturerSurname, l.LectureID, l.Name AS LectureName "
				 + "FROM Course c, User u, CourseOfLecturer cl, Lecture l "
				 + "WHERE cl.LectureID = l.LectureID "
				 + "AND l.CourseID = c.CourseID "
				 + "AND cl.SchoolID = u.SchoolID "
				 + "AND c.Visible = true;";
		return this.getCourses(query);
	}

	@Override
	public ArrayList<Course> getAllInVisibleCourses() {
		String query = "SELECT c.*, u.schoolID, u.Name AS LecturerName, u.Surname AS LecturerSurname, l.LectureID, l.Name AS LectureName "
				 + "FROM Course c, User u, CourseOfLecturer cl, Lecture l "
				 + "WHERE cl.LectureID = l.LectureID "
				 + "AND l.CourseID = c.CourseID "
				 + "AND cl.SchoolID = u.SchoolID "
				 + "AND c.Visible = false;";
		return this.getCourses(query);
	}

	@Override
	public ArrayList<Course> getCourseByStudentID(int schoolID) {
		String query = "SELECT c.*, u.schoolID, u.Name AS LecturerName, u.Surname AS LecturerSurname, l.LectureID, l.Name AS LectureName "
				 + "FROM Course c, User u, CourseOfLecturer cl, Lecture l, CourseOfStudent cs "
				 + "WHERE cl.LectureID = l.LectureID "
				 + "AND l.CourseID = c.CourseID "
				 + "AND cl.SchoolID = u.SchoolID "
				 + "AND cs.LectureID = l.LectureID "
				 + "AND cs.SchoolID = " + schoolID + " "
				 + "AND cs.visible = 1";
		
		return this.getCourses(query);
	}

	@Override
	public ArrayList<Course> getCourseByDepartmentID(int departmentID) {
		String query = "SELECT DISTINCT c.*, u.schoolID, u.Name AS LecturerName, u.Surname AS LecturerSurname, l.LectureID, l.Name AS LectureName "
				 + "FROM Course c, User u, CourseOfLecturer cl, Lecture l, CourseOfStudent cs "
				 + "WHERE cl.LectureID = l.LectureID "
				 + "AND l.CourseID = c.CourseID "
				 + "AND cl.SchoolID = u.SchoolID "
				 + "AND cs.LectureID = l.LectureID "
				 + "AND c.DepartmentID= " + departmentID + " "
				 + "AND c.Visible = true;";
		return this.getCourses(query);
	}

	@Override
	public ArrayList<Course> getCoursesByCode(String courseCode) {
		String query = "SELECT DISTINCT c.*, u.schoolID, u.Name AS LecturerName, u.Surname AS LecturerSurname, l.LectureID, l.Name AS LectureName "
				 + "FROM Course c, User u, CourseOfLecturer cl, Lecture l, CourseOfStudent cs "
				 + "WHERE cl.LectureID = l.LectureID "
				 + "AND l.CourseID = c.CourseID "
				 + "AND cl.SchoolID = u.SchoolID "
				 + "AND cs.LectureID = l.LectureID "
				 + "AND c.Code = " + courseCode + " "
				 + "AND c.Visible = true";
		return this.getCourses(query);
	}

	@Override
	public ArrayList<SimpleCourse> getCoursesByCodeUsingLike(String courseCode) {
		String query = "SELECT DISTINCT c.* "
				 + "FROM Course c "
				 + "WHERE "
				 + "c.Visible = true "
				 + "AND c.Code LIKE  '%" + courseCode + "%';";
		return this.getSimpleCourses(query);
	}

	@Override
	public boolean addNewCourse(String code, String lecture, String lecturerEmail) {
		
		boolean added = false;
		Connection connection = null;
		
		
		try {
			connection = super.getConnection();
			String query = "SET @CourseID = (SELECT CourseID FROM Course WHERE Code = ?); " 
						 + "INSERT INTO Lecture(CourseID, Name) VALUES ( @CourseID, ?); " 
						 + "SET @LectureID = (SELECT LAST_INSERT_ID()); "
						 + "SET @SchoolID = (SELECT SchoolID FROM User WHERE Email = ?); "
						 + "INSERT INTO CourseOfLecturer(LectureID, SchoolID) VALUES (@LectureID, @SchoolID);";
			
			PreparedStatement sqlStatement = connection.prepareStatement(query);
			sqlStatement.setString(1, code);
			sqlStatement.setString(2, lecture);
			sqlStatement.setString(3, lecturerEmail);
			sqlStatement.executeQuery();
			
			added = true;
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		
		
		return added;
	}

	@Override
	public LectureDashboard getLectureDashboard(int lectureID) {
		
		LectureDashboard lectureDashboard = null;
		Connection connection = null;
		Course course = null;
		
		try {
			connection = super.getConnection();
			String query = "SELECT " 
					+ "l.LectureID," 
					+ "(SELECT COUNT(*) FROM CourseOfStudent WHERE LectureID = l.LectureID and Visible = true) AS EnrolledStudentCount, " 
					+ "(SELECT COUNT(*) FROM GradeOfCourse WHERE LectureID = l.LectureID and Visible = true) AS GradeCount " 
					+ "FROM Lecture l " 
					+ "WHERE l.LectureID = ? "
					+ "AND l.Visible = true;";
			
			
			PreparedStatement sqlStatement = connection.prepareStatement(query);
			sqlStatement.setInt(1, lectureID);
			ResultSet resultSet = sqlStatement.executeQuery();
			if (resultSet.next()) {
				
				int enrolledStudentCount = resultSet.getInt("EnrolledStudentCount");
				int gradeCount = resultSet.getInt("GradeCount");
				
				AnouncmentDaoImp anouncmentDaoImp = new AnouncmentDaoImp();
				ArrayList<Anouncment> anouncments = anouncmentDaoImp.getAnouncmentsByLecture(lectureID);
				course = this.getCourseByLectureID(lectureID);
				
				lectureDashboard = new LectureDashboard(course, lectureID, anouncments, enrolledStudentCount, gradeCount);
			}
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		return lectureDashboard;
	
	}

	
	
	@Override
	public ArrayList<SimpleGrade> getLectureGrades(int lectureID) {
		ArrayList<SimpleGrade> grades = new ArrayList<>();
		SimpleGrade grade = null;
		
		Connection connection = null;
		
		
		try {
			connection = super.getConnection();
			String query = "SELECT gc.*, (SELECT AVG(Grade) FROM GradeOfStudent WHERE CourseGradeID = gc.GradeID) AS Average "
					+ "FROM GradeOfCourse gc " 
					+ "WHERE gc.LectureID = ?;";
			
			PreparedStatement sqlStatement = connection.prepareStatement(query);
			sqlStatement.setInt(1, lectureID);		
			ResultSet resultSet = sqlStatement.executeQuery();
			
			while (resultSet.next()) {
				int gradeID = resultSet.getInt("GradeID");
				int affect = resultSet.getInt("Affect");
				String name = resultSet.getString("Name");
				float average = resultSet.getFloat("Average");
				grade = new SimpleGrade(lectureID, gradeID, name, affect, average);
				grades.add(grade);
				
			}
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		
		return grades;
	}

	@Override
	public LectureDetail getLectureDetail(int lectureID) {
		
		LectureDetail gradeDashboard = null;
		
		Course course = this.getCourseByLectureID(lectureID);
		ArrayList<SimpleGrade> grades = this.getLectureGrades(lectureID);
		gradeDashboard = new LectureDetail(course, grades, null);
		
		return gradeDashboard;
	}

	@Override
	public ArrayList<StudentGradeView> getStudentListofGradeByGradeID(int gradeID) {
		
		ArrayList<StudentGradeView> grades = new ArrayList<>();
		StudentGradeView  grade = null;
		Connection connection = null;
		
		String query = "SELECT DISTINCT gos.StudentID, u.Name, u.Surname, gos.Grade "  
					 + "FROM User u, GradeOfCourse goc, GradeOfStudent gos, CourseOfStudent cos " 
					 + "WHERE u.SchoolID = gos.StudentID " 
					 + "AND gos.CourseGradeID = goc.GradeID "  
					 + "AND goc.GradeID = ? "
					 + "AND goc.LectureID = cos.LectureID "
					 + "AND cos.SchoolID = gos.StudentID "
					 + "AND cos.Visible = true";
			
		try {
			connection = super.getConnection();
			PreparedStatement sqlStatement = connection.prepareStatement(query);
			sqlStatement.setInt(1, gradeID);
			ResultSet resultSet = sqlStatement.executeQuery();
				
			while(resultSet.next()) {
				int studentID = resultSet.getInt("StudentID");
				String name = resultSet.getString("Name");
				String surname = resultSet.getString("Surname");
				float studentGrade = resultSet.getFloat("Grade");
				
				String nameSurname = String.format("%s %s", name, surname);
				grade = new StudentGradeView(studentID, gradeID, nameSurname, studentGrade);
				grades.add(grade);
			}
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return grades;
	}
	
	
	
	@Override
	public ArrayList<Course> getAllCoursesNotTakenByStudent(int schoolID) {
		String query = "SELECT DISTINCT c.*, u.schoolID, u.Name AS LecturerName, u.Surname AS LecturerSurname, l.LectureID, l.Name AS LectureName "
				 + "FROM Course c, User u, CourseOfLecturer cl, Lecture l "
				 + "WHERE cl.LectureID = l.LectureID "
				 + "AND l.CourseID = c.CourseID "
				 + "AND cl.SchoolID = u.SchoolID "
				 + "AND l.LectureID NOT IN (Select LectureID FROM CourseOfStudent WHERE SchoolID =" + schoolID +" AND visible = true);";	
		
		return this.getCourses(query);
	}
	
	
	
	
}
