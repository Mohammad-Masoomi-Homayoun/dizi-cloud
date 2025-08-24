package com.dizi.dz.messaging;

import org.springframework.context.annotation.Scope;

@Scope("PROTOTYPE")
public class BB {

    private AA aa;

    public BB(AA aa) {
        this.aa = aa;
    }
}
