package org.luggage_delivery.entity;
/*
  User: admin
  Cur_date: 12.10.2022
  Cur_time: 15:28
*/

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "delivery_status")
public class DeliveryStatus implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "status_name")
    private String statusName;

    @OneToMany(mappedBy = "deliveryStatus", cascade = CascadeType.MERGE)
    private List<Delivery> delivery;

    public DeliveryStatus() {}

    public DeliveryStatus(String statusName) {
        this.statusName = statusName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public List<Delivery> getDelivery() {
        return delivery;
    }

    public void setDelivery(List<Delivery> delivery) {
        this.delivery = delivery;
    }

    @Override
    public String toString() {
        return statusName + " status";
    }
}
