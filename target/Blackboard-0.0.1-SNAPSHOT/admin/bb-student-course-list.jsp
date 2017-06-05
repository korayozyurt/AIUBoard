<%@page import="pojos.Course"%>
<%@page import="pojos.Student"%>
<%@page import="java.util.ArrayList"%>


<% ArrayList<Course> availableCourses = (ArrayList<Course>) session.getAttribute("availableCourses"); %>

<% ArrayList<Course> studentCourses = (ArrayList<Course>) session.getAttribute("studentCourses"); %>

<% Student student = (Student) session.getAttribute("student"); %>


<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Blackboard - Dashboard</title>
    
    <%@include file="style.jsp" %>

</head>

<body>
    <div id="wrapper">
	    <%@ include file="bb-head.jsp" %>

        <!--/. NAV TOP  -->
        <nav class="navbar-default navbar-side" role="navigation">
            <div class="sidebar-collapse">
                       <ul class="nav" id="main-menu">
                <li>
                    <a href="${pageContext.request.contextPath}/admin/dashboard"><i class="fa fa-dashboard"></i> Dashboard</a>
                </li>
                <li>
                    <a  href="${pageContext.request.contextPath}/admin/managers"><i class="fa fa-user"></i> User Management</a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/admin/courses"><i class="fa fa-book"></i> Course Management</a>
                </li>
                <li>
                    <a class="active-menu" href="${pageContext.request.contextPath}/admin/students"><i class="fa fa-graduation-cap"></i> Student Management</a>
                </li>

                <li>
                    <a class="" href="${pageContext.request.contextPath}/admin/lecturers"><i class="fa fa-users"></i> Lecturer Management</a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/admin/settings"><i class="fa fa-edit"></i> Settings</a>
                </li>
            </ul>


            </div>

        </nav>
        <!-- /. NAV SIDE  -->
        <div id="page-wrapper">
            <div id="page-inner">


                <div class="row">
                    <div class="col-md-12">
                        <h1 class="page-header">
                            Student Management
                        </h1>
                    </div>
                </div>
                <!-- /. ROW  -->
                <div class="row bottom-space">
                    <div class="col-md-12">
                        <button type="button" name="button" class="btn btn-success" data-toggle="modal" data-target="#add-new-course"><span class=" glyphicon glyphicon-plus"></span> Add New Course</button>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-12 col-xs-12 col-sm-12">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                Student Course List
                            </div>
                            <div class="panel-body">
                                <table class="table table-responsive">
                                    <thead>
                                        <th class="text-center">Code</th>
                                        <th class="text-center">Course</th>
										<th class="text-center">Lecture</th>
                                        <th class="text-center">Lecturer</th>
                                        <th></th>
                                    </thead>
                                    <tbody>
                                    	<% for(Course course : studentCourses) {%>
                                        <tr class="text-center">
                                            <td><%= course.getCode() %></td>
                                            <td><%= course.getName() %></td>
                                     	   <td><%= course.getLectureName() %></td>
                                            <td><%= course.getLecturerInf() %></td>
                                            <td>
                                                <a class="delete-course" href="${pageContext.request.contextPath}/admin/student/<%= student.getSchoolID()%>/delete/<%= course.getLectureID()%>" class="table-col-space-right">
                                                    <i class="fa fa-trash-o"></i>
                                                </a>
                                                <a href="${pageContext.request.contextPath}/admin/grade/lecture/<%= course.getLectureID()%>/student/<%= student.getSchoolID()%>">
                                                    <i class="fa fa-chevron-right""></i>
                                                </a>
                                            </td>
                                        </tr>
                               			<%} %>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="modal fade" id="add-new-course" tabindex="-1" role="dialog" aria-labelledby="" aria-hidden="true">
          <div class="modal-dialog">
            <div class="modal-content">
              <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <div class="modal-title">
                    Add New Course
                </div>
              </div>
              <div class="modal-body">
                  <form class="" action="index.html" method="post">
                      <div class="row">
                          <div class="col-md-3">
                                  <div class="form-group">
                                		<input class="form-control" name="schoolID" value="<%= student.getSchoolID() %>" readonly>
                                  </div>
                          </div>
                          <div class="col-md-5">
	                            <div class="form-group">
	                                	<input class="form-control" name="name-surname" value="<%= student.getNameSurname() %>" readonly>
	                          	</div>
                      </div>
                  </form>

                  <div class="row">
                    <div class="col-md-12">
                        <table class="table table-responsive">
                            <thead>
                                <th class="text-center">Code</th>
                                <th class="text-center">Course</th>
                                <th class="text-center">Lecture</th>
                                <th class="text-center">Lecturer</th>
                                <th class="text-center"></th>
                            </thead>
                            <tbody>
                            
                            	<%for(Course course: availableCourses){ %>
                            
                                <tr class="text-center">
                                    <td><%= course.getCode() %></td>
                                    <td><%= course.getName() %></td>
                                    <td><%= course.getLectureName() %></td>
                                    <td><%= course.getLecturerName() %> <%= course.getLecturerSurname()%></td>
                                    <td>
                                        <a href="${pageContext.request.contextPath}/admin/student/<%= student.getSchoolID() %>/add/<%= course.getLectureID()%>"><span class="glyphicon glyphicon-plus"></span></a>
                                    </td>
                                </tr>
                                <%} %>
                
                            </tbody>
                        </table>
                    </div>
                  </div>
              </div>
              <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                <button type="button" class="btn btn-success">Save</button>
              </div>
            </div>
          </div>
        </div>

<%@include file="script.jsp" %>



</body>

</html>
