_E-Commerce Simulation__


_Project Overview_

This project simulates a basic e-commerce site where users can view products, add them to their shopping basket, make payments, and choose a shipping method.

-View products and add them to the shopping basket
-View and remove items from the basket
-Payment options (PayPal, Stripe, Payoneer)
-Shipping options (UPS, FedEx, DHL)
-Stock management


_How to Run_

Place the code files in a folder
Compile and run the program using the following commands in the terminal

To compile:  javac Main.java

To run:   java Main

_OOP Concepts_

-Encapsulation
Classes hide their internal details and provide controlled access via methods. For example, the stock quantity in the Product class is updated using the increaseStock() method.

-Inheritance
Abstract classes (Payment, Shipment) are inherited by subclasses (e.g., Paypal, UPS), which inherit their properties and behaviors.

-Polymorphism
The same method is implemented differently in different classes. For example, the processPayment() method works differently for each payment method.