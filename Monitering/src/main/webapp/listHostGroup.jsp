<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Search Host Groups</title>
    <script>
        function doDelete(id){
            if(confirm("Bạn có chắc chắn muốn xóa group với ID = "+id)){
                window.location="check?action=deletehostgroup&groupid="+id;
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

        /* Error message */
        .error {
            color: red;
            font-weight: bold;
        }

        /* Search Form Styling */
        .search-form {
            margin-bottom: 20px;
        }

        .search-form input[type="text"] {
            padding: 10px;
            width: 300px;
            font-size: 16px;
        }

        .search-form button {
            padding: 10px 15px;
            font-size: 16px;
            cursor: pointer;
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
        <a href="check?action=problem">List Problem</a>
        <a href="check?action=addhost">Add Host</a>
        <a href="check?action=updatehost">Update Host</a>
        <a href="check?action=problemhostid">Problem Host ID</a>
    </div>

    <!-- Main Content -->
    <div class="content">
        <h1>Search Host Groups</h1>

        <!-- Search Form -->
        <form class="search-form" method="GET" action="getListHostGroup">
            <div>
                <label for="groupHost">Search by Group Name:</label>
                <input type="text" name="groupHost" id="groupHost" placeholder="Enter Group Name" />
            </div>
            <br>
            <button type="submit">Search</button>
        </form>

        <!-- Error message display -->
        <c:if test="${not empty requestScope.error}">
            <div class="error">${requestScope.error}</div>
        </c:if>

        <!-- Search Results -->
        <c:choose>
            <c:when test="${not empty list}">
                <h2>Search Results</h2>
                <c:forEach items="${list}" var="data">
                    <p>GROUP ID: ${data.getId()}</p>
                    <p>GROUP Name: ${data.getName()}</p>
                    <ul>
                        <c:forEach items="${data.getHosts()}" var="host">
                            <li>Host ID: ${host.getHostid()}, Host Name: ${host.getHostName()}</li>
                        </c:forEach>
                    </ul>
                    <a href="#" onclick="doDelete(${data.getId()});">Delete</a> |
                    <a href="check?action=updatehostgroup&groupid=${data.getId()}">Update</a>
                </c:forEach>
            </c:when>
            <c:otherwise>
                <p>No host groups found. Please try again.</p>
            </c:otherwise>
        </c:choose>
    </div>
</body>
</html>
