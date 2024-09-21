package org.springpoc.cloudruncrud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springpoc.cloudruncrud.model.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
}
