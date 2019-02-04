<%@ page import="meTube.domain.models.viewModels.TubeDetailsViewModel" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <c:import url="templates/head.jsp"/>
    <style type="text/css">
        <%@include file="styles/templatePageStyle.css" %>
        <%@include file="styles/details-style.css" %>
        <%@include file="styles/404-not-found-style.css" %>
    </style>
    <title>MeTube</title>
</head>
<body>
<section class="container">
    <header>
        <section>
            <div class="row d-flex justify-content-center">
                <div class="col col-md-12">
                    <h1><span>Me</span><span>Tube</span> Details</h1>
                    <hr class="trans--grow"/>
                    <p>Exercises: Java EE: JavaServer Pages</p>
                </div>
            </div>
        </section>
    </header>

    <main>
        <section class="jumbotron">
            <div class="row">
                <div class="col col-md-8">
                    <h3><span style="color: #FF642F">400 Error!</span> Bad Request</h3>
                    <hr/>
                    <p>Something went wrong! Cannot create this Tube. Perhaps you can return back to the site's <a href="/">homepage</a> and see if you can find what you are looking for.
                        Or you can try <a href="/tubes/create">create Tube</a> again.</p>
                    <br/>
                    <a href="/tubes/all" class="btn btn-info">Back to Collection</a>
                </div>
                <div class="col col-md-4">
                    <img class="image" src="https://cdn4.iconfinder.com/data/icons/application-windows-3/32/Bad-Request-Server-512.png" style="width: 222px; height: auto" alt="404 image"/>
                </div>
            </div>

        </section>
    </main>

    <c:import url="templates/footer.jsp"/>
</section>

<script type="text/javascript">
    jQuery(document).ready(function ($) {
        setTimeout(function () {
            $('.trans--grow').addClass('grow');
        }, 275);
    });
</script>
</body>
</html>
