package com.bank.csm.annotations;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;


/**
 * Value Mapper annotation will be used inside ValueMappers
 * annotation for mapping of values
 * @author kumar-sand
 *
 */
@Retention(RUNTIME)
@Target(ElementType.ANNOTATION_TYPE)
public @interface ValueMapper {
	String source();
	String target();
}
