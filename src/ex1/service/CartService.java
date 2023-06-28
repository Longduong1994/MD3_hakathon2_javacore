package ex1.service;

import ex1.model.CartItem;
import ex1.model.Product;

import java.util.ArrayList;
import java.util.List;

public class CartService  {

    List<CartItem> cartItems;

    public CartService() {
      cartItems = new ArrayList<>();
    }


    public List<CartItem> getAllCartItems() {
        return cartItems;
    }

    public void CartItemService(){
        cartItems = new ArrayList<>();
    }
    public int getSize(){
        return cartItems.size();
    }
    public void addToCart(Product product, int quantity) {
        boolean flag = false;
        for (CartItem cartItem : cartItems) {
            if (cartItem.getProduct().getProductId() == (product.getProductId())) {
                int newQuantity = cartItem.getQuantity() + quantity;
                if (newQuantity <= product.getStock()) {
                    cartItem.setQuantity(newQuantity);
                    System.out.println("Sản phẩm đã có trong giỏ hàng. Số lượng đã được cập nhật.");
                } else {
                    System.out.println("Sản phẩm không đủ số lượng trong kho.");
                }
                flag = true;
                break;
            }
        }
        if (!flag) {
            if (quantity <= product.getStock()) {
                System.out.println("Thêm Product thành công");
                CartItem cartItem = new CartItem(
                        newCartItemId(),
                        product,
                        product.getProductPrice()*quantity,
                        quantity);
                cartItems.add(cartItem);
            }
        }
    }
    public void editCartItem(int cartItemId, int newQuantity) {
        for (CartItem cartItem : cartItems) {
            if (cartItem.getCartItemId() == cartItemId) {
                Product product = cartItem.getProduct();
                if (newQuantity <= product.getStock()) {
                    cartItem.setQuantity(newQuantity);
                    cartItem.setPrice(newQuantity* product.getProductPrice());
                    System.out.println("Số lượng sản phẩm trong giỏ hàng đã được cập nhật.");
                } else {
                    System.out.println("Sản phẩm không đủ số lượng trong kho.");
                }
                return;
            }
        }
        System.out.println("Sản phẩm không tồn tại trong giỏ hàng.");
    }
    public void removeCartItem(int cartItemId) {
        for (CartItem cartItem : cartItems) {
            if (cartItem.getCartItemId() == cartItemId) {
                cartItems.remove(cartItem);
                System.out.println("Sản phẩm đã được xóa khỏi giỏ hàng.");
                return;
            }
        }
        System.out.println("Sản phẩm không tồn tại trong giỏ hàng.");
    }
    public void clearCart() {
        cartItems.clear();
        System.out.println("Đã xóa toàn bộ giỏ hàng.");
    }


    private int newCartItemId() {
        return cartItems.size()+1;
    }
    public void showCartItems() {
        if (cartItems.isEmpty()) {
            System.out.println("Không có sản phẩm nào trong giỏ hàng.");
            return;
        }

        System.out.println("Danh sách sản phẩm trong giỏ hàng:");
        for (CartItem cartItem : cartItems) {
            System.out.println("ID: " + cartItem.getCartItemId());
            System.out.println("Tên sản phẩm: " + cartItem.getProduct().getProductName());
            System.out.println("Số lượng: " + cartItem.getQuantity());
            System.out.println("Giá: " + cartItem.getPrice());
            System.out.println("------------------------");
        }
    }

}
