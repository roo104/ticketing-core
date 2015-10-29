package dk.unwire.ticketing.core.domain.product.repository;

import dk.unwire.ticketing.core.TicketingCoreApplication;
import dk.unwire.ticketing.core.domain.product.model.InvalidBuyTime;
import dk.unwire.ticketing.core.domain.product.model.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = TicketingCoreApplication.class)
@WebAppConfiguration
@Transactional
public class ProductRepositoryIT {

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void getProduct() {
        // given a product already in DB

        // when finding a product by id
        Product savedProduct = this.productRepository.findOne(1L);

        // then all attributes should be loaded
        validateProduct(savedProduct);

    }

    @Test
    public void findActiveProductsByApplication() {
        // given a product already in DB

        // when finding aLL product by applicationId
        Collection<Product> savedProducts = this.productRepository.findActiveProductsByApplication(1);

        // then a collection of products should be returned with all attributes loaded
        assertNotNull(savedProducts);
        Product savedProduct = savedProducts.stream().skip(1).findFirst().get();
        validateProduct(savedProduct);
        assertNotNull(savedProduct.getVoucherPrice());
        assertNotNull(savedProduct.getStringProperty("name2"));


    }

    private void validateProduct(Product savedProduct) {
        assertNotNull(savedProduct);
        assertNotNull(savedProduct.getProductValidity());
        Collection<InvalidBuyTime> invalidBuyTimes = savedProduct.getInvalidBuyTimes();
        assertNotNull(invalidBuyTimes);
        InvalidBuyTime invalidBuyTime = invalidBuyTimes.stream().findFirst().get();
        assertNotNull(invalidBuyTime);
    }
}