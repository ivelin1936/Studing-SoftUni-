<%@ page import="meTube.domain.models.viewModels.TubeDetailsViewModel" %>
<%@ page import="java.util.List" %>
<%@ page import="meTube.domain.models.viewModels.TubeViewModel" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <c:import url="templates/head.jsp"/>
    <style type="text/css">
        <%@include file="styles/templatePageStyle.css" %>
        <%@include file="styles/details-style.css" %>
        <%@include file="styles/all-tubes-style.css" %>
    </style>
    <title>MeTube DB</title>
</head>
<body>
<% List<TubeViewModel> allTVM = (List<TubeViewModel>) request.getAttribute("AllTubeViewModels"); %>

<section class="container">
    <header>
        <section>
            <div class="row d-flex justify-content-center">
                <div class="col col-md-12">
                    <h1><span>Me</span><span>Tube</span> Collection</h1>
                    <hr class="trans--grow"/>
                    <p>Exercises: Java EE: JavaServer Pages</p>
                </div>
            </div>
        </section>
    </header>

    <main>
        <section class="jumbotron">
            <div class="row">
                <div class="col col-md-12">
                    <h3><i class="fas fa-database"></i> Tube DB</h3>
                    <hr/>
                        <ul>
                            <% for (TubeViewModel tubeViewModel : allTVM) { %>
                                <li onmouseover="mouseOver(this)" onmouseout="mouseOut(this)">
                                    <a href="/tubes/details?name=<%= tubeViewModel.getName() %>">
                                        <i class="fab fa-youtube" style="display: none"></i> <%= tubeViewModel.getName() %>
                                    </a>
                                </li>
                            <% } %>
                        </ul>
                    <hr/>
                    <a href="/tubes/create" class="btn btn-info">Add Tube</a>
                    <br/><br/>
                    <a href="/">Back to Home</a>
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

    function mouseOver(e) {
        $(e).find("i.fab").css("display", "inline-block");
    }

    function mouseOut(e) {
        $(e).find("i.fab").css("display", "none");
    }
</script>
</body>
</html>
