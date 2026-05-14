package ed.lab.ed1labo04.model;

import ed.lab.ed1labo04.entity.CartItem;
import java.util.List;

public class CartRequest {

    private List<CartItem> cartItems;

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }
}
