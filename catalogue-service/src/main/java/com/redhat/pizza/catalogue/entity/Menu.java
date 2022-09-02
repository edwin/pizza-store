package com.redhat.pizza.catalogue.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * <pre>
 *     com.redhat.pizza.catalogue.entity.Menu
 * </pre>
 *
 * @author Muhammad Edwin < edwin at redhat dot com >
 * 02 Sep 2022 22:33
 */
@Entity(name = "T_MENU")
public class Menu extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String foodName;
    private BigDecimal price;

    public Menu(Long id, String foodName, BigDecimal price) {
        this.id = id;
        this.foodName = foodName;
        this.price = price;
    }

    public Menu() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
