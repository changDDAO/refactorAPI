package com.changddao.refactorAPI.domain.items;

import com.changddao.refactorAPI.domain.Item;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@DiscriminatorValue("M")
@Entity
@Getter @Setter
public class Movie extends Item {
    private String director;
    private String actor;

}
