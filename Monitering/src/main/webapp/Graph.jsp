<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "java.util.Map, Model.Disk" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Graph Visualization</title>
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script> <!-- Thêm thư viện Chart.js -->
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
    
//    if (graphData != null && !graphData.isEmpty()) {
//        out.println("<h3>Graph Data:</h3>");
//        out.println("<pre>");
//        for (Map.Entry<String, String> entry : graphData.entrySet()) {
//            out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
//        }
//        out.println("</pre>");
//   } else {
//        out.println("<p>No Graph Data found.</p>");
//    }
    %>

    <!-- Lặp qua tất cả các item của hostid để vẽ đồ thị -->
    <%
        String itemId = "";
        if (graphData != null && !graphData.isEmpty()) {
            for (Map.Entry<String, String> entry : graphData.entrySet()) {
            	String[] keypart = entry.getKey().split("_");
                String temp = keypart[0]; // Lấy phần từ đầu tiên sau khi tách Key để lấy ID
                String units = keypart[1]; // phần tử thứ 2 chưa đơn vị
                String labels = graphData.get(temp + "_" + units + "_labels");
                String data = graphData.get(temp +  "_" + units + "_data");

                if (temp.equals(itemId)) continue; // Nếu đã có, bỏ qua đồ thị
                else {
                    itemId = temp;
                }

                String itemID = itemId.replace(" ", "_"); // Thay thế khoảng trống bằng dấu gạch dưới

                // Xử lý dữ liệu labels và data thành JSON
                labels = "[" + labels + "]"; // Thêm dấu ngoặc để JSON hợp lệ
                data = "[" + data + "]"; // Thêm dấu ngoặc để JSON hợp lệ
    %>

    <h2>Graph <%= itemId %></h2>
    <canvas id="chart-<%= itemID %>" width="1800" height="400"></canvas>


    <script>
        (function() {
            // Lấy dữ liệu từ JSP
            const unitsRaw = "<%= units %>"; // đơn vị
            const labelsRaw = <%= labels %>; // labels là chuỗi từ JSP
            const dataRaw = <%= data %>; // data là chuỗi từ JSP

            // Chuyển đổi chuỗi labels và data thành mảng
            const labels = labelsRaw.map(label => label.trim()); // Xóa khoảng trắng thừa
            const data = dataRaw.map(value => parseFloat(value)); // Chuyển đổi thành số

            // Lấy context của canvas
            const ctx = document.getElementById('chart-<%= itemID %>').getContext('2d');

            // Cấu hình đồ thị
            new Chart(ctx, {
                type: 'line',
                data: {
                    labels: labels,
                    datasets: [{
                        label: unitsRaw,
                        data: data,
                        backgroundColor: 'rgba(255, 99, 132, 0.6)',
                        borderWidth: 1,
                        borderColor: '#777',
                        hoverBorderWidth: 3,
                        hoverBorderColor: '#000'
                    }]
                },
                options: {
                    title: {
                        display: true,
                        text: 'Graph for Item: <%= itemId %>',
                        fontSize: 25
                    },
                    legend: {
                        display: true,
                        position: 'right',
                        labels: {
                            fontColor: '#000'
                        }
                    },
                    layout: {
                        padding: {
                            left: 50,
                            right: 0,
                            bottom: 0,
                            top: 0
                        }
                    },
                    tooltips: {
                        enabled: true
                    }
                }
            });
        })();
    </script>

    <%
            }
        }else{
        	//Vẽ biểu đồ tròn cho ổ đĩa
        	Disk graphDisk = (Disk) request.getAttribute("GraphDisk");
        	String diskName = graphDisk.getName();
        	int value =Integer.parseInt(graphDisk.getLastValue());
        	out.println(diskName + "\t" + value);
        	
        	%>
    <h2>Graph <%= diskName %></h2>

	<canvas id="chart-<%= diskName %>" width="1800" height="400"></canvas>


<script type="text/javascript">
    (function(){
        // Giả sử giá trị disk đã sử dụng và tên ổ đĩa từ Java
        const diskName = "<%= diskName %>";  // Tên ổ đĩa
        const usedPercentage = <%= value %>;  // Phần trăm đã sử dụng

        // Dữ liệu cho biểu đồ tròn
        const data = {
            labels: ['Used', 'Free'],  // Hai phần: đã sử dụng và còn lại
            datasets: [{
                data: [usedPercentage, 100 - usedPercentage],  // Phần đã sử dụng và phần còn lại
                backgroundColor: ['#FF5733', '#C0C0C0'],  // Màu sắc cho các phần
                hoverOffset: 4
            }]
        };

        // Cấu hình biểu đồ tròn
        const ctx = document.getElementById('chart-<%= diskName %>').getContext('2d');  // Cập nhật ID canvas cho phù hợp
        const myPieChart = new Chart(ctx, {
            type: 'pie',
            data: data,
            options: {
                responsive: true,
                plugins: {
                    legend: {
                        position: 'top',
                    },
                    tooltip: {
                        callbacks: {
                            label: function(tooltipItem) {
                                return tooltipItem.label + ': ' + tooltipItem.raw + '%';  // Hiển thị phần trăm
                            }
                        }
                    }
                }
            }
        });

    })();
</script>

   <%
        }
    %>
</body>
</html>
