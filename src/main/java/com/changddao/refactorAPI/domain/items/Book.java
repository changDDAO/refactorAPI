package com.changddao.refactorAPI.domain.items;

import com.changddao.refactorAPI.domain.Item;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@DiscriminatorValue("B")
@Getter @Setter
public class Book extends Item {
    private String author;
    private String isbn;
}
