package ex1.controller;

import ex1.model.Product;
import ex1.service.ProductService;

import java.util.List;

public class ProductController {
    ProductService productService = new ProductService();

    public List<Product> getAll(){
        return productService.getAll();
    }
    public int getSize(){
        return productService.getSize();
    }
    public void save(Product product){
        productService.save(product);
    }
    public void delete(Integer id){
        productService.delete(id);
    }
    public Product findById(Integer id) {
        return productService.findById(id);
    }
}
