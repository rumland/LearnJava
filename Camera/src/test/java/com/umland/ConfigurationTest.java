package com.umland;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Random;

public class ConfigurationTest {
    Configuration configuration;

    @Before
    public void before() throws IOException, URISyntaxException {
        File f = new File("src/test/resources/config.json");
        configuration = Configuration.loadConfiguration(f.toPath());
    }

    @Test
    public void getFactoryType() throws Exception {
        Assert.assertEquals("com.umland.NikonCameraFactory", configuration.getFactoryType());
    }

    @Test
    public void setFactoryType() throws Exception {
        String expectedFactoryType = RandomStringUtils.random(new Random().nextInt(100));
        configuration.setFactoryType(expectedFactoryType);

        Assert.assertEquals(expectedFactoryType, configuration.getFactoryType());
    }

    @Test
    public void getLocation() throws Exception {
        Assert.assertEquals("file:///../out/artifacts/CameraLibrary_jar/CameraLibrary.jar",
                configuration.getLocation());
    }

    @Test
    public void setLocation() throws Exception {
        String expectedLocation = RandomStringUtils.random(new Random().nextInt(100));
        configuration.setLocation(expectedLocation);

        Assert.assertEquals(expectedLocation, configuration.getLocation());
    }
}