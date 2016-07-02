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



}

#LinkName {



}

#ContactBody {



}

#Content {

}

div.fixed {



}

button {


}

.error {


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

</body>
</html>