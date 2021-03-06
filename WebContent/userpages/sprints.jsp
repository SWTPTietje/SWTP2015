<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page import="javax.servlet.http.HttpServletRequest"%>
<%@ page import="javax.servlet.http.HttpServletResponse"%>
<%@ page import="javax.servlet.http.HttpSession"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Sprints</title>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/application.css"/>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css">
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
    </head>

    <body BACKGROUND="${pageContext.request.contextPath}/triangular.png"/>
    <nav class="navbar navbar-default">
        <div class="container-fluid">
            <div class="navbar-header">
                <a class="navbar-brand" href="${pageContext.request.contextPath}/credits.jsp">SWTP-Team</a>
            </div>
            <ul class="nav navbar-nav">
                <li class="active"><a href="${pageContext.request.contextPath}/user/sprints.jsp">Sprints</a></li>
                <li><a href="${pageContext.request.contextPath}/user/index.jsp">Tickets</a></li>
                <li><a href="${pageContext.request.contextPath}/user/components.jsp">Components</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="${pageContext.request.contextPath}/user/userpage.jsp"><span class="glyphicon glyphicon-user" aria-hidden="true"></span>
                        ${pageContext.request.userPrincipal.name}</a></li>
                <li><a href="${pageContext.request.contextPath}/user/?action=logout">logout</a></li>
            </ul>
        </div>
    </nav>
    <a class="btn btn-success" role="button" data-toggle="collapse" href="#new-sprint" aria-expanded="false" aria-controls="new-sprint">
        <span class="glyphicon glyphicon-plus"></span>new Sprint
    </a>
    <div class="collapse well" id="new-sprint">
        <h1>New Sprint</h1>
        <form action="user" method="post">
            <input type="hidden" name="action" value="addSprint" /> 
            <input type="hidden" name="sprintid" value="${thissprint.sprintid}" /> 
            Title:<input name="title" type="text" /> ${errorMsgs.title}<br> 
            Start of Sprint:<br>
            Year<input name="y1" type="text" value="2015" /> ${errorMsgs.title}<br>
            Month<input name="m1" type="text" value="1"/> ${errorMsgs.title}<br>
            Day<input name="d1" type="text" value="1" /> ${errorMsgs.title}<br>

            End of Sprint:<br>
            Year<input name="y2" type="text" value="2016" /> ${errorMsgs.title}<br>
            Month<input name="m2" type="text" value="8"/> ${errorMsgs.title}<br>
            Day<input name="d2" type="text" value="8" /> ${errorMsgs.title}<br>

            Tickets without sprint :<br>
            <c:forEach items="${nosprinttickets}" var="tick1">
                <input type="checkbox" name="tickids" value="${tick1.id}">${tick1.title}${tick1.id}
                <br>
            </c:forEach>
            <input type="submit" value="add sprint">	
        </form>
    </div>

    <c:choose>
        <c:when test="${not empty activesprint}">
            <h1>
                Active Sprint: 
                <a href="${pageContext.request.contextPath.concat("/user/sprintDetail.jsp?sprintid=").concat(activesprint.getSprintid())}">${activesprint.title}
                </a>
            </c:when>
            <c:otherwise>
                '<h1>No active sprint</h1>'
            </c:otherwise>
        </c:choose>

        <h1>Sprints</h1>

        <table>
            <col width="100">
            <col width="200">
            <tr>
                <th>Title</th>
                <th>Tickets</th>
                <th>start</th>
                <th>end</th>
            </tr>
            <c:forEach items="${sprints}" var="sprint1">
                <tr>
                    <td><a
                            href="${pageContext.request.contextPath.concat("/user/sprintDetail.jsp?sprintid=").concat(sprint1.getSprintid())}">
                            ${sprint1.title} </a></td>
                    <td><a
                            href="${pageContext.request.contextPath.concat("/user/sprintstickets.jsp?sprintid=").concat(sprint1.getSprintid())}">
                            tickets </a></td>
                    <td>${sprint1.getStartDateAsString()}</td>
                    <td>${sprint1.getEndDateAsString()}</td>
                </tr>
            </c:forEach>
        </table>
        <br>
        <br>
        <br>
        <a href="${pageContext.request.contextPath}/user/index.jsp?sprintid=-2">
            all tickets </a>
    </body>
</html>