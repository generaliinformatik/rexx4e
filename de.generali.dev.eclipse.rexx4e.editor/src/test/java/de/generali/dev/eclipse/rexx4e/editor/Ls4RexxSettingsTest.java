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

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

/**
 * Ls4RexxSettingsTest
 */
class Ls4RexxSettingsTest
{
	@Test
	void testInvalidSettings()
	{
		final Map<String, String> settingsMap = new HashMap<String, String>();
		settingsMap.put(Ls4RexxSettings.LS4REXX_PORT, null);
		settingsMap.put(Ls4RexxSettings.LS4REXX_JAVA_HOME, null);
		settingsMap.put(Ls4RexxSettings.LS4REXX_JAR, null);
		settingsMap.put(Ls4RexxSettings.LS4REXX_WORKING_DIRECTORY, null);
		final Ls4RexxSettings settings = new Ls4RexxSettings(key -> settingsMap.get(key));
		assertThat(settings.getSocketPort(), is(equalTo(-1)));
		assertThat(settings.isSocketConnection(), is(false));
		assertThat(settings.isValidCommand(), is(false));
	}

	@Test
	void testSocket()
	{
		final Map<String, String> settingsMap = new HashMap<String, String>();
		settingsMap.put(Ls4RexxSettings.LS4REXX_PORT, "5008");
		settingsMap.put(Ls4RexxSettings.LS4REXX_JAVA_HOME, null);
		settingsMap.put(Ls4RexxSettings.LS4REXX_JAR, null);
		settingsMap.put(Ls4RexxSettings.LS4REXX_WORKING_DIRECTORY, null);
		final Ls4RexxSettings settings = new Ls4RexxSettings(key -> settingsMap.get(key));
		assertThat(settings.getSocketPort(), is(equalTo(5008)));
		assertThat(settings.isSocketConnection(), is(true));
		assertThat(settings.isValidCommand(), is(false));
	}

	@Test
	void testSocketInvalidPort()
	{
		final Map<String, String> settingsMap = new HashMap<String, String>();
		settingsMap.put(Ls4RexxSettings.LS4REXX_PORT, "NOT_A_NUMBER");
		settingsMap.put(Ls4RexxSettings.LS4REXX_JAVA_HOME, null);
		settingsMap.put(Ls4RexxSettings.LS4REXX_JAR, null);
		settingsMap.put(Ls4RexxSettings.LS4REXX_WORKING_DIRECTORY, null);
		final Ls4RexxSettings settings = new Ls4RexxSettings(key -> settingsMap.get(key));
		assertThat(settings.getSocketPort(), is(equalTo(-1)));
		assertThat(settings.isSocketConnection(), is(false));
		assertThat(settings.isValidCommand(), is(false));
	}

	@Test
	void testCommand()
	{
		final Map<String, String> settingsMap = new HashMap<String, String>();
		settingsMap.put(Ls4RexxSettings.LS4REXX_PORT, "-1");
		settingsMap.put(Ls4RexxSettings.LS4REXX_JAVA_HOME, "MY_JAVAHOME");
		settingsMap.put(Ls4RexxSettings.LS4REXX_JAR, "MY_LS4REXX_JAR");
		settingsMap.put(Ls4RexxSettings.LS4REXX_WORKING_DIRECTORY, "MY_WD");
		final Ls4RexxSettings settings = new Ls4RexxSettings(key -> settingsMap.get(key));
		assertThat(settings.isSocketConnection(), is(false));
		assertThat(settings.isValidCommand(), is(true));
		assertThat(settings.getStartCommands().get(0), containsString("MY_JAVAHOME"));
		assertThat(settings.getStartCommands().get(0), containsString("bin"));
		assertThat(settings.getStartCommands().get(0), containsString("java"));
		assertThat(settings.getStartCommands().get(1), is(equalTo("-jar")));
		assertThat(settings.getStartCommands().get(2), is(equalTo("MY_LS4REXX_JAR")));
		assertThat(settings.getWorkingDirectory(), is(equalTo("MY_WD")));
	}
}
