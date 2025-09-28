package com.dizi.dz.integrations;

import com.dizi.dz.entity.Dizi;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class EmailOrder {

    private final String email;
    private List<Dizi> dizis = new ArrayList<>();

    public void addTaco(Dizi dizi) {
        this.dizis.add(dizi);
    }
}
