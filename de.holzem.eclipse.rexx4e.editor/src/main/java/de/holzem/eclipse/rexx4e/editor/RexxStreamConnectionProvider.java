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

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
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
	private final Ls4RexxSettings _ls4RexxSettings;
	private Socket _socket;
	private InputStream _inputStream;
	private OutputStream _outputStream;

	public RexxStreamConnectionProvider() {
		_ls4RexxSettings = new Ls4RexxSettings();
		if (!_ls4RexxSettings.isSocketConnection() && _ls4RexxSettings.isValidCommand()) {
			setCommands(_ls4RexxSettings.getStartCommands());
			setWorkingDirectory(_ls4RexxSettings.getWorkingDirectory());
		}
	}

	@Override
	public InputStream getInputStream()
	{
		if (!_ls4RexxSettings.isSocketConnection()) {
			return super.getInputStream();
		} else {
			return _inputStream;
		}
	}

	@Override
	public OutputStream getOutputStream()
	{
		if (!_ls4RexxSettings.isSocketConnection()) {
			return super.getOutputStream();
		} else {
			return _outputStream;
		}
	}

	@Override
	public void start() throws IOException
	{
		if (_ls4RexxSettings.isSocketConnection()) {
			connectToServerViaSocket();
		} else {
			startServerViaCommand();
		}
	}

	@Override
	public void stop()
	{
		if (_ls4RexxSettings.isSocketConnection()) {
			closeSocketConnection();
		} else {
			stopServer();
		}
	}

	private void connectToServerViaSocket() throws IOException
	{
		try {
			_socket = new Socket("localhost", _ls4RexxSettings.getSocketPort());
			RexxPlugin.logMessage(RexxMessages.REXX_PLUGIN_CONNECTION_STARTED);
		} catch (final IOException exc) {
			RexxPlugin.logError(exc);
			throw exc;
		}
		_inputStream = _socket.getInputStream();
		_outputStream = _socket.getOutputStream();
	}

	private void closeSocketConnection()
	{
		if (_socket != null) {
			try {
				_socket.close();
			} catch (final IOException exc) {
				RexxPlugin.logError(exc);
			}
		}
		RexxPlugin.logMessage(RexxMessages.REXX_PLUGIN_CONNECTION_STOPPED);
	}

	private void startServerViaCommand() throws IOException
	{
		super.start();
		// java runtime needs a bit to start
		try {
			TimeUnit.MILLISECONDS.sleep(500);
		} catch (final InterruptedException exc) {
			throw new IOException(exc);
		}
		RexxPlugin.logMessage(RexxMessages.REXX_PLUGIN_SERVER_STARTED);
	}

	private void stopServer()
	{
		super.stop();
		RexxPlugin.logMessage(RexxMessages.REXX_PLUGIN_SERVER_STOPPED);
	}
}