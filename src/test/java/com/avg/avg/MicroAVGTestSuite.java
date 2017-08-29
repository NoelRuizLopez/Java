package com.avg.avg;

import com.avg.model.AvsTest;
import com.avg.model.ImsiTest;
import com.avg.model.Response;
import com.avg.model.ResponseTest;
import com.avg.rest.AVGControllerTest;
import com.avg.service.AVGServiceTest;
import com.avg.utils.ReadFromResourcesFolderTest;
import com.avg.validator.ValidationUtilsTest;
import org.junit.runner.RunWith;

import org.junit.runners.Suite;


// specify a runner class: Suite.class
@RunWith(Suite.class)

// specify an array of test classes
@Suite.SuiteClasses({
        AvsTest.class,
        ImsiTest.class,
        ResponseTest.class,
        AVGControllerTest.class,
        AVGServiceTest.class,
        ReadFromResourcesFolderTest.class,
        ValidationUtilsTest.class
})

public class MicroAVGTestSuite {
}
