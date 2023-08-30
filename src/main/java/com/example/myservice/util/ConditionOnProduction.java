package com.example.myservice.util;

import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Retention;
import org.springframework.context.annotation.Conditional;

@Retention(RUNTIME)
@Conditional(ConditionalOnProduction.class)
public @interface ConditionOnProduction {
}
