# xsd2jsonSchema
A simple Groovy based program to do convert XSD definitions to JSON schema.

This tool is in a terrible early state

## Requirements
* Java 8
* Gradle v4.*
* xjc in JAVA_HOME/bin or in search path


## Handle with gradle
### Using with gradle
```bash
# do a complete release to configured maven repository
gradle publish

# builds a release with all dependencies
# release is built in PROJECT_DIR/build/release
# before a release is build the tests are executed - skip not possible
gradle buildRelease

# run program without any arguments from project
gradle myRun
```
### Usage of the release
After you built a release with gradle or you download a release bundle you can start
the program with the contained start script. If you start it with the help option you
get a full description of the possible parameters
```bash
# or a similar path
cd build/release
# start program in bash environment
./xsd2jsonSchema.sh

# show help in bash environment
./xsd2jsonSchema.sh --help
```

