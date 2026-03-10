package com.ecommerce.Service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ecommerce.Entity.Cart;
import com.ecommerce.Entity.Cart_item;
import com.ecommerce.Entity.Product;
import com.ecommerce.Repository.CartItemRepository;
import com.ecommerce.Repository.CartRepository;
import com.ecommerce.Repository.ProductRepository;

@Service
public class CartItemService {

    private final CartItemRepository cir;
    private final CartRepository cr;
    private final ProductRepository pr;

    public CartItemService(CartItemRepository cir, CartRepository cr, ProductRepository pr){
        this.cir = cir;
        this.cr = cr;
        this.pr = pr;
    }

    public Cart_item addItem(Cart_item item) {

        Cart cart = cr.findById(item.getCart().getId()).orElseThrow();
        Product product = pr.findById(item.getProduct().getId()).orElseThrow();

        item.setCart(cart);
        item.setProduct(product);

        item.setFinal_price(product.getPrice()
                .multiply(new BigDecimal(item.getQuantity())));

        return cir.save(item);
    }

    // Get all cart items
    public List<Cart_item> getAllItems() {
        return cir.findAll();
    }

    // Get cart item by id
    public Cart_item getItem(Integer id) {
        return cir.findById(id).orElse(null);
    }

    // Delete cart item
    public void deleteItem(Integer id) {
        cir.deleteById(id);
    }
    
    public List<Cart_item> getByCartId(int cartId) {
        return cir.findByCart_Id(cartId);
    }
}