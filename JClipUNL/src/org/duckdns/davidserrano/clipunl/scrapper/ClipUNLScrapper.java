package org.duckdns.davidserrano.clipunl.scrapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.duckdns.davidserrano.clipunl.ClipUNLSession;
import org.duckdns.davidserrano.clipunl.exceptions.NetworkErrorException;
import org.duckdns.davidserrano.clipunl.util.ClipUNLConstants;
import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class ClipUNLScrapper {

	protected static Document lastDocument;

	protected static Document getDocument(final ClipUNLSession session,
			final String url, final Map<String, String> data,
			final Method method) {

		try {
			final Response response = Jsoup
					.connect(ClipUNLConstants.CLIP_SERVER + url).data(data)
					.cookies(session.getCookies()).execute().method(method);

			session.setCookies(response.cookies());
			lastDocument = response.parse();
			return lastDocument;

		} catch (IOException e) {
			throw new NetworkErrorException(url);
		}

	}

	protected static Document getDocument(final ClipUNLSession session,
			final String url, final Map<String, String> data) {
		return getDocument(session, url, data, Method.POST);
	}

	protected static Document getDocument(final ClipUNLSession session,
			final String url) {
		return getDocument(session, url, new HashMap<String, String>(), Method.GET);
	}

	protected static Document getLastDocument() {
		return lastDocument;
	}
}