package ex1.service;

import ex1.model.Product;

import java.util.Comparator;

public class ProductComparator implements Comparator<Product> {
    @Override
    public int compare(Product o1, Product o2) {
        return Double.compare(o2.getProductPrice(), o1.getProductPrice());
    }
}
