<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>ADD HOST GROUP</h1>
	<form action="addHostGroup" method="post">
	Group ID: <input type="text" name="groupid" value="${data.getID() }" readonly/>
	Group Name: <input type="text" name="groupName" value="${data.getName() }"/>
	<ul>
  <c:forEach items="${data.getHosts()}" var="list1" varStatus="status">
    <input type="hidden" name="hosts[${status.index}].hostid" value="${list1.getHostid()}"/>
    <input type="hidden" name="hosts[${status.index}].hostName" value="${list1.getHostName()}"/>
  </c:forEach>
</ul>

	<input type="submit" value="Add"/>
	<input type="reset" value="Reset"/>
	</form>
</body>
</html>