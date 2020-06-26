package com.bank.csm.annotations;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * ValueMappers annotation takes argument as mapping of
 * variables on class as meta and it will be used to
 * copy the value from one object to another
 * @author kumar-sand
 *
 */
@Retention(RUNTIME)
@Target(TYPE)
public @interface ValueMappers {
	boolean isNative() default true;
	ValueMapper[] mappers() default {};
}
