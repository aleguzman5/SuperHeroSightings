<%-- 
    Document   : organization
    Created on : Oct 24, 2017, 1:31:11 PM
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
        <title>Organization</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet"> 
    </head>
    <body>
        <div class="container">
            <h1 align="center">Heroes</h1>  
            <div class="navbar">
                <ul class="nav nav-tabs">
                    <li role="presentation"><a href="${pageContext.request.contextPath}/">Home</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/super">Super Humans</a></li>
                    <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/organization">Organizations</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/sighting">Sightings</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/location">Locations</a></li>
                </ul> 
            </div>
            <!-- Main Page Content Start -->
            <div class="row">
                <div class="col-md-6">
                    <h3>Organizations</h3>
                    <table id="organizationTable" class="table table-hover">
                        <tr>
                            <th width="25%">Organization Name</th>
                            <th width="15%">City</th>
                            <th width="15%">Phone</th>
                            <th width="15%">Email</th>
                            <th width="15%"></th>
                            <th width="15%"></th>
                        </tr>
                        <c:forEach var="currentOrganization" items="${organizationList}">
                            <tr>
                                <td>
                                    <a href="displayOrganizationDetails?organizationId=${currentOrganization.organizationId}">
                                        <c:out value="${currentOrganization.name}"/>
                                    </a>
                                </td>
                                <td>
                                    <c:out value="${currentOrganization.city}"/>
                                </td>
                                <td>
                                    <c:out value="${currentOrganization.phone}"/>
                                </td>
                                <td>
                                    <c:out value="${currentOrganization.email}"/>
                                </td>
                                <td>
                                    <a href="displayEditOrganization?organizationId=${currentOrganization.organizationId}">
                                        Edit
                                    </a>
                                </td>
                                <td>
                                    <a href="deleteOrganization?organizationId=${currentOrganization.organizationId}">
                                        Delete
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>                    
                </div> <!-- End col div -->

                <div class="col-md-6">
                    <center><h2>Add New Organization</h2></center>
                    <br>
                    <form class="form-horizontal" 
                          role="form" method="POST" 
                          action="createOrganization">
                        <div class="form-group">
                            <label for="organizationName" class="col-md-4 control-label">Organization Name:</label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" name="organizationName" placeholder="Organization Name" required/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="organizationDescription" class="col-md-4 control-label">Description:</label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" name="organizationDescription" placeholder="Description"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="organizationAddress" class="col-md-4 control-label">Address:</label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" name="organizationAddress" placeholder="Address"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="organizationCity" class="col-md-4 control-label">City:</label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" name="organizationCity" placeholder="City"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="organizationZip" class="col-md-4 control-label">Zip:</label>
                            <div class="col-md-8">
                                <input type="number" class="form-control" name="organizationZip" placeholder="Zip"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="organizationPhone" class="col-md-4 control-label">Phone:</label>
                            <div class="col-md-8">
                                <input type="number" class="form-control" name="organizationPhone" placeholder="Phone"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="organizationEmail" class="col-md-4 control-label">Email:</label>
                            <div class="col-md-8">
                                <input type="email" class="form-control" name="organizationEmail" placeholder="Email"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-offset-4 col-md-8">
                                <input type="submit" 
                                       id="add-organization" 
                                       class="btn btn-primary" 
                                       value="Add New Organization"/>
                            </div>
                        </div>
                    </form>
                </div> <!-- End col div -->
            </div>
        </div>
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>
