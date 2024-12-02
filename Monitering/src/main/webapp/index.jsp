
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
  <c:forEach items="${requestScope.list }" var="data">
    <h1>Device Information</h1>
    <p>Host ID: ${data.getHostid()}</p>
    <p>Host IP: ${data.getHostIP()}</p>
    <p>SNMP: ${data.getSNMP()}</p>
    <p>CPU: ${data.getCPU()}</p>
    <p>Bit Received: ${data.getBitReceive()}</p>
    <p>Bit Sent: ${data.getBitSend()}</p>
    <p>RAM Total: ${data.getRAM_total()}</p>
    <p>RAM Used: ${data.getRAM_used()}</p>
    <p>RAM Utilization: ${data.getRAM_util()}</p>
    <p>Hardware Time: ${data.getTime_hardware()}</p>
    <p>Network Time: ${data.getTime_network()}</p>
    <h2>Disk Information</h2>
    <ul>
      <c:forEach items="${data.getListDisk()}" var="list">
        <li>Name: ${list.name}, Last Value: ${list.lastValue}</li>
      </c:forEach>
    </ul>
    </c:forEach>
  </body>
</html>
 