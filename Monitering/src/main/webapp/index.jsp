<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Document</title>
  </head>
  <body>
    <table border="1px" width="50%">
            <tr>
           
                <th>hostID</th>
                <th>hostName</th>
                <th>nameDevide</th>
                <th>CPU</th>
                <th>bitReceive</th>
                <th>bitSend</th>
                <th>RAM</th>
                <th>RAM_used</th>
                <th>RAM_util</th>
                <th>time_Hardware</th>
                <th>time_Netword</th>
                <th>list</th>
            </tr>
            
            <c:forEach items="${requestScope.data}" var="i">
                <tr>
                    <td>${i.hostID}</td>
                    <td>${i.hostName}</td>
                    <td>${i.nameDevide}</td>
                    <td>${i.CPU}</td>
                    <td>${i.bitReceive}</td>
                 
                    <td>${i.bitSend}</td>
                    <td>${i.RAM}</td>
                    <td>${i.RAM_used}</td>
                    <td>${i.RAM_util}</td>
                    <td>${i.time_Hardware}</td>
                    <td>${i.time_Netword}</td>
                    <td>${i.list}</td>
                    
                </tr>
            </c:forEach>
            
        </table>
  </body>
</html>
