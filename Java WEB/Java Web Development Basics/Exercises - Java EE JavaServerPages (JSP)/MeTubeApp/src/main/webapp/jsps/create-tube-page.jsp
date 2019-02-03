<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <c:import url="templates/head.jsp"/>
    <style type="text/css">
        <%@include file="styles/templatePageStyle.css" %>
        <%@include file="styles/form-style.css" %>
    </style>
    <title>MeTube Create</title>
</head>
<body>
    <section class="container">
        <header>
            <section >
                <div class="row d-flex justify-content-center">
                    <div class="col col-md-12">
                        <h1><span>Me</span><span>Tube</span> Create Tube</h1>
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

                        <form action="/tubes/create" method="post">
                            <div class="form-group">
                                <label for="titleInput">Title</label>
                                <input name="name" type="text" class="form-control" id="titleInput" placeholder="Insert title">
                            </div>

                            <div class="form-group">
                                <label for="descriptionInput">Description</label>
                                <textarea name="description" class="form-control" id="descriptionInput" rows="3" placeholder="Description..."></textarea>
                            </div>

                            <div class="form-group">
                                <label for="youTubeLinkInput">YouTube Link</label>
                                <input name="youTubeLink" type="text" class="form-control" id="youTubeLinkInput" placeholder="Insert YouTube link">
                            </div>

                            <div class="form-group">
                                <label for="uploaderInput">Uploader</label>
                                <input name="uploader" type="text" class="form-control" id="uploaderInput" placeholder="Insert uploader">
                            </div>

                            <button class="btn btn-info" type="submit">Create</button>
                        </form>

                        <hr/>
                        <a href="/">Back to Home.</a>
                    </div>
                </div>
            </section>
        </main>

        <c:import url="templates/footer.jsp"/>
    </section>

    <script type="text/javascript">
        jQuery(document).ready(function($){
            setTimeout(function(){
                $('.trans--grow').addClass('grow');
            }, 275);
        });
    </script>
</body>
</html>
