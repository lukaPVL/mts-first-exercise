package com.mipt.lukapavlov.patterns;

import java.time.Duration;
import java.util.Optional;

// TODO: Декоратор для метрик
//  Должен:
//      1. При findDataByKey - замерять скорость работы метода и отправлять через MetricService (реализация уже есть внутри класса)
//      2. При saveData - замерять скорость работы метода и отправлять через MetricService (реализация уже есть внутри класса)
//      3. При deleteData - замерять скорость работы метода и отправлять через MetricService (реализация уже есть внутри класса)
class MetricableDecorator implements DataService {

    private final DataService wrappedService;
    private final MetricService metricService;

    MetricableDecorator(DataService wrappedService, MetricService metricService) {
        this.wrappedService = wrappedService;
        this.metricService = metricService;
    }

    @Override
    public Optional<String> findDataByKey(String key) {
        long startTime = System.nanoTime();

        try {
            Optional<String> res = wrappedService.findDataByKey(key);
            return res;
        } finally {
            long endTime = System.nanoTime();
            Duration duration = Duration.ofNanos(endTime - startTime);
            metricService.sendMetric(duration);
        }
    }

    @Override
    public void saveData(String key, String data) {
        long startTime = System.nanoTime();

        try {
            wrappedService.saveData(key, data);
        } finally {
            long endTime = System.nanoTime();
            Duration duration = Duration.ofNanos(endTime - startTime);
            metricService.sendMetric(duration);
        }
    }

    @Override
    public boolean deleteData(String key) {
        long startTime = System.nanoTime();

        try {
            return wrappedService.deleteData(key);
        } finally {
            long endTime = System.nanoTime();
            Duration duration = Duration.ofNanos(endTime - startTime);
            metricService.sendMetric(duration);
        }
    }
    // todo: ваш код тут

    public static class MetricService {
        public void sendMetric(Duration duration) {
            System.out.println("Метод выполнялся: " + duration.toString());
        }
    }
}
