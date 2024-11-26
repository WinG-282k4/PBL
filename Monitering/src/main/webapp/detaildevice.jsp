
 <%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Device Information</title>
  </head>
  <body>

    <h1>Device Information</h1>
    <p>Host ID: ${data.hostid}</p>
    <p>Host IP: ${data.hostIP}</p>
    <p>SNMP: ${data.SNMP}</p>
    <p>CPU: ${data.CPU}</p>
    <p>Bit Received: ${data.bitReceive}</p>
    <p>Bit Sent: ${data.bitSend}</p>
    <p>RAM Total: ${data.RAM_total}</p>
    <p>RAM Used: ${data.RAM_used}</p>
    <p>RAM Utilization: ${data.RAM_util}</p>
    <p>Hardware Time: ${data.getTime_hardware()}</p>
    <p>Network Time: ${data.getTime_network()}</p>

    <h2>Disk Information</h2>
    <ul>
      <c:forEach items="${data.getListDisk()}" var="list">
        <li>Name: ${list.name}, Last Value: ${list.lastValue}</li>
      </c:forEach>
    </ul>
  </body>
</html>
 