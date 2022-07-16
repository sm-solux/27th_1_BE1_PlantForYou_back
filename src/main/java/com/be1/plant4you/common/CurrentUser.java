package com.be1.plant4you.common;

import java.lang.annotation.*;

@Target({ ElementType.PARAMETER, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
//@AuthenticationPrincipal
public @interface CurrentUser {
}
