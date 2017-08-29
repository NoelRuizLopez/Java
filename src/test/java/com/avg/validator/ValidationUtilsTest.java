package com.avg.validator;

import com.fasterxml.jackson.databind.JsonNode;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static junit.framework.TestCase.assertTrue;
import static junit.framework.TestCase.assertFalse;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ValidationUtils.class)
@SpringBootTest

public class ValidationUtilsTest {
    private static final String jsonImsi = "{\"imsi\":100000001,\"avs\":[{\"rand\":\"65342AF5456231234567543218765432\",\"autn\":\"65342AF5456231234567543218765432\",\"ck\":\"65342AF5456231234567543218765432\",\"ik\":\"65342AF5456231234567543218765432\",\"xres\":\"65342AF5456231234567543218765432\"}]}";
    private static final String jsonAvs = "{\"rand\":\"65342AF5456231234567543218765432\",\"autn\":\"65342AF5456231234567543218765432\",\"ck\":\"65342AF5456231234567543218765432\",\"ik\":\"65342AF5456231234567543218765432\",\"xres\":\"65342AF5456231234567543218765432\"}";

    @Test
    public void verifyGetJsonNodeString() throws Exception {
        assertTrue(ValidationUtils.getJsonNode(jsonImsi) instanceof JsonNode);
    }

    @Test
    public void verifyIsJsonImsiValid() throws Exception {

        assertTrue(ValidationUtils.isJsonValid(ValidationTypes.IMSI, jsonImsi));

    }

    @Test
    public void verifyIsJsonAvsValid() throws Exception {

        assertTrue(ValidationUtils.isJsonValid(ValidationTypes.AVS, jsonAvs));

    }

    @Test
    public void verifyIsJsonImsiInvalid() throws Exception {

        assertFalse(ValidationUtils.isJsonValid(ValidationTypes.IMSI, jsonAvs));

    }

    @Test
    public void verifyIsJsonAvsInvalid() throws Exception {

        assertFalse(ValidationUtils.isJsonValid(ValidationTypes.AVS, jsonImsi));

    }


}

