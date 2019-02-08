<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <c:import url="templates/head.jsp"/>
    <style type="text/css">
        <%@include file="styles/templatePageStyle.css" %>
        <%@include file="styles/navigation-style.css" %>
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
        <hr class="my-2"/>
        <div class="text-center mt-3">
            <h4 class="h4 text-info">Welcome, <%= request.getSession().getAttribute("username") %></h4>
        </div>
        <hr class="my-4">
        <div class="container">
            ${allTubes}
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