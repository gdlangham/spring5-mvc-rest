package guru.springfamework.service;

import guru.springfamework.api.v1.mapper.CustomerMapper;
import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.domain.Customer;
import guru.springfamework.repositories.CustomerRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class CustomerServiceImplTest {

    private CustomerService customerService;

    @Mock
    CustomerRepository customerRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        customerService = new CustomerServiceImpl(CustomerMapper.INSTANCE, customerRepository);
    }

    @Test
    public void testGetAllCustomers() {
        Customer customer1 = new Customer();
        customer1.setId(1L);
        customer1.setFirstname("Freddie");
        customer1.setLastname("Jones");

        Customer customer2 = new Customer();
        customer2.setId(2L);
        customer2.setFirstname("Bertha");
        customer2.setLastname("Smith");
        List<Customer> customerList = Arrays.asList(customer1, customer2);

        when(customerRepository.findAll()).thenReturn(customerList);

        List<CustomerDTO> customers = customerService.getAllCustomers();
        assertEquals(customerList.size(), customers.size());
    }

    @Test
    public void testGetCustomerByLastname() {
        Customer customer = new Customer();
        customer.setLastname("Jones");
        when(customerRepository.findCustomerByLastname(anyString())).thenReturn(customer);

        CustomerDTO customerDTO = customerService.getCustomerByLastname("Jones");
        assertEquals(customer.getLastname(), customerDTO.getLastname());
    }

    @Test
    public void testCreateNewCustomer() throws Exception {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setId(1L);
        customerDTO.setFirstname("Joey");
        customerDTO.setLastname("Wells");

        Customer savedCustomer = new Customer();
        savedCustomer.setFirstname(customerDTO.getFirstname());
        savedCustomer.setLastname(customerDTO.getLastname());
        savedCustomer.setId(customerDTO.getId());

        when(customerRepository.save(any())).thenReturn(savedCustomer);

        CustomerDTO savedDTO = customerService.createNewCustomer(customerDTO);
        assertEquals(customerDTO.getFirstname(), savedDTO.getFirstname());
        assertEquals("/api/v1/customers/1", savedDTO.getCustomerUrl());
    }

    @Test
    public void testSaveCustomerByDTO() throws Exception {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstname("Jerry");
        customerDTO.setLastname("Rice");
        customerDTO.setId(1L);

        Customer savedCustomer = new Customer();
        savedCustomer.setLastname(customerDTO.getLastname());
        savedCustomer.setFirstname(customerDTO.getFirstname());
        savedCustomer.setId(1L);

        when(customerRepository.save(any(Customer.class))).thenReturn(savedCustomer);
        CustomerDTO savedDTO = customerService.saveCustomerByDTO(1L, customerDTO);

        assertEquals(customerDTO.getFirstname(), savedDTO.getFirstname());
        assertEquals("/api/v1/customers/1", savedDTO.getCustomerUrl());
    }

    @Test
    public void testDeleteCustomerById() {
        Long id = 1L;

        customerRepository.deleteById(id);

        verify(customerRepository, times(1)).deleteById(anyLong());
    }
}