package guru.springfamework.service;

import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.domain.Customer;

import java.util.List;

public interface CustomerService {
    public List<CustomerDTO> getAllCustomers();
    public CustomerDTO getCustomerByLastname(String lastname);
    public CustomerDTO getCustomerById(Long id);
    public CustomerDTO createNewCustomer(CustomerDTO customerDTO);
}
