package com.springboot.springbootref.shops;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopsRepository extends JpaRepository<Shops,Long> {
}
