package com.biggergames.tbslapi.validation.constraints;


import com.biggergames.tbslapi.validation.validator.FileValueArgumentsProvider;
import org.apiguardian.api.API;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.lang.annotation.*;

@Target({ElementType.ANNOTATION_TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@API(
        status = API.Status.EXPERIMENTAL,
        since = "5.0"
)
@ArgumentsSource(FileValueArgumentsProvider.class)
public @interface FileValueSource {

    String[] files() default {};

    String root();

    Class<?>[] classes() default {};
}

