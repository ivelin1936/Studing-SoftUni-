<%@ page import="meTube.domain.models.view.TubeDetailsViewModel" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <c:import url="templates/head.jsp"/>
    <style type="text/css">
        <%@include file="styles/templatePageStyle.css" %>
        <%@include file="styles/navigation-style.css" %>
        <%@include file="styles/tube-details.css" %>
    </style>
    <title>MeTube</title>
</head>
<body>
<section class="container">
    <header>
        <section>
            <div class="row d-flex justify-content-center">
                <div class="col col-md-12">
                    <c:import url="templates/navigation.jsp" />
                    <p>Java EE Workshop : Servlets & JSP</p>
                    <hr class="trans--grow"/>
                </div>
            </div>
        </section>
    </header>

    <main>
        <% TubeDetailsViewModel model = (TubeDetailsViewModel) request.getAttribute("viewModel"); %>

        <hr class="my-2">
        <div class="container-fluid">
            <h2 class="text-center"><%= model.getTitle() %></h2>
            <div class="row">
                <div class="col-md-6 my-5">
                    <div class="embed-responsive embed-responsive-16by9">
                        <iframe
                                width="560"
                                height="315"
                                src="https://www.youtube.com/embed/<%= model.getYouTubeId() %>"
                                frameborder="0"
                                allow="accelerometer;
                                autoplay; encrypted-media; gyroscope; picture-in-picture"
                                allowfullscreen>
                        </iframe>
                    </div>
                    <p><i class="fas fa-cloud-upload-alt"></i> <a href="#" title="uploader details"><%= model.getUploader() %> </a></p>
                </div>
                <div class="col-md-6 my-5">
                    <h1 class="text-center text-info"><%= model.getAuthor() %></h1>
                    <h3 class="text-center text-info"><%= model.getViews() %> Views</h3>
                    <div class="h5 my-5 text-center"><%= model.getDescription() %></div>
                </div>
            </div>
        </div>
        <hr class="my-3"/>
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