<%-- 
    Document   : editOrganization
    Created on : Oct 25, 2017, 8:49:28 AM
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

        <title>Edit Organization</title>
    </head>
    <body>
        <div class="container">
            <h1>Edit Organization</h1>
            <div class="row col-md-12">
                <div class="col-md-10 col-md-offset-1">
                    <br/>
                    <br/>
                    <sf:form class="form-horizontal"
                             role="form"
                             id="edit-organization-form"
                             action="editOrganization"
                             method="POST"
                             modelAttribute="organization">

                        <div>
                            <sf:input type="hidden"
                                      id="organizationId"
                                      name="organizationId"
                                      class="form-control"
                                      path="organizationId"/>
                        </div>

                        <!--Organization Name-->
                        <div class="form-group">
                            <label for="name" class="col-md-2 control-label">Organization Name:</label>
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
                                          name="nddress"
                                          class="form-control"
                                          path="address"/>
                            </div>
                        </div>
                        <div>
                            <div class="form-group">
                                <label for="city" class="col-md-2 control-label">
                                    City:
                                </label>
                                <div class="col-md-5">
                                    <sf:input id="city"
                                              name="city"
                                              class="form-control"
                                              path="city"/>
                                </div>
                            </div>
                        </div>


                        <!--Zip-->
                        <div class="form-group">
                            <label for="zip" class="col-md-2 control-label">Zip:</label>
                            <div class="col-md-5">
                                <sf:input id="zip"
                                          name="zip"
                                          class="form-control"
                                          path="zip"/>
                            </div>
                        </div>
                        <!--Phone-->
                        <div class="form-group">
                            <label for="phone" class="col-md-2 control-label">Phone:</label>
                            <div class="col-md-5">
                                <sf:input id="phone"
                                          name="phone"
                                          class="form-control"
                                          path="phone"/>
                            </div>
                        </div>
                        <!--Email-->
                        <div class="form-group">
                            <label for="email" class="col-md-2 control-label">Email:</label>
                            <div class="col-md-5">
                                <sf:input id="email"
                                          name="email"
                                          class="form-control"
                                          path="email"/>
                            </div>
                        </div>
                        <br>
                        <!--Submit Button-->
                        <div class="form-group">
                            <div class="col-md-offset-2 col-md-8">
                                <input type="submit"
                                       class="btn btn-primary"
                                       id="edit-organization"
                                       action="editOrganization"/>
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