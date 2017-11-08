<%-- 
    Document   : home
    Created on : Oct 24, 2017, 11:13:35 AM
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
            #map {
                width: 600px;
                height: 600px;
                background-color: grey;
            }
            p {
                font-size: 180%;
                font-weight: bold;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <h1 align="center">Heroes</h1>
            <div class="navbar">
                <ul class="nav nav-tabs">
                    <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/">Home</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/super">Super Humans</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/organization">Organizations</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/sighting">Sightings</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/location">Locations</a></li>
                </ul> 
            </div>
            <!-- Main Page Content Start -->
            <div class="row">
                <div class="col-md-12">
                    <p style="text-align: center">
                        Have you seen a Superhero or a Villain? Help us track all the activity around the world! 
                    </p>
                </div>
            </div>
            <div class="row">
                <div class="col-md-6">
                    <br><h3>Latest Super Hero/Villain Sightings</h3>
                    <table id="sightingsTable" class="table table-hover">
                        <tr>
                            <th width="33%">Date</th>
                            <th width="33%">Location</th>
                            <th width="34%">Supers</th>
                        </tr>
                        <tbody id="content-rows">
                            <c:forEach var="sighting" items="${sightingList}" varStatus="theCount">
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
                                        <input type="number" hidden value="${sighting.location.latitude}"
                                               id="sighting-${theCount.count}-latitude"/>
                                        <input type="number" hidden value="${sighting.location.longitude}"
                                               id="sighting-${theCount.count}-longitude"/>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>                     
                </div> <!-- End col div -->
                <div class="col-md-6">
                    <br><br><br>
                    <div id="map"></div>
                    <script>
                        function initMap() {

                            var latitude = parseFloat(document.getElementById('sighting-1-latitude').value);
                            var longitude = parseFloat(document.getElementById('sighting-1-longitude').value);
                            console.log(latitude, longitude);
                            var uluru = {lat: latitude, lng: longitude};
                            var map = new google.maps.Map(document.getElementById('map'), {
                                zoom: 6,
                                center: uluru,
                                mapTypeId: 'terrain'
                            });
                            for (var i = 1; i <= 10; i++) {
                                var latitude = parseFloat(document.getElementById('sighting-' + i + '-latitude').value);
                                var longitude = parseFloat(document.getElementById('sighting-' + i + '-longitude').value);
                                var uluru = {lat: latitude, lng: longitude};
                                var marker = new google.maps.Marker({
                                    position: uluru,
                                    map: map
                                });
                            }
                        }
                    </script>
                    <script async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDQV9rwKo6GUcIQTnNS3o5s3it7fBA-z7M&callback=initMap">
                    </script>
                </div>
            </div>  <!-- End row div --> 
            <!-- Main Page Content Stop -->  
        </div>
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>