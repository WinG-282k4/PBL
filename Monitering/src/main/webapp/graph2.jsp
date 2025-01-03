
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "java.util.Map, Model.Disk" %>
<%@ page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Graph Visualization</title>
        <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    
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
    <h1>Đồ thị cho ${host.getHostName() }</h1>
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
                        borderColor: 'blue',
                        hoverBorderWidth: 3,
                        hoverBorderColor: '#000',
                        pointRadius: 0, // Hide points
                        pointHoverRadius: 5 // Hide points on hover
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
        }
        	
            List<Disk> diskList = (List<Disk>) request.getAttribute("GraphDisk");
            if (diskList != null && !diskList.isEmpty()) {
                for (Disk disk : diskList) {
                    String diskName = disk.getName();
                    double usedPercentage = 0;
                    try {
                        usedPercentage = Double.parseDouble(disk.getLastValue());
                    } catch (NumberFormatException e) {
                        usedPercentage = 0;  // Default value or error handling
                    }
                    
                    // Sanitize the disk name to make it a valid ID
                    String canvasId = diskName.replaceAll("[^a-zA-Z0-9]", "_"); // Replace non-alphanumeric characters with "_"
            %>
            
            <h2>Disk: <%= diskName %></h2>
            <canvas id="chart-<%= canvasId %>" width="400" height="400"></canvas>
            <script>
                document.addEventListener("DOMContentLoaded", function() {
                    const diskName = "<%= diskName %>";
                    const usedPercentage = <%= usedPercentage %>;
                    const canvasId = 'chart-' + "<%= canvasId %>";
                    
                    console.log("Canvas ID: " + canvasId);  // Debugging the generated canvas ID
                    
                    const ctx = document.getElementById(canvasId);
                    
                    if (ctx) {
                        const context = ctx.getContext('2d');
                        new Chart(context, {
                            type: 'pie',
                            data: {
                                labels: ['Used', 'Free'],
                                datasets: [{
                                    data: [usedPercentage, 100 - usedPercentage],
                                    backgroundColor: ['#FF5733', '#C0C0C0'],
                                    hoverOffset: 4
                                }]
                            },
                            options: {
                                plugins: {
                                    legend: {
                                        position: 'top',
                                    },
                                    tooltip: {
                                        callbacks: {
                                            label: function(tooltipItem) {
                                                return tooltipItem.label + ': ' + tooltipItem.raw + '%';
                                            }
                                        }
                                    }
                                }
                            }
                        });
                    } else {
                        console.log("Canvas not found for ID: " + canvasId);
                    }
                });
            </script>

            <%
                }
            } else {
            %>
            <p>No disk data available.</p>
            <%  }%>
</body>
</html>