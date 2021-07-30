package guru.springfamework.api.v1.mapper;

import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.domain.Customer;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CustomerMapperTest {
    private CustomerMapper customerMapper;
    @Before
    public void setUp() throws Exception {
        customerMapper = CustomerMapper.INSTANCE;
    }

    @Test
    public void testCustomerToCustomerDTO() {
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setFirstname("Freddie");
        customer.setLastname("Jones");

        CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);

        assertEquals(Long.valueOf(1L), customerDTO.getId());
        assertEquals("Freddie", customerDTO.getFirstname());
    }
}