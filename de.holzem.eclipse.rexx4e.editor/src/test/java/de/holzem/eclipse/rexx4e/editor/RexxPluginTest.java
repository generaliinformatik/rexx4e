/**
 *  Copyright (c) 2020 Markus Holzem
 *
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v2.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v20.html
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 *  Contributors:
 *  Markus Holzem <markus@holzem.de>
 */
package de.holzem.eclipse.rexx4e.editor;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.io.InputStream;
import java.util.Collection;
import java.util.Dictionary;

import org.eclipse.core.runtime.AssertionFailedException;
import org.junit.jupiter.api.Test;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;
import org.osgi.framework.BundleListener;
import org.osgi.framework.Filter;
import org.osgi.framework.FrameworkListener;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceFactory;
import org.osgi.framework.ServiceListener;
import org.osgi.framework.ServiceObjects;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;

/**
 * RexxPluginTest
 *
 * @author Markus Holzem
 */
public class RexxPluginTest
{
	@Test
	void test()
	{
		final RexxPlugin rexxPlugin = new RexxPlugin();
		final BundleContext ctx = new MyBundleContext();
		try {
			rexxPlugin.start(ctx);
		} catch (final Exception exc) {
			fail("unexpected exception " + exc.toString());
		}
		assertThat(RexxPlugin.getDefault(), is(equalTo(rexxPlugin)));
		try {
			rexxPlugin.stop(ctx);
		} catch (final Exception exc) {
			assertThat(exc, is(instanceOf(AssertionFailedException.class)));
			RexxPlugin.logError(exc);
		}
		assertThat(RexxPlugin.getDefault(), is(nullValue()));
	}

	private class MyBundleContext implements BundleContext
	{
		@Override
		public String getProperty(final String pKey)
		{
			return null;
		}

		@Override
		public Bundle getBundle()
		{
			return null;
		}

		@Override
		public Bundle installBundle(final String pLocation, final InputStream pInput) throws BundleException
		{
			return null;
		}

		@Override
		public Bundle installBundle(final String pLocation) throws BundleException
		{
			return null;
		}

		@Override
		public Bundle getBundle(final long pId)
		{
			return null;
		}

		@Override
		public Bundle[] getBundles()
		{
			return null;
		}

		@Override
		public void addServiceListener(final ServiceListener pListener, final String pFilter)
				throws InvalidSyntaxException
		{
		}

		@Override
		public void addServiceListener(final ServiceListener pListener)
		{
		}

		@Override
		public void removeServiceListener(final ServiceListener pListener)
		{
		}

		@Override
		public void addBundleListener(final BundleListener pListener)
		{
		}

		@Override
		public void removeBundleListener(final BundleListener pListener)
		{
		}

		@Override
		public void addFrameworkListener(final FrameworkListener pListener)
		{
		}

		@Override
		public void removeFrameworkListener(final FrameworkListener pListener)
		{
		}

		@Override
		public ServiceRegistration<?> registerService(final String[] pClazzes, final Object pService,
				final Dictionary<String, ?> pProperties)
		{
			return null;
		}

		@Override
		public ServiceRegistration<?> registerService(final String pClazz, final Object pService,
				final Dictionary<String, ?> pProperties)
		{
			return null;
		}

		@Override
		public <S> ServiceRegistration<S> registerService(final Class<S> pClazz, final S pService,
				final Dictionary<String, ?> pProperties)
		{
			return null;
		}

		@Override
		public <S> ServiceRegistration<S> registerService(final Class<S> pClazz, final ServiceFactory<S> pFactory,
				final Dictionary<String, ?> pProperties)
		{
			return null;
		}

		@Override
		public ServiceReference<?>[] getServiceReferences(final String pClazz, final String pFilter)
				throws InvalidSyntaxException
		{
			return null;
		}

		@Override
		public ServiceReference<?>[] getAllServiceReferences(final String pClazz, final String pFilter)
				throws InvalidSyntaxException
		{
			return null;
		}

		@Override
		public ServiceReference<?> getServiceReference(final String pClazz)
		{
			return null;
		}

		@Override
		public <S> ServiceReference<S> getServiceReference(final Class<S> pClazz)
		{
			return null;
		}

		@Override
		public <S> Collection<ServiceReference<S>> getServiceReferences(final Class<S> pClazz, final String pFilter)
				throws InvalidSyntaxException
		{
			return null;
		}

		@Override
		public <S> S getService(final ServiceReference<S> pReference)
		{
			return null;
		}

		@Override
		public boolean ungetService(final ServiceReference<?> pReference)
		{
			return false;
		}

		@Override
		public <S> ServiceObjects<S> getServiceObjects(final ServiceReference<S> pReference)
		{
			return null;
		}

		@Override
		public File getDataFile(final String pFilename)
		{
			return null;
		}

		@Override
		public Filter createFilter(final String pFilter) throws InvalidSyntaxException
		{
			return null;
		}

		@Override
		public Bundle getBundle(final String pLocation)
		{
			return null;
		}
	}
}
