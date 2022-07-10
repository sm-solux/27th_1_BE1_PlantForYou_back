package com.be1.plant4you.common;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public abstract class ScrapId implements Serializable {

    private Long postId;

    private Long userId;
}
