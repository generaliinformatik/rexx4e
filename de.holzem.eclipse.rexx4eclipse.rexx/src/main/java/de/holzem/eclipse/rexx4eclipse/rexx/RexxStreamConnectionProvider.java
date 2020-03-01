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

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import org.eclipse.lsp4e.server.ProcessStreamConnectionProvider;

/**
 * RexxStreamConnectionProvider connects to an already running REXX language server listening on the agreed port 5008.
 * <p>
 * Usually the StreamConnectionProvider starts the language server itself but while trying to understand the language
 * server protocol and the interactions between editor and language server I prefer to start the language server in
 * debugging mode separately.
 * <p>
 * Later this class will use the implementation of {@link ProcessStreamConnectionProvider}.
 */
public class RexxStreamConnectionProvider extends ProcessStreamConnectionProvider {

	private final int _port = 5008;
	private Socket _socket;
	private InputStream _inputStream;
	private OutputStream _outputStream;

	public RexxStreamConnectionProvider() {
		// do nothing yet
	}

	@Override
	public InputStream getInputStream()
	{
		return _inputStream;
	}

	@Override
	public OutputStream getOutputStream()
	{
		return _outputStream;
	}

	@Override
	public void start() throws IOException
	{
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

	@Override
	public void stop()
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

}
