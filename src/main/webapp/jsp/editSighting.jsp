<%-- 
    Document   : editSighting
    Created on : Oct 26, 2017, 2:51:59 PM
    Author     : Alejandro
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">

        <title>Edit Super Hero/Villain</title>
    </head>
    <body>
        <div class="container">
            <h2>Edit Sighting</h2>
            <div class="row col-md-12">
                <div class="col-md-10 col-md-offset-1">
                    <br/>
                    <br/>
                    <sf:form class="form-horizontal"
                             role="form"
                             id="editSighting"
                             action="editSighting"
                             method="POST"
                             modelAttribute="sighting">
                        <div>
                            <sf:input type="hidden"
                                      id="sightingId"
                                      name="sightingId"
                                      value="${sighting.sightingId}"
                                      class="form-control"
                                      path="sightingId"/>
                        </div>

                        <div class="form-group">
                            <label for="superList" class="col-md-2 control-label">Super Humans:</label>
                            <div class="col-md-5">
                                <select multiple class="form-control" name="superList">
                                    <c:forEach var="currentSuper" items="${superList}">
                                        <c:set var="isSelected" value="false"/>
                                        <c:forEach var="sightingSupers" items="${sighting.supers}">
                                            <c:if test="${sightingSupers.superId == currentSuper.superId}">
                                                <c:set var="isSelected" value="true"/>
                                            </c:if>
                                        </c:forEach>
                                        <c:choose>
                                            <c:when  test="${isSelected}">
                                                <option value="${currentSuper.superId}" 
                                                        selected="true">
                                                    <c:out value="${currentSuper.name}"/>
                                                </option>
                                            </c:when>
                                            <c:otherwise>
                                                <option value="${currentSuper.superId}"> 
                                                    <c:out value="${currentSuper.name}"/>
                                                </option>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>

                        <!--Description-->
                        <div class="form-group">
                            <label for="description" class="col-md-2 control-label">Sighting Date:</label>
                            <div class="col-md-5">

                             <sf:input type="Date"
                                          name="date"
                                          value="${sighting.date}"
                                          class="form-control"
                                          path="date" />
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="pwrList" class="col-md-2 control-label">Location:</label>
                            <div class="col-md-5">
                                <select class="form-control" name="locationList">
                                    <c:forEach var="loc" items="${locList}">
                                        <c:choose>
                                            <c:when test="${sighting.location
                                                            .locationId
                                                            == loc.locationId}">
                                                    <option value="${loc.locationId}" selected>
                                                        <c:out value="${loc.name}"/>
                                                    </option>
                                            </c:when>
                                            <c:otherwise>
                                                <option value="${loc.locationId}">
                                                    <c:out value="${loc.name}"/>
                                                </option>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>    

                        <!--Submit Button-->
                        <div class="form-group">
                            <div class="col-md-offset-2 col-md-8">
                                <input type="submit"
                                       class="btn btn-primary"
                                       id="edit-sighting"/>
                                <!--Cancel Button-->
                                <button class="btn btn-primary"
                                        id="cancel-button"
                                        type="submit">
                                    Cancel
                                </button>
                            </div>
                        </div>
                    </sf:form>
                </div>
            </div>
        </div>
</body>
<script src="js/jquery-3.1.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>
</html>