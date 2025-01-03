<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Document</title>
<script src="../../index2.js"></script>
<script src="../../index3.js"></script>
<link rel="stylesheet" href="index2.css" />
<style>
:root {
	--severity-color-na-bg: #97aab3;
	--severity-color-info-bg: #7499ff;
	--severity-color-warning-bg: #ffc859;
	--severity-color-average-bg: #ffa059;
	--severity-color-high-bg: #e97659;
	--severity-color-disaster-bg: #e45959;
}

.na-bg, .na-bg input[type="radio"]:checked+label, .na-bg:before,
	.flh-na-bg, .status-na-bg, .status-na-bg:before {
	background-color: #97aab3;
}

.info-bg, .info-bg input[type="radio"]:checked+label, .info-bg:before,
	.flh-info-bg, .status-info-bg, .status-info-bg:before {
	background-color: #7499ff;
}

.warning-bg, .warning-bg input[type="radio"]:checked+label, .warning-bg:before,
	.flh-warning-bg, .status-warning-bg, .status-warning-bg:before {
	background-color: #ffc859;
}

.average-bg, .average-bg input[type="radio"]:checked+label, .average-bg:before,
	.flh-average-bg, .status-average-bg, .status-average-bg:before {
	background-color: #ffa059;
}

.high-bg, .high-bg input[type="radio"]:checked+label, .high-bg:before,
	.flh-high-bg, .status-high-bg, .status-high-bg:before {
	background-color: #e97659;
}

.disaster-bg, .disaster-bg input[type="radio"]:checked+label,
	.disaster-bg:before, .flh-disaster-bg, .status-disaster-bg,
	.status-disaster-bg:before {
	background-color: #e45959;
}
/* Tạo màu sắc riêng cho từng mức độ theo bảng màu trong ảnh */
.severity-not-classified {
	background-color: #99A9B0; /* Màu xám nhạt */
	color: #000; /* Màu chữ đen */
}

.severity-information {
	background-color: #5A8DEF; /* Màu xanh dương */
	color: #000; /* Màu chữ đen */
}

.severity-warning {
	background-color: #F4CB5B; /* Màu vàng */
	color: #000; /* Màu chữ đen */
}

.severity-average {
	background-color: #F3A256; /* Màu cam nhạt */
	color: #000; /* Màu chữ đen */
}

.severity-high {
	background-color: #E78264; /* Màu cam đậm */
	color: #000; /* Màu chữ đen */
}

.severity-disaster {
	background-color: #E75C4B; /* Màu đỏ */
	color: #000; /* Màu chữ đen */
}

.severity-unknown {
	background-color: #9e9e9e; /* Màu xám cho trường hợp không xác định */
	color: #fff; /* Màu chữ trắng */
}
.box {
            display: inline-block;
            padding: 2px 3px; /* Giảm kích thước hộp */
            border-radius: 3px; /* Bo góc nhẹ */
            color: white;
            font-size: 12px; /* Giảm kích thước chữ */
            font-weight: bold;
            text-align: center;
        }

         .low-severity {
            background-color: #34af67; /* Màu xanh đậm và không sáng */
        }

        .high-severity {
            background-color: #d64e4e; /* Màu đỏ đậm và không sáng */
        }
/* Tùy chỉnh thêm cho kiểu chữ và kích thước */
</style>
</head>
<body>
	<div class="wrapper">
		<header class="header-title">
			<nav class="sidebar-nav-toggle" role="navigation"
				aria-label="Sidebar control">
				<button type="button" class="btn-icon zi-menu" title="Show sidebar"
					id="sidebar-button-toggle"></button>
			</nav>
			<div>
				<h1 id="page-title-general">Global view</h1>
			</div>
		</header>
		<main>
			<div class="dashboard is-ready">
				<div class="dashboard-navigation">
					<div class="dashboard-navigation-tabs">
						<ul class="sortable sortable-disabled">
							<li tabindex="0" class="sortable-item" style="left: 0px">
								<div class="selected-tab">
									<span title="Page 1">Page 1</span>
									<button type="button" title="Actions" aria-expanded="false"
										aria-haspopup="true"
										class="btn-icon zi-more btn-dashboard-page-properties"></button>
								</div>
							</li>
						</ul>
					</div>
					<div class="dashboard-navigation-controls">
						<button type="button"
							class="btn-icon zi-chevron-left btn-dashboard-previous-page"
							title="Previous page" disabled=""></button>
						<button type="button"
							class="btn-icon zi-chevron-right btn-dashboard-next-page"
							title="Next page" disabled="" style="margin-right: 0px"></button>
						<button type="button"
							class="btn-dashboard-toggle-slideshow btn-alt slideshow-state-started">
							<span class="slideshow-state-stopped">Start slideshow</span><span
								class="slideshow-state-started">Stop slideshow</span>
						</button>
					</div>
				</div>
				<div class="dashboard-grid" style="height: 770px">
					<div class="dashboard-widget-placeholder display-none">
						<div class="dashboard-widget-placeholder-box">
							<div class="dashboard-widget-placeholder-label">
								<div>
									<a href="javascript:void(0)">Add a new widget</a>
								</div>
							</div>
						</div>
					</div>
					<div class="dashboard-grid-widget"
						style="min-width: 1.38889%; min-height: 70px; left: 0%; top: 0px; width: 100%; height: 280px;">
						<div class="dashboard-grid-widget-container">
							<div class="dashboard-grid-widget-header">
								<h4>Top hosts by CPU utilization</h4>
								<ul class="dashboard-grid-widget-actions">
									<li>
										<button type="button" title="Edit"
											class="btn-icon zi-cog-filled js-widget-edit"></button>
									</li>
									<li>
										<button type="button" title="Actions" aria-expanded="false"
											aria-haspopup="true"
											class="btn-icon zi-more js-widget-action"></button>
									</li>
								</ul>
							</div>
							<div
								class="dashboard-grid-widget-contents dashboard-widget-tophosts">
								<div class="dashboard-grid-widget-messages"></div>
								<div class="dashboard-grid-widget-body">
									<table class="list-table" id="t674c6754bc8a2511921729">
										<thead>
											<tr>
												<th>Host ID</th>
												<th>Host Name</th>
												<th>Group name</th>
												<th>SNMP Version</th>
												<th>Interface</th>
												<th>Community</th>
												<th>Availability</th>
												<th>Description</th>

											</tr>
										</thead>
										<c:forEach items="${requestScope.listhost}" var="data">
											<tbody>
												<tr>

													<td class="nowrap">${data.getHostid()}</td>
													<td class="nowrap">${data.getHostName()}</td>
													<td class="nowrap">${data.getGroupname()}</td>
													<td class="nowrap">${data.getSNMP_version()}</td>
													<td class="nowrap">${data.getHostIP()}</td>
													<td class="nowrap">${data.getSNMP_community()}</td>
													<td class="nowrap">
														<!-- Sử dụng JSTL để kiểm tra giá trị severity và thay đổi màu sắc -->
														<c:choose>
															<c:when test="${data.getSNMP() == 1}">
																<div class="box low-severity">SNMP</div>
															</c:when>
															<c:when test="${data.getSNMP() == 2}">
																<div class="box high-severity">SNMP</div>
															</c:when>
															
														</c:choose>
													</td>
													<td class="nowrap">${data.getDescription()}</td>

												</tr>
											</tbody>
										</c:forEach>
									</table>
								</div>
								<div class="dashboard-grid-widget-debug"></div>
							</div>
						</div>
					</div>
					<div class="dashboard-grid-widget"
						style="min-width: 1.38889%; min-height: 70px; left: 0%; top: 280px; width: 100%; height: 140px;">
						<div class="dashboard-grid-widget-container">
							<div class="dashboard-grid-widget-header">
								<h4>Problems by severity</h4>
								<ul class="dashboard-grid-widget-actions">
									<li>
										<button type="button" title="Edit"
											class="btn-icon zi-cog-filled js-widget-edit"></button>
									</li>
									<li>
										<button type="button" title="Actions" aria-expanded="false"
											aria-haspopup="true"
											class="btn-icon zi-more js-widget-action"></button>
									</li>
								</ul>
							</div>
							<div
								class="dashboard-grid-widget-contents dashboard-widget-problemsbysv no-padding">
								<div class="dashboard-grid-widget-messages"></div>
								<div class="dashboard-grid-widget-body">
									<div
										class="by-severity-widget totals-list totals-list-horizontal">
										<div class="disaster-bg">
											<span class="count">${rank0 }</span><span class="name"
												title="Disaster">Disaster</span>
										</div>
										<div class="high-bg">
											<span class="count"><a class="link-action"
												data-hintbox-contents='<table class="list-table" id="t674e48beb573d777171626"><thead><tr><th class="right">Time<span class="arrow-down"></span></th><th class="timeline-th"></th><th class="timeline-th"></th><th>Info</th><th>Host</th><th>Problem</th><th>Duration</th><th>Update</th><th>Actions</th><th>Tags</th></tr></thead><tbody><tr><td class="timeline-date"><a href="tr_events.php?triggerid=24946&amp;amp;eventid=7638">04:05:39</a></td><td class="timeline-axis timeline-dot"></td><td class="timeline-td"></td><td></td><td><a class="link-action wordbreak" data-menu-popup="{&amp;quot;type&amp;quot;:&amp;quot;host&amp;quot;,&amp;quot;data&amp;quot;:{&amp;quot;hostid&amp;quot;:&amp;quot;10644&amp;quot;}}" aria-expanded="false" aria-haspopup="true" role="button" href="javascript:void(0)">May_hoa</a></td><td class="high-bg wordbreak">Unavailable by ICMP ping</td><td>2h 48m 59s</td><td><a class="link-alt" data-eventid="7638" onclick="acknowledgePopUp({eventids: [this.dataset.eventid]}, this);" role="button" href="javascript:void(0)">Update</a></td><td></td><td><span class="tag" data-hintbox-contents="class: os" data-hintbox="1" data-hintbox-static="1">class: os</span><span class="tag" data-hintbox-contents="component: health" data-hintbox="1" data-hintbox-static="1">component: health</span><span class="tag" data-hintbox-contents="component: network" data-hintbox="1" data-hintbox-static="1">component: network</span><button type="button" class="btn-icon zi-more" data-hintbox-contents="&amp;lt;span class=&amp;quot;tag&amp;quot; data-hintbox-contents=&amp;quot;class: os&amp;quot; data-hintbox=&amp;quot;1&amp;quot; data-hintbox-static=&amp;quot;1&amp;quot;&amp;gt;class: os&amp;lt;/span&amp;gt;&amp;lt;span class=&amp;quot;tag&amp;quot; data-hintbox-contents=&amp;quot;component: health&amp;quot; data-hintbox=&amp;quot;1&amp;quot; data-hintbox-static=&amp;quot;1&amp;quot;&amp;gt;component: health&amp;lt;/span&amp;gt;&amp;lt;span class=&amp;quot;tag&amp;quot; data-hintbox-contents=&amp;quot;component: network&amp;quot; data-hintbox=&amp;quot;1&amp;quot; data-hintbox-static=&amp;quot;1&amp;quot;&amp;gt;component: network&amp;lt;/span&amp;gt;&amp;lt;span class=&amp;quot;tag&amp;quot; data-hintbox-contents=&amp;quot;scope: availability&amp;quot; data-hintbox=&amp;quot;1&amp;quot; data-hintbox-static=&amp;quot;1&amp;quot;&amp;gt;scope: availability&amp;lt;/span&amp;gt;&amp;lt;span class=&amp;quot;tag&amp;quot; data-hintbox-contents=&amp;quot;target: windows&amp;quot; data-hintbox=&amp;quot;1&amp;quot; data-hintbox-static=&amp;quot;1&amp;quot;&amp;gt;target: windows&amp;lt;/span&amp;gt;" data-hintbox="1" data-hintbox-class="hintbox-wrap" data-hintbox-static="1"></button></td></tr><tr class="hover-nobg"><td class="timeline-date"><h4>Today</h4></td><td class="timeline-axis timeline-dot-big"></td><td class="timeline-td"></td><td colspan="7"></td></tr><tr><td class="timeline-date"><a href="tr_events.php?triggerid=25129&amp;amp;eventid=5974">2024-12-01 22:51:58</a></td><td class="timeline-axis timeline-dot"></td><td class="timeline-td"></td><td></td><td><a class="link-action wordbreak" data-menu-popup="{&amp;quot;type&amp;quot;:&amp;quot;host&amp;quot;,&amp;quot;data&amp;quot;:{&amp;quot;hostid&amp;quot;:&amp;quot;10644&amp;quot;}}" aria-expanded="false" aria-haspopup="true" role="button" href="javascript:void(0)">May_hoa</a></td><td class="high-bg wordbreak">Interface ethernet_8(Local Area Connection* 7-QoS Packet Scheduler-0000): Link down</td><td>1d 8h 2m</td><td><a class="link-alt" data-eventid="5974" onclick="acknowledgePopUp({eventids: [this.dataset.eventid]}, this);" role="button" href="javascript:void(0)">Update</a></td><td class="nowrap"><span class="icon zi-check color-positive" title="Acknowledged"></span><button type="button" class="btn-icon zi-alert-with-content" data-content="1" aria-label="1 message" data-hintbox-contents="&amp;lt;table class=&amp;quot;list-table&amp;quot; id=&amp;quot;t674e48beb55b5782191290&amp;quot;&amp;gt;&amp;lt;thead&amp;gt;&amp;lt;tr&amp;gt;&amp;lt;th&amp;gt;Time&amp;lt;/th&amp;gt;&amp;lt;th&amp;gt;User&amp;lt;/th&amp;gt;&amp;lt;th&amp;gt;Message&amp;lt;/th&amp;gt;&amp;lt;/tr&amp;gt;&amp;lt;/thead&amp;gt;&amp;lt;tbody&amp;gt;&amp;lt;tr&amp;gt;&amp;lt;td&amp;gt;2024-12-02 14:28:07&amp;lt;/td&amp;gt;&amp;lt;td&amp;gt;Admin (Zabbix Administrator)&amp;lt;/td&amp;gt;&amp;lt;td&amp;gt;sdfdf&amp;lt;/td&amp;gt;&amp;lt;/tr&amp;gt;&amp;lt;/tbody&amp;gt;&amp;lt;/table&amp;gt;" data-hintbox="1" data-hintbox-class="hintbox-wrap-horizontal" data-hintbox-static="1"></button><button type="button" class="btn-icon zi-arrow-up-small color-negative" aria-label="Severity increased" data-hintbox-contents="&amp;lt;table class=&amp;quot;list-table&amp;quot; id=&amp;quot;t674e48beb563d323913513&amp;quot;&amp;gt;&amp;lt;thead&amp;gt;&amp;lt;tr&amp;gt;&amp;lt;th&amp;gt;Time&amp;lt;/th&amp;gt;&amp;lt;th&amp;gt;User&amp;lt;/th&amp;gt;&amp;lt;th&amp;gt;Severity changes&amp;lt;/th&amp;gt;&amp;lt;/tr&amp;gt;&amp;lt;/thead&amp;gt;&amp;lt;tbody&amp;gt;&amp;lt;tr&amp;gt;&amp;lt;td&amp;gt;2024-12-02 14:28:07&amp;lt;/td&amp;gt;&amp;lt;td&amp;gt;Admin (Zabbix Administrator)&amp;lt;/td&amp;gt;&amp;lt;td&amp;gt;Average&amp;amp;nbsp;&amp;amp;rArr;&amp;amp;nbsp;High&amp;lt;/td&amp;gt;&amp;lt;/tr&amp;gt;&amp;lt;/tbody&amp;gt;&amp;lt;/table&amp;gt;" data-hintbox="1" data-hintbox-class="hintbox-wrap-horizontal" data-hintbox-static="1"></button><button type="button" class="btn-icon zi-bullet-right-with-content" data-content="1" aria-label="1 action" data-hintbox-preload="{&amp;quot;type&amp;quot;:&amp;quot;eventactions&amp;quot;,&amp;quot;data&amp;quot;:{&amp;quot;eventid&amp;quot;:&amp;quot;5974&amp;quot;}}" data-hintbox-contents="" data-hintbox="1" data-hintbox-class="hintbox-wrap-horizontal" data-hintbox-static="1"></button></td><td><span class="tag" data-hintbox-contents="class: os" data-hintbox="1" data-hintbox-static="1">class: os</span><span class="tag" data-hintbox-contents="component: network" data-hintbox="1" data-hintbox-static="1">component: network</span><span class="tag" data-hintbox-contents="description: Local Area Connection* 7-QoS Packet Scheduler-0000" data-hintbox="1" data-hintbox-static="1">description: Local Area Connection* 7-QoS Packet Scheduler-0000</span><button type="button" class="btn-icon zi-more" data-hintbox-contents="&amp;lt;span class=&amp;quot;tag&amp;quot; data-hintbox-contents=&amp;quot;class: os&amp;quot; data-hintbox=&amp;quot;1&amp;quot; data-hintbox-static=&amp;quot;1&amp;quot;&amp;gt;class: os&amp;lt;/span&amp;gt;&amp;lt;span class=&amp;quot;tag&amp;quot; data-hintbox-contents=&amp;quot;component: network&amp;quot; data-hintbox=&amp;quot;1&amp;quot; data-hintbox-static=&amp;quot;1&amp;quot;&amp;gt;component: network&amp;lt;/span&amp;gt;&amp;lt;span class=&amp;quot;tag&amp;quot; data-hintbox-contents=&amp;quot;description: Local Area Connection* 7-QoS Packet Scheduler-0000&amp;quot; data-hintbox=&amp;quot;1&amp;quot; data-hintbox-static=&amp;quot;1&amp;quot;&amp;gt;description: Local Area Connection* 7-QoS Packet Scheduler-0000&amp;lt;/span&amp;gt;&amp;lt;span class=&amp;quot;tag&amp;quot; data-hintbox-contents=&amp;quot;interface: ethernet_8&amp;quot; data-hintbox=&amp;quot;1&amp;quot; data-hintbox-static=&amp;quot;1&amp;quot;&amp;gt;interface: ethernet_8&amp;lt;/span&amp;gt;&amp;lt;span class=&amp;quot;tag&amp;quot; data-hintbox-contents=&amp;quot;scope: availability&amp;quot; data-hintbox=&amp;quot;1&amp;quot; data-hintbox-static=&amp;quot;1&amp;quot;&amp;gt;scope: availability&amp;lt;/span&amp;gt;&amp;lt;span class=&amp;quot;tag&amp;quot; data-hintbox-contents=&amp;quot;target: windows&amp;quot; data-hintbox=&amp;quot;1&amp;quot; data-hintbox-static=&amp;quot;1&amp;quot;&amp;gt;target: windows&amp;lt;/span&amp;gt;" data-hintbox="1" data-hintbox-class="hintbox-wrap" data-hintbox-static="1"></button></td></tr><tr class="hover-nobg"><td class="timeline-date"><h4>December</h4></td><td class="timeline-axis timeline-dot-big"></td><td class="timeline-td"></td><td colspan="7"></td></tr><tr><td class="timeline-date"><a href="tr_events.php?triggerid=25147&amp;amp;eventid=5402">2024-11-30 16:33:39</a></td><td class="timeline-axis timeline-dot"></td><td class="timeline-td"></td><td></td><td><a class="link-action wordbreak" data-menu-popup="{&amp;quot;type&amp;quot;:&amp;quot;host&amp;quot;,&amp;quot;data&amp;quot;:{&amp;quot;hostid&amp;quot;:&amp;quot;10645&amp;quot;}}" aria-expanded="false" aria-haspopup="true" role="button" href="javascript:void(0)">Serverwindow8</a></td><td class="high-bg wordbreak">Unavailable by ICMP ping</td><td>2d 14h 20m</td><td><a class="link-alt" data-eventid="5402" onclick="acknowledgePopUp({eventids: [this.dataset.eventid]}, this);" role="button" href="javascript:void(0)">Update</a></td><td></td><td><span class="tag" data-hintbox-contents="class: os" data-hintbox="1" data-hintbox-static="1">class: os</span><span class="tag" data-hintbox-contents="component: health" data-hintbox="1" data-hintbox-static="1">component: health</span><span class="tag" data-hintbox-contents="component: network" data-hintbox="1" data-hintbox-static="1">component: network</span><button type="button" class="btn-icon zi-more" data-hintbox-contents="&amp;lt;span class=&amp;quot;tag&amp;quot; data-hintbox-contents=&amp;quot;class: os&amp;quot; data-hintbox=&amp;quot;1&amp;quot; data-hintbox-static=&amp;quot;1&amp;quot;&amp;gt;class: os&amp;lt;/span&amp;gt;&amp;lt;span class=&amp;quot;tag&amp;quot; data-hintbox-contents=&amp;quot;component: health&amp;quot; data-hintbox=&amp;quot;1&amp;quot; data-hintbox-static=&amp;quot;1&amp;quot;&amp;gt;component: health&amp;lt;/span&amp;gt;&amp;lt;span class=&amp;quot;tag&amp;quot; data-hintbox-contents=&amp;quot;component: network&amp;quot; data-hintbox=&amp;quot;1&amp;quot; data-hintbox-static=&amp;quot;1&amp;quot;&amp;gt;component: network&amp;lt;/span&amp;gt;&amp;lt;span class=&amp;quot;tag&amp;quot; data-hintbox-contents=&amp;quot;scope: availability&amp;quot; data-hintbox=&amp;quot;1&amp;quot; data-hintbox-static=&amp;quot;1&amp;quot;&amp;gt;scope: availability&amp;lt;/span&amp;gt;&amp;lt;span class=&amp;quot;tag&amp;quot; data-hintbox-contents=&amp;quot;target: windows&amp;quot; data-hintbox=&amp;quot;1&amp;quot; data-hintbox-static=&amp;quot;1&amp;quot;&amp;gt;target: windows&amp;lt;/span&amp;gt;" data-hintbox="1" data-hintbox-class="hintbox-wrap" data-hintbox-static="1"></button></td></tr></tbody></table>'
												data-hintbox="1" data-hintbox-static="1" role="button"
												href="javascript:void(0)">${rank1 }</a></span><span class="name"
												title="High">High</span>
										</div>
										<div class="average-bg">
											<span class="count"><a class="link-action"
												data-hintbox-contents='<table class="list-table" id="t674e48beb5cb1465914733"><thead><tr><th class="right">Time<span class="arrow-down"></span></th><th class="timeline-th"></th><th class="timeline-th"></th><th>Info</th><th>Host</th><th>Problem</th><th>Duration</th><th>Update</th><th>Actions</th><th>Tags</th></tr></thead><tbody><tr><td class="timeline-date"><a href="tr_events.php?triggerid=25097&amp;amp;eventid=5973">2024-12-01 22:51:58</a></td><td class="timeline-axis timeline-dot"></td><td class="timeline-td"></td><td></td><td><a class="link-action wordbreak" data-menu-popup="{&amp;quot;type&amp;quot;:&amp;quot;host&amp;quot;,&amp;quot;data&amp;quot;:{&amp;quot;hostid&amp;quot;:&amp;quot;10644&amp;quot;}}" aria-expanded="false" aria-haspopup="true" role="button" href="javascript:void(0)">May_hoa</a></td><td class="average-bg wordbreak">Interface ethernet_7(Local Area Connection* 7-WFP Native MAC Layer LightWeight Filter): Link down</td><td>1d 8h 2m</td><td><a class="link-alt" data-eventid="5973" onclick="acknowledgePopUp({eventids: [this.dataset.eventid]}, this);" role="button" href="javascript:void(0)">Update</a></td><td></td><td><span class="tag" data-hintbox-contents="class: os" data-hintbox="1" data-hintbox-static="1">class: os</span><span class="tag" data-hintbox-contents="component: network" data-hintbox="1" data-hintbox-static="1">component: network</span><span class="tag" data-hintbox-contents="description: Local Area Connection* 7-WFP Native MAC Layer LightWeight Filter" data-hintbox="1" data-hintbox-static="1">description: Local Area Connection* 7-WFP Native MAC Layer LightWeight Filter</span><button type="button" class="btn-icon zi-more" data-hintbox-contents="&amp;lt;span class=&amp;quot;tag&amp;quot; data-hintbox-contents=&amp;quot;class: os&amp;quot; data-hintbox=&amp;quot;1&amp;quot; data-hintbox-static=&amp;quot;1&amp;quot;&amp;gt;class: os&amp;lt;/span&amp;gt;&amp;lt;span class=&amp;quot;tag&amp;quot; data-hintbox-contents=&amp;quot;component: network&amp;quot; data-hintbox=&amp;quot;1&amp;quot; data-hintbox-static=&amp;quot;1&amp;quot;&amp;gt;component: network&amp;lt;/span&amp;gt;&amp;lt;span class=&amp;quot;tag&amp;quot; data-hintbox-contents=&amp;quot;description: Local Area Connection* 7-WFP Native MAC Layer LightWeight Filter&amp;quot; data-hintbox=&amp;quot;1&amp;quot; data-hintbox-static=&amp;quot;1&amp;quot;&amp;gt;description: Local Area Connection* 7-WFP Native MAC Layer LightWeight Filter&amp;lt;/span&amp;gt;&amp;lt;span class=&amp;quot;tag&amp;quot; data-hintbox-contents=&amp;quot;interface: ethernet_7&amp;quot; data-hintbox=&amp;quot;1&amp;quot; data-hintbox-static=&amp;quot;1&amp;quot;&amp;gt;interface: ethernet_7&amp;lt;/span&amp;gt;&amp;lt;span class=&amp;quot;tag&amp;quot; data-hintbox-contents=&amp;quot;scope: availability&amp;quot; data-hintbox=&amp;quot;1&amp;quot; data-hintbox-static=&amp;quot;1&amp;quot;&amp;gt;scope: availability&amp;lt;/span&amp;gt;&amp;lt;span class=&amp;quot;tag&amp;quot; data-hintbox-contents=&amp;quot;target: windows&amp;quot; data-hintbox=&amp;quot;1&amp;quot; data-hintbox-static=&amp;quot;1&amp;quot;&amp;gt;target: windows&amp;lt;/span&amp;gt;" data-hintbox="1" data-hintbox-class="hintbox-wrap" data-hintbox-static="1"></button></td></tr><tr class="hover-nobg"><td class="timeline-date"><h4>December</h4></td><td class="timeline-axis timeline-dot-big"></td><td class="timeline-td"></td><td colspan="7"></td></tr><tr><td class="timeline-date"><a href="tr_events.php?triggerid=24705&amp;amp;eventid=4585">2024-11-26 20:29:05</a></td><td class="timeline-axis timeline-dot"></td><td class="timeline-td"></td><td></td><td><a class="link-action wordbreak" data-menu-popup="{&amp;quot;type&amp;quot;:&amp;quot;host&amp;quot;,&amp;quot;data&amp;quot;:{&amp;quot;hostid&amp;quot;:&amp;quot;10642&amp;quot;}}" aria-expanded="false" aria-haspopup="true" role="button" href="javascript:void(0)">Ngoc_Laptop</a></td><td class="average-bg wordbreak">Interface wireless_0(Wi-Fi-WFP Native MAC Layer LightWeight Filter-0000): Link down</td><td>6d 10h 25m</td><td><a class="link-alt" data-eventid="4585" onclick="acknowledgePopUp({eventids: [this.dataset.eventid]}, this);" role="button" href="javascript:void(0)">Update</a></td><td></td><td><span class="tag" data-hintbox-contents="class: os" data-hintbox="1" data-hintbox-static="1">class: os</span><span class="tag" data-hintbox-contents="component: network" data-hintbox="1" data-hintbox-static="1">component: network</span><span class="tag" data-hintbox-contents="description: Wi-Fi-WFP Native MAC Layer LightWeight Filter-0000" data-hintbox="1" data-hintbox-static="1">description: Wi-Fi-WFP Native MAC Layer LightWeight Filter-0000</span><button type="button" class="btn-icon zi-more" data-hintbox-contents="&amp;lt;span class=&amp;quot;tag&amp;quot; data-hintbox-contents=&amp;quot;class: os&amp;quot; data-hintbox=&amp;quot;1&amp;quot; data-hintbox-static=&amp;quot;1&amp;quot;&amp;gt;class: os&amp;lt;/span&amp;gt;&amp;lt;span class=&amp;quot;tag&amp;quot; data-hintbox-contents=&amp;quot;component: network&amp;quot; data-hintbox=&amp;quot;1&amp;quot; data-hintbox-static=&amp;quot;1&amp;quot;&amp;gt;component: network&amp;lt;/span&amp;gt;&amp;lt;span class=&amp;quot;tag&amp;quot; data-hintbox-contents=&amp;quot;description: Wi-Fi-WFP Native MAC Layer LightWeight Filter-0000&amp;quot; data-hintbox=&amp;quot;1&amp;quot; data-hintbox-static=&amp;quot;1&amp;quot;&amp;gt;description: Wi-Fi-WFP Native MAC Layer LightWeight Filter-0000&amp;lt;/span&amp;gt;&amp;lt;span class=&amp;quot;tag&amp;quot; data-hintbox-contents=&amp;quot;interface: wireless_0&amp;quot; data-hintbox=&amp;quot;1&amp;quot; data-hintbox-static=&amp;quot;1&amp;quot;&amp;gt;interface: wireless_0&amp;lt;/span&amp;gt;&amp;lt;span class=&amp;quot;tag&amp;quot; data-hintbox-contents=&amp;quot;scope: availability&amp;quot; data-hintbox=&amp;quot;1&amp;quot; data-hintbox-static=&amp;quot;1&amp;quot;&amp;gt;scope: availability&amp;lt;/span&amp;gt;&amp;lt;span class=&amp;quot;tag&amp;quot; data-hintbox-contents=&amp;quot;target: windows&amp;quot; data-hintbox=&amp;quot;1&amp;quot; data-hintbox-static=&amp;quot;1&amp;quot;&amp;gt;target: windows&amp;lt;/span&amp;gt;" data-hintbox="1" data-hintbox-class="hintbox-wrap" data-hintbox-static="1"></button></td></tr><tr><td class="timeline-date"><a href="tr_events.php?triggerid=24683&amp;amp;eventid=4584">2024-11-26 20:29:05</a></td><td class="timeline-axis timeline-dot"></td><td class="timeline-td"></td><td></td><td><a class="link-action wordbreak" data-menu-popup="{&amp;quot;type&amp;quot;:&amp;quot;host&amp;quot;,&amp;quot;data&amp;quot;:{&amp;quot;hostid&amp;quot;:&amp;quot;10642&amp;quot;}}" aria-expanded="false" aria-haspopup="true" role="button" href="javascript:void(0)">Ngoc_Laptop</a></td><td class="average-bg wordbreak">Interface ethernet_32772(Local Area Connection* 9): Link down</td><td>6d 10h 25m</td><td><a class="link-alt" data-eventid="4584" onclick="acknowledgePopUp({eventids: [this.dataset.eventid]}, this);" role="button" href="javascript:void(0)">Update</a></td><td></td><td><span class="tag" data-hintbox-contents="class: os" data-hintbox="1" data-hintbox-static="1">class: os</span><span class="tag" data-hintbox-contents="component: network" data-hintbox="1" data-hintbox-static="1">component: network</span><span class="tag" data-hintbox-contents="description: Local Area Connection* 9" data-hintbox="1" data-hintbox-static="1">description: Local Area Connection* 9</span><button type="button" class="btn-icon zi-more" data-hintbox-contents="&amp;lt;span class=&amp;quot;tag&amp;quot; data-hintbox-contents=&amp;quot;class: os&amp;quot; data-hintbox=&amp;quot;1&amp;quot; data-hintbox-static=&amp;quot;1&amp;quot;&amp;gt;class: os&amp;lt;/span&amp;gt;&amp;lt;span class=&amp;quot;tag&amp;quot; data-hintbox-contents=&amp;quot;component: network&amp;quot; data-hintbox=&amp;quot;1&amp;quot; data-hintbox-static=&amp;quot;1&amp;quot;&amp;gt;component: network&amp;lt;/span&amp;gt;&amp;lt;span class=&amp;quot;tag&amp;quot; data-hintbox-contents=&amp;quot;description: Local Area Connection* 9&amp;quot; data-hintbox=&amp;quot;1&amp;quot; data-hintbox-static=&amp;quot;1&amp;quot;&amp;gt;description: Local Area Connection* 9&amp;lt;/span&amp;gt;&amp;lt;span class=&amp;quot;tag&amp;quot; data-hintbox-contents=&amp;quot;interface: ethernet_32772&amp;quot; data-hintbox=&amp;quot;1&amp;quot; data-hintbox-static=&amp;quot;1&amp;quot;&amp;gt;interface: ethernet_32772&amp;lt;/span&amp;gt;&amp;lt;span class=&amp;quot;tag&amp;quot; data-hintbox-contents=&amp;quot;scope: availability&amp;quot; data-hintbox=&amp;quot;1&amp;quot; data-hintbox-static=&amp;quot;1&amp;quot;&amp;gt;scope: availability&amp;lt;/span&amp;gt;&amp;lt;span class=&amp;quot;tag&amp;quot; data-hintbox-contents=&amp;quot;target: windows&amp;quot; data-hintbox=&amp;quot;1&amp;quot; data-hintbox-static=&amp;quot;1&amp;quot;&amp;gt;target: windows&amp;lt;/span&amp;gt;" data-hintbox="1" data-hintbox-class="hintbox-wrap" data-hintbox-static="1"></button></td></tr><tr><td class="timeline-date"><a href="tr_events.php?triggerid=24682&amp;amp;eventid=4583">2024-11-26 20:29:05</a></td><td class="timeline-axis timeline-dot"></td><td class="timeline-td"></td><td></td><td><a class="link-action wordbreak" data-menu-popup="{&amp;quot;type&amp;quot;:&amp;quot;host&amp;quot;,&amp;quot;data&amp;quot;:{&amp;quot;hostid&amp;quot;:&amp;quot;10642&amp;quot;}}" aria-expanded="false" aria-haspopup="true" role="button" href="javascript:void(0)">Ngoc_Laptop</a></td><td class="average-bg wordbreak">Interface wireless_32768(Wi-Fi): Link down</td><td>6d 10h 25m</td><td><a class="link-alt" data-eventid="4583" onclick="acknowledgePopUp({eventids: [this.dataset.eventid]}, this);" role="button" href="javascript:void(0)">Update</a></td><td></td><td><span class="tag" data-hintbox-contents="class: os" data-hintbox="1" data-hintbox-static="1">class: os</span><span class="tag" data-hintbox-contents="component: network" data-hintbox="1" data-hintbox-static="1">component: network</span><span class="tag" data-hintbox-contents="description: Wi-Fi" data-hintbox="1" data-hintbox-static="1">description: Wi-Fi</span><button type="button" class="btn-icon zi-more" data-hintbox-contents="&amp;lt;span class=&amp;quot;tag&amp;quot; data-hintbox-contents=&amp;quot;class: os&amp;quot; data-hintbox=&amp;quot;1&amp;quot; data-hintbox-static=&amp;quot;1&amp;quot;&amp;gt;class: os&amp;lt;/span&amp;gt;&amp;lt;span class=&amp;quot;tag&amp;quot; data-hintbox-contents=&amp;quot;component: network&amp;quot; data-hintbox=&amp;quot;1&amp;quot; data-hintbox-static=&amp;quot;1&amp;quot;&amp;gt;component: network&amp;lt;/span&amp;gt;&amp;lt;span class=&amp;quot;tag&amp;quot; data-hintbox-contents=&amp;quot;description: Wi-Fi&amp;quot; data-hintbox=&amp;quot;1&amp;quot; data-hintbox-static=&amp;quot;1&amp;quot;&amp;gt;description: Wi-Fi&amp;lt;/span&amp;gt;&amp;lt;span class=&amp;quot;tag&amp;quot; data-hintbox-contents=&amp;quot;interface: wireless_32768&amp;quot; data-hintbox=&amp;quot;1&amp;quot; data-hintbox-static=&amp;quot;1&amp;quot;&amp;gt;interface: wireless_32768&amp;lt;/span&amp;gt;&amp;lt;span class=&amp;quot;tag&amp;quot; data-hintbox-contents=&amp;quot;scope: availability&amp;quot; data-hintbox=&amp;quot;1&amp;quot; data-hintbox-static=&amp;quot;1&amp;quot;&amp;gt;scope: availability&amp;lt;/span&amp;gt;&amp;lt;span class=&amp;quot;tag&amp;quot; data-hintbox-contents=&amp;quot;target: windows&amp;quot; data-hintbox=&amp;quot;1&amp;quot; data-hintbox-static=&amp;quot;1&amp;quot;&amp;gt;target: windows&amp;lt;/span&amp;gt;" data-hintbox="1" data-hintbox-class="hintbox-wrap" data-hintbox-static="1"></button></td></tr></tbody></table>'
												data-hintbox="1" data-hintbox-static="1" role="button"
												href="javascript:void(0)">${rank2}</a></span><span class="name"
												title="Average">Average</span>
										</div>
										<div class="warning-bg">
											<span class="count"><a class="link-action"
												data-hintbox-contents='<table class="list-table" id="t674e48beb625b419220818"><thead><tr><th class="right">Time<span class="arrow-down"></span></th><th class="timeline-th"></th><th class="timeline-th"></th><th>Info</th><th>Host</th><th>Problem</th><th>Duration</th><th>Update</th><th>Actions</th><th>Tags</th></tr></thead><tbody><tr><td class="timeline-date"><a href="tr_events.php?triggerid=25146&amp;amp;eventid=7460">2024-12-02 15:22:20</a></td><td class="timeline-axis timeline-dot"></td><td class="timeline-td"></td><td></td><td><a class="link-action wordbreak" data-menu-popup="{&amp;quot;type&amp;quot;:&amp;quot;host&amp;quot;,&amp;quot;data&amp;quot;:{&amp;quot;hostid&amp;quot;:&amp;quot;10644&amp;quot;}}" aria-expanded="false" aria-haspopup="true" role="button" href="javascript:void(0)">May_hoa</a></td><td class="warning-bg wordbreak">FS [C:\ Label:WINDOWS 10 X64  Serial Number f6bbee90]: Space is low (used &amp;gt; 80%, total 112.3GB)</td><td>15h 32m 18s</td><td><a class="link-alt" data-eventid="7460" onclick="acknowledgePopUp({eventids: [this.dataset.eventid]}, this);" role="button" href="javascript:void(0)">Update</a></td><td></td><td><span class="tag" data-hintbox-contents="class: os" data-hintbox="1" data-hintbox-static="1">class: os</span><span class="tag" data-hintbox-contents="component: storage" data-hintbox="1" data-hintbox-static="1">component: storage</span><span class="tag" data-hintbox-contents="filesystem: C:\ Label:WINDOWS 10 X64  Serial Number f6bbee90" data-hintbox="1" data-hintbox-static="1">filesystem: C:\ Label:WINDOWS 10 X64  Serial Number f6bbee90</span><button type="button" class="btn-icon zi-more" data-hintbox-contents="&amp;lt;span class=&amp;quot;tag&amp;quot; data-hintbox-contents=&amp;quot;class: os&amp;quot; data-hintbox=&amp;quot;1&amp;quot; data-hintbox-static=&amp;quot;1&amp;quot;&amp;gt;class: os&amp;lt;/span&amp;gt;&amp;lt;span class=&amp;quot;tag&amp;quot; data-hintbox-contents=&amp;quot;component: storage&amp;quot; data-hintbox=&amp;quot;1&amp;quot; data-hintbox-static=&amp;quot;1&amp;quot;&amp;gt;component: storage&amp;lt;/span&amp;gt;&amp;lt;span class=&amp;quot;tag&amp;quot; data-hintbox-contents=&amp;quot;filesystem: C:\ Label:WINDOWS 10 X64  Serial Number f6bbee90&amp;quot; data-hintbox=&amp;quot;1&amp;quot; data-hintbox-static=&amp;quot;1&amp;quot;&amp;gt;filesystem: C:\ Label:WINDOWS 10 X64  Serial Number f6bbee90&amp;lt;/span&amp;gt;&amp;lt;span class=&amp;quot;tag&amp;quot; data-hintbox-contents=&amp;quot;scope: availability&amp;quot; data-hintbox=&amp;quot;1&amp;quot; data-hintbox-static=&amp;quot;1&amp;quot;&amp;gt;scope: availability&amp;lt;/span&amp;gt;&amp;lt;span class=&amp;quot;tag&amp;quot; data-hintbox-contents=&amp;quot;scope: capacity&amp;quot; data-hintbox=&amp;quot;1&amp;quot; data-hintbox-static=&amp;quot;1&amp;quot;&amp;gt;scope: capacity&amp;lt;/span&amp;gt;&amp;lt;span class=&amp;quot;tag&amp;quot; data-hintbox-contents=&amp;quot;target: windows&amp;quot; data-hintbox=&amp;quot;1&amp;quot; data-hintbox-static=&amp;quot;1&amp;quot;&amp;gt;target: windows&amp;lt;/span&amp;gt;" data-hintbox="1" data-hintbox-class="hintbox-wrap" data-hintbox-static="1"></button></td></tr><tr class="hover-nobg"><td class="timeline-date"><h4>Yesterday</h4></td><td class="timeline-axis timeline-dot-big"></td><td class="timeline-td"></td><td colspan="7"></td></tr><tr><td class="timeline-date"><a href="tr_events.php?triggerid=24719&amp;amp;eventid=1237">2024-11-21 09:41:37</a></td><td class="timeline-axis timeline-dot"></td><td class="timeline-td"></td><td></td><td><a class="link-action wordbreak" data-menu-popup="{&amp;quot;type&amp;quot;:&amp;quot;host&amp;quot;,&amp;quot;data&amp;quot;:{&amp;quot;hostid&amp;quot;:&amp;quot;10643&amp;quot;}}" aria-expanded="false" aria-haspopup="true" role="button" href="javascript:void(0)">Thanh_Laptop</a></td><td class="warning-bg wordbreak">Unavailable by ICMP ping</td><td>11d 21h 13m</td><td><a class="link-alt" data-eventid="1237" onclick="acknowledgePopUp({eventids: [this.dataset.eventid]}, this);" role="button" href="javascript:void(0)">Update</a></td><td class="nowrap"><button type="button" class="btn-icon zi-alert-with-content" data-content="1" aria-label="1 message" data-hintbox-contents="&amp;lt;table class=&amp;quot;list-table&amp;quot; id=&amp;quot;t674e48beb61b3402074095&amp;quot;&amp;gt;&amp;lt;thead&amp;gt;&amp;lt;tr&amp;gt;&amp;lt;th&amp;gt;Time&amp;lt;/th&amp;gt;&amp;lt;th&amp;gt;User&amp;lt;/th&amp;gt;&amp;lt;th&amp;gt;Message&amp;lt;/th&amp;gt;&amp;lt;/tr&amp;gt;&amp;lt;/thead&amp;gt;&amp;lt;tbody&amp;gt;&amp;lt;tr&amp;gt;&amp;lt;td&amp;gt;2024-11-21 10:01:14&amp;lt;/td&amp;gt;&amp;lt;td&amp;gt;Admin (Zabbix Administrator)&amp;lt;/td&amp;gt;&amp;lt;td&amp;gt;Ping ko đc do mạng trường chặn&amp;lt;/td&amp;gt;&amp;lt;/tr&amp;gt;&amp;lt;/tbody&amp;gt;&amp;lt;/table&amp;gt;" data-hintbox="1" data-hintbox-class="hintbox-wrap-horizontal" data-hintbox-static="1"></button><button type="button" class="btn-icon zi-arrow-down-small color-positive" aria-label="Severity decreased" data-hintbox-contents="&amp;lt;table class=&amp;quot;list-table&amp;quot; id=&amp;quot;t674e48beb6206662514041&amp;quot;&amp;gt;&amp;lt;thead&amp;gt;&amp;lt;tr&amp;gt;&amp;lt;th&amp;gt;Time&amp;lt;/th&amp;gt;&amp;lt;th&amp;gt;User&amp;lt;/th&amp;gt;&amp;lt;th&amp;gt;Severity changes&amp;lt;/th&amp;gt;&amp;lt;/tr&amp;gt;&amp;lt;/thead&amp;gt;&amp;lt;tbody&amp;gt;&amp;lt;tr&amp;gt;&amp;lt;td&amp;gt;2024-11-21 10:01:14&amp;lt;/td&amp;gt;&amp;lt;td&amp;gt;Admin (Zabbix Administrator)&amp;lt;/td&amp;gt;&amp;lt;td&amp;gt;High&amp;amp;nbsp;&amp;amp;rArr;&amp;amp;nbsp;Warning&amp;lt;/td&amp;gt;&amp;lt;/tr&amp;gt;&amp;lt;/tbody&amp;gt;&amp;lt;/table&amp;gt;" data-hintbox="1" data-hintbox-class="hintbox-wrap-horizontal" data-hintbox-static="1"></button><button type="button" class="btn-icon zi-bullet-right-with-content" data-content="1" aria-label="1 action" data-hintbox-preload="{&amp;quot;type&amp;quot;:&amp;quot;eventactions&amp;quot;,&amp;quot;data&amp;quot;:{&amp;quot;eventid&amp;quot;:&amp;quot;1237&amp;quot;}}" data-hintbox-contents="" data-hintbox="1" data-hintbox-class="hintbox-wrap-horizontal" data-hintbox-static="1"></button></td><td><span class="tag" data-hintbox-contents="class: os" data-hintbox="1" data-hintbox-static="1">class: os</span><span class="tag" data-hintbox-contents="component: health" data-hintbox="1" data-hintbox-static="1">component: health</span><span class="tag" data-hintbox-contents="component: network" data-hintbox="1" data-hintbox-static="1">component: network</span><button type="button" class="btn-icon zi-more" data-hintbox-contents="&amp;lt;span class=&amp;quot;tag&amp;quot; data-hintbox-contents=&amp;quot;class: os&amp;quot; data-hintbox=&amp;quot;1&amp;quot; data-hintbox-static=&amp;quot;1&amp;quot;&amp;gt;class: os&amp;lt;/span&amp;gt;&amp;lt;span class=&amp;quot;tag&amp;quot; data-hintbox-contents=&amp;quot;component: health&amp;quot; data-hintbox=&amp;quot;1&amp;quot; data-hintbox-static=&amp;quot;1&amp;quot;&amp;gt;component: health&amp;lt;/span&amp;gt;&amp;lt;span class=&amp;quot;tag&amp;quot; data-hintbox-contents=&amp;quot;component: network&amp;quot; data-hintbox=&amp;quot;1&amp;quot; data-hintbox-static=&amp;quot;1&amp;quot;&amp;gt;component: network&amp;lt;/span&amp;gt;&amp;lt;span class=&amp;quot;tag&amp;quot; data-hintbox-contents=&amp;quot;scope: availability&amp;quot; data-hintbox=&amp;quot;1&amp;quot; data-hintbox-static=&amp;quot;1&amp;quot;&amp;gt;scope: availability&amp;lt;/span&amp;gt;&amp;lt;span class=&amp;quot;tag&amp;quot; data-hintbox-contents=&amp;quot;target: windows&amp;quot; data-hintbox=&amp;quot;1&amp;quot; data-hintbox-static=&amp;quot;1&amp;quot;&amp;gt;target: windows&amp;lt;/span&amp;gt;" data-hintbox="1" data-hintbox-class="hintbox-wrap" data-hintbox-static="1"></button></td></tr></tbody></table>'
												data-hintbox="1" data-hintbox-static="1" role="button"
												href="javascript:void(0)">${rank3 }</a></span><span class="name"
												title="Warning">Warning</span>
										</div>
										<div class="info-bg">
											<span class="count"><a class="link-action"
												data-hintbox-contents='<table class="list-table" id="t674e48beb6dc1415615529"><thead><tr><th class="right">Time<span class="arrow-down"></span></th><th class="timeline-th"></th><th class="timeline-th"></th><th>Info</th><th>Host</th><th>Problem</th><th>Duration</th><th>Update</th><th>Actions</th><th>Tags</th></tr></thead><tbody><tr><td class="timeline-date"><a href="tr_events.php?triggerid=24553&amp;amp;eventid=4586">2024-11-26 20:29:06</a></td><td class="timeline-axis timeline-dot"></td><td class="timeline-td"></td><td></td><td><a class="link-action wordbreak" data-menu-popup="{&amp;quot;type&amp;quot;:&amp;quot;host&amp;quot;,&amp;quot;data&amp;quot;:{&amp;quot;hostid&amp;quot;:&amp;quot;10642&amp;quot;}}" aria-expanded="false" aria-haspopup="true" role="button" href="javascript:void(0)">Ngoc_Laptop</a></td><td class="info-bg wordbreak">System name has changed (new name: DESKTOP-GBVGH5C)</td><td>6d 10h 25m</td><td><a class="link-alt" data-eventid="4586" onclick="acknowledgePopUp({eventids: [this.dataset.eventid]}, this);" role="button" href="javascript:void(0)">Update</a></td><td></td><td><span class="tag" data-hintbox-contents="class: os" data-hintbox="1" data-hintbox-static="1">class: os</span><span class="tag" data-hintbox-contents="component: system" data-hintbox="1" data-hintbox-static="1">component: system</span><span class="tag" data-hintbox-contents="scope: notice" data-hintbox="1" data-hintbox-static="1">scope: notice</span><button type="button" class="btn-icon zi-more" data-hintbox-contents="&amp;lt;span class=&amp;quot;tag&amp;quot; data-hintbox-contents=&amp;quot;class: os&amp;quot; data-hintbox=&amp;quot;1&amp;quot; data-hintbox-static=&amp;quot;1&amp;quot;&amp;gt;class: os&amp;lt;/span&amp;gt;&amp;lt;span class=&amp;quot;tag&amp;quot; data-hintbox-contents=&amp;quot;component: system&amp;quot; data-hintbox=&amp;quot;1&amp;quot; data-hintbox-static=&amp;quot;1&amp;quot;&amp;gt;component: system&amp;lt;/span&amp;gt;&amp;lt;span class=&amp;quot;tag&amp;quot; data-hintbox-contents=&amp;quot;scope: notice&amp;quot; data-hintbox=&amp;quot;1&amp;quot; data-hintbox-static=&amp;quot;1&amp;quot;&amp;gt;scope: notice&amp;lt;/span&amp;gt;&amp;lt;span class=&amp;quot;tag&amp;quot; data-hintbox-contents=&amp;quot;scope: security&amp;quot; data-hintbox=&amp;quot;1&amp;quot; data-hintbox-static=&amp;quot;1&amp;quot;&amp;gt;scope: security&amp;lt;/span&amp;gt;&amp;lt;span class=&amp;quot;tag&amp;quot; data-hintbox-contents=&amp;quot;target: windows&amp;quot; data-hintbox=&amp;quot;1&amp;quot; data-hintbox-static=&amp;quot;1&amp;quot;&amp;gt;target: windows&amp;lt;/span&amp;gt;" data-hintbox="1" data-hintbox-class="hintbox-wrap" data-hintbox-static="1"></button></td></tr><tr><td class="timeline-date"><a href="tr_events.php?triggerid=24702&amp;amp;eventid=4587">2024-11-26 20:29:05</a></td><td class="timeline-axis timeline-dot"></td><td class="timeline-td"></td><td></td><td><a class="link-action wordbreak" data-menu-popup="{&amp;quot;type&amp;quot;:&amp;quot;host&amp;quot;,&amp;quot;data&amp;quot;:{&amp;quot;hostid&amp;quot;:&amp;quot;10642&amp;quot;}}" aria-expanded="false" aria-haspopup="true" role="button" href="javascript:void(0)">Ngoc_Laptop</a></td><td class="info-bg wordbreak">Interface ethernet_32770(Local Area Connection* 7): Link down</td><td>6d 10h 25m</td><td><a class="link-alt" data-eventid="4587" onclick="acknowledgePopUp({eventids: [this.dataset.eventid]}, this);" role="button" href="javascript:void(0)">Update</a></td><td class="nowrap"><span class="icon zi-check color-positive" title="Acknowledged"></span><button type="button" class="btn-icon zi-alert-with-content" data-content="1" aria-label="1 message" data-hintbox-contents="&amp;lt;table class=&amp;quot;list-table&amp;quot; id=&amp;quot;t674e48beb6c53456916597&amp;quot;&amp;gt;&amp;lt;thead&amp;gt;&amp;lt;tr&amp;gt;&amp;lt;th&amp;gt;Time&amp;lt;/th&amp;gt;&amp;lt;th&amp;gt;User&amp;lt;/th&amp;gt;&amp;lt;th&amp;gt;Message&amp;lt;/th&amp;gt;&amp;lt;/tr&amp;gt;&amp;lt;/thead&amp;gt;&amp;lt;tbody&amp;gt;&amp;lt;tr&amp;gt;&amp;lt;td&amp;gt;2024-12-02 14:29:01&amp;lt;/td&amp;gt;&amp;lt;td&amp;gt;Admin (Zabbix Administrator)&amp;lt;/td&amp;gt;&amp;lt;td&amp;gt;không thể ping được&amp;lt;/td&amp;gt;&amp;lt;/tr&amp;gt;&amp;lt;/tbody&amp;gt;&amp;lt;/table&amp;gt;" data-hintbox="1" data-hintbox-class="hintbox-wrap-horizontal" data-hintbox-static="1"></button><button type="button" class="btn-icon zi-arrow-down-small color-positive" aria-label="Severity decreased" data-hintbox-contents="&amp;lt;table class=&amp;quot;list-table&amp;quot; id=&amp;quot;t674e48beb6c85259082224&amp;quot;&amp;gt;&amp;lt;thead&amp;gt;&amp;lt;tr&amp;gt;&amp;lt;th&amp;gt;Time&amp;lt;/th&amp;gt;&amp;lt;th&amp;gt;User&amp;lt;/th&amp;gt;&amp;lt;th&amp;gt;Severity changes&amp;lt;/th&amp;gt;&amp;lt;/tr&amp;gt;&amp;lt;/thead&amp;gt;&amp;lt;tbody&amp;gt;&amp;lt;tr&amp;gt;&amp;lt;td&amp;gt;2024-12-02 14:29:01&amp;lt;/td&amp;gt;&amp;lt;td&amp;gt;Admin (Zabbix Administrator)&amp;lt;/td&amp;gt;&amp;lt;td&amp;gt;Average&amp;amp;nbsp;&amp;amp;rArr;&amp;amp;nbsp;Information&amp;lt;/td&amp;gt;&amp;lt;/tr&amp;gt;&amp;lt;/tbody&amp;gt;&amp;lt;/table&amp;gt;" data-hintbox="1" data-hintbox-class="hintbox-wrap-horizontal" data-hintbox-static="1"></button><button type="button" class="btn-icon zi-bullet-right-with-content" data-content="1" aria-label="1 action" data-hintbox-preload="{&amp;quot;type&amp;quot;:&amp;quot;eventactions&amp;quot;,&amp;quot;data&amp;quot;:{&amp;quot;eventid&amp;quot;:&amp;quot;4587&amp;quot;}}" data-hintbox-contents="" data-hintbox="1" data-hintbox-class="hintbox-wrap-horizontal" data-hintbox-static="1"></button></td><td><span class="tag" data-hintbox-contents="class: os" data-hintbox="1" data-hintbox-static="1">class: os</span><span class="tag" data-hintbox-contents="component: network" data-hintbox="1" data-hintbox-static="1">component: network</span><span class="tag" data-hintbox-contents="description: Local Area Connection* 7" data-hintbox="1" data-hintbox-static="1">description: Local Area Connection* 7</span><button type="button" class="btn-icon zi-more" data-hintbox-contents="&amp;lt;span class=&amp;quot;tag&amp;quot; data-hintbox-contents=&amp;quot;class: os&amp;quot; data-hintbox=&amp;quot;1&amp;quot; data-hintbox-static=&amp;quot;1&amp;quot;&amp;gt;class: os&amp;lt;/span&amp;gt;&amp;lt;span class=&amp;quot;tag&amp;quot; data-hintbox-contents=&amp;quot;component: network&amp;quot; data-hintbox=&amp;quot;1&amp;quot; data-hintbox-static=&amp;quot;1&amp;quot;&amp;gt;component: network&amp;lt;/span&amp;gt;&amp;lt;span class=&amp;quot;tag&amp;quot; data-hintbox-contents=&amp;quot;description: Local Area Connection* 7&amp;quot; data-hintbox=&amp;quot;1&amp;quot; data-hintbox-static=&amp;quot;1&amp;quot;&amp;gt;description: Local Area Connection* 7&amp;lt;/span&amp;gt;&amp;lt;span class=&amp;quot;tag&amp;quot; data-hintbox-contents=&amp;quot;interface: ethernet_32770&amp;quot; data-hintbox=&amp;quot;1&amp;quot; data-hintbox-static=&amp;quot;1&amp;quot;&amp;gt;interface: ethernet_32770&amp;lt;/span&amp;gt;&amp;lt;span class=&amp;quot;tag&amp;quot; data-hintbox-contents=&amp;quot;scope: availability&amp;quot; data-hintbox=&amp;quot;1&amp;quot; data-hintbox-static=&amp;quot;1&amp;quot;&amp;gt;scope: availability&amp;lt;/span&amp;gt;&amp;lt;span class=&amp;quot;tag&amp;quot; data-hintbox-contents=&amp;quot;target: windows&amp;quot; data-hintbox=&amp;quot;1&amp;quot; data-hintbox-static=&amp;quot;1&amp;quot;&amp;gt;target: windows&amp;lt;/span&amp;gt;" data-hintbox="1" data-hintbox-class="hintbox-wrap" data-hintbox-static="1"></button></td></tr><tr><td class="timeline-date"><a href="tr_events.php?triggerid=24548&amp;amp;eventid=2189">2024-11-22 11:00:37</a></td><td class="timeline-axis timeline-dot"></td><td class="timeline-td"></td><td></td><td><a class="link-action wordbreak" data-menu-popup="{&amp;quot;type&amp;quot;:&amp;quot;host&amp;quot;,&amp;quot;data&amp;quot;:{&amp;quot;hostid&amp;quot;:&amp;quot;10642&amp;quot;}}" aria-expanded="false" aria-haspopup="true" role="button" href="javascript:void(0)">Ngoc_Laptop</a></td><td class="info-bg wordbreak">Unavailable by ICMP ping</td><td>10d 19h 54m</td><td><a class="link-alt" data-eventid="2189" onclick="acknowledgePopUp({eventids: [this.dataset.eventid]}, this);" role="button" href="javascript:void(0)">Update</a></td><td class="nowrap"><button type="button" class="btn-icon zi-alert-with-content" data-content="3" aria-label="3 messages" data-hintbox-contents="&amp;lt;table class=&amp;quot;list-table&amp;quot; id=&amp;quot;t674e48beb6d69619741230&amp;quot;&amp;gt;&amp;lt;thead&amp;gt;&amp;lt;tr&amp;gt;&amp;lt;th&amp;gt;Time&amp;lt;/th&amp;gt;&amp;lt;th&amp;gt;User&amp;lt;/th&amp;gt;&amp;lt;th&amp;gt;Message&amp;lt;/th&amp;gt;&amp;lt;/tr&amp;gt;&amp;lt;/thead&amp;gt;&amp;lt;tbody&amp;gt;&amp;lt;tr&amp;gt;&amp;lt;td&amp;gt;2024-12-02 14:21:06&amp;lt;/td&amp;gt;&amp;lt;td&amp;gt;Admin (Zabbix Administrator)&amp;lt;/td&amp;gt;&amp;lt;td&amp;gt;không thể ping được&amp;lt;/td&amp;gt;&amp;lt;/tr&amp;gt;&amp;lt;tr&amp;gt;&amp;lt;td&amp;gt;2024-11-22 11:09:15&amp;lt;/td&amp;gt;&amp;lt;td&amp;gt;Admin (Zabbix Administrator)&amp;lt;/td&amp;gt;&amp;lt;td&amp;gt;Đổi mạng vẫn ko ping đc, do máy&amp;lt;/td&amp;gt;&amp;lt;/tr&amp;gt;&amp;lt;tr&amp;gt;&amp;lt;td&amp;gt;2024-11-22 11:04:56&amp;lt;/td&amp;gt;&amp;lt;td&amp;gt;Admin (Zabbix Administrator)&amp;lt;/td&amp;gt;&amp;lt;td&amp;gt;Mạng trường dùng VPN ko thể ping&amp;lt;/td&amp;gt;&amp;lt;/tr&amp;gt;&amp;lt;/tbody&amp;gt;&amp;lt;/table&amp;gt;" data-hintbox="1" data-hintbox-class="hintbox-wrap-horizontal" data-hintbox-static="1"></button><button type="button" class="btn-icon zi-arrow-down-small color-positive" aria-label="Severity decreased" data-hintbox-contents="&amp;lt;table class=&amp;quot;list-table&amp;quot; id=&amp;quot;t674e48beb6d91789105743&amp;quot;&amp;gt;&amp;lt;thead&amp;gt;&amp;lt;tr&amp;gt;&amp;lt;th&amp;gt;Time&amp;lt;/th&amp;gt;&amp;lt;th&amp;gt;User&amp;lt;/th&amp;gt;&amp;lt;th&amp;gt;Severity changes&amp;lt;/th&amp;gt;&amp;lt;/tr&amp;gt;&amp;lt;/thead&amp;gt;&amp;lt;tbody&amp;gt;&amp;lt;tr&amp;gt;&amp;lt;td&amp;gt;2024-11-22 11:04:56&amp;lt;/td&amp;gt;&amp;lt;td&amp;gt;Admin (Zabbix Administrator)&amp;lt;/td&amp;gt;&amp;lt;td&amp;gt;High&amp;amp;nbsp;&amp;amp;rArr;&amp;amp;nbsp;Information&amp;lt;/td&amp;gt;&amp;lt;/tr&amp;gt;&amp;lt;/tbody&amp;gt;&amp;lt;/table&amp;gt;" data-hintbox="1" data-hintbox-class="hintbox-wrap-horizontal" data-hintbox-static="1"></button><button type="button" class="btn-icon zi-bullet-right-with-content" data-content="3" aria-label="3 actions" data-hintbox-preload="{&amp;quot;type&amp;quot;:&amp;quot;eventactions&amp;quot;,&amp;quot;data&amp;quot;:{&amp;quot;eventid&amp;quot;:&amp;quot;2189&amp;quot;}}" data-hintbox-contents="" data-hintbox="1" data-hintbox-class="hintbox-wrap-horizontal" data-hintbox-static="1"></button></td><td><span class="tag" data-hintbox-contents="class: os" data-hintbox="1" data-hintbox-static="1">class: os</span><span class="tag" data-hintbox-contents="component: health" data-hintbox="1" data-hintbox-static="1">component: health</span><span class="tag" data-hintbox-contents="component: network" data-hintbox="1" data-hintbox-static="1">component: network</span><button type="button" class="btn-icon zi-more" data-hintbox-contents="&amp;lt;span class=&amp;quot;tag&amp;quot; data-hintbox-contents=&amp;quot;class: os&amp;quot; data-hintbox=&amp;quot;1&amp;quot; data-hintbox-static=&amp;quot;1&amp;quot;&amp;gt;class: os&amp;lt;/span&amp;gt;&amp;lt;span class=&amp;quot;tag&amp;quot; data-hintbox-contents=&amp;quot;component: health&amp;quot; data-hintbox=&amp;quot;1&amp;quot; data-hintbox-static=&amp;quot;1&amp;quot;&amp;gt;component: health&amp;lt;/span&amp;gt;&amp;lt;span class=&amp;quot;tag&amp;quot; data-hintbox-contents=&amp;quot;component: network&amp;quot; data-hintbox=&amp;quot;1&amp;quot; data-hintbox-static=&amp;quot;1&amp;quot;&amp;gt;component: network&amp;lt;/span&amp;gt;&amp;lt;span class=&amp;quot;tag&amp;quot; data-hintbox-contents=&amp;quot;scope: availability&amp;quot; data-hintbox=&amp;quot;1&amp;quot; data-hintbox-static=&amp;quot;1&amp;quot;&amp;gt;scope: availability&amp;lt;/span&amp;gt;&amp;lt;span class=&amp;quot;tag&amp;quot; data-hintbox-contents=&amp;quot;target: windows&amp;quot; data-hintbox=&amp;quot;1&amp;quot; data-hintbox-static=&amp;quot;1&amp;quot;&amp;gt;target: windows&amp;lt;/span&amp;gt;" data-hintbox="1" data-hintbox-class="hintbox-wrap" data-hintbox-static="1"></button></td></tr></tbody></table>'
												data-hintbox="1" data-hintbox-static="1" role="button"
												href="javascript:void(0)">${rank4 }</a></span><span class="name"
												title="Information">Information</span>
										</div>
										<div class="na-bg">
											<span class="count">${rank5}</span><span class="name"
												title="Not classified">Not classified</span>
										</div>
									</div>
								</div>
								<div class="dashboard-grid-widget-debug"></div>
							</div>
						</div>
					</div>
					<div class="dashboard-grid-widget"
						style="min-width: 1.38889%; min-height: 70px; left: 0%; top: 420px; width: 100%; height: 350px;">
						<div class="dashboard-grid-widget-container">
							<div class="dashboard-grid-widget-header">
								<h4>Current problems</h4>
								<ul class="dashboard-grid-widget-actions">
									<li>
										<button type="button" title="Edit"
											class="btn-icon zi-cog-filled js-widget-edit"></button>
									</li>
									<li>
										<button type="button" title="Actions" aria-expanded="false"
											aria-haspopup="true"
											class="btn-icon zi-more js-widget-action"></button>
									</li>
								</ul>
							</div>
							<div
								class="dashboard-grid-widget-contents dashboard-widget-problems">
								<div class="dashboard-grid-widget-messages"></div>
								<div class="dashboard-grid-widget-body">
									<table class="list-table" id="t674c74265fb06583012183">
										<thead>
											<tr>
												<th class="cell-width right"><a href="">Time<span
														class="arrow-down"></span></a></th>
												<th class="timeline-th"></th>
												<th class="timeline-th"></th>
												<th><a href="">Severity</a></th>
												<th class="cell-width"></th>
												<th>Status</th>
												<th><a href="">Host</a></th>
												<th><a href="">Problem</a></th>
												<th>Update</th>
												<th>Actions</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach items="${requestScope.listproblem }" var="data">
												<tr>
													<td class="timeline-date">${data.getClock()}</td>
													<td class="timeline-axis timeline-dot"></td>
													<td class="timeline-td"></td>
													<td
														class="${data.getSeverity() == 0 ? 'severity-not-classified' :
            data.getSeverity() == 1 ? 'severity-information' :
            data.getSeverity() == 2 ? 'severity-warning' :
            data.getSeverity() == 3 ? 'severity-average' :
            data.getSeverity() == 4 ? 'severity-high' :
            data.getSeverity() == 5 ? 'severity-disaster' : 'severity-unknown'}">
														<c:choose>
															<c:when test="${data.getSeverity() == 0}">Not Classified</c:when>
															<c:when test="${data.getSeverity() == 1}">Information</c:when>
															<c:when test="${data.getSeverity() == 2}">Warning</c:when>
															<c:when test="${data.getSeverity() == 3}">Average</c:when>
															<c:when test="${data.getSeverity() == 4}">High</c:when>
															<c:when test="${data.getSeverity() == 5}">Disaster</c:when>
															<c:otherwise>Unknown</c:otherwise>
														</c:choose>
													</td>

													<td></td>
													<td><span class="problem-unack-fg">PROBLEM</span></td>
													<td><a class="link-action wordbreak"
														data-menu-popup='{"type":"host","data":{"hostid":"10646"}}'
														aria-expanded="false" aria-haspopup="true" role="button"
														href="check?action=">${data.getHName()}</a></td>
													<td>
														<div class="link-action wordbreak">${data.getName()}</div>
													</td>

													<td><a class="link-alt" data-eventid="5606"
														role="button"
														href="check?action=updateproblem&eventid=${data.getEventId()}">Update</a>
													</td>
													<td>${data.getAction().size()}action</td>

												</tr>
											</c:forEach>
										</tbody>
									</table>
								</div>
								<div class="dashboard-grid-widget-debug"></div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</main>
		<output id="msg-global-footer" class="msg-global-footer msg-warning"
			style="left: 185px; width: 527px"></output>
		<footer role="contentinfo">
			Zabbix 7.0.5. © 2001–2024, <a class="grey link-alt" target="_blank"
				rel="noopener noreferrer" href="https://www.zabbix.com/">Zabbix
				SIA</a>
		</footer>
	</div>
</body>
</html>