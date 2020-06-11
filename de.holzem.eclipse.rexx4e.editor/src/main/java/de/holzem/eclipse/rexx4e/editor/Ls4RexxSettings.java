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

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * Ls4RexxSettings
 */
class Ls4RexxSettings
{
	public static final String OS_NAME = "os.name";
	public static final String USER_DIR = "user.dir";
	public static final String LS4REXX_PORT = "LS4REXX_PORT";
	public static final String LS4REXX_JAVA_HOME = "LS4REXX_JAVA_HOME";
	public static final String LS4REXX_JAR = "LS4REXX_JAR";
	public static final String LS4REXX_WORKING_DIRECTORY = "LS4REXX_WORKING_DIRECTORY";
	private final Function<String, String> _mappingFunction;

	Ls4RexxSettings() {
		_mappingFunction = this::getPropertyOrEnvironmentSetting;
	}

	Ls4RexxSettings(final Function<String, String> pMappingFunction) {
		_mappingFunction = pMappingFunction;
	}

	boolean isSocketConnection()
	{
		return (getLs4RexxPort() >= 0);
	}

	boolean isValidCommand()
	{
		if (isSocketConnection()) {
			return false;
		}
		final String javaExecutable = getJavaExecutableInternal();
		final String ls4RexxJar = getLs4RexxJarInternal();
		final String workingDirectory = getWorkingDirectory();
		return (javaExecutable != null && ls4RexxJar != null && workingDirectory != null);
	}

	int getSocketPort()
	{
		return getLs4RexxPort();
	}

	List<String> getStartCommands()
	{
		final List<String> commands = new ArrayList<String>();
		commands.add(getJavaExecutableInternal());
		commands.add("-jar");
		commands.add(getLs4RexxJarInternal());
		return commands;
	}

	String getWorkingDirectory()
	{
		String workingDirectory = _mappingFunction.apply(LS4REXX_WORKING_DIRECTORY);
		if (workingDirectory == null) {
			workingDirectory = System.getProperty(USER_DIR);
		}
		return workingDirectory;
	}

	private int getLs4RexxPort()
	{
		final String setting = _mappingFunction.apply(LS4REXX_PORT);
		int port = -1;
		if (setting != null) {
			try {
				port = Integer.parseInt(setting);
			} catch (final NumberFormatException exc) {
				RexxPlugin.logError(RexxMessages.REXX_PLUGIN_PORT_NUMBER_ERROR);
				port = -1;
			}
		}
		return port;
	}

	private String getJavaExecutableInternal()
	{
		final String javaHome = _mappingFunction.apply(LS4REXX_JAVA_HOME);
		String javaExecutable = null;
		final String javaBinary = (System.getProperty(OS_NAME).toLowerCase().contains("win") ? "java.exe" : "java");
		if (javaHome != null) {
			final Path javaPath = Paths.get(javaHome, "bin", javaBinary);
			javaExecutable = javaPath.toString();
		} else {
			RexxPlugin.logError(RexxMessages.REXX_PLUGIN_ENV_LS4REXX_JAVA_HOME_MISSING_ERROR);
		}
		return javaExecutable;
	}

	private String getLs4RexxJarInternal()
	{
		final String jar = _mappingFunction.apply(LS4REXX_JAR);
		if (jar == null) {
			RexxPlugin.logError(RexxMessages.REXX_PLUGIN_ENV_LS4REXX_JAVA_HOME_MISSING_ERROR);
		}
		return jar;
	}

	private String getPropertyOrEnvironmentSetting(final String pKey)
	{
		String setting = System.getProperty(pKey);
		if (setting == null) {
			setting = System.getenv(pKey);
		}
		return setting;
	}
}
