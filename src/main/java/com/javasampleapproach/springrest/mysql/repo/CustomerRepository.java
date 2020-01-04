package com.javasampleapproach.springrest.mysql.repo;

import com.javasampleapproach.springrest.mysql.model.Customer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
	List<Customer> findByAge(int age);
}
