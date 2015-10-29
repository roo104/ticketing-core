package dk.unwire.ticketing.core.domain.product.repository;

import dk.unwire.ticketing.core.domain.product.model.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long>  {

    @Query("from Product p " +
            "where p.applicationId = ?1 " +
            "and p.activated = true " +
            "and p.deleted = false")
    Collection<Product> findActiveProductsByApplication(int applicationId);
}
