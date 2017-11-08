<%-- 
    Document   : super
    Created on : Oct 24, 2017, 1:30:56 PM
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
        <title>Superheroe</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet"> 
    </head>
    <body>  
        <div class="container">
            <h1 align="center">Heroes</h1>
            <div class="navbar">
                <ul class="nav nav-tabs">
                    <li role="presentation"><a href="${pageContext.request.contextPath}/">Home</a></li>
                    <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/super">Super Humans</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/organization">Organizations</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/sighting">Sightings</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/location">Locations</a></li>
                </ul> 
            </div>
            <!-- Main Page Content Start -->
            <div class="row">
                <div class="col-md-6">
                    <h3>Super Hero/Villains</h3>
                    <table id="superTable" class="table table-hover" style="table-layout: fixed; width: 100%">
                        <tr>
                            <th width="20%">Super Name</th>
                            <th width="20%">Description</th>
                            <th width="20%">Power</th>
                            <th width="20%">Organizations</th>
                            <th width="10%"></th>
                            <th width="10%"></th>
                        </tr>
                        <c:forEach var="superList" items="${superList}">
                            <tr>
                                <td style="word-wrap: break-word">
                                    <c:out value="${superList.name}"/>
                                </td>
                                <td>
                                    <c:out value="${superList.description}"/>
                                </td>
                                <td>
                                    <c:out value="${superList.superPower.name}"/>
                                </td>
                                <td>
                                    <c:forEach var="org" items="${superList.organizations}">
                                        <c:out value="${org.name}"/><br>
                                    </c:forEach>
                                </td>
                                <td>
                                    <a href="displayEditSuper?superId=${superList.superId}">
                                        Edit
                                    </a>

                                </td>
                                <td>
                                    <a href="deleteSuper?superId=${superList.superId}">
                                        Delete
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>                    
                </div> <!-- End col div -->
                <div class="col-md-6">
                    <center><h2>Add New Super Hero/Villain</h2></center>
                    <br>
                    <form class="form-horizontal" 
                          role="form" 
                          method="POST" 
                          action="createSuper">
                        <div class="form-group">
                            <label for="add-super-name" class="col-md-4 control-label">Super Name:</label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" name="superName" placeholder="Name" required/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-description" class="col-md-4 control-label">Description:</label>
                            <div class="col-md-8">
                                <input type="text" 
                                       class="form-control" 
                                       name="superDescription" 
                                       placeholder="Ex: Faster than a speeding bullet!"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-super-power" class="col-md-4 control-label">
                                Super Power:
                            </label>
                            <div class="col-md-8">
                                <select class="form-control" name="superPowerId" placeholder="Flight" required>
                                    <!-- Add to items = superList-->
                                    <c:forEach var="superPower" items="${spList}">
                                        <option value="${superPower.superPowerId}">${superPower.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-organization" class="col-md-4 control-label">
                                Organization:
                            </label>
                            <div class="col-md-8">
                                <select multiple type="text" class="form-control" name="organizationId">
                                    <!-- Add to items = organizationList-->
                                    <c:forEach var="organizationChoice" items="${organizationList}">
                                        <option value="${organizationChoice.organizationId}">${organizationChoice.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-offset-4 col-md-8">
                                <input type="submit" 
                                       id="add-super-human" 
                                       class="btn btn-primary" 
                                       value="Add Super Human"/>
                            </div>
                        </div>
                    </form>
                </div> <!-- End col div -->
            </div>
        </div><!--End container div -->

        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>