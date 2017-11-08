<%-- 
    Document   : editLocation
    Created on : Oct 25, 2017, 8:48:59 AM
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

        <title>Edit Location</title>
    </head>
    <body>
        <div class="container">
            <center><h1>Edit Location</h1></center>
            <div class="row col-md-12">
                <div class="col-md-10 col-md-offset-1">
                    <br/>
                    <br/>
                    <sf:form class="form-horizontal"
                             role="form"
                             id="edit-location-form"
                             action="editLocation"
                             method="POST"
                             modelAttribute="location">

                        <div>
                            <sf:input type="hidden"
                                      id="locationId"
                                      name="locationId"
                                      class="form-control"
                                      path="locationId"/>
                        </div>

                        <!--Location Name-->
                        <div class="form-group">
                            <label for="name" class="col-md-2 control-label">Location Name:</label>
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
                        <!--Address-->
                        <div class="form-group">
                            <label for="address" class="col-md-2 control-label">Address:</label>
                            <div class="col-md-5">
                                <sf:input type="text"
                                          id="address"
                                          name="address"
                                          class="form-control"
                                          path="address"/>
                            </div>
                        </div>

                        <div>
                            <div class="form-group">
                                <label for="latitude" class="col-md-2 control-label">
                                    Latitude:
                                </label>
                                <div class="col-md-5">
                                    <sf:input type="text"
                                              id="latitude"
                                              name="latitude"
                                              class="form-control"
                                              path="latitude"/>
                                </div>
                            </div>
                        </div>


                        <!--Longitude-->
                        <div class="form-group">
                            <label for="longitude" class="col-md-2 control-label">Latitude:</label>
                            <div class="col-md-5">
                                <sf:input id="longitude"
                                          name="longitude"
                                          class="form-control"
                                          path="longitude"/>
                            </div>
                        </div>
                        <br>
                        <!--Submit Button-->
                        <div class="form-group">
                            <div class="col-md-offset-2 col-md-8">
                                <input type="submit"
                                       class="btn btn-primary"
                                       id="edit-location"
                                       action="editLocation"/>
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