import java.util.*;

class Product {
    String productId;
    String productName;
    double price;
    String category;
    int stockQuantity;

    static int totalProducts = 0;
    static String[] categories = {"Electronics", "Clothing", "Books", "Home", "Toys"};

    Product(String productId, String productName, double price, String category, int stockQuantity) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.category = category;
        this.stockQuantity = stockQuantity;
        totalProducts++;
    }

    static Product findProductById(Product[] products, String productId) {
        for (Product p : products) {
            if (p != null && p.productId.equals(productId)) return p;
        }
        return null;
    }

    static List<Product> getProductsByCategory(Product[] products, String category) {
        List<Product> list = new ArrayList<>();
        for (Product p : products) {
            if (p != null && p.category.equalsIgnoreCase(category)) list.add(p);
        }
        return list;
    }
}

class ShoppingCart {
    String cartId;
    String customerName;
    Product[] products;
    int[] quantities;
    double cartTotal;

    ShoppingCart(String cartId, String customerName, int size) {
        this.cartId = cartId;
        this.customerName = customerName;
        this.products = new Product[size];
        this.quantities = new int[size];
        this.cartTotal = 0;
    }

    void addProduct(Product product, int quantity) {
        for (int i = 0; i < products.length; i++) {
            if (products[i] == null) {
                if (product.stockQuantity >= quantity) {
                    products[i] = product;
                    quantities[i] = quantity;
                    product.stockQuantity -= quantity;
                    calculateTotal();
                }
                return;
            }
        }
    }

    void removeProduct(String productId) {
        for (int i = 0; i < products.length; i++) {
            if (products[i] != null && products[i].productId.equals(productId)) {
                products[i].stockQuantity += quantities[i];
                products[i] = null;
                quantities[i] = 0;
                calculateTotal();
                return;
            }
        }
    }

    void calculateTotal() {
        cartTotal = 0;
        for (int i = 0; i < products.length; i++) {
            if (products[i] != null) {
                cartTotal += products[i].price * quantities[i];
            }
        }
    }

    void displayCart() {
        System.out.println("Cart ID: " + cartId + " Customer: " + customerName);
        for (int i = 0; i < products.length; i++) {
            if (products[i] != null) {
                System.out.println(products[i].productName + " x " + quantities[i] + " = " + (products[i].price * quantities[i]));
            }
        }
        System.out.println("Total: " + cartTotal);
    }

    void checkout() {
        System.out.println("Checkout for " + customerName + " Total: " + cartTotal);
        for (int i = 0; i < products.length; i++) {
            products[i] = null;
            quantities[i] = 0;
        }
        cartTotal = 0;
    }
}

public class Main_Q2 {
    public static void main(String[] args) {
        Product[] storeProducts = {
            new Product("P1", "Laptop", 60000, "Electronics", 10),
            new Product("P2", "Phone", 30000, "Electronics", 15),
            new Product("P3", "T-Shirt", 500, "Clothing", 50),
            new Product("P4", "Jeans", 1200, "Clothing", 40),
            new Product("P5", "Novel", 300, "Books", 30),
            new Product("P6", "Cookbook", 800, "Books", 20),
            new Product("P7", "Sofa", 25000, "Home", 5),
            new Product("P8", "Mixer", 4000, "Home", 10),
            new Product("P9", "Toy Car", 700, "Toys", 25),
            new Product("P10", "Doll", 900, "Toys", 30)
        };

        Scanner sc = new Scanner(System.in);
        ShoppingCart cart = new ShoppingCart("C1", "Alice", 20);

        while (true) {
            System.out.println("1. Browse All Products");
            System.out.println("2. Browse by Category");
            System.out.println("3. Add to Cart");
            System.out.println("4. Remove from Cart");
            System.out.println("5. View Cart");
            System.out.println("6. Checkout");
            System.out.println("7. Exit");
            int choice = sc.nextInt();
            sc.nextLine();

            if (choice == 1) {
                for (Product p : storeProducts) {
                    System.out.println(p.productId + " " + p.productName + " " + p.price + " Stock: " + p.stockQuantity);
                }
            } else if (choice == 2) {
                System.out.println("Enter Category:");
                String cat = sc.nextLine();
                List<Product> list = Product.getProductsByCategory(storeProducts, cat);
                for (Product p : list) {
                    System.out.println(p.productId + " " + p.productName + " " + p.price + " Stock: " + p.stockQuantity);
                }
            } else if (choice == 3) {
                System.out.println("Enter Product ID:");
                String id = sc.nextLine();
                System.out.println("Enter Quantity:");
                int qty = sc.nextInt();
                Product p = Product.findProductById(storeProducts, id);
                if (p != null) {
                    cart.addProduct(p, qty);
                } else {
                    System.out.println("Product not found");
                }
            } else if (choice == 4) {
                System.out.println("Enter Product ID to remove:");
                String id = sc.nextLine();
                cart.removeProduct(id);
            } else if (choice == 5) {
                cart.displayCart();
            } else if (choice == 6) {
                cart.checkout();
            } else if (choice == 7) {
                break;
            }
        }
    }
}


