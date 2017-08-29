package com.avg.model;

import com.avg.MicroAVG;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Imsi.class)
@SpringBootTest
//@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ImsiTest {

    private Imsi imsi;
    private Avs avs;

    @Before
    public void setup() {
        this.avs = new Avs("65342AF5456231234567543218765433",
                "65342AF5456231234567543218765433",
                "65342AF5456231234567543218765433",
                "65342AF5456231234567543218765433",
                "65342AF54562312345675433");

        this.imsi = new Imsi(10000005, avs);
    }

    @Test
    public void verifyImsiBuilder() throws Exception {

        imsi = new Imsi();

        assertNotNull(imsi.getImsi());

    }

    @Test
    public void verifyImsiBuilderWithImsi() throws Exception {

        imsi = new Imsi(10000007);

        assertEquals("imsi = 10000007", 10000007, imsi.getImsi());
        assertEquals("avs long = 0", 0, imsi.getAvs().size());


    }

    @Test
    public void verifyImsiBuilderByString() throws Exception {
        String jsonImsi = "{\"imsi\":100000001,\"avs\":[{\"rand\":\"65342AF5456231234567543218765432\",\"autn\":\"65342AF5456231234567543218765432\",\"ck\":\"65342AF5456231234567543218765432\",\"ik\":\"65342AF5456231234567543218765432\",\"xres\":\"65342AF5456231234567543218765432\"}]}";
        imsi = new Imsi(jsonImsi);

        //imsi
        assertEquals("This value must be 100000001", 100000001, imsi.getImsi());

        //Avs
        assertEquals("avs long = 1", 1, imsi.getAvs().size());

        assertEquals("Value Ok = 1",
                "65342AF5456231234567543218765432",
                          imsi.getAvs(). get(0).getRand());

    }


    @Test
    public void verifysetImsi() throws Exception {

        imsi.setImsi(10000003);

        assertEquals("This value must be 10000003", 10000003, imsi.getImsi());

    }



    @Test
    public void verifygetImsi() throws Exception {

        assertEquals("This value must be 10000005", 10000005, imsi.getImsi());
    }

    @Test
    public void verifysetAvs() throws Exception {
        this.avs = new Avs("65342AF5456231234567543218765435",
                "65342AF5456231234567543218765435",
                "65342AF5456231234567543218765435",
                "65342AF5456231234567543218765435",
                "65342AF54562312345675435");

        List<Avs> list =  new ArrayList<Avs>();
        list.add(avs);
        imsi.setAvs(list);

        //Avs
        assertEquals("avs long = 1", 1, imsi.getAvs().size());

        assertEquals("Value Ok = 65342AF5456231234567543218765435",
                "65342AF5456231234567543218765435",
                imsi.getAvs().get(0).getRand());

    }


    @Test
    public void verifygetAvs() throws Exception {
        //Avs
        assertEquals("avs long = 1", 1, imsi.getAvs().size());

        assertEquals("Value Ok = 65342AF5456231234567543218765433",
                "65342AF5456231234567543218765433",
                imsi.getAvs().get(0).getRand());

    }


    @Test
    public void verifyaddAvs() throws Exception {

        Avs avs = new Avs("65342AF5456231234567543218765435",
                "65342AF5456231234567543218765435",
                "65342AF5456231234567543218765435",
                "65342AF5456231234567543218765435",
                "65342AF54562312345675435");

        imsi.addAvs(avs);
        //Avs
        assertEquals("avs long = 2", 2, imsi.getAvs().size());

        assertEquals("Value Ok = 65342AF5456231234567543218765435",
                "65342AF5456231234567543218765435",
                imsi.getAvs().get(1).getRand());

    }


    @Test
    public void verifyremoveAvs() throws Exception {

        Avs avs = new Avs("65342AF5456231234567543218765435",
                "65342AF5456231234567543218765435",
                "65342AF5456231234567543218765435",
                "65342AF5456231234567543218765435",
                "65342AF54562312345675435");

        imsi.addAvs(avs);

        imsi.deleteAvs(avs);
        //Avs
        assertEquals("avs long = 1", 1, imsi.getAvs().size());

        assertEquals("Value Ok = 65342AF5456231234567543218765433",
                "65342AF5456231234567543218765433",
                imsi.getAvs().get(0).getRand());

    }


}

