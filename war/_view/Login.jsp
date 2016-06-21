<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<html>
<head>
<title> Albatross! </title>

<style> 
.error {
	color: red; 
}

td.label {
	text-align: right;
}

button {
	margin-top: 1px;
}

#pageName {
	color: green;
	font-size: 200%;
	text-align: center;
	background-color: black;
	font-variant: small-caps;
}

#logon {
	margin: 50px auto;
	border: 1px solid darkgreen;
	width: 400px;
	padding: 10px;
}
</style>

</head>


<body>
	<div id = pagename> Albatross here! Generate some numbers for an albatross.</div>
		<div id = logon> 
			<table>
			<tr>
			<form action = form action="${pageContext.servletContext.contextPath}/Login" method="post">
			<c:if test="${! empty errorMessage"}>
				<div class ="error"> {$errorMessage}</div>
			</c:if>
			<td class = "label">Username: </td> 
			<td> <input type = "text" name = "username" size = "12"/> </td>
			</tr>
			
			<tr> 
				<td class="label"> Password:</td>
				<td><input type="password" name="password" size="12"/> </td>	
			</tr>
			<tr> 
			<td> <input type = "Submit" name = "submit value = Login"/></td>
			</form>
			</tr>
			
			<form action = "${pageContext.servletContext.contextPath}/CreateAccount" method = "get">
			<tr> 
				<td><input type = "Submit" name = "submit" value = "Create Account"/> </td>
			</tr>
			</form>
			</table>
		</div>
</body>
</html>