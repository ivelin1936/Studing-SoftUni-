<%@ page import="meTube.domain.models.viewModels.TubeDetailsViewModel" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <c:import url="templates/head.jsp"/>
    <style type="text/css">
        <%@include file="styles/templatePageStyle.css" %>
        <%@include file="styles/details-style.css" %>
    </style>
    <title>MeTube</title>
</head>
<body>
<% TubeDetailsViewModel tdvm = (TubeDetailsViewModel) request.getAttribute("TubeDetailsViewModel"); %>

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
                <div class="col col-md-12">
                    <h3><i class="fab fa-youtube"></i> <%= tdvm.getName() %>
                    </h3>
                    <hr/>
                    <p><%= tdvm.getDescription() %>
                    </p>
                    <hr/>
                </div>
            </div>

            <div class="row">
                <div class="col col-md-6">
                    <a href="<%= tdvm.getYouTubeLink() %>" class="details-link" target="_blank"><i
                            class="fas fa-play"></i> Link to Video.</a>
                </div>
                <div class="col col-md-6">
                    <p><i class="fas fa-cloud-upload-alt"></i> <%= tdvm.getUploader() %>
                    </p>
                </div>
            </div>

            <!-- Validate that the video is from youTube, if is - using the iframe and the video's youTubeFrameId... -->
            <% if (tdvm.isYouTube()) { %>
                <div class="row">
                    <div class="col col-md-12">
                        <iframe
                                width="560"
                                height="315"
                                src="https://www.youtube.com/embed/<%= tdvm.getYouTubeFrameId() %>"
                                frameborder="0"
                                allow="accelerometer;
                                autoplay; encrypted-media; gyroscope; picture-in-picture"
                                allowfullscreen>
                        </iframe>
                    </div>s
                </div>
            <% } %>

            <div class="row">
                <div class="col col-md-12">
                    <hr/>
                    <a href="/tubes/all" class="btn btn-info">Back</a>
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
