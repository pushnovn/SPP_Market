<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Market service</title>
    <link href="assets/bootstrap/css/bootstrap.css" rel='stylesheet' type='text/css' />
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script type="text/javascript" src="assets/js/jquery-1.11.1.min.js"></script>
    <script type="text/javascript" src="assets/js/jquery-cookie.js"></script>
    <script>
        $(document).ready(function() {
            var idOfActiveTab = $.cookie("idOfActiveTab");
            if (idOfActiveTab != "")
            {
                $( '.tab[data-id="' + idOfActiveTab + '"]' ).addClass('active');
                $('.tab-pane:nth-child('+idOfActiveTab +')').addClass('active');
            } else {
                $('.tab[data-id="1"]').addClass('active');
                $('.tab-pane:nth-child(1)').addClass('active');
            }
            $('.tab').bind('click', function(){
                var id = $(this).attr('data-id');
                $.cookie("idOfActiveTab", id);
            });
        });
    </script>
</head>
<body>
<div class="container">
	<div class="row">
		<div class="span12">
			<div class="" id="loginModal">
				<div class="modal-header">
				<!--	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>-->
                    <a href="index.jsp" class="btn btn-primary" type="submit">Home</a>
					<h3>Register new account here</h3>
                    </br>
                    <a href="signin-page.jsp">
                        Arleady have account? Authorisation here.
                    </a>
				</div>
				<div class="modal-body">
					<div class="well">
						<ul class="nav nav-tabs">
							<li data-id="1" class="tab">
                                <a href="#ascustomer" data-toggle="tab" >
                                    Sign up as client
                                </a>
                            </li>
							<li data-id="2" class="tab">
                                <a href="#assupplier" data-toggle="tab" >
                                    Sign up as company
                                </a>
                            </li>
						</ul>

						<div id="myTabContent" class="tab-content">
							<div class="tab-pane in" id="ascustomer">
								<form class="form-horizontal" action='signup-as-customer.action' method="POST">
									<fieldset>
										&nbsp;
										<div id="legend">
											<legend class="">New client</legend>
										</div>
										<div class="form-group">
											<input class="form-control" placeholder="Login" name="login" type="text">
										</div>
										<div class="form-group">
											<input class="form-control" placeholder="Password" name="password" type="password" value="">
										</div>
										<div class="form-group">
											<input class="form-control" placeholder="Repeat password" name="repeatpass" type="password">
										</div>
                                        <div class="form-group">
                                            <input class="form-control" placeholder="E-mail" name="email" type="email">
                                        </div>
										<div class="form-group">
											<input class="form-control" placeholder="Firstname" name="firstname" type="text" value="">
										</div>
										<div class="form-group">
											<input class="form-control" placeholder="Lastname" name="lastname" type="text">
										</div>
										<div class="form-group">
											<input class="form-control" placeholder="Middlename" name="middlename" type="text" value="">
										</div>
										&nbsp;
										<div class="form-group">
                                            <div style="color: red;">
                                                <ul style="list-style-type: none;">
                                                    <s:iterator value="arrayListOfErrorMessagesForCostumer">
                                                        <li><s:property/></li>
                                                    </s:iterator>
                                                </ul>
                                            </div>
                                            </br>
											<div class="controls">
												<button class="btn btn-primary" type="submit">Create account</button>
											</div>
										</div>
									</fieldset>
								</form>
							</div>

							<div class="tab-pane" id="assupplier">
								<form class="form-horizontal" action='signup-as-supplier.action' method="POST">
									<fieldset>
										&nbsp;
										<div id="legend">
											<legend class="">New company</legend>
										</div>
										<div class="form-group">
											<input class="form-control" placeholder="Login" name="login" type="text">
										</div>
										<div class="form-group">
											<input class="form-control" placeholder="Password" name="password" type="password" value="">
										</div>
                                        <div class="form-group">
                                            <input class="form-control" placeholder="Repeat password" name="repeatpass" type="password">
                                        </div>
                                        <div class="form-group">
                                            <input class="form-control" placeholder="E-mail" name="email" type="email">
                                        </div>
										<div class="form-group">
											<input class="form-control" placeholder="Company name" name="companyName" type="text" value="">
										</div>
										&nbsp;
										<div class="form-group">
                                            <div style="color: red;">
                                                <ul style="list-style-type: none;">
                                                    <s:iterator value="arrayListOfErrorMessagesForSupplier">
                                                        <li><s:property/></li>
                                                    </s:iterator>
                                                </ul>
                                            </div>
                                            </br>
											<div class="controls">
												<button class="btn btn-primary" type="submit">Create account</button>
											</div>
										</div>
									</fieldset>
								</form>
							</div>
						</div>
					</div>
				</div>
                <%--<div>--%>
                    <%--</br>--%>
                    <%--<a href="signin-page.jsp">--%>
                       <%--Arleady have account? Authorisation here.--%>
                    <%--</a>--%>
                <%--</div>--%>
			</div>
		</div>
	</div>
</div>
<!-- for bootstrap working -->
<script src="assets/bootstrap/js/bootstrap.js"></script>
<!-- //for bootstrap working -->
</body>
</html>