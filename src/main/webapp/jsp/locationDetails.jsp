<%-- 
    Document   : locationDetails
    Created on : Oct 25, 2017, 6:10:19 PM
    Author     : Alejandro
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Company Contacts</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">        
    </head>
    <body>
        <div class="container">
            <h2>Location Details</h2>
            <hr/>
            <p>
                <label> Name: </label><c:out value=" ${location.name}"/> 
            </p>
            <p>
                <label> Description: </label><c:out value=" ${location.description}"/>
            </p>
            <p>
                <label> Address: </label><c:out value=" ${location.address}"/>
            </p>
            <p>
                <label> Latitude: </label><c:out value=" ${location.latitude}"/>
            </p>
            <p>
                <label> Longitude: </label><c:out value=" ${location.longitude}"/>
            </p>
        </div>
            <div class="col-md-offset-2 col-md-8">
                <!--Go Back Button-->
                <div class="form-group">
                    <form class="form-horizontal" role="form" method="GET" action="location">
                        <input type="submit"
                               class="btn btn-primary"
                               id="edit-organization"
                               value="Go Back"
                               style="margin-left: 60px"/>
                    </form>
                </div>
            </div>
            <!-- Placed at the end of the document so the pages load faster -->
            <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
            <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>
</html>