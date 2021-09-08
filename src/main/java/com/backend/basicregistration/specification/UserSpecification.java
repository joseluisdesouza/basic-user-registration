package com.backend.basicregistration.specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.backend.basicregistration.entity.User;

import lombok.Builder;

@Builder
public class UserSpecification implements Specification<User>{
	
	@Builder.Default
    private final transient Optional<Long> id = Optional.empty();
	
	@Builder.Default
    private final transient Optional<String> name = Optional.empty();

	@Override
	public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
		List<Predicate> predicates = new ArrayList<>();
		id.ifPresent(s -> predicates.add(root.get("id").in(s)));
		name.ifPresent(s -> predicates.add(builder.like(builder.lower(root.get("name")), "%" + s.toLowerCase() + "%")));
		return builder.and(predicates.toArray(new Predicate[0]));
	}
	

}
