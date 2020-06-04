# rexx4e

This is a REXX Editor based on the following Eclipse technologies
   
* [Eclipse LSP4E](https://projects.eclipse.org/projects/technology.lsp4e) 
* [Eclipse TM4E - TextMate support in the Eclipse IDE](https://projects.eclipse.org/projects/technology.tm4e) 
* [Eclipse LSP4J](https://projects.eclipse.org/projects/technology.lsp4j) 

Based on the [Language Server Protocol](https://microsoft.github.io/language-server-protocol/) it is nowadays possible to create editor language support for different tools. One is therefore not tied in with a technology.

However I still prefer the Eclipse IDE for development and this project provides the syntax highlighting for REXX and the connectivity to a REXX language server with is implemented in [ls4rexx](https://github.com/holzem/ls4rexx). 

# Usage

This plugin needs the following environment variables to be configured:

Connect to a running REXX language server on localhost via socket (default of ls4rexx is `5008`)

* `LS4REXX_PORT` - connect to a running language server on the specified port, if specified

Plugin should start the REXX language server itself:  

* `LS4REXX_JAR` - path to the fat JAR file of ls4rexx
* `LS4REXX_JAVA_HOME` - Java home directory to start the server
* `LS4REXX_WORKING_DIRECTORY` - Working directory for the REXX language server

# Licenses

rexx4e is published under the Apache 2.0 License

# Building and Contributing

To build and contribute to rexx4e consult the [Contributing Guilde](https://github.com/holzem/rexx4e/blob/master/CONTRIBUTING.md)

# Acknowledgements

This projects makes use of third party projects. Refer to the [Acknowledgements Information](https://github.com/holzem/rexx4e/blob/master/ACKNOWLEDGEMENT.md)   



