package org.cyc;

import java.io.IOException;
import java.util.Calendar;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.GenericPortlet;
import javax.portlet.PortletContext;
import javax.portlet.PortletException;
import javax.portlet.PortletSecurityException;
import javax.portlet.PortletSession;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.UnavailableException;

public class NumberGuesser extends GenericPortlet {

	/* (non-Javadoc)
	 * @see javax.portlet.GenericPortlet#doView(javax.portlet.RenderRequest, javax.portlet.RenderResponse)
	 */
	@Override
	protected void doView(RenderRequest request, RenderResponse response)
			throws PortletException, IOException, UnavailableException {

		response.setContentType("text/html");
		
		PortletContext portletCtxt = this.getPortletContext();
		System.out.println("portletCtxt -->" + portletCtxt.getPortletContextName());

		System.out.println("in doView ->"+request.getParameterMap() +" <> "+request.getParameterNames());
		
		PortletSession portletSession = request.getPortletSession();
        
		if (portletSession.getAttribute("magicnum") == null ) {
			portletSession.setAttribute("magicnum",
					String.valueOf(Calendar.DAY_OF_WEEK));		
		}
		
		if (portletSession.getAttribute("guessess") == null) {
			portletSession.setAttribute("guessess", String.valueOf(0));
			portletSession.setAttribute("message",
					"Guess number (clue as time line) from 1 to 7");			
		}
		
        String urlPage = "/numberguesser.jsp";
		
		portletCtxt.getRequestDispatcher(urlPage).include(request, response);
		
	}

	/* (non-Javadoc)
	 * @see javax.portlet.Portlet#processAction(javax.portlet.ActionRequest, javax.portlet.ActionResponse)
	 */
	@Override
	public void processAction(ActionRequest request, ActionResponse response)
			throws PortletException, PortletSecurityException, IOException {
		System.out.println("in processAction->" + request.getParameterMap());
		System.out.println("request.getParameter of guessednum "
				+ request.getParameter("guessednum"));

		String numberStr = request.getParameter("guessednum");
		response.setRenderParameter("guessednum", numberStr);
		PortletSession portletSession = request.getPortletSession();
		System.out.println("request.getParameter of number " + numberStr);
		if (numberStr != null) {
			String magicNumStr = (String) portletSession
					.getAttribute("magicnum");
			String guessNumStr = (String) String.valueOf(portletSession
					.getAttribute("guessess"));
			int number = Integer.parseInt(numberStr);
			int magicNum = Integer.parseInt(magicNumStr);
			portletSession.setAttribute("guessess",
					(Integer.parseInt(guessNumStr) + 1));

			if (number < magicNum) {
				portletSession.setAttribute("message",
						"Guess a higher number from the number (clue as time line) "
								+ number + " from 1 to 7");
			} else if(number > magicNum) {
				portletSession.setAttribute("message",
						"Guess a lower number from the (clue as time line)"
								+ number + " from 1 to 7");
			}

			if (number == magicNum) {
				portletSession.setAttribute("successmessage", "Magic number "
						+ magicNum + " is correct !!! Play again");
				portletSession.removeAttribute("magicnum");
				portletSession.removeAttribute("guessess");
			} else {
				portletSession.removeAttribute("successmessage");
			}
		}

	}
}
