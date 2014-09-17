package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class index_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList(2);
    _jspx_dependants.add("/WEB-INF/layouts/base.jsp");
    _jspx_dependants.add("/WEB-INF/layouts/reource.jsp");
  }

  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.AnnotationProcessor _jsp_annotationprocessor;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_annotationprocessor = (org.apache.AnnotationProcessor) getServletConfig().getServletContext().getAttribute(org.apache.AnnotationProcessor.class.getName());
  }

  public void _jspDestroy() {
    _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.release();
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=utf-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\r\n");
      out.write("<html>\r\n");
      out.write("<html lang=\"en\" class=\"no-js\">\r\n");
      out.write("<head>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      if (_jspx_meth_c_005fset_005f0(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\r\n");
      out.write("<meta http-equiv=\"Pragma\" content=\"no-cache\" />\r\n");
      out.write("<!-- <meta http-equiv=\"Content-Type\" content=\"text/html;charset=utf-8\" /> -->\r\n");
      out.write("<!-- <meta http-equiv=\"Cache-Control\" content=\"no-store\" />\r\n");
      out.write("\r\n");
      out.write("<meta http-equiv=\"Expires\" content=\"0\" />\r\n");
      out.write("<meta content=\"width=device-width, initial-scale=1.0\" name=\"viewport\" />\r\n");
      out.write("<meta content=\"\" name=\"description\" /> \r\n");
      out.write("<meta content=\"\" name=\"author\" />-->\r\n");
      out.write("\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("\tvar rootPath = '");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("';\r\n");
      out.write("</script>\r\n");
      out.write('\r');
      out.write('\n');
      out.write("<link href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/global/bootstrap/css/bootstrap.min.css\"\r\n");
      out.write("\trel=\"stylesheet\" type=\"text/css\" />\r\n");
      out.write("<link href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/global/font-awesome/css/font-awesome.min.css\"\r\n");
      out.write("\trel=\"stylesheet\" type=\"text/css\" />\r\n");
      out.write("<link href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/global/bootstrap/css/bootstrap.min.css\"\r\n");
      out.write("\trel=\"stylesheet\" type=\"text/css\" />\r\n");
      out.write("<link href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/global/uniform/css/uniform.default.css\"\r\n");
      out.write("\trel=\"stylesheet\" type=\"text/css\" />\r\n");
      out.write("<link href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/global/css/style-metronic.css\" rel=\"stylesheet\"\r\n");
      out.write("\ttype=\"text/css\" />\r\n");
      out.write("<link href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/global/css/style.css\" rel=\"stylesheet\"\r\n");
      out.write("\ttype=\"text/css\" />\r\n");
      out.write("<link href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/global/css/style-responsive.css\" rel=\"stylesheet\"\r\n");
      out.write("\ttype=\"text/css\" />\r\n");
      out.write("<link href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/global/css/default.css\" rel=\"stylesheet\"\r\n");
      out.write("\ttype=\"text/css\" />\r\n");
      out.write("<link href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/global/css/custom.css\" rel=\"stylesheet\"\r\n");
      out.write("\ttype=\"text/css\" />\r\n");
      out.write("\r\n");
      out.write("\t\r\n");
      out.write("<script type=\"text/javascript\"\r\n");
      out.write("\tsrc=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/global/js/jquery-1.10.2.min.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\"\r\n");
      out.write("\tsrc=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/global/jquery-ui/jquery-ui-1.10.3.custom.min.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\"\r\n");
      out.write("\tsrc=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/global/bootstrap/js/bootstrap.min.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\"\r\n");
      out.write("\tsrc=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/global/bootstrap-hover-dropdown/bootstrap-hover-dropdown.min.js\"></script>\r\n");
      out.write("<script src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/global/js/app.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\"\r\n");
      out.write("\tsrc=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/global/js/respond.js\"></script>\r\n");
      out.write("\r\n");
      out.write("<title>计划任务业务数据管理系统</title>\r\n");
      out.write("<link rel=\"shortcut icon\" href=\"favicon.ico\" />\r\n");
      out.write("</head>\r\n");
      out.write("<!-- Piwik -->\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("    var _paq = _paq || [];\r\n");
      out.write("    _paq.push(['trackPageView']);\r\n");
      out.write("    _paq.push(['enableLinkTracking']);\r\n");
      out.write("    (function() {\r\n");
      out.write("        var u=((\"https:\" == document.location.protocol) ? \"https\" : \"http\") + \"://localhost:8081/piwik/\";\r\n");
      out.write("        _paq.push(['setTrackerUrl', u+'receive']);\r\n");
      out.write("        _paq.push(['setSiteId', 1]);\r\n");
      out.write("        var d=document, g=d.createElement('script'), s=d.getElementsByTagName('script')[0]; g.type='text/javascript';\r\n");
      out.write("        g.defer=true; g.async=true; g.src=u+'static/js/piwik.js'; s.parentNode.insertBefore(g,s);\r\n");
      out.write("    })();\r\n");
      out.write("</script>\r\n");
      out.write("\r\n");
      out.write("<!-- End Piwik Code -->\r\n");
      out.write("<body class=\"page-header-fixed    page-footer-fixed\">\r\n");
      out.write("\t<div class=\"header navbar navbar-fixed-top\">\r\n");
      out.write("\t\t<div class=\"header-inner\">\r\n");
      out.write("\t\t\t<a class=\"navbar-brand\">\r\n");
      out.write("                ");
      out.write("\r\n");
      out.write("            </a>\r\n");
      out.write("            <a href=\"javascript:;\" class=\"navbar-toggle\" data-toggle=\"collapse\" data-target=\".navbar-collapse\"> <img src=\"global/img/sidebar-toggler.jpg\" />\r\n");
      out.write("\t\t\t</a>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t</div>\r\n");
      out.write("\t<div class=\"clearfix\"></div>\r\n");
      out.write("\t<div class=\"page-container\">\r\n");
      out.write("\r\n");
      out.write("\t\t<div class=\"page-sidebar navbar-collapse collapse\">\r\n");
      out.write("\t\t\t<ul class=\"page-sidebar-menu\" data-auto-scroll=\"true\"\r\n");
      out.write("\t\t\t\tdata-slide-speed=\"200\">\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t<li class=\"sidebar-toggler-wrapper\">\r\n");
      out.write("\t\t\t\t\t<!-- BEGIN SIDEBAR TOGGLER BUTTON -->\r\n");
      out.write("\t\t\t\t\t<div class=\"sidebar-toggler hidden-phone\"></div> <!-- BEGIN SIDEBAR TOGGLER BUTTON -->\r\n");
      out.write("\t\t\t\t</li>\r\n");
      out.write("\t\t\t\t<li class=\"start\"><a class=\" start\"> <i class=\"fa fa-home\"></i>\r\n");
      out.write("\t\t\t\t\t\t<span class=\"title\">定时任务管理</span> <span class=\"selected\"> </span><span\r\n");
      out.write("\t\t\t\t\t\tclass=\"arrow open\"> </span>\r\n");
      out.write("\t\t\t\t</a>\r\n");
      out.write("\t\t\t\t\t<ul class=\"sub-menu\">\r\n");
      out.write("\t\t\t\t\t\t<li><a class=\"ajaxify start\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/app/list\"> <i\r\n");
      out.write("\t\t\t\t\t\t\t\tclass=\"fa fa-cogs\"></i> <span class=\"title\"> 应用查看 </span> <span\r\n");
      out.write("\t\t\t\t\t\t\t\tclass=\"selected\"/></a></li>\r\n");
      out.write("\t\t\t\t\t\t<li><a class=\"ajaxify\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/job/list\"> <i\r\n");
      out.write("\t\t\t\t\t\t\t\tclass=\"fa fa-cogs\"></i> <span class=\"title\">任务和计划查看 </span> <span\r\n");
      out.write("\t\t\t\t\t\t\t\tclass=\"selected\"/></a></li>\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\t\t<li><a> <i class=\"fa fa-cogs\"></i> <span class=\"title\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t日志管理 </span> <span class=\"selected\"> </span><span class=\"arrow\">\r\n");
      out.write("\t\t\t\t\t\t\t</span>\r\n");
      out.write("\t\t\t\t\t\t</a>\r\n");
      out.write("\t\t\t\t\t\t\t<ul class=\"sub-menu\">\r\n");
      out.write("\t\t\t\t\t\t\t\t<li><a class=\"ajaxify\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/log/view\">日志查看</a></li>\r\n");
      out.write("\t\t\t\t\t\t\t\t<li><a class=\"ajaxify\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/log/statistics\">任务统计</a></li>\r\n");
      out.write("\t\t\t\t\t\t\t</ul></li>\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\t\t<li><a> <i class=\"fa fa-cogs\"></i> <span class=\"title\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t提醒管理 </span> <span class=\"selected\"> </span><span class=\"arrow\">\r\n");
      out.write("\t\t\t\t\t\t\t</span>\r\n");
      out.write("\t\t\t\t\t\t</a>\r\n");
      out.write("\t\t\t\t\t\t\t<ul class=\"sub-menu\">\r\n");
      out.write("\t\t\t\t\t\t\t\t<li><a class=\"ajaxify\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/contacts/configinfo\">配置应用联系人</a></li>\r\n");
      out.write("\t\t\t\t\t\t\t\t<li><a class=\"ajaxify\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/contacts/view\">联系人管理</a></li>\r\n");
      out.write("\t\t\t\t\t\t\t</ul></li>\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\t</ul></li>\r\n");
      out.write("\r\n");
      out.write("\t\t\t</ul>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t\t<div class=\"page-content-wrapper\">\r\n");
      out.write("\t\t\t<div id=\"nomal-content\" class=\"page-content\">\r\n");
      out.write("\t\t\t\t<div class=\"theme-panel hidden-xs hidden-sm\">\r\n");
      out.write("\t\t\t\t\t<div class=\"toggler-close\"></div>\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t<div class=\"page-content-body\"></div>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\t\t<div class=\"footer\">\r\n");
      out.write("\t\t\t<div class=\"footer-inner\">2014 &copy; Design by one.</div>\r\n");
      out.write("\t\t\t<div class=\"footer-tools\">\r\n");
      out.write("\t\t\t\t<span class=\"go-top\"> <i class=\"fa fa-angle-up\"></i>\r\n");
      out.write("\t\t\t\t</span>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\r\n");
      out.write("\t</div>\r\n");
      out.write("\r\n");
      out.write("\t<script>\r\n");
      out.write("\t\tjQuery(document).ready(function() {\r\n");
      out.write("\t\t\tApp.init();\r\n");
      out.write("\t\t\t$('.page-sidebar .ajaxify.start').click() // load the content for the dashboard page.\r\n");
      out.write("\t\t});\r\n");
      out.write("\t</script>\r\n");
      out.write("\t<!-- END JAVASCRIPTS -->\r\n");
      out.write("\t<script type=\"text/javascript\">\r\n");
      out.write("\t\tvar _gaq = _gaq || [];\r\n");
      out.write("\t\t_gaq.push([ '_setAccount', 'UA-37564768-1' ]);\r\n");
      out.write("\t\t_gaq.push([ '_setDomainName', 'keenthemes.com' ]);\r\n");
      out.write("\t\t_gaq.push([ '_setAllowLinker', true ]);\r\n");
      out.write("\t\t_gaq.push([ '_trackPageview' ]);\r\n");
      out.write("\t\t(function() {\r\n");
      out.write("\t\t\tvar ga = document.createElement('script');\r\n");
      out.write("\t\t\tga.type = 'text/javascript';\r\n");
      out.write("\t\t\tga.async = true;\r\n");
      out.write("\t\t\tga.src = ('https:' == document.location.protocol ? 'https://'\r\n");
      out.write("\t\t\t\t\t: 'http://')\r\n");
      out.write("\t\t\t\t\t+ 'stats.g.doubleclick.net/dc.js';\r\n");
      out.write("\t\t\tvar s = document.getElementsByTagName('script')[0];\r\n");
      out.write("\t\t\ts.parentNode.insertBefore(ga, s);\r\n");
      out.write("\t\t})();\r\n");
      out.write("\r\n");
      out.write("\t</script>\r\n");
      out.write("\r\n");
      out.write("</body>\r\n");
      out.write("<!-- END BODY -->\r\n");
      out.write("</html>");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }

  private boolean _jspx_meth_c_005fset_005f0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:set
    org.apache.taglibs.standard.tag.rt.core.SetTag _jspx_th_c_005fset_005f0 = (org.apache.taglibs.standard.tag.rt.core.SetTag) _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.rt.core.SetTag.class);
    _jspx_th_c_005fset_005f0.setPageContext(_jspx_page_context);
    _jspx_th_c_005fset_005f0.setParent(null);
    // /WEB-INF/layouts/base.jsp(4,0) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fset_005f0.setVar("ctx");
    // /WEB-INF/layouts/base.jsp(4,0) name = value type = javax.el.ValueExpression reqTime = true required = false fragment = false deferredValue = true expectedTypeName = java.lang.Object deferredMethod = false methodSignature = null
    _jspx_th_c_005fset_005f0.setValue(new org.apache.jasper.el.JspValueExpression("/WEB-INF/layouts/base.jsp(4,0) '${pageContext.request.contextPath}'",_el_expressionfactory.createValueExpression(_jspx_page_context.getELContext(),"${pageContext.request.contextPath}",java.lang.Object.class)).getValue(_jspx_page_context.getELContext()));
    int _jspx_eval_c_005fset_005f0 = _jspx_th_c_005fset_005f0.doStartTag();
    if (_jspx_th_c_005fset_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.reuse(_jspx_th_c_005fset_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.reuse(_jspx_th_c_005fset_005f0);
    return false;
  }
}
