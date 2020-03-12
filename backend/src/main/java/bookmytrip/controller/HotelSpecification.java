package bookmytrip.controller;

import bookmytrip.entity.Hotel;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;

@And({
    @Spec(path = "stars", spec = Equal.class),
    @Spec(path = "breakfastIncl", spec = Equal.class)
})
public interface HotelSpecification extends EntrySpecification<Hotel> {}
