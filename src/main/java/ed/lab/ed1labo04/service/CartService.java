package ed.lab.ed1labo04.service;

import ed.lab.ed1labo04.entity.*;
import ed.lab.ed1labo04.repository.CartRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {

    private final CartRepository cartRepo;
    private final ProductService productService;

    public CartService(CartRepository cartRepo, ProductService productService) {
        this.cartRepo = cartRepo;
        this.productService = productService;
    }

    // ✅ CREAR CARRITO
    public Cart create(List<CartItem> requestItems) {

        List<CartItem> items = new ArrayList<>();
        double total = 0;

        for (CartItem r : requestItems) {

            // ✅ validar cantidad
            if (r.getQuantity() <= 0) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid quantity");
            }

            // ✅ obtener producto
            Product p = productService.getById(r.getProductId());

            // ✅ validar inventario
            if (p.getQuantity() < r.getQuantity()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Not enough stock");
            }

            // ✅ restar inventario
            p.setQuantity(p.getQuantity() - r.getQuantity());

            // ✅ crear item final
            CartItem item = new CartItem();
            item.setProductId(p.getId());
            item.setName(p.getName());
            item.setPrice(p.getPrice());
            item.setQuantity(r.getQuantity());

            // ✅ calcular total
            total += p.getPrice() * r.getQuantity();

            items.add(item);
        }

        Cart cart = new Cart();
        cart.setCartItems(items);
        cart.setTotalPrice(total);

        return cartRepo.save(cart);
    }

    // ✅ OBTENER CARRITO
    public Cart getById(Long id) {
        return cartRepo.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Cart not found")
                );
    }
}


