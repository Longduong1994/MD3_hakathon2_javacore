package ex1.controller;

import ex1.model.CartItem;
import ex1.model.Product;
import ex1.service.CartService;


import java.util.List;

public class CartItemController {
    CartService cartService = new CartService();

    public List<CartItem> getAll(){
        return cartService.getAllCartItems();
    }

    public int getSize(){
        return cartService.getSize();
    }
    public void addCartItem(Product product, int quantity){
        cartService.addToCart(product,quantity);
    }
    public void editCartItem(int id, int quantity){
        cartService.editCartItem( id, quantity);
    }

    public void clearCart() {
      cartService.clearCart();
    }

    public void removeCartItem(int id){
        cartService.removeCartItem(id);
    }
    public void displayData(){
        cartService.showCartItems();
    }

}
