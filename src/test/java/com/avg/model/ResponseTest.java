package com.avg.model;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Response.class)
@SpringBootTest
//@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ResponseTest {

    private Response res;

    @Before
    public void setup() {
        this.res = new Response(200, "Test message");
    }

    @Test
    public void verifyResponseBuilder() throws Exception {

        res = new Response();

        assertNotNull(res);

    }

    @Test
    public void verifyResponseBuilderWithParams() throws Exception {

        res = new Response(400, "This is a message test");

        assertEquals(400, res.getResponseCode());
        assertEquals("This is a message test", res.getMessage());


    }


    @Test
    public void verifySetMessage() throws Exception {

        res.setMessage("Set message test");

        assertEquals("Set message test", res.getMessage());


    }

    @Test
    public void verifySetResponseCode() throws Exception {

        res.setResponseCode(500);

        assertEquals(500, res.getResponseCode());

    }

    @Test
    public void verifyGetMessage() throws Exception {

        assertEquals("Test message", res.getMessage());


    }

    @Test
    public void verifyGetResponseCode() throws Exception {

        assertEquals(200, res.getResponseCode());

    }

}

