package org.cyc;

import java.io.IOException;
import java.io.PrintWriter;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.GenericPortlet;
import javax.portlet.PortletException;
import javax.portlet.PortletSecurityException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.UnavailableException;

public class SimplePortletSession extends GenericPortlet {

	/* (non-Javadoc)
	 * @see javax.portlet.GenericPortlet#doView(javax.portlet.RenderRequest, javax.portlet.RenderResponse)
	 */
	@Override
	protected void doView(RenderRequest request, RenderResponse response)
			throws PortletException, IOException, UnavailableException {
		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();

		String timesVisited = (String) request.getPortletSession()
				.getAttribute("timesvisited");

		if (timesVisited == null) {
			timesVisited = Integer.toString(1);
			request.getPortletSession().setAttribute("timesvisited",
					timesVisited);
		} else {
			int cntr = Integer.parseInt(timesVisited) + 1;
			timesVisited = Integer.toString(cntr);
			request.getPortletSession().setAttribute("timesvisited",
					timesVisited);
		}

		writer.write("Portlet Session . You had visited this portlet for the time number :"
				+ timesVisited);
		writer.close();
	}

	/* 
	 * Always manage portlet session related code within the action part of the portlet. 
	 * (non-Javadoc)
	 * @see javax.portlet.Portlet#processAction(javax.portlet.ActionRequest, javax.portlet.ActionResponse)
	 */
	@Override
	public void processAction(ActionRequest request, ActionResponse response)
			throws PortletException, PortletSecurityException, IOException {
		// TODO Auto-generated method stub
		super.processAction(request, response);
	}
}
