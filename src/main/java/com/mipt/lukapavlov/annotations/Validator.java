package com.mipt.lukapavlov.annotations;

import java.lang.reflect.Field;

public class Validator {

    public static ValidationResult validate(Object object) throws IllegalAccessException {
        if (object == null) {
            throw new NullPointerException("object is null");
        }

        ValidationResult validationResult = new ValidationResult();

        for (Field field : object.getClass().getDeclaredFields()) {
            field.setAccessible(true);

            Object fieldValue = field.get(object);

            if (field.isAnnotationPresent(Email.class)) {
                validateEmail(field, fieldValue, validationResult);
            }

            if (field.isAnnotationPresent(NotNull.class)) {
                validateNotNull(field, fieldValue, validationResult);
            }

            if (field.isAnnotationPresent(Range.class)) {
                validateRange(field, fieldValue, validationResult);
            }

            if (field.isAnnotationPresent(Size.class)) {
                validateSize(field, fieldValue, validationResult);
            }
        }
        return validationResult;
    }

    public static void validateEmail(Field field, Object fieldValue, ValidationResult validationResult) {
        Email typeOfAnnotation = field.getAnnotation(Email.class);

        if (fieldValue.getClass() != String.class) {
            validationResult.addError(typeOfAnnotation.message());
        }

        if (!(String.valueOf(fieldValue).matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$"))) {
            validationResult.addError(typeOfAnnotation.message());
        }
    }

    public static void validateNotNull(Field field, Object fieldValue, ValidationResult validationResult) {
        NotNull typeOfAnnotation = field.getAnnotation(NotNull.class);

        if (fieldValue.getClass() == null) {
            validationResult.addError(typeOfAnnotation.message());
        }
    }

    public static void validateRange(Field field, Object fieldValue, ValidationResult validationResult) {
        Range typeOfAnnotation = field.getAnnotation(Range.class);

        if (fieldValue.getClass() != Integer.class) {
            validationResult.addError(typeOfAnnotation.message());
        }

        Integer value = Integer.valueOf(fieldValue.toString());

        if (value < typeOfAnnotation.min() || value > typeOfAnnotation.max()) {
            validationResult.addError(typeOfAnnotation.message());
        }
    }

    public static void validateSize(Field field, Object fieldValue, ValidationResult validationResult) {
        Size typeOfAnnotation = field.getAnnotation(Size.class);

        if (fieldValue.getClass() != Integer.class) {
            validationResult.addError(typeOfAnnotation.message());
        }

        Integer value = Integer.valueOf(fieldValue.toString());

        if (value < typeOfAnnotation.min() || value > typeOfAnnotation.max()) {
            validationResult.addError(typeOfAnnotation.message());
        }
    }
}
