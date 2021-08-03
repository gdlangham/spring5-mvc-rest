package guru.springfamework.init;

import guru.springfamework.domain.Category;
import guru.springfamework.domain.Customer;
import guru.springfamework.domain.Vendor;
import guru.springfamework.repositories.CategoryRepository;
import guru.springfamework.repositories.CustomerRepository;
import guru.springfamework.repositories.VendorRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class DBLoader implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    private final CustomerRepository customerRepository;
    private final VendorRepository vendorRepository;

    public DBLoader(final CategoryRepository categoryRepository, final CustomerRepository customerRepository,
                    final VendorRepository vendorRepository) {
        this.categoryRepository = categoryRepository;
        this.customerRepository = customerRepository;
        this.vendorRepository = vendorRepository;
    }

    @Override
    public void run(final String... args) throws Exception {

        Category fruits = new Category();
        fruits.setName("Fruits");

        Category dried = new Category();
        dried.setName("dried");

        Category fresh = new Category();
        fresh.setName("Fresh");

        Category exotic = new Category();
        exotic.setName("Exotic");

        Category nuts = new Category();
        nuts.setName("Nuts");

        categoryRepository.save(fruits);
        categoryRepository.save(nuts);
        categoryRepository.save(fresh);
        categoryRepository.save(exotic);
        categoryRepository.save(dried);

        System.out.println("Data Loaded with size of " + categoryRepository.count());

        Customer customer1 = new Customer();
        customer1.setLastname("Floppy");
        customer1.setFirstname("Fluffy");

        Customer billy = new Customer();
        billy.setFirstname("Billy");
        billy.setLastname("Goat");

        Customer filly = new Customer();
        filly.setLastname("Horse");
        filly.setFirstname("Filly");

        customerRepository.save(customer1);
        customerRepository.save(billy);
        customerRepository.save(filly);

        Vendor v1 = new Vendor();
        v1.setId(1L);
        v1.setName("Home Depot");

        Vendor v2 = new Vendor();
        v2.setId(2L);
        v2.setName("Krispy Kreme");

        Vendor v3 = new Vendor();
        v3.setId(3L);
        v3.setName("Greer's Food Stores");

        vendorRepository.saveAll(Arrays.asList(v1, v2, v3));
    }
}
