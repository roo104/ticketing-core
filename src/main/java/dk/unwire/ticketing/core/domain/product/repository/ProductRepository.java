package dk.unwire.ticketing.core.domain.product.repository;

import dk.unwire.ticketing.core.domain.product.model.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long>  {
}
