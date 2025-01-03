
 <%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Device Information</title>
    <link rel="stylesheet" href="CSS/index2.css" />
    <style type="text/css">
    
    
    /* General Styling */
body {
  font-family: Arial, sans-serif;
  margin: 0;
  padding: 0;
  background-color: #f4f4f9;
}

h1, h2 {
  color: #333;
}

/* Navigation Bar */
.navbar {
  background-color: #333;
  overflow: hidden;
}

.navbar ul {
  list-style-type: none;
  margin: 0;
  padding: 0;
}

.navbar ul li {
  float: left;
}

.navbar ul li a {
  display: block;
  color: white;
  padding: 14px 20px;
  text-align: center;
  text-decoration: none;
}

.navbar ul li a:hover {
  background-color: #575757;
}

/* Sections */
.info-section {
  margin: 20px;
  padding: 20px;
  background-color: white;
  border-radius: 8px;
  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
}

.info-table {
  width: 100%;
  border-collapse: collapse;
}

.info-table th, .info-table td {
  padding: 12px;
  text-align: left;
  border-bottom: 1px solid #ddd;
}

.info-table th {
  width: 200px;
  background-color: #f2f2f2;
}

.info-table td {
  background-color: #f9f9f9;
}

/* Disk List */
.disk-list {
  list-style-type: none;
  padding: 0;
}

.disk-list li {
  padding: 8px 0;
  background-color: #f9f9f9;
  margin-bottom: 5px;
  border-left: 5px solid #3498db;
}

.disk-list li:nth-child(even) {
  background-color: #f1f1f1;
}
    </style>
  </head>
  <body>

    <table class="list-table" id="t674c6754bc8a2511921729">
						<tr>
							<th style="font-weight: 700;">Host</th>
							<th style="font-weight: 700;">Name</th>
							<th style="font-weight: 700;">Last Value</th>
							
						</tr>
						<tbody>
							<tr>
								<td class="nowrap">${hostname} </td>
								<td class="nowrap">Name</td>
								<td class="nowrap">${list.getName()}</td>
							</tr>
							<tr>
								<td class="nowrap">${hostname} </td>
								<td class="nowrap">Host ID</td>
								<td class="nowrap">${list.getHostid()}</td>
							</tr>
							<tr>
								<td class="nowrap">${hostname} </td>
								<td class="nowrap">Host IP</td>
								<td class="nowrap">${list.getHostIP()}</td>
							</tr>
							<tr>
								<td class="nowrap">${hostname} </td>							
								<td class="nowrap">SNMP</td>
								<td class="nowrap">${list.getSNMP().getValue()}</td>
							</tr>
							<tr>
								<td class="nowrap">${hostname} </td>							
								<td class="nowrap">CPU</td>
								<td class="nowrap">${list.getCPU().getValue()}</td>
							</tr>
							<tr>
								<td class="nowrap">${hostname} </td>							
								<td class="nowrap">Bit Receive</td>
								<td class="nowrap">${list.getBitReceive().getValue()}</td>
							</tr>
							<tr>
								<td class="nowrap">${hostname} </td>							
								<td class="nowrap">Bit Send</td>
								<td class="nowrap">${list.getBitSend().getValue()}</td>
							</tr>
							<tr>
								<td class="nowrap">${hostname} </td>							
								<td class="nowrap">RAM Total</td>
								<td class="nowrap">${list.getRAM_total().getValue()} </td>
							</tr>
							<tr>
								<td class="nowrap">${hostname} </td>							
								<td class="nowrap">RAM Used</td>
								<td class="nowrap">${list.getRAM_used().getValue()} </td>
							</tr>
							<tr>
								<td class="nowrap">${hostname} </td>							
								<td class="nowrap">RAM Util</td>
								<td class="nowrap">${list.getRAM_util().getValue()} </td>
							</tr>
							<tr>
								<td class="nowrap">${hostname} </td>							
								<td class="nowrap">Time Hardware</td>
								<td class="nowrap">${list.getTime_hardware().getValue()}</td>
							</tr>
							<tr>
								<td class="nowrap">${hostname} </td>							
								<td class="nowrap">Time Network</td>
								<td class="nowrap">${list.getTime_network().getValue()}</td>
							</tr>

							<c:forEach items="${list.getListDisk()}" var="data">
								<tr>
									<td class="nowrap">${hostname} </td>								
									<td class="nowrap">${data.getName()}</td>
									<td class="nowrap">${data.getLastValue()} GB</td>
								</tr>
							</c:forEach>


						</tbody>
					</table>
  </body>
</html>
 