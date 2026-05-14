package ed.lab.ed1labo04.service;

import ed.lab.ed1labo04.entity.Product;
import ed.lab.ed1labo04.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;


import java.util.List;

@Service
public class ProductService {

    private final ProductRepository repo;

    public ProductService(ProductRepository repo) {
        this.repo = repo;
    }

    // ✅ Crear producto
    public Product create(Product product) {

        if (product.getPrice() <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid price");
        }

        product.setQuantity(0);

        return repo.save(product);
    }

    // ✅ Obtener todos
    public List<Product> getAll() {
        return repo.findAll();
    }

    // ✅ Obtener por id
    public Product getById(Long id) {
        return repo.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found")
                );
    }


    // ✅ Actualizar producto
    public Product update(Long id, Double price, Integer quantity) {

        Product product = getById(id);

        if (price <= 0 || quantity < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid data");
        }

        product.setPrice(price);
        product.setQuantity(quantity);

        return repo.save(product);
    }
}

