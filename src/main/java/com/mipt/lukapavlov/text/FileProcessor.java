package com.mipt.lukapavlov.text;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class FileProcessor {
    /**
     * Разбивает файл на части указанного размера
     * @param sourcePath путь к исходному файлу
     * @param outputDir директория для сохранения частей
     * @param partSize размер каждой части в байтах
     * @return список путей к созданным частям
     */
    public List<Path> splitFile(String sourcePath, String outputDir, int partSize) throws IOException {

        List<Path> partPaths = new ArrayList<>();
        Path sourceFile = Paths.get(sourcePath);
        String originalName = sourceFile.getFileName().toString();

        Files.createDirectories(Paths.get(outputDir));

        try (FileChannel sourceChannel = FileChannel.open(sourceFile, StandardOpenOption.READ)) {
            long fileSize = sourceChannel.size();
            long bytesProcessed = 0;
            int partNumber = 1;

            ByteBuffer buffer = ByteBuffer.allocate(partSize);

            while (bytesProcessed < fileSize) {

                String partFileName = String.format("%s.part%d", originalName, partNumber);
                Path partPath = Paths.get(outputDir, partFileName);
                partPaths.add(partPath);

                try (FileChannel destChannel = FileChannel.open(partPath,
                        StandardOpenOption.CREATE, StandardOpenOption.WRITE)) {

                    buffer.clear();

                    int bytesRead = sourceChannel.read(buffer);
                    if (bytesRead == -1) break;

                    buffer.flip();

                    destChannel.write(buffer);

                    bytesProcessed += bytesRead;
                    partNumber++;
                }
            }
        }

        return partPaths;
    }

    /**
     * Объединяет части файла обратно в один файл
     * @param partPaths список путей к частям файла (в правильном порядке)
     * @param outputPath путь для результирующего файла
     */
    public void mergeFiles(List<Path> partPaths, String outputPath) throws IOException {
        // TODO: Реализовать объединение частей используя FileChannel
        // - Проверить что все части существуют
        // - Объединить в правильном порядке
        try (FileChannel destChannel = FileChannel.open(Paths.get(outputPath),
                StandardOpenOption.CREATE, StandardOpenOption.WRITE)) {

            ByteBuffer buffer = ByteBuffer.allocate(8192);

            for (Path partPath : partPaths) {
                if (!Files.exists(partPath)) {
                    throw new IOException("Такой части нет");
                }
                try (FileChannel sourceChannel = FileChannel.open(partPath, StandardOpenOption.READ)) {
                    buffer.clear();

                    while (sourceChannel.read(buffer) > 0) {
                        buffer.flip();
                        destChannel.write(buffer);
                        buffer.clear();
                    }
                }
            }
        }
    }
}
