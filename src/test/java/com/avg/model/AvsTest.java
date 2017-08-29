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
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Avs.class)
@SpringBootTest
//@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AvsTest {


    private Avs avs;

    @Before
    public void setup() {
        this.avs = new Avs("65342AF5456231234567543218765433",
                "65342AF5456231234567543218765433",
                "65342AF5456231234567543218765433",
                "65342AF5456231234567543218765433",
                "65342AF5456231234567543218765433");
    }

    @Test
    public void verifyAvsBuilder() throws Exception {

        Avs avs2 = new Avs();

        assertNotNull(avs2);

    }

    @Test
    public void verifyAvsBuilderInput() throws Exception {

        this.avs = new Avs("65342AF5456231234567543218765434",
                "65342AF5456231234567543218765434",
                "65342AF5456231234567543218765434",
                "65342AF5456231234567543218765434",
                "65342AF5456231234567543218765434");


        assertEquals("65342AF5456231234567543218765434", avs.getRand());
        assertEquals("65342AF5456231234567543218765434", avs.getIk());
        assertEquals("65342AF5456231234567543218765434", avs.getXres());
        assertEquals("65342AF5456231234567543218765434", avs.getCk());
        assertEquals("65342AF5456231234567543218765434", avs.getAutn());


    }

    @Test
    public void verifyEquals() throws Exception {

        this.avs = new Avs("65342AF5456231234567543218765434",
                "65342AF5456231234567543218765434",
                "65342AF5456231234567543218765434",
                "65342AF5456231234567543218765434",
                "65342AF5456231234567543218765434");


        assertTrue(this.avs.equals(this.avs));
    }

    @Test
    public void verifyNotEquals() throws Exception {

        this.avs = new Avs("65342AF5456231234567543218765434",
                "65342AF5456231234567543218765434",
                "65342AF5456231234567543218765434",
                "65342AF5456231234567543218765434",
                "65342AF5456231234567543218765434");

        Avs avs2 = new Avs("65342AF5456231234567543218765435",
                "65342AF5456231234567543218765434",
                "65342AF5456231234567543218765434",
                "65342AF5456231234567543218765434",
                "65342AF5456231234567543218765434");

        assertTrue(!this.avs.equals(avs2));
    }

    /*
    @Test
    public void verifyAvsBuilderByString() throws Exception {
        String jsonImsi = "{\"imsi\":100000001,\"avs\":[{\"rand\":\"65342AF5456231234567543218765432\",\"autn\":\"65342AF5456231234567543218765432\",\"ck\":\"65342AF5456231234567543218765432\",\"ik\":\"65342AF5456231234567543218765432\",\"xres\":\"65342AF5456231234567543218765432\"}]}";
        imsi = new Imsi(jsonImsi);

        //imsi
        assertEquals("This value must be 100000001", 100000001, imsi.getImsi());

        //Avs
        assertEquals("avs long = 1", 1, imsi.getAvs().size());

        assertEquals("Value Ok = 1",
                "\"65342AF5456231234567543218765432\"",
                          imsi.getAvs().get(0).getRand());

    }
    */

    @Test
    public void verifysetRand() throws Exception {

        avs.setRand("65342AF5456231234567543218765434");

        assertEquals("This value must be 65342AF5456231234567543218765434",
                     "65342AF5456231234567543218765434", avs.getRand());

    }

    @Test
    public void verifysetXres() throws Exception {

        avs.setXres("65342AF5456231234567543218765434");

        assertEquals("This value must be 65342AF5456231234567543218765434",
                "65342AF5456231234567543218765434", avs.getXres());

    }

    @Test
    public void verifysetAutn() throws Exception {

        avs.setAutn("65342AF5456231234567543218765434");

        assertEquals("This value must be 65342AF5456231234567543218765434",
                "65342AF5456231234567543218765434", avs.getAutn());

    }

    @Test
    public void verifysetCk() throws Exception {

        avs.setCk("65342AF5456231234567543218765434");

        assertEquals("This value must be 65342AF5456231234567543218765434",
                "65342AF5456231234567543218765434", avs.getCk());

    }

    @Test
    public void verifysetIk() throws Exception {

        avs.setIk("65342AF5456231234567543218765434");

        assertEquals("This value must be 65342AF5456231234567543218765434",
                "65342AF5456231234567543218765434", avs.getIk());

    }


    @Test
    public void verifygetRand() throws Exception {

        assertEquals("This value must be 65342AF5456231234567543218765433",
                "65342AF5456231234567543218765433", avs.getRand());

    }

    @Test
    public void verifygetXres() throws Exception {

        assertEquals("65342AF5456231234567543218765433", avs.getXres());

    }

    @Test
    public void verifygetAutn() throws Exception {

        assertEquals("65342AF5456231234567543218765433", avs.getAutn());

    }

    @Test
    public void verifygetCk() throws Exception {

        assertEquals("This value must be 65342AF5456231234567543218765433",
                "65342AF5456231234567543218765433", avs.getCk());

    }

    @Test
    public void verifygetIk() throws Exception {

        assertEquals("This value must be 65342AF5456231234567543218765433",
                "65342AF5456231234567543218765433", avs.getIk());

    }

}

