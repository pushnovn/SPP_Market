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
	<script type="text/javascript" src="assets/js/jquery-2.1.4.min.js"></script>
	<style>
		.vertical-offset-100{
			padding-top:100px;
		}
	</style>
</head>
<body>
<div class="container">
	<div class="row vertical-offset-100">
		<div class="col-md-4 col-md-offset-4">
			<div class="panel panel-default">
				<div class="panel-heading">
                    <h3 class="panel-title">Enter your login to receive your password via email</h3>
				</div>
				<div class="panel-body">
					<form accept-charset="UTF-8" action='sendPasswordToEmail.action' method="POST">
						<fieldset>
							<div class="form-group">
								<input class="form-control" placeholder="Login" name="login" type="text">
							</div>
                            <div style="color: red;">
                                <ul>
                                    <s:iterator value="arrayListOfErrorMessages">
                                        <li><s:property/></li>
                                    </s:iterator>
                                </ul>
                            </div>
                            </br>
							<input class="btn btn-sm btn-primary btn-block" type="submit" value="Get password">
						</fieldset>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- for bootstrap working -->
<script src="assets/bootstrap/js/bootstrap.js"></script>
<!-- //for bootstrap working -->
</body>
</html>