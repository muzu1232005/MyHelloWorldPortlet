<%@taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet"%>
<%@ page contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" language="java"%>
<portlet:defineObjects/>



<%=renderRequest.getPortletSession().getAttribute("message") %>

Think of number from 1 to 7 <BR/>

<portlet:actionURL var="URL"/>

<% if(renderRequest.getPortletSession().getAttribute("successmessage") != null) {
	out.write((String)renderRequest.getPortletSession().getAttribute("successmessage"));
	}
	%>


<form action='<%= URL %>' method="POST">
<input  name="guessednum" id="guessednum" type="text" value="<%= renderRequest.getParameter("guessednum") == null ? 0 : renderRequest.getParameter("guessednum")%>"/>
<input  type="submit" value="Guess"/>
</form>


No of guesses : <%=renderRequest.getPortletSession().getAttribute("guessess") %>