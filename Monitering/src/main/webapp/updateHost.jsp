<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Update Host</title>
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
	background-color: #0b2d42;;
}

/* Error message */
.error {
	color: red;
	font-weight: bold;
}

/* Body and font settings */
body {
	font-family: Arial, sans-serif;
	margin: 0;
	padding: 0;
	background-color: #f4f4f4;
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
        <h1>Update Host</h1>
        <form action="updateHost" method="post">
            Host Name: <input type="text" name="hostName" value="${host.getHostName() }" />
            Host ID: <input type="text" name="hostid" value="${host.getHostid() }" readonly />
            Host IP: <input type="text" name="hostIP" value="${host.getHostIP() }" />
            
            Group Name: 
            <select id="Groupid" name="Groupid" onchange="updateGroupName()">
                <c:forEach var="group" items="${list}">
                    <option value="${group.getId()}" data-name="${group.getName()}"
                        ${group.getId() == host.getGroupid() ? 'selected' : ''}>
                        ${group.name}
                    </option>
                </c:forEach>
            </select>
            <br>
            <input type="hidden" id="Groupname" name="Groupname" value="" />

 			<input type="hidden" name="SNMP" value="${host.getSNMP()}" />	
 			SNMP version: <input type="text" name="SNMP_version" value="${host.getSNMP_version() }" readonly/>
            SNMP community: <input type="text" name="SNMP_community" value="${host.getSNMP_community() }" />
            Description: <input type="text" name="Description" value="${host.getDescription() }" />

            <input type="submit" value="Update" />
            <input type="reset" value="Reset" />
        </form>
    </div>

</body>
</html>
