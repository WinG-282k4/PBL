
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
            window.location="deleteHost?id="+id;
        }
    }
    </script>
  </head>
  <body>
  <a href="addHost.jsp">Add host</a>
  <c:forEach items="${requestScope.list }" var="data">
    <h1>Host Information</h1>
   
    <p>Host Name: ${data.getHostName()}</p>
    <p>Host ID: ${data.getHostid()}</p>
    <p>Host IP: ${data.getHostIP()}</p>
    <p>Group ID: ${data.getGroupid()}</p>
    <p>Group name: ${data.getGroupname()}</p>
    <p>SNMP: ${data.getSNMP()}</p>
    <p>SNMP_community: ${data.getSNMP_community()}</p>
    <p>SNMP_version: ${data.getSNMP_version()}</p>
    <p>Description: ${data.getDescription()}</p>
    <a href="detailHost?ID=${data.getHostid()}">detail</a>
    <a href="#" onclick="doDelete(${data.getHostid()});">delete</a>
    <a href="updateHost?hostid=${data.getHostid()}">update</a>
    </c:forEach>
  </body>
</html>
 