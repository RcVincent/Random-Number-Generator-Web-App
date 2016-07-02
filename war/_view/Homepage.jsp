<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<html>
<head>
<title>Random Number Generator Home</title>
<style type = "text/css">
#PageName {
	color: green;
	font-size: 200%;
	text-align: center;
	background-color: darkblue;
	font-variant: small-caps;
}

#LinkContent {
	float: left;
	border: 1px solid darkgreen;
	width: 400px;
	margin-left:12px;
	margin-top:12px;
	margin-bottom:12px;
}

#LinkName {
	color:darkblue;
	font-size: 150%;
	width:300px;
	margin-left:13px;
	margin-bottom:20px;
}

#ContactBody {
	margin-left:20px;
	margin-top:20px;
	margin-bottom:20px;


}

#Content {
	float:left;
}

div.fixed {
	position: fixed;
	bottom: 10px;
	right: 10px;
}

button {
	margin-top: 3px;
	margin-left: 5px;
	margin-bottom: 5px;
}

.error {
	color: red;
}

td.label {
	text-align: right;
}

</style>
</head>

<body>
	<div id="fb-root"></div>
	<script>
			(function(d, s, id) {
				var js, fjs = d.getElementsByTagName(s)[0];
				if (d.getElementById(id))
					return;
				js = d.createElement(s);
				js.id = id;
				js.src = "//connect.facebook.net/en_US/sdk.js#xfbml=1&version=v2.6";
				fjs.parentNode.insertBefore(js, fjs);
			}(document, 'script', 'facebook-jssdk'));
		</script>
	</div>
	<c:if test="${! empty errorMessage}">
		<div class="error">${errorMessage}</div>
	</c:if>


	<br>
		<div id="LinkContent">
			<div id="LinkName">Your Account</div>
			<div>
				<form action="${pageContext.servletContext.contextPath}/your-account"
					method="post">
					<table>
						<tr>
							<td><input type="Submit" name="submit"
								value="Click to view Account Info" /></td>
						</tr>
					</table>
				</form>
			</div>

		</div>

</html>