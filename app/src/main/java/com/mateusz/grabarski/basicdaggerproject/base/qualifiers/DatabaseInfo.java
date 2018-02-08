package com.mateusz.grabarski.basicdaggerproject.base.qualifiers;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

/**
 * Created by Mateusz Grabarski on 08.02.2018.
 */
@Qualifier
@Retention(RetentionPolicy.RUNTIME)
public @interface DatabaseInfo {
}
