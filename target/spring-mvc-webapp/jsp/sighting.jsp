<%-- 
    Document   : sighting
    Created on : Oct 24, 2017, 1:31:29 PM
    Author     : Alejandro
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet"> 
        <style type = "text/css">
            /*            body {
                            background-image: url("jsp/heroes.jpg");
                        }*/
        </style>
    </head>
    <body>
        <div class="container">
            <h1 align="center">Heroes</h1>
            <div class="navbar">
                <ul class="nav nav-tabs">
                    <li role="presentation"><a href="${pageContext.request.contextPath}/">Home</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/super">Super Humans</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/organization">Organizations</a></li>
                    <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/sighting">Sightings</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/location">Locations</a></li>
                </ul> 
            </div>
            <!-- Main Page Content Start -->
            <div class="row">
                <div class="col-md-6">
                    <br><h3>Latest Super Hero/Villain Sightings</h3>
                    <table id="sightingsTable" class="table table-hover">
                        <tr>
                            <th width="25%">Date</th>
                            <th width="25%">Location</th>
                            <th width="25%">Supers</th>
                            <th width="25%">Organization</th>
                        </tr>
                        <tbody id="content-rows">
                            <c:forEach var="sighting" items="${sightingList}">
                                <tr>
                                    <td>
                                        <c:out value="${sighting.date}"/>
                                    </td>
                                    <td>
                                        <c:out value="${sighting.location.name}"/>
                                    </td>
                                    <td>
                                        <c:forEach var="hero" items="${sighting.supers}">
                                            <c:out value="${hero.name}"/><br><br>
                                        </c:forEach>
                                    </td>
                                    <td>
                                        <c:forEach var="hero" items="${sighting.supers}">
                                            <c:forEach var="org" items="${hero.organizations}">
                                                <c:out value="${org.name}"/><br>
                                            </c:forEach><br>
                                        </c:forEach>
                                    </td>
                                    <td>
                                        <a href="displayEditSighting?sightingId=${sighting.sightingId}">
                                            Edit
                                        </a>
                                    </td>
                                    <td>
                                        <a href="deleteSighting?sightingId=${sighting.sightingId}">
                                            Delete
                                        </a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>                     
                </div> <!-- End col div --> 
                <div class="col-md-6">
                    <center><h2>Did you see a Superhero/Villain?</h2></center>
                    <br>
                    <form class="form-horizontal" 
                          role="form" 
                          method="POST" 
                          action="createSighting">

                        <div class="form-group">
                            <label for="add-superHuman" 
                                   class="col-md-4 control-label">
                                Super Humans:
                            </label>
                            <div class="col-md-8">
                                <select multiple class="form-control" 
                                        name="superIds" 
                                        placeholder="Batman"
                                        required>
                                    <c:forEach var="hero" items="${superList}">
                                        <option value="${hero.superId}">${hero.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="add-sighting-date" 
                                   class="col-md-4 control-label">
                                Sighting Date:
                            </label>
                            <div class="col-md-8">
                                <input type="Date" 
                                       class="form-control" 
                                       name="sightingDate" 
                                       placeholder="YYYYMMDD"
                                       required/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="add-location" class="col-md-4 control-label">Location:</label>
                            <div class="col-md-8">
                                <select class="form-control" 
                                        name="locationId" 
                                        placeholder="Location"
                                        required>
                                    <c:forEach var="location" items="${locationList}">
                                        <option value="${location.locationId}">${location.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-md-offset-4 col-md-8">
                                <input type="submit" 
                                       id="add-sighting" 
                                       formmethod="POST" 
                                       formaction="createSighting" 
                                       class="btn btn-primary" 
                                       value="Add New Sighting"/>
                            </div>
                        </div>
                    </form>
                </div> <!-- End col div -->
            </div>  <!-- End row div --> 
            <!-- Main Page Content Stop -->  
        </div>
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>
