package com.mipt.lukapavlov.text;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TextFileAnalyzer {
    public static class AnalysisResult {
        private final long lineCount;  // количество строк в файле
        private final long wordCount; // количество слов в файле
        private final long charCount; // количество символов в файле


        public AnalysisResult(long lineCount, long wordCount, long charCount) {
            this.lineCount = lineCount;
            this.wordCount = wordCount;
            this.charCount = charCount;
        }

        public long getLineCount() {
            return lineCount;
        }
        public long getWordCount() {
            return wordCount;
        }
        public long getCharCount() {
            return charCount;
        }

        @Override
        public String toString() {
            return "lineCount: " + lineCount + "\n" +
                    "wordCount: " + wordCount + "\n" +
                    "charCount: " + charCount;
        }

        // TODO: не забудь прописать конструктор, геттеры, toString()
    }

    public AnalysisResult analyzeFile(String filePath) throws IOException {
        // TODO: Реализовать анализ файла по переданному пути `filePath`, используя BufferedReader
        // Подсчитать в файле: lineCount, wordCount, charCount
        // Использовать try-with-resources для автоматического закрытия потоков
        long lineCount = 0;
        long wordCount = 0;
        long charCount = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader((filePath)))) {
            String line;

            while ((line = reader.readLine()) != null) {
                lineCount++;
                charCount += line.length();

                String[] words = line.split("\\s+");

                wordCount += words.length;
            }
        }
        return new AnalysisResult(lineCount, wordCount, charCount);
    }

    public void saveAnalysisResult(AnalysisResult result, String outputPath) throws IOException {
        // TODO: Сохранить результаты в файл по указанному пути `outputPath` используя BufferedWriter в
    }
}

