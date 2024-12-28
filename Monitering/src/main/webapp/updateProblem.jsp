<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="Model.Problem"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Update Problem</title>
<script type="text/javascript">
	function updateGroupName() {
		const select = document.getElementById("Groupid");
		const selectedOption = select.options[select.selectedIndex];
		const groupName = selectedOption.getAttribute("data-name");
		document.getElementById("Groupname").value = groupName;
	}

	// Call the function when the page loads to set the default value
	window.onload = updateGroupName;
</script>

<style>
/* Body and font settings */
body {
	font-family: Arial, sans-serif;
	margin: 0;
	padding: 0;
	background-color: #f4f4f4;
}

/* Sidebar Styling */
.sidebar {
	position: fixed;
	top: 0;
	left: 0;
	height: 100%;
	width: 220px;
	background-color: #2f4f7f;
	color: white;
	padding-top: 20px;
	box-shadow: 2px 0 5px rgba(0, 0, 0, 0.1);
}

.sidebar a {
	display: block;
	padding: 15px;
	color: white;
	text-decoration: none;
	font-size: 16px;
	margin: 5px 0;
}

.sidebar a:hover {
	background-color: #4a6ea9;
}

/* Content Area */
.content {
	padding: 20px;
}

h1 {
	font-size: 24px;
	font-weight: bold;
	color: #333;
}

form {
	font-size: 16px;
	line-height: 1.6;
}

input[type="text"] {
	width: 100%;
	padding: 8px;
	margin: 5px 0;
	border: 1px solid #ccc;
	border-radius: 4px;
}

input[type="submit"], input[type="reset"] {
	padding: 10px 20px;
	background-color: #0275b8;;
	color: white;
	border: none;
	cursor: pointer;
	margin-top: 10px;
}

input[type="submit"]:hover, input[type="reset"]:hover {
	background-color: #0275b8;;
}

/* Error message */
.error {
	color: red;
	font-weight: bold;
}

/* Responsive Design */
@media ( max-width : 768px) {
	.sidebar {
		width: 100%;
		height: auto;
		position: relative;
	}
	.content {
		margin-left: 0;
	}
}
input[type="text"], select {
    width: 100%; /* Đảm bảo các thẻ có cùng chiều rộng */
    padding: 8px; /* Tương tự padding cho input */
    margin: 5px 0; /* Khoảng cách giữa các phần tử */
    display: block; /* Đảm bảo block layout */
    border: 1px solid #ccc; /* Viền giống input */
    border-radius: 4px; /* Tạo góc bo tròn */
    box-sizing: border-box; /* Bao gồm padding vào kích thước tổng */
    font-size: 14px; /* Kiểu chữ tương tự */
}

input[readonly] {
    background-color: #f5f5f5; /* Màu nền nhẹ cho trường chỉ đọc */
    color: #666; /* Màu chữ nhạt hơn */
}

select {
    background-color: #fff; /* Màu nền trắng */
    -webkit-appearance: none; /* Loại bỏ kiểu mặc định trình duyệt */
    -moz-appearance: none;
    appearance: none;
}

select:focus, input[type="text"]:focus {
    border-color: #4CAF50; /* Màu viền khi được focus */
    outline: none; /* Bỏ viền focus mặc định */
}

</style>
</head>

<body>

	<!-- Sidebar 
    <div class="sidebar">
        <a href="check?action=listgrouphost">List Group Host</a>
        <a href="check?action=addhost">Add Host</a>
        <a href="check?action=problem">List Problem</a>
        <a href="check?action=updatehost">Update Host</a>
        <a href="check?action=problemhostid">Problem Host ID</a>
    </div>
-->
	<!-- Main Content -->

	<div class="content">
		<h1>Update Problem</h1>
		<%
		Problem problem = (Problem) request.getAttribute("problem");
		request.setAttribute("hostid", problem.getHid());
		%>
		<form action="updateProblem" method="post">
			Message: <input type="text" name="message" /> <input type="hidden"
				name="eventid" value="${problem.getEventId() }" readonly /> 
			Name :
			<input type="text" name="name" value="${problem.getName() }" readonly />
			Host ID: <input type="text" name="hostid"
				value="${problem.getHid() }" readonly /> 
			Host Name: <input
				type="text" name="hostname" value="${problem.getHName()}" readonly />
			Severity : <select name="seversity">
				<option value="0" ${problem.getSeverity() =="0" ? "selected" : "" }>Not
					Classified</option>
				<option value="1" ${problem.getSeverity() == '1' ? 'selected' : ''}>Information</option>
				<option value="2" ${problem.getSeverity() == '2' ? 'selected' : ''}>Warning</option>
				<option value="3" ${problem.getSeverity() == '3' ? 'selected' : ''}>Average</option>
				<option value="4" ${problem.getSeverity() == '4' ? 'selected' : ''}>High</option>
				<option value="5" ${problem.getSeverity() == '5' ? 'selected' : ''}>Disaster</option>

			</select> 
			Clock: <input type="text" name="clock"
				value="${problem.getClock() }" readonly /> AckClock: <input
				type="text" name="ackclock" value="${problem.getAckClock()}"
				readonly /> Acknowledged: <input type="checkbox" name="acknowledge"
				value="true" <%=problem.isAcknowledged() ? "checked" : ""%> />
			<table
				style="border: 2px solid black; border-collapse: collapse; width: 100%;">
				<tr style="border: 1px solid black;">
					<th style="border: 1px solid black;">Message</th>
					<th style="border: 1px solid black;">Clock</th>
					<th style="border: 1px solid black;">Old Severity</th>
					<th style="border: 1px solid black;">New Severity</th>
				</tr>
				<c:forEach items="${problem.getAction() }" var="list1">
					<tr style="border: 1px solid black;">
						<td style="border: 1px solid black;">${list1.getMessage()}</td>
						<td style="border: 1px solid black;">${list1.getClock()}</td>
						<td style="border: 1px solid black;"><c:choose>
								<c:when test="${list1.get_old() == 0}">Not Classified</c:when>
								<c:when test="${list1.get_old() == 1}">Information</c:when>
								<c:when test="${list1.get_old() == 2}">Warning</c:when>
								<c:when test="${list1.get_old() == 3}">Average</c:when>
								<c:when test="${list1.get_old() == 4}">High</c:when>
								<c:when test="${list1.get_old() == 5}">Disaster</c:when>
								<c:otherwise>Unknown</c:otherwise>
							</c:choose></td>

						<td style="border: 1px solid black;">
						<c:choose>
								<c:when test="${list1.get_new() == 0}">Not Classified</c:when>
								<c:when test="${list1.get_new() == 1}">Information</c:when>
								<c:when test="${list1.get_new() == 2}">Warning</c:when>
								<c:when test="${list1.get_new() == 3}">Average</c:when>
								<c:when test="${list1.get_new() == 4}">High</c:when>
								<c:when test="${list1.get_new() == 5}">Disaster</c:when>
								<c:otherwise>Unknown</c:otherwise>
							</c:choose></td>
					</tr>
				</c:forEach>
			</table>

			<input type="submit" value="Update" /> <input type="reset"
				value="Reset" />
		</form>
	</div>

</body>
</html>
