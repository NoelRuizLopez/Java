package com.avg.utils;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ReadFromResourcesFolderTest.class)
@SpringBootTest
public class ReadFromResourcesFolderTest {

    private ReadFromResourcesFolder rfrf = null;

    @Before
    public void setup() {
        String file_path = "imsi_schema.json";
        rfrf = new ReadFromResourcesFolder(file_path);
    }

    @Test
    public void verifyBuilder() throws Exception {
        ReadFromResourcesFolder rfrf2 = null;
        rfrf2 = new ReadFromResourcesFolder();

        assertNotNull(rfrf2);
    }

    @Test
    public void verifyBuilderFile() throws Exception {
        assertNotNull(rfrf);
    }

    @Test
    public void verifyGetFile() throws Exception {
        assertTrue(rfrf.getFile() instanceof File);
    }

    @Test
    public void verifyGetContent() throws Exception {
        String file_path2 = "imsi_schema.json";
        assertTrue(rfrf.getContent(file_path2) instanceof String);
    }

}
