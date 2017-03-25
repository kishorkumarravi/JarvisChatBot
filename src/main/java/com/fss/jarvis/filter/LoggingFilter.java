/**
 * @(#)LoggingFilter.java 13-Apr-2016 11:25:35 am
 *
 * Copyright 2016 Financial Software & Systems (P) Ltd. All Rights Reserved.
 *
 * This software is the proprietary information of Financial Software & Systems (P) Ltd.
 * Use is subject to license terms.
 */
package com.fss.jarvis.filter;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.nio.charset.Charset;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.atomic.AtomicLong;

import javax.annotation.Priority;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.client.ClientResponseContext;
import javax.ws.rs.client.ClientResponseFilter;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.ext.WriterInterceptor;
import javax.ws.rs.ext.WriterInterceptorContext;

import org.glassfish.jersey.message.MessageUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author <a href="mohammeda@fss.co.in">Abdullah</a>
 * @since
 * @version 1.0
 * @created date: 13-Apr-2016 11:25:35 am
 */

@Provider
@PreMatching
@Priority(Integer.MIN_VALUE)
@SuppressWarnings("ClassWithMultipleLoggers")
public final class LoggingFilter implements ContainerRequestFilter,
		ClientRequestFilter, ContainerResponseFilter, ClientResponseFilter,
		WriterInterceptor {

	private static final Logger LOGGER = LoggerFactory.getLogger("accesslog");
	private static final String NOTIFICATION_PREFIX = "* ";
	private static final String REQUEST_PREFIX = "> ";
	private static final String RESPONSE_PREFIX = "< ";
	private static final String ENTITY_LOGGER_PROPERTY = LoggingFilter.class
			.getName() + ".entityLogger";
	private static final String LOGGING_ID_PROPERTY = LoggingFilter.class
			.getName() + ".id";

	private static final Comparator<Map.Entry<String, List<String>>> COMPARATOR = new Comparator<Map.Entry<String, List<String>>>() {

		@Override
		public int compare(final Map.Entry<String, List<String>> o1,
				final Map.Entry<String, List<String>> o2) {
			return o1.getKey().compareToIgnoreCase(o2.getKey());
		}
	};

	private static final int DEFAULT_MAX_ENTITY_SIZE = 8 * 1024;

	//
	@SuppressWarnings("NonConstantLogger")
	private static final Logger LOGGERINTERNAL = LoggerFactory
			.getLogger(LoggingFilter.class);

	private static final int MAXENTITYSIZE = DEFAULT_MAX_ENTITY_SIZE;

	private final AtomicLong longId = new AtomicLong(0);
	private final boolean printEntity = true;

	/**
	 * Create a logging filter logging the request and response to a default JDK
	 * logger, named as the fully qualified class name of this class. Entity
	 * logging is turned off by default.z
	 */
	public LoggingFilter() {
		this(LOGGER, true);
	}

	/**
	 * Create a logging filter with custom logger and custom settings of entity
	 * logging.
	 *
	 * @param logger
	 *            the logger to log requests and responses.
	 * @param printEntity
	 *            if true, entity will be logged as well up to the default
	 *            MAXENTITYSIZE, which is 8KB
	 */
	@SuppressWarnings("BooleanParameter")
	public LoggingFilter(Logger logger, final boolean printEntity) {
		// this.logger = logger;
		// this.printEntity = printEntity;
		// this.MAXENTITYSIZE = DEFAULT_MAX_ENTITY_SIZE;
	}

	/**
	 * Creates a logging filter with custom logger and entity logging turned on,
	 * but potentially limiting the size of entity to be buffered and logged.
	 *
	 * @param logger
	 *            the logger to log requests and responses.
	 * @param MAXENTITYSIZE
	 *            maximum number of entity bytes to be logged (and buffered) -
	 *            if the entity is larger, logging filter will print (and buffer
	 *            in memory) only the specified number of bytes and print
	 *            "...more..." string at the end. Negative values are
	 *            interpreted as zero.
	 */
	public LoggingFilter(final Logger logger) {
		/*
		 * this.logger = logger; this.printEntity = true; this.MAXENTITYSIZE =
		 * Math.max(0, MAXENTITYSIZE);
		 */
	}

	private void log(final StringBuilder b) {
		if (LOGGERINTERNAL != null) {
			LOGGERINTERNAL.info("\n");
			if(b.toString().contains("clPn") && b.toString().contains("ofst")){
			
				LOGGERINTERNAL.info("Pin generated successfully");

			}else{
				
				LOGGERINTERNAL.info(b.toString());

			}
		}
	}

	private StringBuilder prefixId(final StringBuilder b, final long id) {
		b.append(Long.toString(id)).append(" ");
		return b;
	}

	private void printRequestLine(final StringBuilder b, final String note,
			final long id, final String method, final URI uri) {
		prefixId(b, id).append(NOTIFICATION_PREFIX).append(note)
				.append(" on thread ").append(Thread.currentThread().getName())
				.append("\n");
		prefixId(b, id).append(REQUEST_PREFIX).append(method).append(" ")
				.append(uri.toASCIIString()).append("\n");
	}

	private void printResponseLine(final StringBuilder b, final String note,
			final long id, final int status) {
		prefixId(b, id).append(NOTIFICATION_PREFIX).append(note)
				.append(" on thread ").append(Thread.currentThread().getName())
				.append("\n");
		prefixId(b, id).append(RESPONSE_PREFIX)
				.append(Integer.toString(status)).append("\n");
	}

	private void printPrefixedHeaders(final StringBuilder b, final long id,
			final String prefix, final MultivaluedMap<String, String> headers) {
		for (final Map.Entry<String, List<String>> headerEntry : getSortedHeaders(headers
				.entrySet())) {
			final List<?> val = headerEntry.getValue();
			final String header = headerEntry.getKey();

			if (val.size() == 1) {
				prefixId(b, id).append(prefix).append(header).append(": ")
						.append(val.get(0)).append("\n");
			} else {
				final StringBuilder sb = new StringBuilder();
				boolean add = false;
				for (final Object s : val) {
					if (add) {
						sb.append(',');
					}
					add = true;
					sb.append(s);
				}
				prefixId(b, id).append(prefix).append(header).append(": ")
						.append(sb.toString()).append("\n");
			}
		}
	}

	private Set<Map.Entry<String, List<String>>> getSortedHeaders(
			final Set<Map.Entry<String, List<String>>> headers) {
		final TreeSet<Map.Entry<String, List<String>>> sortedHeaders = new TreeSet<Map.Entry<String, List<String>>>(
				COMPARATOR);
		sortedHeaders.addAll(headers);
		return sortedHeaders;
	}

	private InputStream logInboundEntity(final StringBuilder b,
			InputStream stream, final Charset charset) throws IOException {
		if (!stream.markSupported()) {
			stream = new BufferedInputStream(stream);
		}
		stream.mark(MAXENTITYSIZE + 1);
		final byte[] entity = new byte[MAXENTITYSIZE + 1];
		final int entitySize = stream.read(entity);
		b.append(new String(entity, 0, Math.min(entitySize, MAXENTITYSIZE),
				charset));
		if (entitySize > MAXENTITYSIZE) {
			b.append("...more...");
		}
		b.append('\n');
		stream.reset();
		return stream;
	}

	@Override
	public void filter(final ClientRequestContext context) throws IOException {
		final long id = longId.incrementAndGet();
		context.setProperty(LOGGING_ID_PROPERTY, id);

		final StringBuilder b = new StringBuilder();

		printRequestLine(b, "Sending client request", id, context.getMethod(),
				context.getUri());
		printPrefixedHeaders(b, id, REQUEST_PREFIX, context.getStringHeaders());

		if (printEntity && context.hasEntity()) {
			final OutputStream stream = new LoggingStream(b,
					context.getEntityStream());
			context.setEntityStream(stream);
			context.setProperty(ENTITY_LOGGER_PROPERTY, stream);
			// not calling log(b) here - it will be called by the interceptor
		} else {
			log(b);
		}
	}

	@Override
	public void filter(final ClientRequestContext requestContext,
			final ClientResponseContext responseContext) throws IOException {
		final Object requestId = requestContext
				.getProperty(LOGGING_ID_PROPERTY);
		final long id = requestId != null ? (Long) requestId : longId
				.incrementAndGet();

		final StringBuilder b = new StringBuilder();

		printResponseLine(b, "Client response received", id,
				responseContext.getStatus());
		printPrefixedHeaders(b, id, RESPONSE_PREFIX,
				responseContext.getHeaders());

		if (printEntity && responseContext.hasEntity()) {
			responseContext.setEntityStream(logInboundEntity(b,
					responseContext.getEntityStream(),
					MessageUtils.getCharset(responseContext.getMediaType())));
		}

		log(b);
	}

	@Override
	public void filter(final ContainerRequestContext context)
			throws IOException {
		final long id = longId.incrementAndGet();
		context.setProperty(LOGGING_ID_PROPERTY, id);

		final StringBuilder b = new StringBuilder();

		printRequestLine(b, "(REQ) - Server has received a request", id,
				context.getMethod(), context.getUriInfo().getRequestUri());
		printPrefixedHeaders(b, id, REQUEST_PREFIX, context.getHeaders());

		if (printEntity && context.hasEntity()) {
			context.setEntityStream(logInboundEntity(b,
					context.getEntityStream(),
					MessageUtils.getCharset(context.getMediaType())));
		}

		log(b);
	}

	@Override
	public void filter(final ContainerRequestContext requestContext,
			final ContainerResponseContext responseContext) throws IOException {
		final Object requestId = requestContext
				.getProperty(LOGGING_ID_PROPERTY);
		final long id = requestId != null ? (Long) requestId : longId
				.incrementAndGet();

		final StringBuilder b = new StringBuilder();

		printResponseLine(b, "(RSP) - Server responded with a response", id,
				responseContext.getStatus());
		printPrefixedHeaders(b, id, RESPONSE_PREFIX,
				responseContext.getStringHeaders());

		if (printEntity && responseContext.hasEntity()) {
			final OutputStream stream = new LoggingStream(b,
					responseContext.getEntityStream());
			responseContext.setEntityStream(stream);
			requestContext.setProperty(ENTITY_LOGGER_PROPERTY, stream);
			// not calling log(b) here - it will be called by the interceptor
		} else {
			log(b);
		}
	}

	@Override
	public void aroundWriteTo(
			final WriterInterceptorContext writerInterceptorContext)
			throws IOException, WebApplicationException {
		final LoggingStream stream = (LoggingStream) writerInterceptorContext
				.getProperty(ENTITY_LOGGER_PROPERTY);
		writerInterceptorContext.proceed();
		if (stream != null) {
			log(stream.getStringBuilder(MessageUtils
					.getCharset(writerInterceptorContext.getMediaType())));
		}
	}

	private static class LoggingStream extends FilterOutputStream {

		private final StringBuilder b;
		private final ByteArrayOutputStream baos = new ByteArrayOutputStream();

		LoggingStream(final StringBuilder b, final OutputStream inner) {
			super(inner);

			this.b = b;
		}

		StringBuilder getStringBuilder(final Charset charset) {
			// write entity to the builder
			final byte[] entity = baos.toByteArray();

			b.append(new String(entity, 0, Math.min(entity.length,
					MAXENTITYSIZE), charset));
			if (entity.length > MAXENTITYSIZE) {
				b.append("...more...");
			}
			b.append('\n');

			return b;
		}

		@Override
		public void write(final int i) throws IOException {
			if (baos.size() <= MAXENTITYSIZE) {
				baos.write(i);
			}
			out.write(i);
		}
	}
}
