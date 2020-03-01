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
package de.holzem.eclipse.rexx4eclipse.rexx;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * RexxPlugin class controls the plug-in life cycle
 *
 * @author Markus Holzem
 */
public class RexxPlugin extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "de.holzem.eclipse.rexx4eclipse.rexx"; //$NON-NLS-1$

	// The shared instance
	private static RexxPlugin plugin;

	/**
	 * The constructor
	 */
	public RexxPlugin() {
	}

	@Override
	public void start(final BundleContext context) throws Exception
	{
		super.start(context);
		plugin = this;
		logMessage(RexxMessages.REXX_PLUGIN_STARTED);
	}

	@Override
	public void stop(final BundleContext context) throws Exception
	{
		plugin = null;
		// throws NullPointer Exception when Platform is shutting down
		// logMessage(RexxMessages.REXX_PLUGIN_STOPPED);
		super.stop(context);
	}

	public static RexxPlugin getDefault()
	{
		return plugin;
	}

	public static String getPluginId()
	{
		return RexxPlugin.PLUGIN_ID;
	}

	public static void logError(final Throwable pThrowable)
	{
		logStatus(new Status(IStatus.ERROR, getPluginId(), RexxMessages.REXX_PLUGIN_INTERNAL_ERROR, pThrowable));
	}

	public static void logMessage(final String pMessage)
	{
		logStatus(new Status(IStatus.INFO, getPluginId(), pMessage));
	}

	private static void logStatus(final IStatus pStatus)
	{
		if (Platform.isRunning()) {
			getDefault().getLog().log(pStatus);
		} else {
			System.out.println(pStatus.toString());
		}
	}
}
