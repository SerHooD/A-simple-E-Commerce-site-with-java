import java.util.ArrayList;
import java.util.Scanner;

class Product {
    int id;
    String name;
    int stockQuantity;
    int price;

    public Product(int id, String name, int price, int stockQuantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }

    public void increaseStock(int quantity) {
        stockQuantity += quantity;
    }
}

class ShoppingBasket {
    ArrayList<Product> basketItems = new ArrayList<>();
    ArrayList<Integer> quantities = new ArrayList<>();

    void addProduct(Product product, int quantity) {
        System.out.println("---------------------------------------");
        if (product.stockQuantity >= quantity) {
            basketItems.add(product);
            quantities.add(quantity);
            product.stockQuantity -= quantity;
            System.out.println(quantity + "x " + product.name + " added to the basket.");
        } else {
            System.out.println("Insufficient stock!");
        }
    }

    void showBasket() {
        System.out.println("---------------------------------------");
        if (basketItems.isEmpty()) {
            System.out.println("Your basket is empty.");


        } else {
            System.out.println("Shopping Basket:");
            for (int i = 0; i < basketItems.size(); i++) {
                Product product = basketItems.get(i);
                int quantity = quantities.get(i);
                System.out.println((i + 1) + ". " + product.name + " - Quantity: " + quantity + " - Total Price: $" + product.price*quantity);
            }
        }
    }

    void removeProduct(int index) {
        if (index < 1 || index > basketItems.size()) {
            System.out.println("Invalid selection.");
            return;
        }
        Product product = basketItems.get(index - 1);
        int quantity = quantities.get(index - 1);
        product.increaseStock(quantity);
        basketItems.remove(index - 1);
        quantities.remove(index - 1);
        System.out.println("Product removed from the basket.");
    }
}

abstract class Payment {
    public int calculateTotal(ShoppingBasket basket) {
        int total = 0;
        for (int i = 0; i < basket.basketItems.size(); i++) {
            Product product = basket.basketItems.get(i);
            int quantity = basket.quantities.get(i);
            total += product.price * quantity;
        }
        return total;
    }

    abstract void processPayment(int amount);
}

class Paypal extends Payment {
    @Override
    void processPayment(int amount) {
        System.out.println("Payment of $" + amount + " completed successfully via PayPal.");
    }
}

class Stripe extends Payment {
    @Override
    void processPayment(int amount) {
        System.out.println("Payment of $" + amount + " completed successfully via Stripe.");
    }
}

class Payoneer extends Payment {
    @Override
    void processPayment(int amount) {
        System.out.println("Payment of $" + amount + " completed successfully via Payoneer.");
    }
}

abstract class Shipment {
    String address;

    public Shipment(String address) {
        this.address = address;
    }

    public abstract void order();
}

class UPS extends Shipment {
    public UPS(String address) {
        super(address);
    }

    @Override
    public void order() {
        System.out.println("Shipped via UPS to: " + address);
    }
}

class FedEx extends Shipment {
    public FedEx(String address) {
        super(address);
    }

    @Override
    public void order() {
        System.out.println("Shipped via FedEx to: " + address);
    }
}

class DHL extends Shipment {
    public DHL(String address) {
        super(address);
    }

    @Override
    public void order() {
        System.out.println("Shipped via DHL to: " + address);
    }
}

public class Main {
    public static void main(String[] args) {
        Product product1 = new Product(1, "Laptop", 500, 10);
        Product product2 = new Product(2, "Phone", 600, 20);
        Product product3 = new Product(3, "Tablet", 700, 15);

        ShoppingBasket basket = new ShoppingBasket();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Store!");

        while (true) {
            System.out.println("---------------------------------------");
            System.out.println("\nMenu:");
            System.out.println("1. View Products");
            System.out.println("2. Add Product to Basket");
            System.out.println("3. View Basket");
            System.out.println("4. Remove Product from Basket");
            System.out.println("5. Payment and Shipment");
            System.out.println("6. Quit");
            System.out.print("Your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> {
                    System.out.println("\nProducts:");
                    System.out.println("1. " + product1.name + " - Price: $" + product1.price + " - Stock: " + product1.stockQuantity);
                    System.out.println("2. " + product2.name + " - Price: $" + product2.price + " - Stock: " + product2.stockQuantity);
                    System.out.println("3. " + product3.name + " - Price: $" + product3.price + " - Stock: " + product3.stockQuantity);
                }
                case 2 -> {
                    System.out.print("\nEnter the product number to add (1-3): ");
                    int productChoice = scanner.nextInt();
                    System.out.print("Enter the quantity: ");
                    int quantity = scanner.nextInt();

                    switch (productChoice) {
                        case 1 -> basket.addProduct(product1, quantity);
                        case 2 -> basket.addProduct(product2, quantity);
                        case 3 -> basket.addProduct(product3, quantity);
                        default -> System.out.println("Invalid product number.");
                    }
                }
                case 3 -> basket.showBasket();
                case 4 -> {
                    if(basket.basketItems.isEmpty()) {
                        System.out.println("---------------------------------------");
                        System.out.println("Your basket is empty. No products to remove.");
                        continue;
                    }
                    basket.showBasket();
                    System.out.print("\nEnter the product number to remove: ");
                    int productChoice = scanner.nextInt();
                    basket.removeProduct(productChoice);
                }
                case 5 -> {
                    if (basket.basketItems.isEmpty()) {
                        System.out.println("Your basket is empty. Add items before payment.");
                        continue;
                    }

                    int totalAmount = new Paypal().calculateTotal(basket);
                    System.out.println("\nTotal Payment: $" + totalAmount);
                    System.out.println("Choose payment method: 1-PayPal 2-Stripe 3-Payoneer");
                    int paymentMethod = scanner.nextInt();
                    scanner.nextLine();
                    Payment payment;
                    switch (paymentMethod) {
                        case 1 -> payment = new Paypal();
                        case 2 -> payment = new Stripe();
                        case 3 -> payment = new Payoneer();
                        default -> {
                            System.out.println("Invalid payment method.");
                            continue;
                        }
                    }
                    payment.processPayment(totalAmount);


                    System.out.print("\nEnter your shipping address: ");
                    String address = scanner.nextLine();
                    System.out.println("Choose shipping method: 1-UPS 2-FedEx 3-DHL");
                    int shippingChoice = scanner.nextInt();

                    Shipment shipment;
                    switch (shippingChoice) {
                        case 1 -> shipment = new UPS(address);
                        case 2 -> shipment = new FedEx(address);
                        case 3 -> shipment = new DHL(address);
                        default -> {
                            System.out.println("Invalid shipping method.");
                            continue;
                        }
                    }
                    shipment.order();
                }
                case 6 -> {
                    System.out.println("Exiting the store.");
                    scanner.close();
                    return;
                }
                default -> System.out.println("Invalid choice. Please select between 1 and 6.");
            }
        }
    }
}
