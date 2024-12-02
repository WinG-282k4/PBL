<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Search Hosts</title>
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

        table {
            width: 100%;
            border-collapse: collapse;
        }

        table, th, td {
            border: 1px solid black;
        }

        th, td {
            padding: 10px;
            text-align: left;
        }

        th {
            background-color: #f2f2f2;
        }

        .actions a {
            margin-right: 10px;
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
    <script>
        function doDelete(id) {
            if (confirm("Bạn có chắc chắn muốn xóa host với ID = " + id)) {
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
        <h1>Search Hosts</h1>

        <!-- Search Form -->
        <form method="GET" action="getthongtin">
            <div>
                <label for="groupHost">Search by Group Host:</label>
                <input type="text" name="groupHost" id="groupHost" placeholder="Enter Group Host Name" />
            </div>
            <br>
            <div>
                <label for="hostName">Search by Host Name:</label>
                <input type="text" name="hostName" id="hostName" placeholder="Enter Host Name" />
            </div>
            <br>
            <button type="submit">Search</button>
        </form>

        <!-- Search Results -->
        <c:choose>
            <c:when test="${not empty list}">
                <h2>Search Results</h2>
                <table>
                    <thead>
                        <tr>
                            <th>Host Name</th>
                            <th>Host ID</th>
                            <th>Host IP</th>
                            <th>Group Name</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${list}" var="host">
                            <tr>
                                <td>${host.getHostName()}</td>
                                <td>${host.getHostid()}</td>
                                <td>${host.getHostIP()}</td>
                                <td>${host.getGroupname()}</td>
                                <td>
                                    <a href="check?action=graph&ID=${host.getHostid()}">Graph</a>
                                    <a href="check?action=detailDevice&ID=${host.getHostid()}">Item</a>
                                    <a href="#" onclick="doDelete('${host.getHostid()}')">Delete</a>
                                    <a href="check?action=updatehost&hostid=${host.getHostid()}">Update</a>
                                    <a href="check?action=problemhostid&hostid=${host.getHostid()}">Problem</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:when>
            <c:otherwise>
                <p>No hosts found. Please try again.</p>
            </c:otherwise>
        </c:choose>
    </div>
</body>
</html>
