package com.mipt.lukapavlov.annotations;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Range {
    int min() default 0;
    int max() default Integer.MAX_VALUE;
    String message();
}

