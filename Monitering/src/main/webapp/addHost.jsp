<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ADD HOST - Zabbix Style</title>
<style>
    body {
        font-family: Arial, sans-serif;
        background-color: #f5f5f5;
        margin: 0;
        padding: 0;
        display: flex;
        justify-content: center;
        align-items: center;
        height: 100vh;
    }
    .form-container {
        width: 500px;
        padding: 20px;
        background: #fff;
        box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        border-radius: 8px;
        border: 1px solid #ddd;
    }
    .form-container h1 {
        font-size: 24px;
        font-weight: bold;
        margin-bottom: 20px;
        color: #333;
        text-align: center;
    }
    .form-group {
        margin-bottom: 15px;
    }
    .form-group label {
        display: block;
        font-size: 14px;
        color: #555;
        margin-bottom: 5px;
    }
    .form-group input, .form-group select {
        width: 100%;
        padding: 10px;
        font-size: 14px;
        border: 1px solid #ccc;
        border-radius: 4px;
        box-sizing: border-box;
    }
    .form-group input:focus, .form-group select:focus {
        border-color: #4881c2;
        outline: none;
    }
    .form-actions {
        display: flex;
        justify-content: space-between;
        margin-top: 20px;
    }
    .form-actions input {
        padding: 10px 15px;
        font-size: 14px;
        border: none;
        border-radius: 4px;
        cursor: pointer;
    }
    .form-actions input[type="submit"] {
        background-color: #4881c2;
        color: white;
    }
    .form-actions input[type="submit"]:hover {
        background-color: #34679e;
    }
    .form-actions input[type="reset"] {
        background-color: #ccc;
        color: #333;
    }
    .form-actions input[type="reset"]:hover {
        background-color: #999;
    }
</style>
<script type="text/javascript">
    function updateGroupName() {
        const select = document.getElementById("Groupid");
        const selectedOption = select.options[select.selectedIndex];
        const groupName = selectedOption.getAttribute("data-name");
        document.getElementById("Groupname").value = groupName;
    }

    // Gọi hàm khi tải trang để đặt giá trị mặc định
    window.onload = updateGroupName;
</script>
</head>
<body>
    <div class="form-container">
        <h1>ADD HOST</h1>
        <form action="addHost" method="post">
            <div class="form-group">
                <label for="hostName">Host Name</label>
                <input type="text" id="hostName" name="hostName" />
            </div>
            <div class="form-group">
                <label for="hostIP">Host IP</label>
                <input type="text" id="hostIP" name="hostIP" />
            </div>
            <div class="form-group">
                <label for="Groupid">Group Name</label>
                <select id="Groupid" name="Groupid" onchange="updateGroupName()">
                    <c:forEach var="group" items="${list}">
                        <option value="${group.getId()}" data-name="${group.getName()}" 
                            ${group.getId() == host.getGroupid() ? 'selected' : ''}>
                            ${group.name}
                        </option>
                    </c:forEach>
                </select>
                <input type="hidden" id="Groupname" name="Groupname" value="" />
            </div>
            <div class="form-group">
                <label for="SNMP_version">SNMP Version</label>
                <input type="text" id="SNMP_version" name="SNMP_version" value="2" readonly />
            </div>
            <div class="form-group">
                <label for="SNMP_community">SNMP Community</label>
                <input type="text" id="SNMP_community" name="SNMP_community" />
            </div>
            <div class="form-group">
                <label for="Description">Description</label>
                <input type="text" id="Description" name="Description" />
            </div>
            <div class="form-actions">
                <input type="submit" value="Add" />
                <input type="reset" value="Reset" />
            </div>
        </form>
    </div>
</body>
</html>
