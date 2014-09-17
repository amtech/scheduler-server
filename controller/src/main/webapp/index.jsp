<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<html lang="en" class="no-js">
<head>
<%@ include file="/WEB-INF/layouts/base.jsp"%>
<%@ include file="/WEB-INF/layouts/reource.jsp"%>
<title>计划任务业务数据管理系统</title>
<link rel="shortcut icon" href="favicon.ico" />
</head>
<!-- Piwik -->
<script type="text/javascript">
    var _paq = _paq || [];
    _paq.push(['trackPageView']);
    _paq.push(['enableLinkTracking']);
    (function() {
        var u=(("https:" == document.location.protocol) ? "https" : "http") + "://localhost:8081/piwik/";
        _paq.push(['setTrackerUrl', u+'receive']);
        _paq.push(['setSiteId', 1]);
        var d=document, g=d.createElement('script'), s=d.getElementsByTagName('script')[0]; g.type='text/javascript';
        g.defer=true; g.async=true; g.src=u+'static/js/piwik.js'; s.parentNode.insertBefore(g,s);
    })();
</script>
<%--<noscript><p><img src="http://localhost/piwik-master/piwik.php?idsite=1" style="border:0;" alt="" /></p></noscript>--%>
<!-- End Piwik Code -->
<body class="page-header-fixed    page-footer-fixed">
	<div class="header navbar navbar-fixed-top">
		<div class="header-inner">
			<a class="navbar-brand">
                <%--<img class="img-responsive" alt="logo" src="global/img/logo.png">--%>
            </a>
            <a href="javascript:;" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse"> <img src="global/img/sidebar-toggler.jpg" />
			</a>
		</div>
	</div>
	<div class="clearfix"></div>
	<div class="page-container">

		<div class="page-sidebar navbar-collapse collapse">
			<ul class="page-sidebar-menu" data-auto-scroll="true"
				data-slide-speed="200">

				<li class="sidebar-toggler-wrapper">
					<!-- BEGIN SIDEBAR TOGGLER BUTTON -->
					<div class="sidebar-toggler hidden-phone"></div> <!-- BEGIN SIDEBAR TOGGLER BUTTON -->
				</li>
				<li class="start"><a class=" start"> <i class="fa fa-home"></i>
						<span class="title">定时任务管理</span> <span class="selected"> </span><span
						class="arrow open"> </span>
				</a>
					<ul class="sub-menu">
						<li><a class="ajaxify start" href="${ctx}/app/list"> <i
								class="fa fa-cogs"></i> <span class="title"> 应用查看 </span> <span
								class="selected"/></a></li>
						<li><a class="ajaxify" href="${ctx}/job/list"> <i
								class="fa fa-cogs"></i> <span class="title">任务和计划查看 </span> <span
								class="selected"/></a></li>

						<li><a> <i class="fa fa-cogs"></i> <span class="title">
									日志管理 </span> <span class="selected"> </span><span class="arrow">
							</span>
						</a>
							<ul class="sub-menu">
								<li><a class="ajaxify" href="${ctx}/log/view">日志查看</a></li>
								<li><a class="ajaxify" href="${ctx}/log/statistics">任务统计</a></li>
							</ul></li>

						<li><a> <i class="fa fa-cogs"></i> <span class="title">
									提醒管理 </span> <span class="selected"> </span><span class="arrow">
							</span>
						</a>
							<ul class="sub-menu">
								<li><a class="ajaxify" href="${ctx}/contacts/configinfo">配置应用联系人</a></li>
								<li><a class="ajaxify" href="${ctx}/contacts/view">联系人管理</a></li>
							</ul></li>

					</ul></li>

			</ul>
		</div>
		<div class="page-content-wrapper">
			<div id="nomal-content" class="page-content">
				<div class="theme-panel hidden-xs hidden-sm">
					<div class="toggler-close"></div>

				</div>
				<div class="page-content-body"></div>
			</div>
		</div>


		<div class="footer">
			<div class="footer-inner">2014 &copy; Design by one.</div>
			<div class="footer-tools">
				<span class="go-top"> <i class="fa fa-angle-up"></i>
				</span>
			</div>
		</div>

	</div>

	<script>
		jQuery(document).ready(function() {
			App.init();
			$('.page-sidebar .ajaxify.start').click() // load the content for the dashboard page.
		});
	</script>
	<!-- END JAVASCRIPTS -->
	<script type="text/javascript">
		var _gaq = _gaq || [];
		_gaq.push([ '_setAccount', 'UA-37564768-1' ]);
		_gaq.push([ '_setDomainName', 'keenthemes.com' ]);
		_gaq.push([ '_setAllowLinker', true ]);
		_gaq.push([ '_trackPageview' ]);
		(function() {
			var ga = document.createElement('script');
			ga.type = 'text/javascript';
			ga.async = true;
			ga.src = ('https:' == document.location.protocol ? 'https://'
					: 'http://')
					+ 'stats.g.doubleclick.net/dc.js';
			var s = document.getElementsByTagName('script')[0];
			s.parentNode.insertBefore(ga, s);
		})();

	</script>

</body>
<!-- END BODY -->
</html>