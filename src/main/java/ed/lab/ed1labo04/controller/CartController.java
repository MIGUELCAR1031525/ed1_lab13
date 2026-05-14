package ed.lab.ed1labo04.controller;

import ed.lab.ed1labo04.entity.Cart;
import ed.lab.ed1labo04.model.CartRequest;
import ed.lab.ed1labo04.service.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {

    private final CartService service;

    public CartController(CartService service) {
        this.service = service;
    }

    // ✅ POST /cart
    @PostMapping
    public ResponseEntity<Cart> create(@RequestBody CartRequest request) {
        return ResponseEntity.status(201).body(service.create(request.getCartItems()));
    }

    // ✅ GET /cart/{id}
    @GetMapping("/{id}")
    public Cart get(@PathVariable Long id) {
        return service.getById(id);
    }
}


