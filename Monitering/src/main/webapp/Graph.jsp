<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Map, Model.Disk" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Graph Visualization</title>
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script> <!-- Thêm thư viện Chart.js -->
    <style>
        /* Basic Styling */
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f4;
        }

        /* Sidebar Styling */
        .sidebar {
            position: fixed;
            top: 0;
            left: 0;
            height: 100%;
            width: 220px;
            background-color: #2f4f7f;
            color: white;
            padding-top: 20px;
            box-shadow: 2px 0 5px rgba(0, 0, 0, 0.1);
        }

        .sidebar a {
            display: block;
            padding: 15px;
            color: white;
            text-decoration: none;
            font-size: 16px;
            margin: 5px 0;
        }

        .sidebar a:hover {
            background-color: #4a6ea9;
        }

        /* Content Area */
        .content {
            margin-left: 240px; /* Space for the sidebar */
            padding: 20px;
        }

        h1 {
            font-size: 24px;
            font-weight: bold;
            color: #333;
            text-align: center;
        }

        /* Canvas Styling */
        canvas {
            max-width: 90%;
            margin: 20px auto;
            display: block;
        }

        /* Responsive Design */
        @media (max-width: 768px) {
            .sidebar {
                width: 100%;
                height: auto;
                position: relative;
            }

            .content {
                margin-left: 0;
            }
        }
    </style>
</head>
<body>
    <!-- Sidebar -->
    <div class="sidebar">
        <a href="check?action=listgrouphost">List Group Host</a>
        <a href="check?action=problem">List Problem</a>
        <a href="check?action=addhost">Add Host</a>
        <a href="check?action=updatehost">Update Host</a>
        <a href="check?action=problemhostid">Problem Host ID</a>
    </div>

    <!-- Main Content -->
    <div class="content">
        <h1>Đồ thị cho 1 host</h1>

        <%
            // Lấy danh sách đồ thị từ request attribute
            Map<String, String> graphData = (Map<String, String>) request.getAttribute("GraphData");
        
            if (graphData != null && !graphData.isEmpty()) {
                String itemId = "";
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
            } else {
                // Vẽ biểu đồ tròn cho ổ đĩa
                Disk graphDisk = (Disk) request.getAttribute("GraphDisk");
                String diskName = graphDisk.getName();
                int value = Integer.parseInt(graphDisk.getLastValue());
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
    </div>
</body>
</html>
