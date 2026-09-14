package org.acme;

import java.io.Serializable;

/**
 * Sample row model rendered by the {@code p:dataTable} on {@code index.xhtml}.
 * <p>
 * Faces needs plain getters, so keep this a JavaBean rather than a record.
 * It must be {@link Serializable} because the owning bean is {@code @ViewScoped}.
 */
public class Car implements Serializable {

    private static final long serialVersionUID = 1L;

    private final int year;
    private final String brand;
    private final String color;
    private final double price;

    public Car(int year, String brand, String color, double price) {
        this.year = year;
        this.brand = brand;
        this.color = color;
        this.price = price;
    }

    public int getYear() {
        return year;
    }

    public String getBrand() {
        return brand;
    }

    public String getColor() {
        return color;
    }

    public double getPrice() {
        return price;
    }
}
