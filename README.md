# rexx4e

This is a Eclipse REXX Editor based on the [Language Server Protocol](https://microsoft.github.io/language-server-protocol/) using [ls4rexx](https://github.com/holzem/ls4rexx). 

It makes use of the following projects:
   
* [Eclipse LSP4E](https://projects.eclipse.org/projects/technology.lsp4e) 
* [Eclipse TM4E - TextMate support in the Eclipse IDE](https://projects.eclipse.org/projects/technology.tm4e) 

# Get started

* Clone this repository
* Open the folder in your terminal / command line
* Run `./mvnw clean verify` (OSX, Linux) or `mvnw.cmd clean verify` (Windows)
* After successful compilation you can find an eclipse update site in `de.holzem.eclipse.rexx4e.repository/target/repository`

# Usage

You need the ls4rexx fat jar to run the editor. By default (environment variable `LS4REXX_PORT` not set or set to `-1`) 
the language server ls4rexx is started/stopped by this plugin   

* `LS4REXX_JAR` - path to the fat JAR file of ls4rexx
* `LS4REXX_JAVA_HOME` - Java home directory to start the server
* `LS4REXX_WORKING_DIRECTORY` - Working directory for the REXX language server

Currently the language server logs debug output to `logfile.log` in the working directory of the started server.

# Developer

To connect to a running ls4rexx language server on localhost via socket (default of ls4rexx is `5008` if not set by argument `--port`): 

* `LS4REXX_PORT` - connect to a running language server on the specified port, if specified

Currently the language server logs debug output to `logfile.log` in the working directory of the started server.

# Licenses

rexx4e is published under the Eclipse Public License 2.0.

# Building and Contributing

To build and contribute to rexx4e consult the [Contributing Guilde](https://github.com/holzem/rexx4e/blob/master/CONTRIBUTING.md)

# Acknowledgements

This projects makes use of third party projects. Refer to the [Acknowledgements Information](https://github.com/holzem/rexx4e/blob/master/ACKNOWLEDGEMENT.md)   
