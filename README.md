<p align="center"><img src="docs/media/logo.svg"/></p>

# Pixole Logger
*Simple, lightweight and easily customizable java logging tool.*
<hr>

### Set up

Setup for *Pixole Logger* is really simple as it is all contained to a single .jar file.<br>
Simply download the .jar and include it in your java project.<br>
If you want to provide configuration when it starts up, you can also place the ```plog_config.properties``` file in a setup folder in the root directory of the project. <br>
*Note that this configuration can be done in the code if you would rather not include an extra file.*

### Logging
*Pixole Logger* supports various logging levels, allowing you to easily filter which log messages are shown. Any logs at the same or a higher logging level to the current level given in the configuration will be shown.
~~~ java
// Sending log messages with increasingly higher logging levels
// DEBUG, INFO, WARNING, ERROR
PLog.debug("This is a debug log message");
PLog.info("This is an info log message");
PLog.warning("This is a warning log message");
PLog.error("This is an error message");

// Or alternatively the logging level can be specified as follows
// NB: this will perform exactly the same as PLog.warning("...")
PLog.log("This is a message to be logged", PLoggingLevel.WARNING)
~~~


#### Multiple log files
By default, all logs will be written to the default log file (DEF_LOG_NAME.plog) under the log directory (LOG_DIR) as specified in the config file. *Pixole Logger* also supports multiple simutaneous log files. To specify an alternative log file, use the following syntax:
~~~ java
// Writing logs to multiple different log files
PLog.debug("This is a debug log message", "myLogFileName");
PLog.info("This is an info log message", "tests");
PLog.warning("This is a warning log message", "server");
PLog.error("This is an error message", "tests");
~~~


This will write to logs using the name as given i.e. (myLogFileName -> myLogFileName.plog). Any time you do not specify a log file name, the default log file will be written to instead (default.plog unless specified differently in the .properties file)

### Configuring
*Pixole Logger* can be customized either using a properties file or calling specific methods in the java code. You can even use a combination of the two. The properties file is loaded when the logger starts. *Pixole Logger* will use the most recent configuration provided (i.e. if you set an output directory in the properties file then change it in the code, it will use the directory set in the code) 

#### Properties file
You can configure *Pixole Logger* using a properties file by placing the file below under ```setup/plog_config.properties``` in the root directory of your project. Changing the values will affect how the logger performs.

~~~ java-properties
## PLog properties file - Edit this to change the default values

# Any logs messages created at this level or above will be logged
# Can be ALL, DEBUG, INFO, WARNING, ERROR, NOLOGS
LOGGING_LEVEL = ALL

# If true, includes date stamps before each log message
INCLUDE_DATE_STAMPS = true
# If true, includes time stamps before each log message
INCLUED_TIME_STAMPS = true

# If true, log messages will be written to StdOut (normally the console)
WRITE_LOGS_TO_STDOUT = false

# If true, log messages will be written to a log file (DEF_LOG_NAME under LOG_DIR unless specified)
WRITE_LOGS_TO_LOG_FILE = true
LOG_DIR = logs/
DEF_LOG_NAME = default
~~~

#### Java code
Some of these properties can also be set/updated at runtime using the following syntax

~~~ java
PLog.includeDateStamps(true);
PLog.includeTimeStamps(false);
PLog.setDefLogFile("logfile");
PLog.setLogDir("applicationLogs/");
PLog.writeLogsToStdOut(true);
PLog.writeLogsToFile(false);
~~~