package de.lisaplus.tools.xsd2json

import com.beust.jcommander.JCommander
import com.beust.jcommander.Parameter
import com.beust.jcommander.ParameterException
import org.slf4j.Logger
import org.slf4j.LoggerFactory

/**
 * Created by eiko on 10.07.17.
 */
class Xsd2JsonSchema {
    @Parameter(names = [ '-x', '--xsd' ], description = "Path to XSD schema to parse", required = true)
    String model

    @Parameter(names = [ '-o', '--output' ], description = "Name of the output file", required = true)
    String outputName

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
        log.info("outPutBase=${outputName}")
        def xjcCommandString = getXjcCommandString()
        def javacCommandString = getJavacCommandString()
        def classFileBasePath = generateJavaClassesFromXsd(xjcCommandString)
        def generatedClassPath = compileGenerated(classFileBasePath,javacCommandString)
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
                commandStr = javaHomePath+'/'+commandStr
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
