<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Search Hosts</title>
<link rel="stylesheet" href="CSS/index2.css" />
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
				<h1 id="page-title-general">Hosts</h1>
			</div>
			<div class="header-doc-link"></div>
			<div class="header-controls">
				<nav aria-label="Content controls">
					<ul>
						<li><a href="check?action=addhost"><button type="button">
									Create host</button></a></li>
					</ul>
				</nav>
			</div>
		</header>
		<main>
			<div id="monitoring_hosts_filter"
				class="filter-container tabfilter-container">
				<div class="tabfilter-content-container">
					<div class="tabfilter-tabs-container">
						<div id="tabfilter_0">
							<form method="get" action="lastestData" accept-charset="utf-8"
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
															data-params='{"objectOptions":{"with_hosts":1,"enrich_parent_groups":1},"id":"groupids_0","object_name":"hostGroup","name":"groupids[]","data":[{"name":"22tdt3","id":"22"}],"popup":{"parameters":{"multiselect":"1","srctbl":"host_groups","srcfld1":"groupid","dstfrm":"zbx_filter","dstfld1":"groupids_0","real_hosts":1,"enrich_parent_groups":1}},"url":"jsrpc.php?type=11&amp;method=multiselect.get&amp;object_name=hostGroup&amp;with_hosts=1&amp;enrich_parent_groups=1"}'
															style="width: 300px">
															<div class="selected">
																<ul class="multiselect-list"></ul>
															</div>
															<input id="groupids_0_ms" class="input" type="text"
																name="groupHost" autocomplete="off"
																placeholder="type here to search"
																aria-label="Host groups. type here to search"
																aria-required="false" />
														</div>
													</div>
												</div>
											</li>
											<li>
												<div class="table-forms-td-left">
													<label for="groupids_0_ms">Host name</label>
												</div>
												<div class="table-forms-td-right">
													<div class="multiselect-control">
														<div id="groupids_0" class="multiselect"
															role="application"
															data-params='{"objectOptions":{"with_hosts":1,"enrich_parent_groups":1},"id":"groupids_0","object_name":"hostGroup","name":"groupids[]","data":[{"name":"22tdt3","id":"22"}],"popup":{"parameters":{"multiselect":"1","srctbl":"host_groups","srcfld1":"groupid","dstfrm":"zbx_filter","dstfld1":"groupids_0","real_hosts":1,"enrich_parent_groups":1}},"url":"jsrpc.php?type=11&amp;method=multiselect.get&amp;object_name=hostGroup&amp;with_hosts=1&amp;enrich_parent_groups=1"}'
															style="width: 300px">
															<div class="selected">
																<ul class="multiselect-list"></ul>
															</div>
															<input id="groupids_0_ms" class="input" type="text"
																name="hostName" autocomplete="off"
																placeholder="type here to search"
																aria-label="Host groups. type here to search"
																aria-required="false" />
														</div>
													</div>
												</div>
											</li>
										</ul>
									</div>
								</div>
								<div class="filter-forms form-buttons">
									<button type="submit" name="filter_apply" value="1">Apply</button>
									<button type="reset" name="filter_reset" value="1"
										class="btn-alt">Reset</button>
								</div>

							</form>
						</div>
					</div>
				</div>
				<form method="post" action="zabbix.php" accept-charset="utf-8"
					name="host_view">
					<table class="list-table" id="t674c6754bc8a2511921729">
						<tr>
							<th style="font-weight: 700;">Host</th>
							<th style="font-weight: 700;">Name</th>
							<th style="font-weight: 700;">Last Value</th>
							
						</tr>
						<tbody>
							<tr>
								<td class="nowrap">${hostname} </td>
								<td class="nowrap">Host Name</td>
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
								<td class="nowrap">${list.getCPU().getValue()} </td>
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
								<td class="nowrap">${list.getRAM_total().getValue()}</td>
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
					<div class="table-paging">
						<nav class="paging-btn-container" role="navigation"
							aria-label="Pager">
							
							<br>
						</nav>
					</div>
				</form>
				
		</main>
		<output id="msg-global-footer" class="msg-global-footer msg-warning"
			style="left: 185px; width: 527px"></output>
		<footer role="contentinfo">
			PBL4: Giám sát thiết bị mạng <a class="grey link-alt" target="_blank"
				rel="noopener noreferrer" href="https://www.zabbix.com/">Nhóm
				Nguyễn Phan Thanh, Hồ Minh Ngọc, Lê Văn Hòa</a>
		</footer>
	</div>
</body>
</html>

