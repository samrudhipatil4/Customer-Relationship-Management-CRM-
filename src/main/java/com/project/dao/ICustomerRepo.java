package com.project.dao;
import com.project.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ICustomerRepo extends JpaRepository<Customer,Integer>{

    @Query(value = "select * from CUSTOMER where first_Name = :firstName", nativeQuery = true)
    public Customer fetchCustomerByfirstName(String firstName);
    @Query(value = "select * from CUSTOMER where last_Name = :lastName", nativeQuery = true)
    public Customer fetchCustomerBylastName(String firstName);
}

