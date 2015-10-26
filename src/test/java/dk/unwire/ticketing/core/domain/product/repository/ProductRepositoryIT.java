package dk.unwire.ticketing.core.domain.product.repository;

import dk.unwire.ticketing.core.TicketingCoreApplication;
import dk.unwire.ticketing.core.domain.product.model.Product;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = TicketingCoreApplication.class)
@WebAppConfiguration
@Transactional
public class ProductRepositoryIT {

    @Autowired
    private ProductRepository productRepository;

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void insertProduct() {
        Product product = new Product();
        product.addProductProperty("prop1", "val1");

        this.productRepository.save(product);

        Product savedProduct = this.productRepository.findOne(product.getId());
        assertNotNull(savedProduct);
        assertTrue(savedProduct.getProductProperties().size() >= 1);
    }
}