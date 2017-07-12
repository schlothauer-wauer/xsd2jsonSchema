package de.lisaplus.tools.xsd2json

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.jsonschema.JsonSchema
import com.kjetland.jackson.jsonSchema.JsonSchemaGenerator
import org.junit.Test

import static junit.framework.Assert.assertNotNull
import static junit.framework.Assert.assertTrue

/**
 * Created by eiko on 10.07.17.
 */
class Test_Xsd2JsonSchema {
    @Test
    void generateJavaClassesFromXsd() {
        Xsd2JsonSchema inst = new Xsd2JsonSchema();
        inst.model = 'src/test/resources/test_xsd/michael_120.xsd'
        inst.outputName = 'tmp/michael_120_1.json'
        def xjcCommandString = Xsd2JsonSchema.getXjcCommandString()
        def pathToGeneratedJavaFiles = inst.generateJavaClassesFromXsd(xjcCommandString)
        assertTrue(new File("${pathToGeneratedJavaFiles}/ActivePeriod.java").exists())
        assertTrue(new File("${pathToGeneratedJavaFiles}/ActivePeriodList.java").exists())
        assertTrue(new File("${pathToGeneratedJavaFiles}/Approach.java").exists())
        assertTrue(new File("${pathToGeneratedJavaFiles}/ApproachLane.java").exists())
        assertTrue(new File("${pathToGeneratedJavaFiles}/ApproachList.java").exists())
        assertTrue(new File("${pathToGeneratedJavaFiles}/ClearanceTimeType.java").exists())
        assertTrue(new File("${pathToGeneratedJavaFiles}/ConnectingLane.java").exists())
        assertTrue(new File("${pathToGeneratedJavaFiles}/Connection.java").exists())
        assertTrue(new File("${pathToGeneratedJavaFiles}/ConnectionTrajectoryAddGrpC.java").exists())
        assertTrue(new File("${pathToGeneratedJavaFiles}/ConnectToList.java").exists())
        assertTrue(new File("${pathToGeneratedJavaFiles}/ControlData.java").exists())
        assertTrue(new File("${pathToGeneratedJavaFiles}/DataParameters.java").exists())
        assertTrue(new File("${pathToGeneratedJavaFiles}/EmissionType.java").exists())
        assertTrue(new File("${pathToGeneratedJavaFiles}/EnabledLaneList.java").exists())
        assertTrue(new File("${pathToGeneratedJavaFiles}/FuelType.java").exists())
        assertTrue(new File("${pathToGeneratedJavaFiles}/GenericLane.java").exists())
        assertTrue(new File("${pathToGeneratedJavaFiles}/GeoShape.java").exists())
        assertTrue(new File("${pathToGeneratedJavaFiles}/IndexedPosition.java").exists())
        assertTrue(new File("${pathToGeneratedJavaFiles}/InputList.java").exists())
        assertTrue(new File("${pathToGeneratedJavaFiles}/IntersectionGeometryAdd.java").exists())
        assertTrue(new File("${pathToGeneratedJavaFiles}/IntersectionGeometryAddList.java").exists())
        assertTrue(new File("${pathToGeneratedJavaFiles}/IntersectionGeometry.java").exists())
        assertTrue(new File("${pathToGeneratedJavaFiles}/IntersectionGeometryList.java").exists())
        assertTrue(new File("${pathToGeneratedJavaFiles}/IntersectionReferenceID.java").exists())
        assertTrue(new File("${pathToGeneratedJavaFiles}/IntersectionType.java").exists())
        assertTrue(new File("${pathToGeneratedJavaFiles}/LaneAttributes.java").exists())
        assertTrue(new File("${pathToGeneratedJavaFiles}/LaneDataAttributeAddGrpC.java").exists())
        assertTrue(new File("${pathToGeneratedJavaFiles}/LaneDataAttribute.java").exists())
        assertTrue(new File("${pathToGeneratedJavaFiles}/LaneDataAttributeList.java").exists())
        assertTrue(new File("${pathToGeneratedJavaFiles}/LaneList.java").exists())
        assertTrue(new File("${pathToGeneratedJavaFiles}/LaneReferenceList.java").exists())
        assertTrue(new File("${pathToGeneratedJavaFiles}/MapData.java").exists())
        assertTrue(new File("${pathToGeneratedJavaFiles}/NodeAttributeSetXY.java").exists())
        assertTrue(new File("${pathToGeneratedJavaFiles}/NodeAttributeXY.java").exists())
        assertTrue(new File("${pathToGeneratedJavaFiles}/NodeAttributeXYList.java").exists())
        assertTrue(new File("${pathToGeneratedJavaFiles}/NodeLLmD64B.java").exists())
        assertTrue(new File("${pathToGeneratedJavaFiles}/NodeSetXY.java").exists())
        assertTrue(new File("${pathToGeneratedJavaFiles}/NodeXY.java").exists())
        assertTrue(new File("${pathToGeneratedJavaFiles}/ObjectFactory.java").exists())
        assertTrue(new File("${pathToGeneratedJavaFiles}/OutputList.java").exists())
        assertTrue(new File("${pathToGeneratedJavaFiles}/Position3D.java").exists())
        assertTrue(new File("${pathToGeneratedJavaFiles}/Position.java").exists())
        assertTrue(new File("${pathToGeneratedJavaFiles}/REGIONRegGenericLane.java").exists())
        assertTrue(new File("${pathToGeneratedJavaFiles}/REGIONRegLaneDataAttributes.java").exists())
        assertTrue(new File("${pathToGeneratedJavaFiles}/REGIONRegRestrictionUserType.java").exists())
        assertTrue(new File("${pathToGeneratedJavaFiles}/RegulatorySpeedLimit.java").exists())
        assertTrue(new File("${pathToGeneratedJavaFiles}/RestrictionAppliesToEnum.java").exists())
        assertTrue(new File("${pathToGeneratedJavaFiles}/RestrictionClassAssignment.java").exists())
        assertTrue(new File("${pathToGeneratedJavaFiles}/RestrictionClassList.java").exists())
        assertTrue(new File("${pathToGeneratedJavaFiles}/RestrictionUserTypeAddGrpC.java").exists())
        assertTrue(new File("${pathToGeneratedJavaFiles}/RestrictionUserType.java").exists())
        assertTrue(new File("${pathToGeneratedJavaFiles}/RestrictionUserTypeList.java").exists())
        assertTrue(new File("${pathToGeneratedJavaFiles}/SegmentAttributesXYList.java").exists())
        assertTrue(new File("${pathToGeneratedJavaFiles}/SegmentAttributeXY.java").exists())
        assertTrue(new File("${pathToGeneratedJavaFiles}/SensorAllocation.java").exists())
        assertTrue(new File("${pathToGeneratedJavaFiles}/SensorAllocationList.java").exists())
        assertTrue(new File("${pathToGeneratedJavaFiles}/SensorDeviceType.java").exists())
        assertTrue(new File("${pathToGeneratedJavaFiles}/Sensor.java").exists())
        assertTrue(new File("${pathToGeneratedJavaFiles}/SensorList.java").exists())
        assertTrue(new File("${pathToGeneratedJavaFiles}/SensorPurpose.java").exists())
        assertTrue(new File("${pathToGeneratedJavaFiles}/SensorRelation.java").exists())
        assertTrue(new File("${pathToGeneratedJavaFiles}/SensorRelationList.java").exists())
        assertTrue(new File("${pathToGeneratedJavaFiles}/SignalGroup.java").exists())
        assertTrue(new File("${pathToGeneratedJavaFiles}/SignalGroupList.java").exists())
        assertTrue(new File("${pathToGeneratedJavaFiles}/SignalGroupRelation.java").exists())
        assertTrue(new File("${pathToGeneratedJavaFiles}/SignalGroupRelationList.java").exists())
        assertTrue(new File("${pathToGeneratedJavaFiles}/SpeedLimitList.java").exists())
        assertTrue(new File("${pathToGeneratedJavaFiles}/SpeedLimitType.java").exists())
        assertTrue(new File("${pathToGeneratedJavaFiles}/TLC.java").exists())
        assertTrue(new File("${pathToGeneratedJavaFiles}/Topology.java").exists())
        assertTrue(new File("${pathToGeneratedJavaFiles}/VariantCategory.java").exists())
        assertTrue(new File("${pathToGeneratedJavaFiles}/Variant.java").exists())
        assertTrue(new File("${pathToGeneratedJavaFiles}/VariantList.java").exists())
        assertTrue(new File("${pathToGeneratedJavaFiles}/Version.java").exists())
        assertTrue(new File("${pathToGeneratedJavaFiles}/VlogCat.java").exists())
        assertTrue(new File("${pathToGeneratedJavaFiles}/VlogIndicator.java").exists())
        assertTrue(new File("${pathToGeneratedJavaFiles}/VlogIO.java").exists())
    }

    @Test
    void compileGeneratedJavaClasses() {
        Xsd2JsonSchema inst = new Xsd2JsonSchema();
        inst.model = 'src/test/resources/test_xsd/michael_120.xsd'
        inst.outputName = 'tmp/michael_120_1.json'
        def xjcCommandString = Xsd2JsonSchema.getXjcCommandString()
        def javacCommandString = Xsd2JsonSchema.getJavacCommandString()
        def pathToGeneratedJavaFiles = inst.generateJavaClassesFromXsd(xjcCommandString)
        def generatedClassPath = inst.compileGenerated(pathToGeneratedJavaFiles,javacCommandString)
        assertTrue(new File("${generatedClassPath}/ActivePeriod.class").exists())
        assertTrue(new File("${generatedClassPath}/ActivePeriodList.class").exists())
        assertTrue(new File("${generatedClassPath}/Approach.class").exists())
        assertTrue(new File("${generatedClassPath}/ApproachLane.class").exists())
        assertTrue(new File("${generatedClassPath}/ApproachList.class").exists())
        assertTrue(new File("${generatedClassPath}/ClearanceTimeType.class").exists())
        assertTrue(new File("${generatedClassPath}/ConnectingLane.class").exists())
        assertTrue(new File("${generatedClassPath}/Connection.class").exists())
        assertTrue(new File("${generatedClassPath}/ConnectionTrajectoryAddGrpC.class").exists())
        assertTrue(new File("${generatedClassPath}/ConnectToList.class").exists())
        assertTrue(new File("${generatedClassPath}/ControlData.class").exists())
        assertTrue(new File("${generatedClassPath}/DataParameters.class").exists())
        assertTrue(new File("${generatedClassPath}/EmissionType.class").exists())
        assertTrue(new File("${generatedClassPath}/EnabledLaneList.class").exists())
        assertTrue(new File("${generatedClassPath}/FuelType.class").exists())
        assertTrue(new File("${generatedClassPath}/GenericLane.class").exists())
        assertTrue(new File("${generatedClassPath}/GeoShape.class").exists())
        assertTrue(new File("${generatedClassPath}/IndexedPosition.class").exists())
        assertTrue(new File("${generatedClassPath}/InputList.class").exists())
        assertTrue(new File("${generatedClassPath}/IntersectionGeometryAdd.class").exists())
        assertTrue(new File("${generatedClassPath}/IntersectionGeometryAddList.class").exists())
        assertTrue(new File("${generatedClassPath}/IntersectionGeometry.class").exists())
        assertTrue(new File("${generatedClassPath}/IntersectionGeometryList.class").exists())
        assertTrue(new File("${generatedClassPath}/IntersectionReferenceID.class").exists())
        assertTrue(new File("${generatedClassPath}/IntersectionType.class").exists())
        assertTrue(new File("${generatedClassPath}/LaneAttributes.class").exists())
        assertTrue(new File("${generatedClassPath}/LaneDataAttributeAddGrpC.class").exists())
        assertTrue(new File("${generatedClassPath}/LaneDataAttribute.class").exists())
        assertTrue(new File("${generatedClassPath}/LaneDataAttributeList.class").exists())
        assertTrue(new File("${generatedClassPath}/LaneList.class").exists())
        assertTrue(new File("${generatedClassPath}/LaneReferenceList.class").exists())
        assertTrue(new File("${generatedClassPath}/MapData.class").exists())
        assertTrue(new File("${generatedClassPath}/NodeAttributeSetXY.class").exists())
        assertTrue(new File("${generatedClassPath}/NodeAttributeXY.class").exists())
        assertTrue(new File("${generatedClassPath}/NodeAttributeXYList.class").exists())
        assertTrue(new File("${generatedClassPath}/NodeLLmD64B.class").exists())
        assertTrue(new File("${generatedClassPath}/NodeSetXY.class").exists())
        assertTrue(new File("${generatedClassPath}/NodeXY.class").exists())
        assertTrue(new File("${generatedClassPath}/ObjectFactory.class").exists())
        assertTrue(new File("${generatedClassPath}/OutputList.class").exists())
        assertTrue(new File("${generatedClassPath}/Position3D.class").exists())
        assertTrue(new File("${generatedClassPath}/Position.class").exists())
        assertTrue(new File("${generatedClassPath}/REGIONRegGenericLane.class").exists())
        assertTrue(new File("${generatedClassPath}/REGIONRegLaneDataAttributes.class").exists())
        assertTrue(new File("${generatedClassPath}/REGIONRegRestrictionUserType.class").exists())
        assertTrue(new File("${generatedClassPath}/RegulatorySpeedLimit.class").exists())
        assertTrue(new File("${generatedClassPath}/RestrictionAppliesToEnum.class").exists())
        assertTrue(new File("${generatedClassPath}/RestrictionClassAssignment.class").exists())
        assertTrue(new File("${generatedClassPath}/RestrictionClassList.class").exists())
        assertTrue(new File("${generatedClassPath}/RestrictionUserTypeAddGrpC.class").exists())
        assertTrue(new File("${generatedClassPath}/RestrictionUserType.class").exists())
        assertTrue(new File("${generatedClassPath}/RestrictionUserTypeList.class").exists())
        assertTrue(new File("${generatedClassPath}/SegmentAttributesXYList.class").exists())
        assertTrue(new File("${generatedClassPath}/SegmentAttributeXY.class").exists())
        assertTrue(new File("${generatedClassPath}/SensorAllocation.class").exists())
        assertTrue(new File("${generatedClassPath}/SensorAllocationList.class").exists())
        assertTrue(new File("${generatedClassPath}/SensorDeviceType.class").exists())
        assertTrue(new File("${generatedClassPath}/Sensor.class").exists())
        assertTrue(new File("${generatedClassPath}/SensorList.class").exists())
        assertTrue(new File("${generatedClassPath}/SensorPurpose.class").exists())
        assertTrue(new File("${generatedClassPath}/SensorRelation.class").exists())
        assertTrue(new File("${generatedClassPath}/SensorRelationList.class").exists())
        assertTrue(new File("${generatedClassPath}/SignalGroup.class").exists())
        assertTrue(new File("${generatedClassPath}/SignalGroupList.class").exists())
        assertTrue(new File("${generatedClassPath}/SignalGroupRelation.class").exists())
        assertTrue(new File("${generatedClassPath}/SignalGroupRelationList.class").exists())
        assertTrue(new File("${generatedClassPath}/SpeedLimitList.class").exists())
        assertTrue(new File("${generatedClassPath}/SpeedLimitType.class").exists())
        assertTrue(new File("${generatedClassPath}/TLC.class").exists())
        assertTrue(new File("${generatedClassPath}/Topology.class").exists())
        assertTrue(new File("${generatedClassPath}/VariantCategory.class").exists())
        assertTrue(new File("${generatedClassPath}/Variant.class").exists())
        assertTrue(new File("${generatedClassPath}/VariantList.class").exists())
        assertTrue(new File("${generatedClassPath}/Version.class").exists())
        assertTrue(new File("${generatedClassPath}/VlogCat.class").exists())
        assertTrue(new File("${generatedClassPath}/VlogIndicator.class").exists())
        assertTrue(new File("${generatedClassPath}/VlogIO.class").exists())
    }

    @Test
    void classLoaderTest() {
        Xsd2JsonSchema inst = new Xsd2JsonSchema();
        inst.model = 'src/test/resources/test_xsd/michael_120.xsd'
        inst.outputName = 'tmp/michael_120_1.json'
        def xjcCommandString = Xsd2JsonSchema.getXjcCommandString()
        def javacCommandString = Xsd2JsonSchema.getJavacCommandString()
        def pathToGeneratedJavaFiles = inst.generateJavaClassesFromXsd(xjcCommandString)
        def generatedClassPath = inst.compileGenerated(pathToGeneratedJavaFiles,javacCommandString)
        final GroovyClassLoader classLoader = new GroovyClassLoader()
        classLoader.addClasspath(generatedClassPath)

        Class c = classLoader.loadClass('Topology')
        assertNotNull(c)
        ObjectMapper mapper = new ObjectMapper()
        JsonSchemaGenerator schemaGen = new JsonSchemaGenerator(mapper,false)
        JsonNode schema = schemaGen.generateJsonSchema(c);
        def objectMapper = new ObjectMapper()
        String s = objectMapper.writeValueAsString(schema)

        assertNotNull(s)
        assertTrue(s.startsWith('{"$schema":"http://json-schema.org/draft-04/schema#"'))
    }

    @Test
    void testFindXjc() {
        def s = Xsd2JsonSchema.getXjcCommandString()
        assertNotNull(s)
    }

    @Test
    void testFindJavac() {
        def s = Xsd2JsonSchema.getJavacCommandString()
        assertNotNull(s)
    }

    private static void classToSchema(def classLoader, def className) {
        Class c = classLoader.loadClass(className)
        assertNotNull(c)
        ObjectMapper mapper = new ObjectMapper()
        JsonSchemaGenerator schemaGen = new JsonSchemaGenerator(mapper,false)
        JsonNode schema = schemaGen.generateJsonSchema(c);
        def objectMapper = new ObjectMapper()
        String s = objectMapper.writeValueAsString(schema)
        assertNotNull(s)
        assertTrue(s.startsWith('{"$schema":"http://json-schema.org/draft-04/schema#"'))
        def outputFile = new File("tmp/${className}.json")
        outputFile.write(s)
        assertTrue(outputFile.exists())
    }

    @Test
    void testTorben() {
        Xsd2JsonSchema inst = new Xsd2JsonSchema();
        inst.model = 'src/test/resources/test_xsd/torben.xsd'
        inst.outputName = 'tmp/torben.json'
        def xjcCommandString = Xsd2JsonSchema.getXjcCommandString()
        def javacCommandString = Xsd2JsonSchema.getJavacCommandString()
        def pathToGeneratedJavaFiles = inst.generateJavaClassesFromXsd(xjcCommandString)
        def generatedClassPath = inst.compileGenerated(pathToGeneratedJavaFiles,javacCommandString)
        final GroovyClassLoader classLoader = new GroovyClassLoader()
        classLoader.addClasspath(generatedClassPath)

        classToSchema(classLoader,'StdCollectionType')
        classToSchema(classLoader,'GeoCollectionType')
        classToSchema(classLoader,'LocCollectionType')
        classToSchema(classLoader,'CycCollectionType')
        classToSchema(classLoader,'DetCollectionType')
        classToSchema(classLoader,'TimCollectionType')
        classToSchema(classLoader,'CnResponseType')
    }

    @Test
    void testTorben2() {
        Xsd2JsonSchema inst = new Xsd2JsonSchema();
        inst.model = 'src/test/resources/test_xsd/torben_huelle.xsd'
        inst.outputName = 'tmp/torben_huelle.json'
        def xjcCommandString = Xsd2JsonSchema.getXjcCommandString()
        def javacCommandString = Xsd2JsonSchema.getJavacCommandString()
        def pathToGeneratedJavaFiles = inst.generateJavaClassesFromXsd(xjcCommandString)
        def generatedClassPath = inst.compileGenerated(pathToGeneratedJavaFiles,javacCommandString)
        final GroovyClassLoader classLoader = new GroovyClassLoader()
        classLoader.addClasspath(generatedClassPath)
        classToSchema(classLoader,'Container')
    }
}
