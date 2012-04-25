package com.pellcorp.android.vline.offline;

import java.net.URI;

import org.apache.http.HttpResponse;
import org.apache.http.ProtocolException;
import org.apache.http.protocol.HttpContext;

public class DefaultRedirectHandler extends
		org.apache.http.impl.client.DefaultRedirectHandler {
	public URI lastRedirectedUri;

	public DefaultRedirectHandler() {
	}

	@Override
	public URI getLocationURI(HttpResponse response, HttpContext context)
			throws ProtocolException {

		lastRedirectedUri = super.getLocationURI(response, context);
		return lastRedirectedUri;
	}

	public URI getLastRedirectedUri() {
		return lastRedirectedUri;
	}
}
