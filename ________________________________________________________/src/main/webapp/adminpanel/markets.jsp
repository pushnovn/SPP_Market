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
            <a class="navbar-brand" href="index.jsp">Adminpanel</a>
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
                        <a href="adminpanel.action"><i class="fa fa-fw fa-dashboard"></i> Dashboard</a>
                    </li>
                    <li>
                        <a href="adminpanel-users.action"><i class="fa fa-fw fa-users"></i> Users</a>
                    </li>
                    <li>
                        <a href="adminpanel-add-admin.action"><i class="fa fa-fw fa-plus"></i> Add admin</a>
                    </li>
                    <li>
                        <a href="adminpanel-categories.action"><i class="fa fa-fw fa-bookmark"></i> Categories</a>
                    </li>
                    <li>
                        <a href="adminpanel-orders.action"><i class="fa fa-fw fa-usd"></i> Orders</a>
                    </li>
                    <li class="active">
                        <a href="adminpanel-markets.action"><i class="fa fa-fw fa-building"></i> Markets</a>
                    </li>
                </ul>
            </div>
            <!-- /.sidebar-collapse -->
        </div>
        <!-- /.navbar-static-side -->
    </nav>

    <%@include file='markets-add-modal.jsp'%>
    <%@include file='markets-edit-modal.jsp'%>
    <%@include file='markets-delete-modal.jsp'%>

    <div id="page-wrapper">

        <div class="container-fluid">

            <!-- Page Heading -->
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">
                        Markets
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
                    <div class="panel-heading">
                        <button class="btn btn-default btn-sm" onclick="showAddModal()">
                            <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
                            Add new market
                        </button>
                        &middot;
                        <a href="/generateMarketsXLS.action">Generate XLS</a>
                        &middot;
                        <a href="/generateMarketsCSV.action">Generate CSV</a>
                    </div>
                    <div class="panel-body">
                        <div class="table-responsive">
                            <div style="color: red;">
                                <s:property value="errorString"></s:property>
                            </div>
                            <p>Markets list:</p>
                            <table class="table table-striped table-bordered table-hover">
                                <thead>
                                <tr>
                                    <th>#</th>
                                    <th>name</th>
                                    <th>address</th>
                                    <th>actions</th>
                                </tr>
                                </thead>
                                <tbody>
                                <s:iterator value="marketsList" var="market">
                                    <tr>
                                        <td><s:property value="id"></s:property></td>
                                        <td><s:property value="name"></s:property></td>
                                        <td><s:property value="address"></s:property></td>
                                        <td>
                                            <button class="btn btn-link btn-sm"
                                                    id="<s:property value="id"/>"
                                                    name="<s:property value="name"/>"
                                                    address="<s:property value="address"/>"
                                                    onclick="showEditModal(this)">
                                                <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
                                                update
                                            </button>
                                            &middot;
                                            <button class="btn btn-link btn-sm" id_instance="<s:property value="id"/>"
                                                    onclick="showDeleteModal(this)">
                                                <span class="glyphicon glyphicon-minus" aria-hidden="true"></span>
                                                delete
                                            </button>
                                            &middot;
                                            <a href="/generateMarketPDF.action?id=<s:property value="id"/>">PDF</a>
                                                <%--<a href="/generateAnalysePDF.action?id=<s:property value="id"/>">PDF</a>--%>
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
    function showAddModal()
    {
        $('.markets_add_modal').modal();
    }

    function showEditModal(instance)
    {
        $('#markets_edit_id').val($(instance).attr('id'));
        $('#markets_edit_name').val($(instance).attr('name'));
        $('#markets_edit_address').val($(instance).attr('address'));
        $('.markets_edit_modal').modal();
    }

    function showDeleteModal(instance)
    {
        var id = $(instance).attr('id_instance');
        $('#warehouses_delete_id').val(id);
        $('.warehouses_delete_modal').modal();
    }
</script>

</body>

</html>
