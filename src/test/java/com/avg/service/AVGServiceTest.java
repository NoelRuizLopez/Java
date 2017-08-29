package com.avg.service;


import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.avg.exceptions.AvgException;
import com.avg.model.Avs;
import com.avg.model.Imsi;
import com.avg.repository.ImsiRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;



@RunWith(SpringJUnit4ClassRunner.class)
public class AVGServiceTest {

    @Mock
    private ImsiRepository imsiRepository;

    @InjectMocks
    private AVGServiceImpl avgService;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllImsis(){
        List<Imsi> imsiList = new ArrayList<Imsi>();
        imsiList.add(new Imsi(10000001));
        imsiList.add(new Imsi(10000002));
        imsiList.add(new Imsi(10000003));
        imsiList.add(new Imsi(10000004));
        when(imsiRepository.findAll()).thenReturn(imsiList);


        List<Imsi> result = avgService.getAllImsis();
        assertEquals(4, result.size());

    }
    @Test
    public void testGetAllAvsFromImsi() throws IOException, AvgException {
        String imsi_json = "{\"imsi\":100000005,\"avs\":[{\"rand\":\"65342AF5456231234567543218765400\"," +
                "\"autn\":\"65342AF5456231234567543218765400\",\"ck\":\"65342AF5456231234567543218765400\"," +
                "\"ik\":\"65342AF5456231234567543218765400\",\"xres\":\"65342AF5456231234567543218765400\"}]}";
        Imsi imsi = new Imsi(imsi_json);
        when(imsiRepository.findById(100000005L)).thenReturn(Optional.of(imsi));
        Imsi result = avgService.getAllAvsFromImsi(100000005).get();
        assertEquals("65342AF5456231234567543218765400", result.getAvs().get(0).getRand());
        assertEquals(100000005, result.getImsi());

    }
    @Test
    public void testPostImsi() throws IOException, AvgException {
        String imsi_json = "{\"imsi\":100000006,\"avs\":[{\"rand\":\"65342AF5456231234567543218765400\"," +
                "\"autn\":\"65342AF5456231234567543218765400\",\"ck\":\"65342AF5456231234567543218765400\"," +
                "\"ik\":\"65342AF5456231234567543218765400\",\"xres\":\"65342AF5456231234567543218765400\"}]}";
        Imsi imsi = new Imsi(imsi_json);
        when(imsiRepository.save(imsi)).thenReturn(imsi);
        Imsi result = avgService.postImsi(imsi);
        assertEquals(100000006, result.getImsi());
        assertEquals("65342AF5456231234567543218765400", result.getAvs().get(0).getRand());
    }

    @Test
    public void testAddAvstoImsi(){

    }
    @Test
    public void testDeleteImsi() throws IOException, AvgException {
        String imsi_json = "{\"imsi\":100000007,\"avs\":[{\"rand\":\"65342AF5456231234567543218765400\"," +
                "\"autn\":\"65342AF5456231234567543218765400\",\"ck\":\"65342AF5456231234567543218765400\"," +
                "\"ik\":\"65342AF5456231234567543218765400\",\"xres\":\"65342AF5456231234567543218765400\"}]}";
        Imsi imsi = new Imsi(imsi_json);
        avgService.deleteImsi(100000007L);
        verify(imsiRepository, times(1)).deleteById(100000007L);

    }

 /*    @Test
    public void testDeleteAvs(){

    }*/

}


