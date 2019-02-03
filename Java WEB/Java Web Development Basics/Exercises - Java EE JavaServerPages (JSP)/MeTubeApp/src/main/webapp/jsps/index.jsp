<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <c:import url="templates/head.jsp"/>
    <style type="text/css">
        <%@include file="styles/templatePageStyle.css" %>
    </style>
    <title>MeTube</title>
</head>
<body>
    <section class="container">
        <header>
            <section >
                <div class="row d-flex justify-content-center">
                    <div class="col col-md-12">
                        <h1>Welcome to <span>Me</span><span>Tube</span> Application</h1>
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
                        <h3>Cool app in Beta version</h3>
                        <hr/>
                        <p>Simple application, only with Servlets and JSP, exploring the most interesting exploits of the Servlet API and JavaServer pages.</p>
                        <p>MeTube is an application in which you create tubes, with several properties. It has many versions, and you will most probably see it several times.
                            It's a simple web application which has several pages and one object entity.
                        </p>
                    </div>
                </div>
                <div class="row">
                    <div class="col col-md-6">
                        <a href="/tubes/create" class="btn btn-info">Create Tube</a>
                    </div>
                    <div class="col col-md-6">
                        <a href="/tubes/all" class="btn btn-info">All Tubes</a>
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
