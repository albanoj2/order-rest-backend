package com.dzone.albanoj2.example.rest.resource;

import java.util.Collection;
import java.util.stream.Collectors;

public abstract class ResourceAssembler<DomainType, ResourceType> {
	
	public abstract ResourceType toResource(DomainType domainObject);

	public Collection<ResourceType> toResourceCollection(Collection<DomainType> domainObjects) {
		return domainObjects.stream().map(o -> toResource(o)).collect(Collectors.toList());
	}
}
