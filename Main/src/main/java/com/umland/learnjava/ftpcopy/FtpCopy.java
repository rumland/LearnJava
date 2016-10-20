package com.umland.learnjava.ftpcopy;

import org.apache.commons.io.FileUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Path;

public class FtpCopy {
    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    @Test
    public void copyAll() throws Exception {
        final String hostname = "71.205.135.200";
        final String baseFtpDir = "/shares/ftp_copy_from/Jeanne_memorial/";
        final String baseFtpDirectory = "ftp://" + hostname + baseFtpDir;

        FTPClient ftpClient = new FTPClient();
        ftpClient.connect(hostname);
        ftpClient.enterRemotePassiveMode();
        ftpClient.login("anonymous", "");
        ftpClient.changeWorkingDirectory(baseFtpDir);

        File folder = temporaryFolder.newFolder("JeanneMemorial");

        for (FTPFile file : ftpClient.listFiles(baseFtpDir)) {
            URL fileUrl = new URL(baseFtpDirectory + file.getName());
            URLConnection urlConnection = fileUrl.openConnection();
            try (InputStream inputStream = urlConnection.getInputStream()) {
                Path destination = folder.toPath().resolve(file.getName());
                System.out.printf("Copying: %s to %s.%n", fileUrl.toString(), destination.toString());
                FileUtils.copyInputStreamToFile(inputStream, destination.toFile());
            }
        }
    }
}
