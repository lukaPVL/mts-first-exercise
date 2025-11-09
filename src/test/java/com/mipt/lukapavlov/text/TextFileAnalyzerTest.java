package com.mipt.lukapavlov.text;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TextFileAnalyzerTest {
    @Test
    void testAnalyzeFile() throws IOException {
        TextFileAnalyzer analyzer = new TextFileAnalyzer();

        // Создаем временный тестовый файл
        Path testFile = Files.createTempFile("test", ".txt");
        Files.write(testFile, Arrays.asList("Hello world!", "This is test."));

        TextFileAnalyzer.AnalysisResult result = analyzer.analyzeFile(testFile.toString());

        // TODO: пропиши тут все проверки на `result`
        assertEquals(2, result.getLineCount());
        assertEquals(5, result.getWordCount());
        assertEquals(12 + 13, result.getCharCount());
    }

    @Test
    void testSaveAnalysisResult() throws IOException {
        TextFileAnalyzer analyzer = new TextFileAnalyzer();

        // Создаем тестовый результат
        TextFileAnalyzer.AnalysisResult result = new TextFileAnalyzer.AnalysisResult(2, 5, 20);

        // Сохранить в файл
        Path outputFile = Files.createTempFile("analysis", ".txt");
        analyzer.saveAnalysisResult(result, outputFile.toString());

        // Проверить что файл создан и содержит данные
        // TODO добавь проверки на файл по пути: outputFile
        //  1. его размер больше 0
        //  2. прочитай этот файл и посмотри что в нем записаны аналогинчные данные как в объекте `result`
    }
}
