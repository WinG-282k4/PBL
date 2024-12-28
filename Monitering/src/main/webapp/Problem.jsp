<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Document</title>
<link rel="stylesheet" href="CSS/index2.css" />
<style type="text/css">
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
				<h1 id="page-title-general">Problems</h1>
			</div>
		</header>
		<main>
			<div id="monitoring_problem_filter"
				class="filter-container tabfilter-container filter-space"
				data-disable-initial-check="1" data-accessible="1"
				data-profile-idx="web.monitoring.problem" data-profile-idx2="0">
				<div class="tabfilter-content-container">
					<div class="tabfilter-tabs-container">
						<div id="tabfilter_0">
							<form method="post" action="zabbix.php" accept-charset="utf-8"
								name="zbx_filter">
								<div class="table filter-forms">
									<div class="cell">
										<ul class="table-forms">
											<li>
												<div class="table-forms-td-left">
													<label for="groupids_0_ms">Host groups</label>
												</div>
												<div class="table-forms-td-right">
													<div class="multiselect-control">
														<div id="groupids_0" class="multiselect"
															role="application"
															data-params='{"objectOptions":{"with_hosts":1,"enrich_parent_groups":1},"id":"groupids_0","object_name":"hostGroup","name":"groupids[]","data":[],"popup":{"parameters":{"srctbl":"host_groups","srcfld1":"groupid","dstfrm":"zbx_filter","dstfld1":"groupids_0","multiselect":1,"real_hosts":1,"enrich_parent_groups":1}},"url":"jsrpc.php?type=11&amp;method=multiselect.get&amp;object_name=hostGroup&amp;with_hosts=1&amp;enrich_parent_groups=1"}'
															style="width: 300px">
															<div class="selected">
																<ul class="multiselect-list"></ul>
															</div>
															<input id="groupids" class="input" type="text"
																autocomplete="off" placeholder="type here to search"
																aria-label="Host groups. type here to search"
																aria-required="false" />
														</div>
														<ul class="btn-split">
														</ul>
													</div>
												</div>
											</li>

										</ul>
									</div>
								</div>
								<div class="filter-forms form-buttons">
									<button type="submit" name="filter_apply" value="1">Apply</button>
									<button type="reset" name="filter_reset" value="1"
										class="btn-alt" onclick="deldata()">
										Reset</button>
								</div>
							</form>
						</div>
					</div>

				</div>

			</div>
			<div class="flickerfreescreen" data-timestamp="1733063718"
				id="flickerfreescreen_problem">
				<form method="post" action="" accept-charset="utf-8"
					id="problem_form" name="problem">
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
							<c:forEach items="${requestScope.list }" var="data">
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
										href="check?action=">${data.getHName()}</a>
									</td>
									<td>
										<div class="link-action wordbreak">${data.getName()}</div>
									</td>

									<td><a class="link-alt" data-eventid="5606" role="button"
										href="check?action=updateproblem&eventid=${data.getEventId()}">Update</a>
									</td>
									<td>${data.getAction().size()} action</td>

								</tr>
							</c:forEach>
						</tbody>
					</table>
					<div class="table-paging">
						<nav class="paging-btn-container" role="navigation"
							aria-label="Pager">
							<div class="table-stats">Displaying 11 of 11 found</div>
						</nav>
					</div>

				</form>
			</div>
			<div hidden="" class="overlay-dialogue notif ui-draggable"
				style="display: none; right: 10px; top: 10px">
				<div class="overlay-dialogue-header cursor-move ui-draggable-handle">
					<ul>
						<li>
							<button title="Snooze for Admin" class="btn-icon zi-bell"></button>
						</li>
						<li>
							<button title="Mute for Admin" class="btn-icon zi-speaker"></button>
						</li>
					</ul>
					<button title="Close" type="button" class="btn-overlay-close"></button>
				</div>
				<ul class="notif-body"></ul>
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
	<script>
		function deldata() {
			document.getElementById("groupids").value = "";
			document.getElementById("hostids").value = "";
		}
	</script>
</body>
</html>

