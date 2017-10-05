package org.cyc;

import java.io.IOException;
import java.io.PrintWriter;
import javax.portlet.GenericPortlet;
import javax.portlet.PortletException;
import javax.portlet.PortletSecurityException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.UnavailableException;

public class MultipleModePortlet extends GenericPortlet {

	/* (non-Javadoc)
	 * @see javax.portlet.GenericPortlet#doView(javax.portlet.RenderRequest, javax.portlet.RenderResponse)
	 */
	@Override
	protected void doView(RenderRequest request, RenderResponse response)
			throws PortletException, IOException, UnavailableException {
		String language = request.getLocale().getDisplayLanguage();
		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		writer.write("In View Mode . You are requested to speak in your locale language as : "+language + " <br> Your country of dwelling is "+ request.getLocale().getDisplayCountry());
		writer.close();
	}

	/* (non-Javadoc)
	 * @see javax.portlet.GenericPortlet#doEdit(javax.portlet.RenderRequest, javax.portlet.RenderResponse)
	 */
	@Override
	protected void doEdit(RenderRequest request, RenderResponse response)
			throws PortletException, PortletSecurityException, IOException {
		// TODO Auto-generated method stub
		//super.doEdit(request, response);
		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		writer.write("In Edit Mode");
		writer.close();
	
	}

	/* (non-Javadoc)
	 * @see javax.portlet.GenericPortlet#doHelp(javax.portlet.RenderRequest, javax.portlet.RenderResponse)
	 */
	@Override
	protected void doHelp(RenderRequest request, RenderResponse response)
			throws PortletException, PortletSecurityException, IOException {
		// TODO Auto-generated method stub
		//super.doHelp(request, response);
		PrintWriter writer = response.getWriter();
		writer.write("In Help Mode");
		writer.close();
	}
}
