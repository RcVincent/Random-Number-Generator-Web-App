<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<html> 
	<head> 
		<title>Random Number Generator</title>
		<style type = "text/css">
		.error{
			color:red;
		}
		td.label {
			text-align: right;
		}
		</style>	
	</head>
	
	<body>
		<c:if test="${!empty errorMessage }">
			<div class="error">${errorMessage}</div>
		</c:if>
		
		<form action="${pageContext.servletContext.contextPath}/RNG" method = "post">
			<table> 
				<tr> 
					<td class = "label"> Number: </td>
					<td> <input type = "text" name = "number" value = ${number}/> </td>
				</tr>
			</table>
			<input type = "Submit" name = "submit" value = "Generate!">
		</form>
	</body>

</html>