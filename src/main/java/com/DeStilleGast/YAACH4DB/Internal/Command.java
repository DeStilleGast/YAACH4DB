package com.DeStilleGast.YAACH4DB.Internal;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by DeStilleGast on 12-12-2017.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Command {
    String name();
    String usage() default "No parameters need";

    String[] aliases() default {};
    String description() default "No description set";

    String category() default "";
}
