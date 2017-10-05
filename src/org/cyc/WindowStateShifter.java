package org.cyc;

import java.io.IOException;
import java.io.PrintWriter;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.GenericPortlet;
import javax.portlet.PortletException;
import javax.portlet.PortletSecurityException;
import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.UnavailableException;
import javax.portlet.WindowState;

public class WindowStateShifter extends GenericPortlet {

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.portlet.GenericPortlet#doView(javax.portlet.RenderRequest,
	 * javax.portlet.RenderResponse)
	 */
	@Override
	protected void doView(RenderRequest request, RenderResponse response)
			throws PortletException, IOException, UnavailableException {
		response.setContentType("text/html");

		//  Creates a portlet URL targeting the portlet, it is associated with action.
		PortletURL actionURL = response.createActionURL();
		// On clicking this actionURL, this will trigger the processAction() on the portlet.

		actionURL.setParameter("windowShifter", "true");
        System.out.println("actionURL -->"+actionURL.toString());
		PrintWriter writer = response.getWriter();
		writer.write("Hello World! I am your window state shifter. <BR> Your current window state is "+request.getWindowState());
		writer.write("<BR> To shift window states - ");
		writer.write("<a href=" + actionURL + "><B> Click here </B></a>");
		writer.close();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.portlet.Portlet#processAction(javax.portlet.ActionRequest,
	 * javax.portlet.ActionResponse)
	 */
	@Override
	public void processAction(ActionRequest request, ActionResponse response)
			throws PortletException, PortletSecurityException, IOException {
		// TODO Auto-generated method stub
		String reqParamAction = request.getParameter("windowShifter");

		if (reqParamAction != null) {
			WindowState windowState = request.getWindowState();

			if (windowState == WindowState.MAXIMIZED) {
				response.setWindowState(WindowState.NORMAL);
			}

			if (windowState == WindowState.NORMAL) {
				response.setWindowState(WindowState.MAXIMIZED);
			}

		}

	}

}
