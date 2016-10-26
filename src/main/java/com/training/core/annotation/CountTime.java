package com.training.core.annotation;

import java.lang.annotation.*;

/**
 * Created by Athos on 2016-08-02.
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CountTime {
}
