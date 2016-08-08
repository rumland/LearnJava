package com.umland;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collection;
import java.util.Random;

@RunWith(Parameterized.class)
public class ConfigurationTest {
    public Configuration configuration;

    @Parameterized.Parameter
    public String configName;

    @Parameterized.Parameter(1)
    public String factoryName;

    @Parameterized.Parameters(name="{index}: config={0}; factory={1}")
    public static Collection<Object[]> getTestData() {
        String nikonPath = "src/test/resources/config.nikon.json";
        String canonPath = "src/test/resources/config.canon.json";
        return Arrays.asList(new Object[][] {
                {canonPath, "com.umland.CanonCameraFactory"},
                {nikonPath, "com.umland.NikonCameraFactory"}
        });
    }

    @Before
    public void beforeTest() throws IOException {
        configuration = Configuration.loadConfiguration(Paths.get(configName));
    }

    @Test
    public void getFactoryType() throws Exception {
        Assert.assertEquals(factoryName, configuration.getFactoryType());
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