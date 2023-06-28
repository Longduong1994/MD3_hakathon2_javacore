package ex1.service;


import ex1.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductService implements IService<Product,Integer> {
    static List<Product> products;

    public ProductService(){
        products = new ArrayList<>();
    }
    public int getSize(){
        return products.size();
    }

    @Override
    public List<Product> getAll() {
        return products;
    }

    @Override
    public void save(Product product) {
        if (findById(product.getProductId()) == null) {
            products.add(product);
        }else {
            products.set(products.indexOf(findById(product.getProductId())),product);
        }

    }

    @Override
    public Product findById(Integer id) {
        for (Product product : products) {
            if (product.getProductId() == id) {
                return product;
            }
        }
        return null;
    }


    @Override
    public void delete(Integer id) {
        if(findById(id)==null) {
            System.out.println("Id không tồn tại!!!");
            return;
        }
        products.remove(findById(id));
    }
}
