package de.lisaplus.tools.xsd2json

import com.beust.jcommander.JCommander
import com.beust.jcommander.Parameter
import com.beust.jcommander.ParameterException
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import com.kjetland.jackson.jsonSchema.JsonSchemaGenerator
import org.slf4j.Logger
import org.slf4j.LoggerFactory

/**
 * Created by eiko on 10.07.17.
 */
class Xsd2JsonSchema {
    @Parameter(names = [ '-x', '--xsd' ], description = "Path to XSD schema to parse", required = true)
    String model

    @Parameter(names = [ '-o', '--output' ], description = "Path to the output file", required = true)
    String outputFilePath

    @Parameter(names = [ '-e', '--entryType' ], description = "What is the entry type for the modell", required = true)
    String entryType


    @Parameter(names = ['-h','--help'], help = true)
    boolean help = false

    static void main(String ... args) {
        Xsd2JsonSchema xsd2JsonSchema = new Xsd2JsonSchema()
        try {
            JCommander jCommander = JCommander.newBuilder()
                    .addObject(xsd2JsonSchema)
                    .build()
            jCommander.setProgramName(xsd2JsonSchema.getClass().typeName)
            jCommander.parse(args)
            if (xsd2JsonSchema.help) {
                jCommander.usage()
                return
            }
            xsd2JsonSchema.run()
        }
        catch(ParameterException e) {
            e.usage()
        }
        
    }

    void run() {
        File modelFile = new File (model)
        if (!modelFile.exists()) {
            def errorMsg = "XSD does not exist: '${modelFile}'"
            log.error(errorMsg)
            throw new Exception(errorMsg)
        }
        if (!modelFile.isFile()) {
            def errorMsg = "XSD is no normal file: '${modelFile}'"
            log.error(errorMsg)
            throw new Exception(errorMsg)
        }
        log.info("xsd=${model}")
        log.info("outPutBase=${outputFilePath}")
        def xjcCommandString = getXjcCommandString()
        def javacCommandString = getJavacCommandString()
        def classFileBasePath = generateJavaClassesFromXsd(xjcCommandString)
        def generatedClassPath = compileGenerated(classFileBasePath,javacCommandString)
        final GroovyClassLoader classLoader = new GroovyClassLoader()
        classLoader.addClasspath(generatedClassPath)
        classToSchema(classLoader,entryType,outputFilePath)
    }

    private static void classToSchema(def classLoader, def className, def outputFilePath) {
        Class c = classLoader.loadClass(className)
        ObjectMapper mapper = new ObjectMapper()
        JsonSchemaGenerator schemaGen = new JsonSchemaGenerator(mapper,false)
        JsonNode schema = schemaGen.generateJsonSchema(c);
        def objectMapper = new ObjectMapper()
        String s = objectMapper.writeValueAsString(schema)
        def outputFile = new File(outputFilePath)
        outputFile.write(s)
    }


    static String getCommandString(def commandStr,def msgPrefix) {
        def command = [commandStr,'-version']
        try {
            def process = new ProcessBuilder(command)
                    .redirectErrorStream(true)
                    .start()
            process.waitFor()
            return commandStr
        }
        catch (IOException e) {
            log.info ("${msgPrefix} in searchPath")
            String javaHomePath = System.getenv('JAVA_HOME')
            if (javaHomePath) {
                commandStr = javaHomePath+'/bin/'+commandStr
                command = [commandStr,'-version']
                try {
                    def process = new ProcessBuilder(command)
                            .redirectErrorStream(true)
                            .start()
                    process.waitFor()
                    return commandStr
                }
                catch(IOException e2) {
                    def errorMsg = "${msgPrefix} in JAVA_HOME/bin"
                    log.error(errorMsg)
                    throw new Exception(errorMsg)
                }
            }
            else {
                def errorMsg = "${msgPrefix}, maybe it helps to set JAVA_HOME"
                log.error(errorMsg)
                throw new Exception(errorMsg)
            }
        }
    }

    static String getXjcCommandString() {
        return getCommandString('xjc',"can't find JAXB XML schema compiler 'xjc'")
    }


    static String getJavacCommandString() {
        return getCommandString('javac',"can't find Java compiler 'javac'")
    }


    String generateJavaClassesFromXsd (String xjcCommand) {
        File f = File.createTempDir()
        String pathToGenerate = f.getCanonicalPath()
        log.info ("create Java classes from XSD here: "+pathToGenerate)
        def command = [xjcCommand,'-npa','-p','','-d',f.getCanonicalPath(),pathToGenerate,model]
        def process = new ProcessBuilder(command)
                .redirectErrorStream(true)
                .start()
        process.inputStream.eachLine {
            log.info(it)
        }
        process.waitFor()
        if (process.exitValue()!=0) {
            def errorMsg = 'error while generate Java classes from XSD'
            log.error(errorMsg)
            throw new Exception(errorMsg)
        }
        return f.getCanonicalPath()
    }

    static String compileGenerated(def sourceFileDir,String javacCommand) {
        File f = File.createTempDir()
        String pathToGenerate = f.getCanonicalPath()
        log.info("class path for compiled Java stuff: $pathToGenerate")
        def command = [javacCommand,'-d',f.getCanonicalPath(),'-source','1.8']

        File src = new File(sourceFileDir).eachFile { file ->
            command.add(file.getCanonicalPath())
        }

        def process = new ProcessBuilder(command)
                .redirectErrorStream(true)
                .start();
        process.inputStream.eachLine {
            log.info(it)
        }
        process.waitFor();
        if (process.exitValue()!=0) {
            def errorMsg = 'error while compile Java classes'
            log.error(errorMsg)
            throw new Exception(errorMsg)
        }
        return pathToGenerate
    }

    private static final Logger log=LoggerFactory.getLogger(Xsd2JsonSchema.class)

}
