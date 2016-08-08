package com.umland;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collection;

/**
 * Test integration of Camera, CameraFactory and Configuration.
 *
 * This is an example of how to do side-by-side development. This is where you can have
 * multiple variations of a class implementation loaded at the same time. In this
 * particular example all variations must implement the same interface, Camera. This is
 * not an example of hot deployment.
 */
@RunWith(Parameterized.class)
public class ConfigurationIT {
    @Parameterized.Parameter
    public String configName;

    @Parameterized.Parameter(1)
    public String expectedPhotoTakenMessage;

    @Parameterized.Parameters(name="{index}: config={0}; photoTakenMessage={1}")
    public static Collection<Object[]> getTestData() {
        String nikonPath = "src/test/resources/config.nikon.json";
        String canonPath = "src/test/resources/config.canon.json";
        return Arrays.asList(new Object[][] {
                {nikonPath, "Nikon photo taken."},
                {canonPath, "Canon photo taken."}
        });
    }

    @Test
    public void takePhotoTest() throws IOException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        Configuration configuration = Configuration.loadConfiguration(Paths.get(configName));
        String location = configuration.getLocation();
        URL url = new URL(location);
        URLClassLoader urlClassLoader = new URLClassLoader(new URL[] {url});
        Class<? extends CameraFactory> cameraFactoryClass =
                Class.forName(configuration.getFactoryType(), true, urlClassLoader).asSubclass(CameraFactory.class);
        CameraFactory cameraFactory = cameraFactoryClass.newInstance();
        Camera camera = cameraFactory.createCamera();

        Assert.assertEquals(expectedPhotoTakenMessage, camera.takePhoto());
    }
}
