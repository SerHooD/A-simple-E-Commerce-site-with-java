### _E-Commerce Simulation_

**_Project Overview_**  
This project simulates a basic e-commerce site where users can view products, add them to their shopping basket, make payments, and choose a shipping method.

- View products and add them to the shopping basket  
- View and remove items from the basket  
- Payment options: PayPal, Stripe, Payoneer  
- Shipping options: UPS, FedEx, DHL  
- Stock management  

**_How to Run_**  
1. Place the code files in a folder.  
2. Compile and run the program using the following commands in the terminal:  

   - To compile: `javac Main.java`  
   - To run: `java Main`  

**_OOP Concepts_**  

- **Encapsulation**:  
  Classes hide internal details and provide controlled access via methods. For example, the `increaseStock()` method in the `Product` class updates the stock quantity.  

- **Inheritance**:  
  Abstract classes (e.g., `Payment`, `Shipment`) are inherited by subclasses (`Paypal`, `UPS`), sharing properties and behaviors.  

- **Polymorphism**:  
  Methods are implemented differently across classes. For instance, the `processPayment()` method behaves uniquely for each payment method.
