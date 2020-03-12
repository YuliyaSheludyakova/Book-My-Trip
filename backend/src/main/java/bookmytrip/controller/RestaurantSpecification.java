package bookmytrip.controller;

import bookmytrip.entity.Restaurant;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;

@And({
    @Spec(path = "priceLevel", spec = Equal.class)
})
public interface RestaurantSpecification extends EntrySpecification<Restaurant> {}
