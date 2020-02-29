/**
 * Copyright 2020 Markus Holzem
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.holzem.eclipse.rexx4eclipse.rexx.internal;

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
 * ActivatorTest
 *
 * @author Markus Holzem
 */
public class ActivatorTest {

	@Test
	void test()
	{
		final Activator activator = new Activator();
		final BundleContext ctx = new MyBundleContext();
		try {
			activator.start(ctx);
		} catch (final Exception exc) {
			fail("unexpected exception " + exc.toString());
		}
		assertThat(Activator.getDefault(), is(equalTo(activator)));
		try {
			activator.stop(ctx);
		} catch (final Exception exc) {
			assertThat(exc, is(instanceOf(AssertionFailedException.class)));
		}
		assertThat(Activator.getDefault(), is(nullValue()));

	}

	private class MyBundleContext implements BundleContext {

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
		public ServiceRegistration<?> registerService(
				final String[] pClazzes,
				final Object pService,
				final Dictionary<String, ?> pProperties)
		{
			return null;
		}

		@Override
		public ServiceRegistration<?> registerService(
				final String pClazz,
				final Object pService,
				final Dictionary<String, ?> pProperties)
		{
			return null;
		}

		@Override
		public <S> ServiceRegistration<S> registerService(
				final Class<S> pClazz,
				final S pService,
				final Dictionary<String, ?> pProperties)
		{
			return null;
		}

		@Override
		public <S> ServiceRegistration<S> registerService(
				final Class<S> pClazz,
				final ServiceFactory<S> pFactory,
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
