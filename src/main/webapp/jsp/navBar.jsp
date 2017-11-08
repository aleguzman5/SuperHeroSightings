<%-- 
    Document   : navBar
    Created on : Oct 24, 2017, 1:12:46 PM
    Author     : Alejandro
--%>

<header>
    <div class="container">
        <h1 align="center">Heroes</h1>
        <br>
        <div class="navbar">
            <ul class="nav nav-tabs">
                <li role="presentation"><a class="active nav-link" href="${pageContext.request.contextPath}/">Home</a></li>
                <li role="presentation"><a class="nav-link" href="${pageContext.request.contextPath}/super">Super Humans</a></li>
                <li role="presentation"><a class="nav-link" href="${pageContext.request.contextPath}/organization">Organizations</a></li>
                <li role="presentation"><a class="nav-link" href="${pageContext.request.contextPath}/sighting">Sightings</a></li>
                <li role="presentation"><a class="nav-link" href="${pageContext.request.contextPath}/location">Locations</a></li>
            </ul> 
        </div>
    </div>
</header>