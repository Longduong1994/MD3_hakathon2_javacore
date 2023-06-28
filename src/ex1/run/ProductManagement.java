package ex1.run;

import ex1.config.Config;
import ex1.controller.CartItemController;
import ex1.controller.CatalogController;
import ex1.controller.ProductController;

import ex1.model.CartItem;
import ex1.model.Catalog;
import ex1.model.Product;
import ex1.service.ProductComparator;


import java.util.Collections;


public class ProductManagement {
    private static final CatalogController catalogController = new CatalogController();
    private static final ProductController productController = new ProductController();
    private static final CartItemController cartItemController = new CartItemController();


    public static void main(String[] args) {
        int choice;
        while (true) {
            System.out.println("**************************BASIC-MENU**************************\n" +
                    "1. Quản lý danh mục\n" +
                    "2. Quản lý sản phẩm\n" +
                    "3. Dành cho người dùng (***)\n" +
                    "4. Thoát\n"
            );
            System.out.println("Nhập lựa chọn của bạn:");
            choice = Config.getInteger();
            switch (choice) {
                case 1:
                    catalogManagement();
                    break;
                case 2:
                    productManagement();
                    break;
                case 3:
                    userManagement();
                    break;
                case 4:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Vui lòng nhập lại từ 1-4");
            }
        }
    }

    public static void catalogManagement() {
        int choice;
        while (true) {
            System.out.println("********************CATALOG-MANAGEMENT********************\n" +
                    "1. Nhập số danh mục thêm mới và nhập thông tin cho từng danh mục \n" +
                    "2. Hiển thị thông tin tất cả các danh mục \n" +
                    "3. Sửa tên danh mục theo mã danh mục \n" +
                    "4. Xóa danh muc theo mã danh mục (lưu ý ko xóa khi có sản phẩm) \n" +
                    "5. Quay lại\n"
            );
            System.out.println("Mời nhập lựa chọn!!!");
            choice = Config.getInteger();
            switch (choice) {
                case 1:
                    addCatalog();
                    break;
                case 2:
                    getAllCatalog();
                    break;
                case 3:
                    updateCatalog();
                    break;
                case 4:
                    deleteCatalog();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Xin mời nhập lại");
            }
        }
    }

    public static void productManagement() {
        int choice;
        while (true) {
            System.out.println("********************PRODUCT-MANAGEMENT********************\n" +
                    "1. Nhập số sản sản phẩm và nhập thông tin sản phẩm \n" +
                    "2. Hiển thị thông tin các sản phẩm \n" +
                    "3. Sắp xếp sản phẩm theo giá giảm dần \n" +
                    "4. Xóa sản phẩm theo mã \n" +
                    "5. Tìm kiếm sách theo tên sách \n" +
                    "6. Thay đổi thông tin của sách theo mã sách \n" +
                    "7. Quay lại");
            System.out.println("Mời nhập lựa chọn!!!");
            choice = Config.getInteger();
            switch (choice) {
                case 1:
                    addProduct();
                    break;
                case 2:
                    getAllProduct();
                    break;
                case 3:
                    sort();
                    break;
                case 4:
                    deleteProduct();
                    break;
                case 5:
                    System.out.println("Nhập tên Product cần tìm:");
                    String name = Config.getString();
                    searchByName(name);
                    break;
                case 6:
                    updateProduct();
                    break;
                case 7:
                    return;
                default:
                    System.out.println("Xin mời nhập lại");
            }
        }
    }

    public static void userManagement() {
        int choice;
        while (true) {
            System.out.println("**************************MENU-USER**************************\n" +
                    "1. Xem danh sách sản phẩm\n" +
                    "2. Thêm vào giỏ hàng\n" +
                    "3. Xem tất cả sản phẩm giỏ hàng\n" +
                    "4. Thay đổi số lượng sản phẩm trong giỏ hàng\n" +
                    "5. Xóa 1 sản phẩm trong giỏ hàng\n" +
                    "6. Xóa toàn bộ sản phẩm trong giỏ hàng\n" +
                    "7. Quay lại");
            System.out.println("Mời bạn nhập lựa chọn:");
            choice = Config.getInteger();
            switch (choice) {
                case 1:
                    getAllProduct();
                    break;
                case 2:
                    addCart();
                    break;
                case 3:
                    getAllCartItems();
                    break;
                case 4:
                    editCart();
                    break;
                case 5:
                    removeCart();
                    break;
                case 6:
                    clearAll();
                    break;
                case 7:
                    return;
                default:
                    System.out.println("Vui lòng nhập lại!!");
            }
        }

    }

    public static void addCatalog() {
        System.out.println("Nhập số lượng cần thêm:");
        int n = Config.getInteger();
        for (int i = 0; i < n; i++) {
            System.out.println("Nhập thông tin Catalog thứ: " + (i + 1));
            Catalog newCatalog = new Catalog();
            newCatalog.inputData();
            catalogController.save(newCatalog);
        }
    }

    public static void getAllCatalog() {
        for (Catalog catalog : catalogController.getAll()) {
            catalog.displayData();
        }
    }

    public static void updateCatalog() {
        System.out.println("Nhập ID cần sửa:");
        int id = Config.getInteger();
        Catalog updateCatalog = catalogController.findById(id);
        if (updateCatalog == null) {
            System.out.println("Không tìm thấy Catalog");
            return;
        }
        System.out.println("Catalog cần sửa là: ");
        updateCatalog.displayData();
        updateCatalog.inputData();
        catalogController.save(updateCatalog);
    }

    public static void deleteCatalog() {
        System.out.println("Nhập ID cần xóa:");
        int id = Config.getInteger();
        boolean flag = false;
        Catalog deleteCatalog = catalogController.findById(id);
        for (Product product : productController.getAll()) {
            if (product.getCatalog().getCatalogName().equals(deleteCatalog.getCatalogName())) {
                System.out.println("Catalog đang được sử dụng, không thể xóa!! ");
                flag = true;
                break;
            }

        }
        if (flag == false) {
            catalogController.delete(id);
        }
    }

    public static void addProduct() {
        System.out.println("Nhập số lượng cần thêm:");
        int n = Config.getInteger();
        for (int i = 0; i < n; i++) {
            System.out.println("Nhập thông tin Product thứ: " + (i + 1));
            Product newProduct = new Product();
            newProduct.inputData(catalogController.getAll());
            productController.save(newProduct);
        }
    }

    public static void getAllProduct() {
        for (Product product : productController.getAll()) {
            product.displayData();

        }
    }

    public static void updateProduct() {
        System.out.println("Nhập ID cần sửa:");
        int id = Config.getInteger();
        Product editProduct = productController.findById(id);
        if (editProduct == null) {
            System.out.println("Không tìm thấy Product");
            return;
        }
        System.out.println("Catalog cần sửa là: ");
        editProduct.displayData();
        editProduct.inputData(catalogController.getAll());
        productController.save(editProduct);
    }

    public static void deleteProduct() {
        System.out.println("Nhập mã ID cần xóa:");
        int id = Config.getInteger();
        productController.delete(id);
    }

    public static void searchByName(String name) {
        boolean flag = false;
        for (Product product : productController.getAll()) {
            if (product.getProductName().toLowerCase().contains(name.toLowerCase())) {
                product.displayData();
                flag = true;
                break;
            }
        }
        if (!flag) {
            System.out.println("Không có Product này!!!");
        }

    }

    public static void sort() {
        Collections.sort(productController.getAll(), new ProductComparator());
    }

    public static void addCart() {
        System.out.println("Nhập mã sản phẩm cần thêm:");
        int id = Config.getInteger();
        System.out.println("Nhập số lượng:");
        int quantity = Config.getInteger();
        for (Product product : productController.getAll()) {
            if (product.getProductId() == (id)) {
                cartItemController.addCartItem(product, quantity);
                break;
            }
        }
    }

    public static void editCart() {
        System.out.println("Nhập ID cần sửa:");
        int id = Config.getInteger();
        System.out.println("Nhập số lượng thay đổi");
        int quantity = Config.getInteger();
        cartItemController.editCartItem(id, quantity);
    }

    public static void getAllCartItems() {
       cartItemController.displayData();
    }


    public static void removeCart() {
        System.out.println("Nhập ID cần xóa:");
        int id = Config.getInteger();
        cartItemController.removeCartItem(id);
    }

    public static void clearAll() {
        cartItemController.clearCart();
    }


}
