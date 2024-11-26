<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>UPDATE HOST</h1>
	<form action="updateHost" method="post">
	Host Name: <input type="text" name="hostName" value="${host.getHostName() }"/>
	Host ID: <input type="text" name="hostid" value="${host.getHostid() }"/>
	Host IP: <input type="text" name="hostIP" value="${host.getHostIP() }"/>
	Group ID: <input type="text" name="Groupid" value="${host.getGroupid() }"/>
	Group Name: <input type="text" name="Groupname" value="${host.getGroupname() }"/>
	SNMP : <input type="text" name="SNMP" value="${host.getSNMP()}"/>
	SNMP community : <input type="text" name="SNMP_community" value="${host.getSNMP_community() }"/>
	SNMP version : <input type="text" name="SNMP_version" value="${host.getSNMP_version() }"/>
	Description : <input type="text" name="Description" value="${host.getDescription() }"/>
	<input type="submit" value="update"/>
	<input type="reset" value="Reset"/>
	</form>
</body>
</html>