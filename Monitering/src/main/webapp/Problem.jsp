<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Device Information</title>
    <script>
        function doDelete(id){
            if(confirm("bạn có chắc chắn muốn xóa id = "+id)){
                window.location="check?action=deletehost&id="+id;
            }
        }
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
            margin-left: 240px; /* Space for the sidebar */
            padding: 20px;
        }

        h1 {
            font-size: 24px;
            font-weight: bold;
            color: #333;
        }

        p {
            font-size: 18px;
            margin-bottom: 10px;
        }

        ul {
            list-style-type: none;
            padding: 0;
        }

        li {
            font-size: 16px;
            margin-bottom: 8px;
        }

        /* Error message */
        .error {
            color: red;
            font-weight: bold;
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
    <!-- Sidebar -->
    <div class="sidebar">
        <a href="check?action=listgrouphost">List Group Host</a>
        <a href="check?action=addhost">Add Host</a>
        <a href="check?action=problem">List Problem</a>
        <a href="check?action=updatehost">Update Host</a>
        <a href="check?action=problemhostid">Problem Host ID</a>
    </div>

    <!-- Main Content -->
    <div class="content">
        <c:forEach items="${requestScope.list }" var="data">
            <h1>Host Information</h1>

            <p>Event ID: ${data.getEventId()}</p>
            <p>Name: ${data.getName()}</p>
            <p>Hostname: ${data.getHid()}</p>
            <p>Host ID: ${data.getHName()}</p>
            <p>Severity: ${data.getSeverity()}</p>
            <p>Clock: ${data.getClock()}</p>
            <p>Acknowledged Clock: ${data.getAckClock()}</p>
            <p>Acknowledged: ${data.isAcknowledged()}</p>

            <h3>Actions</h3>
            <ul>
                <c:forEach items="${data.getAction()}" var="list1">
                    <li>Message: ${list1.getMessage()}, Clock: ${list1.getClock()}, Old Severity: ${list1.get_old()}, New Severity: ${list1.get_new()}</li>
                </c:forEach>
            </ul>

            <br/>
            <a href="check?action=updateproblem&eventid=${data.getEventId()}">Update</a>
        </c:forEach>
    </div>
</body>
</html>
