package framework.annotations;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.SOURCE)
public @interface Get {
    String value() default ""; // relative path, e.g. "/{id}"
}