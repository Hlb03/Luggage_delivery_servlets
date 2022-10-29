package org.luggage_delivery.entity;
/*
  User: admin
  Cur_date: 12.10.2022
  Cur_time: 15:58
*/

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;

@Entity
@Table(name = "delivery")
public class Delivery implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "luggage_size")
    private BigDecimal size;
    @Column(name = "total_price")
    private BigDecimal totalPrice;
    @Column(name = "luggage_type")
    private String luggageType;
    @Column(name = "weight")
    private BigDecimal weight;
    @Column(name = "start_date")
    private Date startDate;
    @Column(name = "delivery_date")
    private Date deliveryDate;
    @Column(name = "delivery_address")
    private String deliveryAddress;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "delivery_status_id")
    private DeliveryStatus deliveryStatus;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "routes_id")
    private Route route;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public Delivery() {}

    public Delivery(BigDecimal size, BigDecimal totalPrice, String luggageType, BigDecimal weight, Date startDate,
                    Date deliveryDate, String deliveryAddress, DeliveryStatus deliveryStatus, Route route, User user) {
        this.size = size;
        this.totalPrice = totalPrice;
        this.luggageType = luggageType;
        this.weight = weight;
        this.startDate = startDate;
        this.deliveryDate = deliveryDate;
        this.deliveryAddress = deliveryAddress;
        this.deliveryStatus = deliveryStatus;
        this.route = route;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BigDecimal getSize() {
        return size;
    }

    public void setSize(BigDecimal size) {
        this.size = size;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getLuggageType() {
        return luggageType;
    }

    public void setLuggageType(String luggageType) {
        this.luggageType = luggageType;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public DeliveryStatus getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(DeliveryStatus deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Delivery " + id + " with dimensions " + size + " costs " + totalPrice + " " + luggageType + " "
                + weight + ". Order was made - " + startDate + " and plans to be on " +  deliveryDate +
                " . Current status of delivery is " + deliveryStatus + ". The route is - " + route +
                ". Order was made by " + user;
    }
}
