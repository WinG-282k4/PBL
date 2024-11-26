<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>ADD HOST</h1>
	<form action="addHost" method="post">
	Host Name: <input type="text" name="hostName"/>
	Host ID: <input type="text" name="hostid"/>
	Host IP: <input type="text" name="hostIP"/>
	Group ID: <input type="text" name="Groupid"/>
	Group Name: <input type="text" name="Groupname"/>
	SNMP : <input type="text" name="SNMP"/>
	SNMP community : <input type="text" name="SNMP_community"/>
	SNMP version : <input type="text" name="SNMP_version"/>
	Description : <input type="text" name="Description"/>
	<input type="submit" value="Add"/>
	<input type="reset" value="Reset"/>
	</form>
</body>
</html>