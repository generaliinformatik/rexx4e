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

import org.eclipse.osgi.util.NLS;

/**
 * RexxMessages initializes the message constants from the properties file.
 */
public class RexxMessages {// extends NLS {
	private static final String BUNDLE_NAME = "de.holzem.eclipse.rexx4eclipse.rexx.RexxMessages";//$NON-NLS-1$

	private RexxMessages() {
		// Do not instantiate
	}

	public static String REXX_PLUGIN_STARTED;
	public static String REXX_PLUGIN_STOPPED;
	public static String REXX_PLUGIN_INTERNAL_ERROR;
	public static String REXX_PLUGIN_CONNECTION_STARTED;
	public static String REXX_PLUGIN_CONNECTION_STOPPED;

	static {
		NLS.initializeMessages(BUNDLE_NAME, RexxMessages.class);
	}

}
