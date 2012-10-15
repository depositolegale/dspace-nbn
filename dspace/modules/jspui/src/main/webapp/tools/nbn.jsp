<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.dspace.org/dspace-tags.tld" prefix="dspace" %>

<%@ page import="java.io.*" %>
<%@ page import="java.net.*" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="org.apache.log4j.Logger" %>
<%@ page import="org.dspace.app.webui.util.JSPManager" %>
<%@ page import="org.dspace.identifier.NbnIdentifierProvider" %>
<%@ page import="org.apache.commons.codec.binary.*" %>
<%@ page import="org.apache.commons.httpclient.*" %>
<%@ page import="org.apache.commons.httpclient.auth.AuthScope" %>
<%@ page import="org.apache.commons.httpclient.methods.PostMethod" %>
<%@ page import="org.apache.commons.httpclient.methods.StringRequestEntity" %>
<%@ page import="org.dspace.core.ConfigurationManager" %>
<%@ page import="org.dspace.content.Item" %>
<%@ page import="org.dspace.core.Context" %>
<%@ page import="org.dspace.core.LogManager" %>
<%@ page import="org.dspace.content.DCValue" %>
<%@ page import="org.dspace.app.webui.util.UIUtil" %>
<%@ page import="org.apache.commons.httpclient.util.EncodingUtil" %>


<dspace:layout locbar="link" titlekey="jsp.home.title" nocache="true">

<b>Assign NBN</b>
<p>
<%

String MyNBN= "";
MyNBN= NbnIdentifierProvider.register(request.getParameter("handle"));

if (!MyNBN.equals(""))

{

Context context = null;
Item mioItem = null;

try {

 context = UIUtil.obtainContext(request);
 mioItem = Item.find(context, Integer.parseInt(request.getParameter("item_id")));
 mioItem.addMetadata("dc","identifier","nbn",null,MyNBN);
 mioItem.update();
 context.complete();

   }
 
   catch(SQLException se)
    {
    	// Database error occurred.
        Logger log = Logger.getLogger("org.dspace.jsp");
        log.warn(LogManager.getHeader(context,
                "database_error",
                se.toString()), se);

        // Also email an alert
        UIUtil.sendAlert(request, se);
      JSPManager.showInternalError(request, response);
    }
  



out.print("ASSIGNED NBN: <a href="+request.getContextPath()+"/tools/edit-item?item_id="+request.getParameter("item_id")+">"+MyNBN+"</a></br>");




}

else { 

out.print("ERROR: NBN not assigned: <a href="+request.getContextPath()+"/tools/edit-item?item_id="+request.getParameter("item_id")+">"+MyNBN+"</a></br>");


}


         
%>

</dspace:layout>
