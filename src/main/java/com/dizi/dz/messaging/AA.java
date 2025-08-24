package com.dizi.dz.messaging;

import org.springframework.context.annotation.Scope;

@Scope("SINGLETON")
public class AA {
    // BB is prototype scoped >
        // 1. use provider from jakarta.provider
        // 2. @Lookup from springframework to break the circular dependency
        // 3. Scoped proxy mode - recently is not recommended to change
    private BB bb;

    public AA(BB bb) {
        this.bb = bb;

    }
}
