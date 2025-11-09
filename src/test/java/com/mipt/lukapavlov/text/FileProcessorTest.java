package com.mipt.lukapavlov.text;

import org.junit.jupiter.api.Test;
import org.w3c.dom.ls.LSOutput;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class FileProcessorTest {
    @Test
    void testSplitAndMergeFile() throws IOException {
        FileProcessor processor = new FileProcessor();

        // Создать тестовый файл
        Path testFile = Files.createTempFile("test", ".dat");
        byte[] testData = new byte[1500]; // 1.5KB данных
        new Random().nextBytes(testData);
        Files.write(testFile, testData);

        // Разбить на части по 500 байт
        String outputDir = Files.createTempDirectory("parts").toString();
        List<Path> parts = processor.splitFile(testFile.toString(), outputDir, 500);
        // Должно быть 3 части
        // TODO: напиши тут необходимые проверки asserts
        assertEquals(3, parts.size(), "Должно быть создано 3 части файла");

        for (Path part : parts) {
            assertTrue(Files.exists(part), "Часть файла должна существовать: " + part);
            assertTrue(Files.size(part) > 0, "Часть файла не должна быть пустой: " + part);
        }

        assertEquals(500, Files.size(parts.get(0)), "Первая часть должна быть 500 байт");
        assertEquals(500, Files.size(parts.get(1)), "Вторая часть должна быть 500 байт");
        assertEquals(500, Files.size(parts.get(2)), "Третья часть должна быть 500 байт");

        // Объединить обратно
        Path mergedFile = Files.createTempFile("merged", ".dat");
        processor.mergeFiles(parts, mergedFile.toString());

        // Проверить что исходный и объединенный файлы идентичны
        assertArrayEquals(Files.readAllBytes(testFile), Files.readAllBytes(mergedFile), "Исходный и объединенный файлы должны быть идентичны");

        assertEquals(1500, Files.size(mergedFile), "Объединенный файл должен быть размером 1500 байт");

    }
}
