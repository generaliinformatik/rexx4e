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
package de.holzem.eclipse.rexx4e.editor;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.eclipse.lsp4e.server.ProcessStreamConnectionProvider;
import org.eclipse.lsp4e.server.StreamConnectionProvider;

/**
 * RexxStreamConnectionProvider starts the REXX language server or connects to a runnung REXX language server on the
 * specified port.
 * <p>
 * Environment variables to configure the utilized language server:
 * <dl>
 * <dt>LS4REXX_PORT</dt>
 * <dd>connect to a running language server on the specified port, if specified</dd>
 * <dt>LS4REXX_JAR</dt>
 * <dd>path to the fat JAR file of ls4rexx</dd>
 * <dt>LS4REXX_JAVA_HOME</dt>
 * <dd>Java home directory to start the server</dd>
 * <dt>LS4REXX_WORKING_DIRECTORY</dt>
 * <dd>Working directory for the REXX language server</dd>
 * </dl>
 */
public class RexxStreamConnectionProvider extends ProcessStreamConnectionProvider implements StreamConnectionProvider
{
	private final int _port;
	private final String _ls4rexx_working_directory;
	private final String _ls4rexx_java;
	private final String _ls4rexx_jar;
	private Socket _socket;
	private InputStream _inputStream;
	private OutputStream _outputStream;

	public RexxStreamConnectionProvider() {
		_port = getLs4RexxPort();
		if (_port < 0) {
			_ls4rexx_jar = getLs4RexxJar();
			_ls4rexx_java = getLs4RexxJava();
			_ls4rexx_working_directory = getLs4RexxWorkingDirectory();
			final List<String> commands = new ArrayList<String>();
			commands.add(_ls4rexx_java);
			commands.add("-jar");
			commands.add(_ls4rexx_jar);
			setCommands(commands);
			setWorkingDirectory(_ls4rexx_working_directory);
		} else {
			_ls4rexx_jar = null;
			_ls4rexx_java = null;
			_ls4rexx_working_directory = null;
		}
	}

	@Override
	public InputStream getInputStream()
	{
		if (_port < 0) {
			return super.getInputStream();
		} else {
			return _inputStream;
		}
	}

	@Override
	public OutputStream getOutputStream()
	{
		if (_port < 0) {
			return super.getOutputStream();
		} else {
			return _outputStream;
		}
	}

	@Override
	public void start() throws IOException
	{
		if (_port < 0) {
			super.start();
			// java runtime needs a bit to start
			try {
				TimeUnit.MILLISECONDS.sleep(500);
			} catch (final InterruptedException exc) {
				throw new IOException(exc);
			}
			RexxPlugin.logMessage(RexxMessages.REXX_PLUGIN_SERVER_STARTED);
		} else {
			try {
				_socket = new Socket("localhost", _port);
				RexxPlugin.logMessage(RexxMessages.REXX_PLUGIN_CONNECTION_STARTED);
			} catch (final IOException exc) {
				RexxPlugin.logError(exc);
				throw exc;
			}
			_inputStream = _socket.getInputStream();
			_outputStream = _socket.getOutputStream();
		}
	}

	@Override
	public void stop()
	{
		if (_port < 0) {
			super.stop();
			RexxPlugin.logMessage(RexxMessages.REXX_PLUGIN_SERVER_STOPPED);
		} else {
			if (_socket != null) {
				try {
					_socket.close();
				} catch (final IOException exc) {
					RexxPlugin.logError(exc);
				}
			}
			RexxPlugin.logMessage(RexxMessages.REXX_PLUGIN_CONNECTION_STOPPED);
		}
	}

	private int getLs4RexxPort()
	{
		int port = -1;
		final String ls4rexx_port = System.getenv("LS4REXX_PORT");
		if (ls4rexx_port != null) {
			try {
				port = Integer.parseInt(ls4rexx_port);
			} catch (final NumberFormatException exc) {
				RexxPlugin.logError(RexxMessages.REXX_PLUGIN_PORT_NUMBER_ERROR);
			}
		}
		return port;
	}

	private String getLs4RexxJava()
	{
		final String javaHome = System.getenv("LS4REXX_JAVA_HOME");
		if (javaHome == null) {
			RexxPlugin.logError(RexxMessages.REXX_PLUGIN_ENV_LS4REXX_JAVA_HOME_MISSING_ERROR);
		}
		String java = null;
		if (javaHome != null) {
			final String javaExecutable = (System.getProperty("os.name").toLowerCase().contains("win") ? "java.exe"
					: "java");
			final Path javaPath = Paths.get(javaHome, "bin", javaExecutable);
			if (!Files.isExecutable(javaPath)) {
				RexxPlugin.logError(RexxMessages.REXX_PLUGIN_ENV_LS4REXX_JAVA_HOME_MISSING_ERROR);
			} else
				java = javaPath.toString();
		}
		return java;
	}

	private String getLs4RexxJar()
	{
		final String jar = System.getenv("LS4REXX_JAR");
		if (jar == null) {
			RexxPlugin.logError(RexxMessages.REXX_PLUGIN_ENV_LS4REXX_JAVA_HOME_MISSING_ERROR);
		}
		return jar;
	}

	private String getLs4RexxWorkingDirectory()
	{
		String workingDirectory = System.getenv("LS4REXX_WORKING_DIRECTORY");
		if (workingDirectory == null) {
			workingDirectory = System.getProperty("user.dir");
		}
		return workingDirectory;
	}
}
