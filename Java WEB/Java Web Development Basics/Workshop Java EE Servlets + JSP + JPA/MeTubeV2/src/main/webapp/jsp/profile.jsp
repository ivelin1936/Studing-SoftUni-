<%@ page import="meTube.domain.models.service.UserServiceModel" %>
<%@ page import="meTube.domain.models.service.TubeServiceModel" %>
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
        <% UserServiceModel model = (UserServiceModel) request.getAttribute("model"); %>

        <hr class="my-2"/>
        <div class="text-center mt-3">
            <h4 class="text-info text-center">@<%= model.getUsername() %></h4>
            <h4 class="text-info text-center">(<%= model.getEmail() %>)</h4>
        </div>
        <hr>
        <div class="container-fluid">
            <div class="row d-flex flex-column">
                <table class="table table-hover table-dark">
                    <thead>
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">Title</th>
                        <th scope="col">Author</th>
                        <th scope="col">Actions</th>
                    </tr>
                    </thead>
                    <tbody>
                        <% for (int i = 0; i < model.getTubes().size(); i++) { %>
                        <tr>
                            <td><%= i + 1 %></td>
                            <td><%= model.getTubes().get(i).getTitle() %></td>
                            <td><%= model.getTubes().get(i).getAuthor() %></td>
                            <td><a href="/tube/details?id=<%= model.getTubes().get(i).getId() %>">Details</a></td>
                        </tr>
                        <% } %>
                    </tbody>
                </table>
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