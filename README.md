# rexx4eclipse

This is a REXX Editor based on the following Eclipse technologies
   
* [Eclipse LSP4E](https://projects.eclipse.org/projects/technology.lsp4e) 
* [Eclipse TM4E - TextMate support in the Eclipse IDE](https://projects.eclipse.org/projects/technology.tm4e) 
* [Eclipse LSP4J](https://projects.eclipse.org/projects/technology.lsp4j) 

Based on the [Language Server Protocol](https://microsoft.github.io/language-server-protocol/) it is nowadays possible to create editor language support for different tools. One is therefore not tied in with a technology.

However I still prefer the Eclipse IDE for development and this project provides the syntax highlighting for REXX and the connectivity to a REXX language server with is implemented in [lsp4rexx](https://github.com/holzem/lsp4rexx). 

# Usage

This plugin needs the following environment variables to be configured:

Connect to a running REXX language server on localhost via socket (default of lsp4rexx is `5008`)

* `LSP4REXX_PORT` - connect to a running language server on the specified port, if specified

Plugin should start the REXX language server itself:  

* `LSP4REXX_JAR` - path to the fat JAR file of lsp4rexx
* `LSP4REXX_JAVA_HOME` - Java home directory to start the server
* `LSP4REXX_WORKING_DIRECTORY` - Working directory for the REXX language server

# Licenses

rexx4eclipse is published under the Apache 2.0 License

# Building and Contributing

To build and contribute to rexx4eclipse consult the [Contributing Guilde](https://github.com/holzem/rexx4eclipse/blob/master/CONTRIBUTING.md)

# Acknowledgements

This projects makes use of third party projects. Refer to the [Acknowledgements Information](https://github.com/holzem/rexx4eclipse/blob/master/ACKNOWLEDGEMENT.md)   



