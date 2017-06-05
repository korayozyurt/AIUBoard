<%@ page import="pojos.Assignment" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="pojos.CourseAssignment" %>
<%ArrayList<CourseAssignment> courseAssignments = (ArrayList<CourseAssignment>) session.getAttribute("courseAssignments"); %>
<%ArrayList<Assignment> assignments = (ArrayList<Assignment>) session.getAttribute("assignments"); %>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Blackboard - Assignments</title>
		<%@include file="style.jsp" %>
	    
  </head>

  <body>
    <div id="wrapper">
        
        <%@include file="head.jsp" %>
        
        <!--/. NAV TOP  -->
        <nav class="navbar-default navbar-side" role="navigation">
            <div class="sidebar-collapse">
                <ul class="nav" id="main-menu">
                    <li>
                   		 <a href="${pageContext.request.contextPath}/student/dashboard"><i class="fa fa-tachometer"></i> Dashboard</a>
                    </li>
                    <li>
                   		 <a href="${pageContext.request.contextPath}/student/courses"><i class="fa fa-book"></i> Courses</a>
                    </li>
                    <li>
                    	<a href="${pageContext.request.contextPath}/student/grades"><i class="fa fa-table"></i> Grades</a>
                    </li>
                    <li>
                   		 <a class="active-menu" href="${pageContext.request.contextPath}/student/assignments"><i class="fa fa-pencil-square-o"></i> Assignments</a>
                    </li>
                    <li>
                    	<a href="${pageContext.request.contextPath}/student/settings"><i class="fa fa-cog"></i> Settings</a>
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
                          Assignments <small> Assignments at Spring - 2017</small>
                      </h1>
                  </div>
              </div>
              <ul class="nav nav-pills nav-stacked col-md-4">

                <%
                  ArrayList<String> duplicates = new ArrayList<>();
                  int ind = 0;
                  for(Assignment assignment : assignments){ %>
                      <% if(duplicates.contains(String.valueOf(assignment.getCourse().getCourseID()))){ %>
                      <% continue; } %>
                      <% duplicates.add(String.valueOf(assignment.getCourse().getCourseID())); %>
                      <% if(ind == 0){ %>
                        <li class="active"><a href="#<%=assignment.getCourse().getCourseID()%>" data-toggle="pill"><strong><%=assignment.getCourse().getCode() %></strong> - <%=assignment.getCourse().getName()%><i class="fa fa-bell-o"></i></a></li>
                      <% } %>
                      <% if(ind > 0) { %>
                        <li><a href="#<%=assignment.getCourse().getCourseID()%>" data-toggle="pill"><strong><%=assignment.getCourse().getCode() %></strong> - <%=assignment.getCourse().getName()%><i class="fa fa-bell-o"></i></a></li>
                      <% } %>
                   <%
                    ind +=1;
                  } %>
               </ul>


              <div class="tab-content col-md-8">

                <%
                  int var = 0;
                %>
                <% for (CourseAssignment courseAssignment:courseAssignments){ %>

                <% if(var ==0){ %>
                    <div class="tab-pane active" id = "<%=courseAssignment.getCourse().getCourseID()%>">
                 <% }%>
                 <% if(var>0){ %>
                      <div class="tab-pane" id="<%=courseAssignment.getCourse().getCourseID()%>">
                  <% } %>
                  <div class="panel panel-default">
                    <div class="panel-body">
                      <div class="row">
                        <table class="table table-hover">
                          <tbody>
                            <% assignments = courseAssignment.getAssignments(); %>
                            <% for(Assignment assignment : assignments){ %>
                                <tr>
                                  <td>
                                    <label><strong><%=assignment.getCourse().getCourseID()%> </strong> - <%=assignment.getCourse().getName()%></label>
                                    <br>
                                    <small class="text-muted"><%=assignment.getName()%></small>
                                    <br>
                                    <small class="text-muted"><strong>Due Date:</strong></small>
                                    <br>
                                    <small class="text-muted">Submission:</small>
                                  </td>
                                  <td class="text-right">
                                    <br>
                                    <small class="text-muted"><%= assignment.getDueDate()%></small>
                                    <br>
                                    <small class="text-muted"><%=assignment.isSubmitted()%></small>
                                    <% if(assignment.isSubmitted()){ %>
                                      <br>
                                        <strong><i class="fa fa-check-circle fa-lg icongreencolor"></i></strong>
                                    <% }else{ %>
                                      <br>
                                      <strong><i class="fa fa-times-circle fa-lg iconredcolor"></i></strong>
                                    <% } %>
                                  </td>
                                </tr>
                            <% } %>

                            </tr>
                          </tbody>
                        </table>
                      </div>
                    </div>
                  </div>
                </div>

                <% var+=1; %>
                <% } %>

              </div><!-- tab content -->
            </div>
        </div>
    </div>

	<%@include file="script.jsp" %>



</body>

</html>
