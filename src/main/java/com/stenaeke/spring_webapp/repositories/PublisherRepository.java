package com.stenaeke.spring_webapp.repositories;

import com.stenaeke.spring_webapp.domain.Publisher;
import org.springframework.data.repository.CrudRepository;

public interface PublisherRepository extends CrudRepository<Publisher, Long> {
}
