package com.springboot.springbootref.rabbitmq.entity;


//import lombok.Data;
//import lombok.NoArgsConstructor;

//@NoArgsConstructor
//@Data
public class OrderDt {
    private Order order;
    private String status;
    private String message;

    public OrderDt(Order order, String status, String message) {
        this.order = order;
        this.status = status;
        this.message = message;
    }

    public OrderDt() {
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "OrderDt{" +
                "order=" + order +
                ", status='" + status + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}