package com.dizi.dz.repository;

import com.dizi.dz.entity.DiziOrder;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<DiziOrder, Long> {
}
