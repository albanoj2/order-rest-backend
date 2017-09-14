package com.dzone.albanoj2.example.rest.resource;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityLinks;

public abstract class ResourceAssembler<DomainType, ResourceType> {
	
	@Autowired
	protected EntityLinks entityLinks;
	
	public abstract ResourceType toResource(DomainType domainObject);

	public Collection<ResourceType> toResourceCollection(Collection<DomainType> domainObjects) {
		return domainObjects.stream().map(o -> toResource(o)).collect(Collectors.toList());
	}

}
