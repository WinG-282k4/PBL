<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "java.util.List, java.util.Map" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Graph Visualization</title>
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    <style>
        canvas {
            max-width: 90%;
            margin: 20px auto;
            display: block;
        }
    </style>
</head>
<body>
    <h1>Đồ thị cho 1 host</h1>
    <%
        // Lấy danh sách đồ thị từ request attribute
        Map<String, String> graphData = (Map<String, String>) request.getAttribute("GraphData");

        if (graphData != null && !graphData.isEmpty()) {
            out.println("<h3>Graph Data:</h3>");
            out.println("<pre>");
            for (Map.Entry<String, String> entry : graphData.entrySet()) {
                out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
            }
            out.println("</pre>");
        } else {
            out.println("<p>No Graph Data found.</p>");
        }
    %>
	
    <!-- Lặp qua tất cả các item của hostid để vẽ đồ thị -->
    <%
        String itemId = "";
        if (graphData != null && !graphData.isEmpty()) {
            for (Map.Entry<String, String> entry : graphData.entrySet()) {
                String temp = entry.getKey().split("_")[0];
                String labels = graphData.get(temp + "_labels");
                String data = graphData.get(temp + "_data");
                
                if(temp.equals(itemId)) continue; // Nếu đã có, lấy labels
                else {
                    itemId = temp;
                }
                
                String itemID = itemId.replace(" ", "_");  // Thay thế khoảng trống bằng dấu gạch dưới

    // Render đồ thị cho từng item
    %>
    
    <h2>Graph for Item: <%= itemId %></h2>
//run
    <canvas id="chart-<%= itemID %>" width="800" height="400"></canvas>
//run
    
    <script>
    // Lấy dữ liệu từ JSP
    const labelsRaw = "<%= labels %>"; // labels là chuỗi từ JSP
    const dataRaw = "<%= data %>"; // data là chuỗi từ JSP
    
    // Chuyển labels từ chuỗi thành mảng Date (ISO-8601)
    const labels = JSON.parse('[' + labelsRaw + ']').map(timeStr => {
        return new Date('1970-01-01T' + timeStr + 'Z'); // Chuyển đổi chuỗi thời gian thành đối tượng Date
    });
    
    // Chuyển data từ chuỗi thành mảng số
    const data = JSON.parse('[' + dataRaw + ']');
    
    // Lấy context của canvas
    let myChart = document.getElementById('chart-<%= itemID %>').getContext('2d');
    
    // Cấu hình đồ thị
    let massPopChart = new Chart(myChart, {
      type:'line', // bar, horizontalBar, pie, line, doughnut, radar, polarArea
      data:{
        labels:labels,
        datasets:[{
          label:'%',
          data:data,
          //backgroundColor:'green',
          backgroundColor:[
            'rgba(255, 99, 132, 0.6)',
          ],
          borderWidth:1,
          borderColor:'#777',
          hoverBorderWidth:3,
          hoverBorderColor:'#000'
        }]
      },
      options:{
        title:{
          display:true,
          text:'Largest Cities In Massachusetts',
          fontSize:25
        },
        legend:{
          display:true,
          position:'right',
          labels:{
            fontColor:'#000'
          }
        },
        layout:{
          padding:{
            left:50,
            right:0,
            bottom:0,
            top:0
          }
        },
        tooltips:{
          enabled:true
        }
      }
    });
</script>

    <%
            }
        }
    %>
</body>
</html>
