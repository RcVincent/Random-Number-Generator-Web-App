<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<html>
<head>
<title>Change Account Name</title>
<style type="text/css">
.error {
color : red;
}

td.label {
	text-align: right;
}

#PageName {
	color: gold;
	font-size: 200%;
	text-align: center;
	background-color:black;
	font-variant: small-caps;

}
#Logon {
	margin: 50px auto;
	border: 1px solid darkgreen;
	width:400px;
	padding: 10px;
}
#Content {
	float: left;
}

</style>
</head>

<body>
	<div id="PageName">Right, we're going to need a different form</div>
	
		<form action = "${pageContext.servletContext.contextPath}/ChangeUsername" method="post">
		<div id = "logon">
		
		<table>
			<tr>
				<c:if test = "${! empty errorMessage }">
					<div class = "error">${errorMessage}</div>
				</c:if>
				<td class = "label">Username: </td>
				<td><input type = "text" name = "username" size = "12"/> </td>
			</tr>
			
			<tr>
				<td class = "label"> New Username: </td>
				<td><input type = "text" name = "newUsername" size = "12"/> </td>
			</tr>
			
			<tr>
				<td class = "label"> Password: </td>
				<td><input type = "password" name = "password" size = "12"/> </td>
			</tr>
			
			<tr>
			<td><input type = "Submit" name = "submit" value = "Change Username"/> </td>
				</form>
				<form action = "${pageContext.servletContext.contextPath}/Account" method = "post">
				<td><input type = "Submit" name = "submit" value = "Back" /></td></form>
			</tr>
		</table>
		</div>
</body>
</html>