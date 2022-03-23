package com.jd.springboot.jpa;

import java.util.List;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "users" , path = "users")
public interface UserRestRepository extends PagingAndSortingRepository<User, Long> {
	List<User> findByRole(@Param("role") String role);
}
