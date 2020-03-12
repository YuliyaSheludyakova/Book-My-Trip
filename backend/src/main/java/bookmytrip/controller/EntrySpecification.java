package bookmytrip.controller;

import org.springframework.data.jpa.domain.Specification;

import bookmytrip.entity.Entry;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.domain.GreaterThanOrEqual;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;

@And({
    @Spec(path = "city.name", params = "city", spec = Equal.class),
    @Spec(path = "avrgRating", spec = GreaterThanOrEqual.class)
})
public interface EntrySpecification<T extends Entry> extends Specification<T> {}
