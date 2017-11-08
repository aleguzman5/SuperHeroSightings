<%-- 
    Document   : editSuper
    Created on : Oct 25, 2017, 8:49:43 AM
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
        <link rel="stylesheet" type="text/css" href="css/home.css">

        <!--        <style>
                    h1 {
                        text-align: center;
                    }
        
                    .footer{
                        margin-top: 20px;
                    }
                </style>-->
        <title>Edit Super Hero/Villain</title>
    </head>
    <body>
        <div class="container">
            <h2>Edit Super Hero/Villain</h2>
            <div class="row col-md-12">
                <div class="col-md-10 col-md-offset-1">
                    <br/>
                    <br/>
                    <sf:form class="form-horizontal"
                             role="form"
                             id="editSuper"
                             action="editSuper"
                             method="POST"
                             modelAttribute="hero">
                        <div>
                            <sf:input type="hidden"
                                      id="superId"
                                      name="superId"
                                      class="form-control"
                                      path="superId"/>
                        </div>

                        <div class="form-group">
                            <label for="name" class="col-md-2 control-label">Super Name:</label>
                            <div class="col-md-5">
                                <sf:input type="text"
                                          id="name"
                                          name="name"
                                          class="form-control"
                                          path="name"
                                          required="required"/>

                            </div>
                        </div>
                        <!--Description-->
                        <div class="form-group">
                            <label for="description" class="col-md-2 control-label">Description:</label>
                            <div class="col-md-5">

                                <sf:input type="text"
                                          id="description"
                                          name="description"
                                          class="form-control"
                                          path="description" />
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="pwrList" class="col-md-2 control-label">Super Power:</label>
                            <div class="col-md-5">
                                <select class="form-control" name="pwrList">
                                <c:forEach var="superPower" items="${spList}">
                                    <c:choose>
                                        <c:when test="${hero
                                                        .superPower
                                                        .superPowerId 
                                                        == superPower.superPowerId}">
                                                <option value="${superPower.superPowerId}" selected>
                                                    <c:out value="${superPower.name}"/>
                                                </option>
                                        </c:when>
                                        <c:otherwise>
                                            <option value="${superPower.superPowerId}">
                                                <c:out value="${superPower.name}"/>
                                            </option>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </select>
                            </div>
                            
                        </div>    
                        <div class="form-group">
                            <label for="orgList" class="col-md-2 control-label">Organizations</label>
                            <div class="col-md-5">
                                <select multiple class="form-control" name="orgList">
                                    <c:forEach var="org" items="${organizations}">
                                        <c:set var="isSelected" value="false"/>
                                        <c:forEach var="personOrgs" items="${hero.organizations}">
                                            <c:if test="${personOrgs.organizationId == org.organizationId}">
                                                <c:set var="isSelected" value="true"/>
                                            </c:if>
                                        </c:forEach>
                                        <c:choose>
                                            <c:when  test="${isSelected}">
                                                <option value="${org.organizationId}" 
                                                        selected="true">
                                                    <c:out value="${org.name}"/>
                                                </option>
                                            </c:when>
                                            <c:otherwise>
                                                <option value="${org.organizationId}"> 
                                                    <c:out value="${org.name}"/>
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
                                       id="edit-super"
                                       action="editSuper"/>
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