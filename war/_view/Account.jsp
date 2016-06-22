<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
	<head>
		<title>Your Account</title>
		<style type = "text/css">
			#PageName {
				color: gold;
				font-size:200%;
				text-align: center;
				background-color: darktblue;
				font-variant: all-caps;
			}
			
			#AccountInfo{
				font-size:120%;
				text-align: right;
				align: center;
			}
			
			table {
				border-spacing: 10px]
			}
		</style>
	</head>

	<body>
		<div id = "PageName">Your Account milud</div>
		<div id = "AccountInfo">
		<table>
			<tr>
				<td class = "label"> First Name: </td>
				<td> ${firstname} </td>
			</tr>
			<tr>
				<td class = "label"> Last Name: </td>
				<td>${lastname}</td>
			</tr>
		
		</table>
		</div>
	</body>

</html>