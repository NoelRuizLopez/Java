package com.avg.rest;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.avg.MicroAVG;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = MicroAVG.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AVGControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();

    }

    @Test
    public void verifyGetAllImsis() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/authservice/rest/imsi")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(4))).andDo(print());
    }

    @Test
    public void verifyGetAllAvsFromImsi() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/authservice/rest/imsi/100000001")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.avs.[0].rand").exists())
                .andExpect(jsonPath("$.avs.[0].autn").exists())
                .andExpect(jsonPath("$.avs.[0].ck").exists())
                .andExpect(jsonPath("$.avs.[0].ik").exists())
                .andExpect(jsonPath("$.avs.[0].xres").exists())
                .andExpect(jsonPath("$.avs.[0].rand").value("65342AF5456231234567543218765432"))
                .andExpect(jsonPath("$.avs.[0].autn").value("65342AF5456231234567543218765432"))
                .andExpect(jsonPath("$.avs.[0].ck").value("65342AF5456231234567543218765432"))
                .andExpect(jsonPath("$.avs.[0].ik").value("65342AF5456231234567543218765432"))
                .andExpect(jsonPath("$.avs.[0].xres").value("65342AF5456231234567543218765432"))
                .andDo(print());
    }

    @Test
    public void verifyGetAllAvsFromInvalidImsi() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/authservice/rest/imsi/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.errorCode").value(404))
                .andExpect(jsonPath("$.message").value("Imsi doesn´t exist"))
                .andDo(print());
    }


    @Test
    public void verifyGetAllAvsFromMalformedImsi() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/authservice/rest/imsi/f")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.errorCode").value(400))
                .andExpect(jsonPath("$.message").value("The request could not be understood " +
                        "by the server due to malformed syntax."))
                .andDo(print());
    }


    @Test
    public void verifyDeleteImsi() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/authservice/rest/imsi/")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content("{\"imsi\":100000015,\"avs\":[{\"rand\":\"65342AF5456231234567543218765400\"," +
                        "\"autn\":\"65342AF5456231234567543218765400\",\"ck\":\"65342AF5456231234567543218765400\"," +
                        "\"ik\":\"65342AF5456231234567543218765400\",\"xres\":\"65342AF5456231234567543218765400\"}]}"));

        mockMvc.perform(MockMvcRequestBuilders.delete("/authservice/rest/imsi/100000015")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.responseCode").value(200))
                .andExpect(jsonPath("$.message").value("100000015 Deleted"))
                .andDo(print());
    }


    @Test
    public void verifyDeleteMalformedImsi() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/authservice/rest/imsi/f")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.errorCode").value(400))
                .andExpect(jsonPath("$.message").value("The request could not be " +
                        "understood by the server due to malformed syntax."))
                .andDo(print());
    }

    @Test
    public void verifyDeletePhantomImsi() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/authservice/rest/imsi/100000034")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.errorCode").value(404))
                .andExpect(jsonPath("$.message").value("Imsi to delete doesn´t exist"))
                .andDo(print());
    }

    @Test
    public void verifyPostImsi() throws Exception {


        mockMvc.perform(MockMvcRequestBuilders.post("/authservice/rest/imsi")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content("{\"imsi\":100000010,\"avs\":[{\"rand\":\"65342AF5456231234567543218765400\"," +
                        "\"autn\":\"65342AF5456231234567543218765400\",\"ck\":\"65342AF5456231234567543218765400\"," +
                        "\"ik\":\"65342AF5456231234567543218765400\",\"xres\":\"65342AF5456231234567543218765400\"}]}")
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.imsi").exists())
                .andExpect(jsonPath("$.avs").exists())
                .andExpect(jsonPath("$.avs.[0].rand").exists())
                .andExpect(jsonPath("$.avs.[0].rand").value("65342AF5456231234567543218765400"))
                .andDo(print());
    }

    @Test
    public void verifyMalformedPostImsi() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/authservice/rest/imsi/")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content("{ \"id\": \"8\", \"text\" : \"New ToDo Sample\", \"completed\" : \"false\" }")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.errorCode").value(404))
                .andExpect(jsonPath("$.message").value("Imsi malformed"))
                .andDo(print());
    }

    @Test
    public void verifyAddAvstoImsi() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/authservice/rest/imsi/100000001/avs")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content("{\"rand\":\"65342AF5456231234567543218765437\",\"autn\":\"65342AF5456231234567543218765437\"," +
                        "\"ck\":\"65342AF5456231234567543218765437\",\"ik\":\"65342AF5456231234567543218765437\"," +
                        "\"xres\":\"65342AF5456231234567543218765437\"}")
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.avs.[1].rand").exists())
                .andExpect(jsonPath("$.avs.[1].autn").exists())
                .andExpect(jsonPath("$.avs.[1].ck").exists())
                .andExpect(jsonPath("$.avs.[1].ik").exists())
                .andExpect(jsonPath("$.avs.[1].xres").exists())
                .andExpect(jsonPath("$.avs.[1].rand").value("65342AF5456231234567543218765437"))
                .andExpect(jsonPath("$.avs.[1].autn").value("65342AF5456231234567543218765437"))
                .andExpect(jsonPath("$.avs.[1].ck").value("65342AF5456231234567543218765437"))
                .andExpect(jsonPath("$.avs.[1].ik").value("65342AF5456231234567543218765437"))
                .andExpect(jsonPath("$.avs.[1].xres").value("65342AF5456231234567543218765437"))
                .andDo(print());
    }

    @Test
    public void verifyAddAvstoImsiDuplicated() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/authservice/rest/imsi/100000001/avs")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content("{\"rand\":\"65342AF5456231234567543218765437\",\"autn\":\"65342AF5456231234567543218765437\"," +
                        "\"ck\":\"65342AF5456231234567543218765437\",\"ik\":\"65342AF5456231234567543218765437\"," +
                        "\"xres\":\"65342AF5456231234567543218765437\"}")
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andDo(print());

        mockMvc.perform(MockMvcRequestBuilders.post("/authservice/rest/imsi/100000001/avs")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content("{\"rand\":\"65342AF5456231234567543218765437\",\"autn\":\"65342AF5456231234567543218765437\"," +
                        "\"ck\":\"65342AF5456231234567543218765437\",\"ik\":\"65342AF5456231234567543218765437\"," +
                        "\"xres\":\"65342AF5456231234567543218765437\"}")
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.errorCode").exists())
                .andExpect(jsonPath("$.message").exists())
                .andExpect(jsonPath("$.errorCode").value(404))
                .andExpect(jsonPath("$.message").value("Duplicated Avs"))
                .andDo(print());
    }


    @Test
    public void verifyAddMalformedAvstoImsi() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/authservice/rest/imsi/100000001/avs")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content("{ \"id\": \"1\", \"text\" : \"New ToDo Text\", \"completed\" : \"false\" }")
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.errorCode").exists())
                .andExpect(jsonPath("$.message").exists())
                .andExpect(jsonPath("$.errorCode").value(404))
                .andExpect(jsonPath("$.message").value("Avs malformed"))
                .andDo(print());
    }

    @Test
    public void verifyAddAvstoPhantomImsi() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/authservice/rest/imsi/200000001/avs")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content("{\"rand\":\"65342AF5456231234567543218765437\",\"autn\":\"65342AF5456231234567543218765437\"," +
                        "\"ck\":\"65342AF5456231234567543218765437\",\"ik\":\"65342AF5456231234567543218765437\"," +
                        "\"xres\":\"65342AF5456231234567543218765437\"}")
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.errorCode").exists())
                .andExpect(jsonPath("$.message").exists())
                .andExpect(jsonPath("$.errorCode").value(404))
                .andExpect(jsonPath("$.message").value("Imsi doesn´t exist"))
                .andDo(print());
    }

    @Test
    public void verifyAddAvstoMalformedImsi() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/authservice/rest/imsi/f/avs")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.errorCode").value(400))
                .andExpect(jsonPath("$.message").value("The request could not be understood " +
                        "by the server due to malformed syntax."))
                .andDo(print());
    }


    @Test
    public void verifydeleteAvs() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.post("/authservice/rest/imsi")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content("{\"imsi\": 100000111,\"avs\":[{\"rand\":\"65342AF5456231234567543218765400\"," +
                        "\"autn\":\"65342AF5456231234567543218765400\",\"ck\":\"65342AF5456231234567543218765400\"," +
                        "\"ik\":\"65342AF5456231234567543218765400\",\"xres\":\"65342AF5456231234567543218765400\"}]}")
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andDo(print());

        mockMvc.perform(MockMvcRequestBuilders.delete("/authservice/rest/imsi/100000111/avs/")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"rand\":\"65342AF5456231234567543218765400\",\"autn\":\"65342AF5456231234567543218765400\"," +
                        "\"ck\":\"65342AF5456231234567543218765400\",\"ik\":\"65342AF5456231234567543218765400\"," +
                        "\"xres\":\"65342AF5456231234567543218765400\"}")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.responseCode").value(200))
                .andExpect(jsonPath("$.message").value("Avs Deleted from 100000111"))
                .andDo(print());
    }

    @Test
    public void verifydeleteAvsPhantomImsi() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/authservice/rest/imsi/100000112/avs")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"rand\":\"65342AF5456231234567543218765400\",\"autn\":\"65342AF5456231234567543218765400\"," +
                        "\"ck\":\"65342AF5456231234567543218765400\",\"ik\":\"65342AF5456231234567543218765400\"," +
                        "\"xres\":\"65342AF5456231234567543218765400\"}")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.errorCode").value(404))
                .andExpect(jsonPath("$.message").value("Imsi to delete doesn´t exist"))
                .andDo(print());
    }

    @Test
    public void verifydeleteMalformedAvs() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/authservice/rest/imsi")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content("{\"imsi\": 100000111,\"avs\":[{\"rand\":\"65342AF5456231234567543218765400\"," +
                        "\"autn\":\"65342AF5456231234567543218765400\",\"ck\":\"65342AF5456231234567543218765400\"," +
                        "\"ik\":\"65342AF5456231234567543218765400\",\"xres\":\"65342AF5456231234567543218765400\"}]}")
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE));

        mockMvc.perform(MockMvcRequestBuilders.delete("/authservice/rest/imsi/100000111/avs")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"idd\": \"8\", \"text\" : \"New ToDo Sample\", \"completed\" : \"false\" }")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.errorCode").value(404))
                .andExpect(jsonPath("$.message").value("Avs malformed"))
                .andDo(print());
    }


    @Test
    public void verifydeletePhantomAvs() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/authservice/rest/imsi")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content("{\"imsi\": 100000111,\"avs\":[{\"rand\":\"65342AF5456231234567543218765400\"," +
                        "\"autn\":\"65342AF5456231234567543218765400\",\"ck\":\"65342AF5456231234567543218765400\"," +
                        "\"ik\":\"65342AF5456231234567543218765400\",\"xres\":\"65342AF5456231234567543218765400\"}]}")
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE));

        mockMvc.perform(MockMvcRequestBuilders.delete("/authservice/rest/imsi/100000111/avs")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content("{\"rand\":\"65342AF5456231234567543218765401\",\"autn\":\"65342AF5456231234567543218765400\"," +
                        "\"ck\":\"65342AF5456231234567543218765400\",\"ik\":\"65342AF5456231234567543218765400\"," +
                        "\"xres\":\"65342AF5456231234567543218765401\"}")
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.errorCode").value(404))
                .andExpect(jsonPath("$.message").value("Avs doesn't exist in 100000111"))
                .andDo(print());
    }


}

