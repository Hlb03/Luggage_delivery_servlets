package org.luggage_delivery.entity;
/*
  User: admin
  Cur_date: 12.10.2022
  Cur_time: 15:56
*/

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "route")
public class Route implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "start_point")
    private String startPoint;
    @Column(name = "destination_point")
    private String destinationPoint;
    @Column(name = "distance")
    private BigDecimal distance;

    @OneToMany(mappedBy = "route", cascade = CascadeType.MERGE)
    private List<Delivery> delivery;

    public Route() {}

    public Route(String startPoint, String destinationPoint, BigDecimal distance) {
        this.startPoint = startPoint;
        this.destinationPoint = destinationPoint;
        this.distance = distance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(String startPoint) {
        this.startPoint = startPoint;
    }

    public String getDestinationPoint() {
        return destinationPoint;
    }

    public void setDestinationPoint(String destinationPoint) {
        this.destinationPoint = destinationPoint;
    }

    public BigDecimal getDistance() {
        return distance;
    }

    public void setDistance(BigDecimal distance) {
        this.distance = distance;
    }

    public List<Delivery> getDelivery() {
        return delivery;
    }

    public void setDelivery(List<Delivery> delivery) {
        this.delivery = delivery;
    }

    @Override
    public String toString() {
        return "Route{" +
                "id=" + id +
                ", startPoint='" + startPoint + '\'' +
                ", destinationPoint='" + destinationPoint + '\'' +
                ", distance " + distance + '}';
    }
}
