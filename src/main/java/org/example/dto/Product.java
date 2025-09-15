package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private int id;
    private String name;

    public Product copy() {
        return Product.builder()
                .id(this.id)
                .name(this.name)
                .build();
    }

    public Product updateName(String newName) {
        return Product.builder()
                .id(this.id)
                .name(newName)
                .build();
    }
}