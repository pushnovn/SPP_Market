    <%--
      Created by IntelliJ IDEA.
      User: pushn_000
      Date: 23.09.16
      Time: 0:42
      To change this template use File | Settings | File Templates.
    --%>
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@taglib prefix="s" uri="/struts-tags" %>
    <!DOCTYPE html>
    <html lang="en">

    <head>

        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>SPP Market - 404 error</title>

        <!-- CSS -->
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Open+Sans:400,300,300italic,600">
        <link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" href="assets/font-awesome/css/font-awesome.min.css">
        <link rel="stylesheet" href="assets/css/animate.css">
        <link rel="stylesheet" href="assets/css/form-elements.css">
        <link rel="stylesheet" href="assets/css/style.css">
        <link rel="stylesheet" href="assets/css/media-queries.css">

        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->

        <!-- Favicon and touch icons -->
        <link rel="shortcut icon" href="assets/ico/favicon.png">
        <link rel="apple-touch-icon-precomposed" sizes="144x144" href="assets/ico/apple-touch-icon-144-precomposed.png">
        <link rel="apple-touch-icon-precomposed" sizes="114x114" href="assets/ico/apple-touch-icon-114-precomposed.png">
        <link rel="apple-touch-icon-precomposed" sizes="72x72" href="assets/ico/apple-touch-icon-72-precomposed.png">
        <link rel="apple-touch-icon-precomposed" href="assets/ico/apple-touch-icon-57-precomposed.png">

        <style>
            * {
                margin: 0;
                padding: 0;
            }
            html,
            body {
                height: 100%;
            }
            .wrapper {
                display: table;
               /* width: 750px; */
                height: 100%;
                margin: 0 auto;
                font: 14px/20px Arial, sans-serif;
            }
            .content {
                display: table-row;
                height: 100%;
            }
            h1 {
                padding: 40px 20px 30px;
                font: 26px/1.3 Arial, sans-serif;
                text-align: center;
            }
            .footer_f {
                height: 80px;
            }
        </style>

    </head>

    <body>

    <div class="wrapper">

        <div class="content">
            <!-- Top menu -->
            <nav class="navbar navbar-inverse navbar-fixed-top navbar-no-bg" role="navigation">
                <div class="container">
                <!--    <div class="navbar-header">
                        <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#top-navbar-1">
                            <span class="sr-only">Toggle navigation</span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                        </button>
                    </div>
                    <!-- Collect the nav links, forms, and other content for toggling -->
                    <div class="collapse navbar-collapse" id="top-navbar-1">
                        <ul class="nav navbar-nav">
                            <li><a href="index.jsp">Home</a></li>
                            <c:set var="salary" scope="session" value='<%=session.getAttribute("role")%>'/>
                            <c:if test="${salary eq 'Customer'}">
                                <li><a href="customer-dashboard.action">Dashboard</a></li>
                                <li><a href="logout.action">Logout</a></li>
                            </c:if>
                            <c:if test="${salary eq 'Supplier'}">
                                <li><a href="supplier-dashboard.action">Dashboard</a></li>
                                <li><a href="logout.action">Logout</a></li>
                            </c:if>
                            <c:if test="${salary eq 'Admin'}">
                                <li><a href="adminpanel.action">Dashboard</a></li>
                                <li><a href="logout.action">Logout</a></li>
                            </c:if>
                            <c:choose>
                                <c:when test="${salary eq null}">
                                    <li><a href="registration.action">Sign Up</a></li>
                                    <li><a href="authorisation.action">Sign In</a></li>
                                </c:when>
                            </c:choose>
                        </ul>
                    </div>
                </div>
            </nav>

            </br>

            <h1>Ups... 404 error :(</h1>
        </div><!-- .content -->

        <div class="footer">
            <div class="row">
                Developed by Pushnov team. All rights reserved.
            </div>
        </div>

    </div><!-- .wrapper -->

    <!-- Javascript -->
    <script src="assets/js/jquery-1.11.1.min.js"></script>
    <script src="assets/bootstrap/js/bootstrap.min.js"></script>
    <script src="assets/js/jquery.backstretch.min.js"></script>
    <script src="assets/js/wow.min.js"></script>
    <script src="assets/js/retina-1.1.0.min.js"></script>
    <script src="assets/js/waypoints.min.js"></script>
    <script src="assets/js/scripts.js"></script>

    <!--[if lt IE 10]>
    <script src="assets/js/placeholder.js"></script>
    <![endif]-->

    </body></html>
