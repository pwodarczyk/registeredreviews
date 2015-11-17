package com.registeredreviews.web.filter;

import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.registeredreviews.mobile.Constants;
import com.registeredreviews.web.listener.MobileListener;

import fiftyone.mobile.detection.matchers.Result;
import fiftyone.mobile.detection.webapp.Provider;

public class MobileDetectionFilter implements Filter {

	/**
	 * Used to store the result for the current request in the
	 * HttpServletRequest's attribute collection.
	 */
	private static final String RESULT_ATTIBUTE = "51D_RESULT";
	private MobileListener _factory = null;
	final Logger logger = LoggerFactory.getLogger(MobileDetectionFilter.class);

	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		logger.debug("Accessing MobileDetectionFilter........");

		// ONLY call this functionality if the mobile detection has not been
		// called
		if (request.getSession().getAttribute(Constants.IS_MOBILE_DEVICE) == null) {

			Result result = _factory.getProvider().getResult(request);
			String propertyName = "IsMobile";
			final List<String> values = result.getPropertyValues(propertyName);
			Boolean isMobile = Boolean.FALSE;
			if (values.contains("False"))
				isMobile = Boolean.FALSE;
			else
				isMobile = Boolean.TRUE;

			request.getSession().setAttribute(Constants.IS_MOBILE_DEVICE,
					isMobile);

		}
		chain.doFilter(req, res);
	}

	public void init(FilterConfig config) throws ServletException {
		logger.info("Initializing MobileDetectionFilter........");
		_factory = (MobileListener) config.getServletContext().getAttribute(
				Constants.FACTORY_KEY);
		logger.info("Getting MobileListener from key: " + Constants.FACTORY_KEY);
		if (_factory != null) {
			logger.info("51Degrees.mobi Servlet Initialised");
		} else {
			throw new ServletException(
					"51Degrees.mobi listener is not available. "
							+ "Check the class fiftyone.mobile.detection.webapp.Listener "
							+ "is registered in the web.xml file.");
		}
	}

	public void destroy() {
		// add code to release any resource
	}

	/**
	 * @return the currently active detection provider.
	 */
	protected Provider getProvider() {
		return _factory.getProvider();
	}

	/**
	 * Returns the result set associated with the request provided. If this is
	 * the first time the method is called the result will be stored in the
	 * HttpServletRequest's attribute collection so that it does not need to be
	 * fetched again in the future.
	 * 
	 * @param request
	 *            the request property results are for.
	 * @return a set of results containing access to properties.
	 */
	protected Result getResult(final HttpServletRequest request)
			throws ServletException {
		// Check to see if the result has already been fetched.
		Object previousResult = request.getAttribute(RESULT_ATTIBUTE);
		if (previousResult instanceof Result == false) {
			previousResult = _factory.getProvider().getResult(request);
			request.setAttribute(RESULT_ATTIBUTE, previousResult);
		}
		return (Result) previousResult;
	}

	/**
	 * Returns the value associated with the device property requested.
	 * 
	 * @param request
	 *            the request property results are for.
	 * @param propertyName
	 *            device property name required.
	 * @return
	 */
	protected String getProperty(final HttpServletRequest request,
			final String propertyName) throws ServletException {
		final Result result = getResult(request);
		if (result != null) {
			final List<String> values = result.getPropertyValues(propertyName);
			if (values != null) {
				return join(values);
			}
		}
		return null;
	}

	/**
	 * Joins a list of strings into a comma separated string.
	 * 
	 * @param values
	 *            list of values to join
	 * @return single string comma separated
	 */
	protected String join(final List<String> values) {
		final StringBuilder builder = new StringBuilder();
		for (int i = 0; i < values.size(); i++) {
			builder.append(values.get(i));
			if (i + 1 < values.size()) {
				builder.append(", ");
			}
		}
		return builder.toString();
	}

}
