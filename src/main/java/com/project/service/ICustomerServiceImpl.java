package com.project.service;

import com.project.dao.ICustomerRepo;
import com.project.entity.Customer;
import com.project.exception.CustomerNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ICustomerServiceImpl implements ICustomerService {

    @Autowired
    ICustomerRepo customerRepo;

    @Override
    public String registerCustomer(Customer customer) {
        Integer customerId = customerRepo.save(customer).getCustomerId();
        log.info("Customer id is :: " + customerId);
        return log.toString();
    }

    @Override
    public List<Customer> fetchAllCustomer() {

        List<Customer> customerList = customerRepo.findAll();
        return customerList;
    }

    @Override
    public Customer fetchCustomerByfirstName(String firstName) {

        return customerRepo.fetchCustomerByfirstName(firstName);
    }
    @Override
    public Customer fetchCustomerBylastName(String lastName) {

        return customerRepo.fetchCustomerBylastName(lastName);
    }

    @Override
    public String updateCustomerByDetails(Customer customer) {
        Optional<Customer> optional = customerRepo.findById(customer.getCustomerId());
        if(optional.isPresent()){
            customerRepo.save(customer);
            log.info("Customer with id :: " + customer.getCustomerId() + " updated");
            return log.toString();
        }
        else
            throw new CustomerNotFoundException("Customer with id " + customer.getCustomerId() + " not available for updation");

    }

    @Override
    public String deleteCustomerById(Integer id) {
        Optional<Customer> optional = customerRepo.findById(id);
        if(optional.isPresent()){
            customerRepo.delete(optional.get());
            log.info("Customer with id :: " + id + " deleted");
            return log.toString();
        }
        else
          throw new CustomerNotFoundException("Customer with id " + id + " not available for deletion");
    }
}
