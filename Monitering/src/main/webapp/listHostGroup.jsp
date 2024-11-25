
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
            window.location="deleteHostGroup?id="+id;
        }
    }
    </script>
  </head>
  <body>
    <a href="addHostGroup.jsp">Add host</a>
  
  <c:forEach items="${requestScope.list }" var="data">
    <h1>Host Information</h1>
   
	<p>Host ID: ${data.getId()}</p>   
    <p>Host Name: ${data.getName()}</p>
    
    <ul>
      <c:forEach items="${data.getHosts()}" var="list1">
        <li>Host ID: ${list1.getHostid()}, Host Name: ${list1.getHostName()}</li>
      </c:forEach>
    </ul>
    <a href="detailHost?ID=${data.getId()}">detail</a>
    <a href="#" onclick="doDelete(${data.getId()});">delete</a>
    <a href="updateHostGroup?groupid=${data.getId()}">update</a>
    </c:forEach>
  </body>
</html>
 