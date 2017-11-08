<%-- 
    Document   : organizationDetails
    Created on : Oct 25, 2017, 6:10:36 PM
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
            <h2>Organization Details</h2>
            <hr/>
            <p>
                <label> Name: </label><c:out value=" ${organization.name}"/> 
            </p>
            <p>
                <label> Description: </label><c:out value=" ${organization.description}"/>
            </p>
            <p>
                <label> Address: </label><c:out value=" ${organization.address}"/>
            </p>
            <p>
                <label> City: </label><c:out value=" ${organization.city}"/>
            </p>
            <p>
                <label> Zip: </label><c:out value=" ${organization.zip}"/>
            </p>
            <p>
                <label> Phone: </label><c:out value=" ${organization.phone}"/>
            </p>
            <p>
                <label> Email: </label><c:out value=" ${organization.email}"/>
            </p>
        </div>
        <div class="form-group">
            <div class="col-md-offset-2 col-md-8">
                <!--Go Back Button-->
                <div class="form-group">
                    <form class="form-horizontal" role="form" method="GET" action="organization">
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