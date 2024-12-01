<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Device Information</title>
    <style>
        /* Basic Styling */
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
            color: #333;
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
            text-align: center;
        }

        /* Table Styling */
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }

        table, th, td {
            border: 1px solid #ddd;
        }

        th, td {
            padding: 12px;
            text-align: left;
        }

        th {
            background-color: #f2f2f2;
            color: #333;
        }

        tr:nth-child(even) {
            background-color: #f9f9f9;
        }

        /* Button Styling */
        .actions {
            margin-top: 15px;
            text-align: center;
        }

        .actions a {
            padding: 8px 12px;
            margin-right: 10px;
            text-decoration: none;
            background-color: #4881c2;
            color: white;
            border-radius: 4px;
            font-size: 14px;
        }

        .actions a:hover {
            background-color: #34679e;
        }

        .delete {
            background-color: #f44336;
        }

        .delete:hover {
            background-color: #d32f2f;
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
    <script>
        function doDelete(id){
            if(confirm("Bạn có chắc chắn muốn xóa ID = " + id)){
                window.location = "check?action=deletehost&id=" + id;
            }
        }
    </script>
</head>
<body>
    <!-- Sidebar -->
    <div class="sidebar">
        <a href="check?action=listgrouphost">List Group Host</a>
        <a href="check?action=problem">List Problem</a>
        <a href="check?action=addhost">Add Host</a>
        <a href="check?action=updatehost">Update Host</a>
        <a href="check?action=problemhostid">Problem Host ID</a>
    </div>

    <!-- Main Content -->
    <div class="content">
        <h1>Host Information</h1>
        
        <c:forEach items="${requestScope.list}" var="data">
            <table>
                <tr>
                    <th>Host Name</th>
                    <td>${data.getHostName()}</td>
                </tr>
                <tr>
                    <th>Host ID</th>
                    <td>${data.getHostid()}</td>
                </tr>
                <tr>
                    <th>Host IP</th>
                    <td>${data.getHostIP()}</td>
                </tr>
                <tr>
                    <th>Group ID</th>
                    <td>${data.getGroupid()}</td>
                </tr>
                <tr>
                    <th>Group Name</th>
                    <td>${data.getGroupname()}</td>
                </tr>
                <tr>
                    <th>SNMP</th>
                    <td>${data.getSNMP()}</td>
                </tr>
                <tr>
                    <th>SNMP Community</th>
                    <td>${data.getSNMP_community()}</td>
                </tr>
                <tr>
                    <th>SNMP Version</th>
                    <td>${data.getSNMP_version()}</td>
                </tr>
                <tr>
                    <th>Description</th>
                    <td>${data.getDescription()}</td>
                </tr>
            </table>

            <div class="actions">
                <a href="check?action=detailDevice&ID=${data.getHostid()}">Detail</a>
                <a href="#" class="delete" onclick="doDelete('${data.getHostid()}')">Delete</a>
                <a href="check?action=updatehost&hostid=${data.getHostid()}">Update</a>
                <a href="check?action=problemhostid&hostid=${data.getHostid()}">Problem</a>
            </div>
        </c:forEach>
    </div>
</body>
</html>
