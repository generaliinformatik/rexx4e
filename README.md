# rexx4e

This is a Eclipse REXX Editor based on the [Language Server Protocol](https://microsoft.github.io/language-server-protocol/) using [ls4rexx](https://github.com/generaliinformatik/ls4rexx). 

It makes use of the following projects:
   
* [Eclipse lsp4e](https://projects.eclipse.org/projects/technology.lsp4e) 
* [Eclipse tm4e - TextMate support in the Eclipse IDE](https://projects.eclipse.org/projects/technology.tm4e) 

[lsp4e](https://projects.eclipse.org/projects/technology.lsp4e) and
[tm4e](https://projects.eclipse.org/projects/technology.tm4e) have been added
to the Eclipse IDE beginning with Oxygen. The maven build is configured to run against Eclipse Photon (4.8)

It might be necessary to update the tm4e plugins from the [latest release](https://download.eclipse.org/tm4e/releases/) or  
[snapshot](http://download.eclipse.org/tm4e/snapshots/) to make the syntax colouring available.

# Get started

Use use the [Eclipse Installer](http://www.eclipse.org/downloads/packages/installer) to create a separate Eclipse installation
to work on the projects [ls4rexx](https://github.com/generaliinformatik/ls4rexx) and [rexx4e](https://github.com/generaliinformatik/rexx4e).

Start the Eclipse Installer in `Advanced mode`. Select `Eclipse IDE for RCP and RAP developers` as the product. 
Product Version should be `Photon` if you want to target IBM Developer for z/OS (starting from version 15.x) or 
Compuware Topaz Workbench (starting from version 20.x). 
On the projects page add an Eclipse Project with the URI 
[https://raw.githubusercontent.com/generaliinformatik/rexx4e/master/rexx4e.setup](https://raw.githubusercontent.com/generaliinformatik/rexx4e/master/rexx4e.setup)
and select the Rexx4e project. After finishing the installation and the start of the project the result is:
* missing plugins will be added to the product
* repositories will be cloned and imported
* repositories will be built using maven

A more manual approach is

* Clone this repository
* Open the folder in your terminal / command line
* Run `./mvnw clean verify` (OSX, Linux) or `mvnw.cmd clean verify` (Windows)
* After successful compilation you can find an eclipse update site in `de.generali.dev.eclipse.rexx4e.repository/target/repository`

# Usage

You need the ls4rexx fat jar to run the editor. By default (environment variable `LS4REXX_PORT` not set or set to `-1`) 
the language server ls4rexx is started/stopped by this plugin. The following variables have to be set by either set as a property
on the java command line or as an environment variable. A java property takes precedence over the environment variable.   

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

To build and contribute to rexx4e consult the [Contributing Guilde](https://github.com/generaliinformatik/rexx4e/blob/master/CONTRIBUTING.md)

# Acknowledgements

This projects makes use of third party projects. Refer to the [Acknowledgements Information](https://github.com/generaliinformatik/rexx4e/blob/master/ACKNOWLEDGEMENT.md)   
