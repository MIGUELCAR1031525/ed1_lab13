package ed.lab.ed1labo04.repository;

import ed.lab.ed1labo04.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
