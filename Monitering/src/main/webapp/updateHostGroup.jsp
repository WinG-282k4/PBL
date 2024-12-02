<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Update Host Group</title>
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
            margin-left: 240px; /* Space for the sidebar */
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
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            margin-top: 10px;
        }

        input[type="submit"]:hover, input[type="reset"]:hover {
            background-color: #45a049;
        }

        /* Responsive Design */
        @media (max-width: 768px) {
            .sidebar {
                width: 100%;
                height: auto;
                position: relative;
            }

            .content {
                margin-left: 0;
            }
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
        <h1>Update Host Group</h1>
        <form action="updateHostGroup" method="post">
            Group ID: <input type="text" name="groupid" value="${data.getId() }" readonly/>
            Group Name: <input type="text" name="groupName" value="${data.getName() }"/>
            <ul>
                <c:forEach items="${data.getHosts()}" var="list1" varStatus="status">
                    <input type="hidden" name="hosts[${status.index}].hostid" value="${list1.getHostid()}"/>
                    <input type="hidden" name="hosts[${status.index}].hostName" value="${list1.getHostName()}"/>
                </c:forEach>
            </ul>

            <input type="submit" value="Update Group"/>
            <input type="reset" value="Reset"/>
        </form>
    </div>

</body>
</html>
