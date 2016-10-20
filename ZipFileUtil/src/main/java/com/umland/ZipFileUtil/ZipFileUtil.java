package com.umland.ZipFileUtil;

import java.io.IOException;
import java.net.URI;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.Map;

public class ZipFileUtil
{
    public void zipFiles(Path destinationPath, Path... paths) {
        if (Files.notExists(destinationPath)) {
            try {
                Files.createDirectories(destinationPath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try (FileSystem zipFileSystem = createZipFileSystem(destinationPath, true)) {
            final Path root = zipFileSystem.getPath("/");

            for (Path src : paths) {
                if (!Files.isDirectory(src)) {
                    final Path destination = zipFileSystem.getPath(root.toString(), src.toString());
                    final Path parent = destination.getParent();
                    if (Files.notExists(parent)) {
                        Files.createDirectories(parent);
                    }
                    Files.copy(src, destination, StandardCopyOption.REPLACE_EXISTING);
                } else {
                    Files.walkFileTree(src, new SimpleFileVisitor<Path>() {
                        @Override
                        public FileVisitResult visitFile(Path file, BasicFileAttributes attributes) throws IOException {
                            final Path destination = zipFileSystem.getPath(root.toString(), file.toString());
                            Files.copy(file, destination, StandardCopyOption.REPLACE_EXISTING);
                            return FileVisitResult.CONTINUE;
                        }

                        @Override
                        public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attributes) throws IOException {
                            final Path directoryToCreate = zipFileSystem.getPath(root.toString(), dir.toString());
                            if (Files.notExists(directoryToCreate)) {
                                Files.createDirectories(directoryToCreate);
                            }
                            return FileVisitResult.CONTINUE;
                        }
                    });
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private FileSystem createZipFileSystem(Path zipFile, Boolean create) {
        final URI uri = URI.create("jar:file:" + zipFile.toUri().getPath().replace(" ", "%20"));
        final Map<String, String> env = new HashMap<>();
        if (create) {
            env.put("create", create.toString());
        }
        try {
            return FileSystems.newFileSystem(uri, env);
        } catch (IOException e) {
            //TODO ru: study exceptions and build general purpose exception handling classes to
            //TODO ru: make things like this trivial to deal with.
            throw new RuntimeException(e);
        }
    }
}
