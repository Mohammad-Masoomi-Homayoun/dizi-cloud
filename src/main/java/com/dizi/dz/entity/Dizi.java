package com.dizi.dz.entity;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Dizi {

    private Long id;

    private Date createdAt = new Date();

    @NotNull
    @Size(min = 5, message = "Name must be at least 5 characters long")
    private String name;

    @NotNull
    @Size(min = 1, message = "You must choose at least 1 ingredient")
    private List<IngredientRef> ingredients;

    public void addIngredient(Ingredient dizi) {
        this.ingredients.add(new IngredientRef(dizi.getId()));
    }

    public Dizi(String name) {
        this.name = name;
        this.createdAt = new Date();
    }

    public Dizi() {
    }

}
