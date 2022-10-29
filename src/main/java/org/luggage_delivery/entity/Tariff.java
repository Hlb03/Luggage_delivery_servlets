package org.luggage_delivery.entity;
/*
  User: admin
  Cur_date: 12.10.2022
  Cur_time: 15:23
*/

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
public class Tariff implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "type")
    private String type;
    @Column(name = "price")
    private BigDecimal price;

    public Tariff() {}

    public Tariff(String type, BigDecimal price) {
        this.type = type;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return id + " tariff name " + type + " with multiply on coefficient " + price;
    }
}
