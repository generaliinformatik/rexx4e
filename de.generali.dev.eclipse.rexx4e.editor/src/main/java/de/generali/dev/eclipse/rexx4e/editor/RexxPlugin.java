/**
 *  Copyright (c) 2020 Generali Deutschland AG - Team Informatik
 *
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v2.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v20.html
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 *  Contributors:
 *  Markus Holzem <markus.holzem@generali.com>
 */
package de.generali.dev.eclipse.rexx4e.editor;

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
public class RexxPlugin extends AbstractUIPlugin
{
	// The plug-in ID
	public static final String PLUGIN_ID = "de.generali.dev.eclipse.rexx4e.editor"; //$NON-NLS-1$
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

	public static void logError(final String pMessage)
	{
		logStatus(new Status(IStatus.ERROR, getPluginId(), pMessage));
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
