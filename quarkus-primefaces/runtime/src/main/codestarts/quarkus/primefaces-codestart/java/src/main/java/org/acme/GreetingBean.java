package org.acme;

import java.io.Serializable;
import java.util.List;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

/**
 * Backing bean for {@code index.xhtml}.
 * <p>
 * {@code @Named} exposes it to EL as {@code #{greetingBean}} and {@code @ViewScoped} keeps it
 * alive for as long as the user stays on the same view, which is what most Faces pages want.
 * A {@code @ViewScoped} bean is stored in the view state, so it has to be {@link Serializable}.
 */
@Named
@ViewScoped
public class GreetingBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private List<Car> cars;

    @PostConstruct
    public void init() {
        cars = List.of(
                new Car(2021, "Volkswagen", "Blue", 25400.00),
                new Car(2019, "Renault", "Silver", 18900.00),
                new Car(2023, "Volvo", "Black", 47250.00),
                new Car(2020, "Audi", "White", 38100.00),
                new Car(2022, "Fiat", "Red", 16750.00),
                new Car(2018, "Honda", "Green", 14300.00),
                new Car(2024, "BMW", "Grey", 52900.00));
    }

    public void greet() {
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Hello, " + name + "!",
                        "This message was rendered by p:growl over AJAX."));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Car> getCars() {
        return cars;
    }
}
