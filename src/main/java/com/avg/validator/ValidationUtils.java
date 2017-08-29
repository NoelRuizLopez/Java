package com.avg.validator;


import java.io.File;
import java.io.IOException;
import java.net.URL;

import com.avg.MicroAVG;
import com.avg.utils.ReadFromResourcesFolder;
import com.avg.validator.ValidationTypes;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.fge.jackson.JsonLoader;
import com.github.fge.jsonschema.core.exceptions.ProcessingException;
import com.github.fge.jsonschema.core.report.ProcessingReport;
import com.github.fge.jsonschema.main.JsonSchema;
import com.github.fge.jsonschema.main.JsonSchemaFactory;

public class ValidationUtils {


    private static final String JSON_V4_SCHEMA_IDENTIFIER = "http://json-schema.org/draft-04/schema#";
    private static final String JSON_SCHEMA_IDENTIFIER_ELEMENT = "$schema";


    //Private Section
    private static JsonSchema _getSchemaNode(JsonNode jsonNode)
            throws ProcessingException
    {
        final JsonNode schemaIdentifier = jsonNode.get(JSON_SCHEMA_IDENTIFIER_ELEMENT);
        if (null == schemaIdentifier){
            ((ObjectNode) jsonNode).put(JSON_SCHEMA_IDENTIFIER_ELEMENT, JSON_V4_SCHEMA_IDENTIFIER);
        }

        final JsonSchemaFactory factory = JsonSchemaFactory.byDefault();

        return factory.getJsonSchema(jsonNode);
    } // _getSchemaNode() ends

    private static JsonNode getJsonNode(File jsonFile)
            throws IOException
    {
        return JsonLoader.fromFile(jsonFile);
    } // getJsonNode(File) ends

    private static JsonSchema _getValidationSchema(String file_path)
            throws IOException, ProcessingException{

        final JsonNode schemaNode = getJsonNode(new ReadFromResourcesFolder(file_path).getFile());

        return _getSchemaNode(schemaNode);
    }

    private static JsonSchema _getValidationSchema(ValidationTypes type)
            throws IOException, ProcessingException{

        JsonSchema schemaNode = null;

        switch (type) {
            case IMSI:
                schemaNode = _getValidationSchema("imsi_schema.json");
                break;
            case AVS:
                schemaNode = _getValidationSchema("avs_schema.json");
                break;
        }

        return schemaNode;
    }

    //Public Section
    public static JsonNode getJsonNode(String jsonText)
            throws IOException
    {
        return JsonLoader.fromString(jsonText);
    } // getJsonNode(text) ends


    public static boolean isJsonValid(ValidationTypes type, String jsonToValidateStr)
            throws ProcessingException, IOException {

        final JsonSchema schemaNode = _getValidationSchema(type);
        final JsonNode jsonToValidate = getJsonNode(jsonToValidateStr);
        ProcessingReport report = schemaNode.validate(jsonToValidate);

        return report.isSuccess();

    }
}
