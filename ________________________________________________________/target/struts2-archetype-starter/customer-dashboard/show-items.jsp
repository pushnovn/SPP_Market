<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Adminpanel</title>

    <!-- Bootstrap Core CSS -->
    <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- MetisMenu CSS -->
    <link href="vendor/metisMenu/metisMenu.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="dist/css/sb-admin-2.css" rel="stylesheet">

    <!-- Morris Charts CSS -->
    <link href="vendor/morrisjs/morris.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body>

<div id="wrapper">

    <!-- Navigation -->
    <nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="index.jsp">Dashboard</a>
        </div>
        <!-- /.navbar-header -->

        <ul class="nav navbar-top-links navbar-right">
            <!-- /.dropdown -->
            <li class="dropdown">
                <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                    <i class="fa fa-user fa-fw"></i> <i class="fa fa-caret-down"></i>
                    <c:set var="salary" scope="session" value='<%=session.getAttribute("login")%>'/>
                    <c:out value="${salary}"/>
                </a>
                <ul class="dropdown-menu dropdown-user">
                    <li><a href="../.."><i class="fa fa-gear fa-home"></i> Go to mainpage</a>
                    </li>
                    <li class="divider"></li>
                    <li><a href="../logout.action"><i class="fa fa-sign-out fa-fw"></i> Logout</a>
                    </li>
                </ul>
                <!-- /.dropdown-user -->
            </li>
            <!-- /.dropdown -->
        </ul>
        <!-- /.navbar-top-links -->

        <div class="navbar-default sidebar" role="navigation">
            <div class="sidebar-nav navbar-collapse">
                <ul class="nav" id="side-menu">
                    <li>
                        <a href="customer-dashboard.action"><i class="fa fa-fw fa-dashboard"></i> Dashboard</a>
                    </li>
                    <li>
                        <a href="customer-dashboard-make-order.action"><i class="fa fa-fw fa-plus"></i> Make order</a>
                    </li>
                    <li class="active">
                        <a href="customer-dashboard-show-items.action"><i class="fa fa-fw fa-bookmark"></i> Show items</a>
                    </li>
                    <li>
                        <a href="customer-dashboard-show-orders.action"><i class="fa fa-fw fa-usd"></i> Show orders</a>
                    </li>
                </ul>
            </div>
            <!-- /.sidebar-collapse -->
        </div>
        <!-- /.navbar-static-side -->
    </nav>

    <%@include file='show-items-add.jsp'%>

    <div id="page-wrapper">

        <div class="container-fluid">

            <!-- Page Heading -->
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">
                        Items
                    </h1>
                </div>
            </div>
            <!-- /.row -->
        </div>
        <!-- /.container-fluid -->

        <div id="page-inner">
            <div class="row">
                <div class="col-md-12">
                    <!-- Table -->
                    <div class="panel-body">
                        <div class="table-responsive">
                            <p>Items list:</p>
                            <table class="table table-striped table-bordered table-hover">
                                <thead>
                                <tr>
                                    <th>#</th>
                                    <th>name</th>
                                    <th>price</th>
                                    <th>count in warehouse</th>
                                    <th>category</th>
                                    <th>market name</th>
                                    <th>market address</th>
                                    <th>supplier</th>
                                    <th>actions</th>
                                </tr>
                                </thead>
                                <tbody>
                                <s:iterator value="itemsList" var="item">
                                    <tr>
                                        <td><s:property value="id"></s:property></td>
                                        <td><s:property value="name"></s:property></td>
                                        <td><s:property value="price"></s:property></td>
                                        <td><s:property value="countInMarket"></s:property></td>
                                        <td><s:property value="category.getName()"></s:property></td>
                                        <td><s:property value="market.getName()"></s:property></td>
                                        <td><s:property value="market.getAddress()"></s:property></td>
                                        <td><s:property value="supplier.getCompanyName()"></s:property></td>
                                        <td>
                                            <button class="btn btn-primary btn-sm"
                                                    id="<s:property value="id"/>"
                                                    name="<s:property value="name"/>"
                                                    categoryName="<s:property value="category.getName()"/>"
                                                    marketName="<s:property value="market.getName()"/>"
                                                    marketAddress="<s:property value="market.getAddress()"/>"
                                                    companyName="<s:property value="supplier.getCompanyName()"/>"
                                                    onclick="showAddModal(this)">
                                                <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
                                                Add in order
                                            </button>
                                        </td>
                                    </tr>
                                </s:iterator>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </div>
    <!-- /#page-wrapper -->

</div>
<!-- /#wrapper -->

<!-- jQuery -->
<script src="vendor/jquery/jquery.min.js"></script>

<!-- Bootstrap Core JavaScript -->
<script src="vendor/bootstrap/js/bootstrap.min.js"></script>

<!-- Metis Menu Plugin JavaScript -->
<script src="vendor/metisMenu/metisMenu.min.js"></script>

<!-- Morris Charts JavaScript -->
<script src="vendor/raphael/raphael.min.js"></script>
<script src="vendor/morrisjs/morris.min.js"></script>
<script src="data/morris-data.js"></script>

<!-- Custom Theme JavaScript -->
<script src="dist/js/sb-admin-2.js"></script>

<script>
    function showAddModal(instance)
    {
        $('#items_add_id').val($(instance).attr('id'));
        $('#items_add_name').val($(instance).attr('name'));
        $('#items_add_category_name').val($(instance).attr('categoryName'));
        $('#items_add_market_name').val($(instance).attr('marketName'));
        $('#items_add_market_address').val($(instance).attr('marketAddress'));
        $('#items_add_company_name').val($(instance).attr('companyName'));
        $('.items_add_modal').modal();
    }
</script>

</body>

</html>