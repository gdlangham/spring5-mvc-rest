package guru.springfamework.init;

import guru.springfamework.domain.Category;
import guru.springfamework.domain.Customer;
import guru.springfamework.repositories.CategoryRepository;
import guru.springfamework.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DBLoader implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    private final CustomerRepository customerRepository;

    public DBLoader(final CategoryRepository categoryRepository, final CustomerRepository customerRepository) {
        this.categoryRepository = categoryRepository;
        this.customerRepository = customerRepository;
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
    }
}
