// package garment.pkg3;
//importing list and array list

import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Date;

public class garment{

    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);

        // fabric
        System.out.print("Enter Fabric ID: ");
        String fabricId = scanner.nextLine();
        System.out.print("Enter Fabric Type: ");
        String fabricType = scanner.nextLine();
        System.out.print("Enter Fabric Color: ");
        String fabricColor = scanner.nextLine();
        System.out.print("Enter Fabric Price per Meter: ");
        double pricePerMeter = scanner.nextDouble();
        System.out.println("--------------------------");
        scanner.nextLine();
        // fabric object
        Fabric fabric = new Fabric(fabricId, fabricType, fabricColor, pricePerMeter);

        //garment
        System.out.print("Enter Garment ID: ");
        String garmentId = scanner.nextLine();
        System.out.print("Enter Garment Name: ");
        String garmentName = scanner.nextLine();
        System.out.print("Enter Garment Description: ");
        String garmentDescription = scanner.nextLine();
        System.out.print("Enter Garment Size: ");
        String garmentSize = scanner.nextLine();
        System.out.print("Enter Garment Color: ");
        String garmentColor = scanner.nextLine();
        System.out.print("Enter Garment Price: ");
        double garmentPrice = scanner.nextDouble();
        System.out.print("Enter Garment Stock Quantity: ");
        int stockQuantity = scanner.nextInt();
        System.out.println("--------------------------");
        scanner.nextLine();

        //garment object
        Garment garment = new Garment(garmentId, garmentName, garmentDescription, garmentSize, garmentColor, garmentPrice, stockQuantity, fabric);

        // customer
        System.out.print("Enter Customer ID: ");
        String customerId = scanner.nextLine();
        System.out.print("Enter Customer Name: ");
        String customerName = scanner.nextLine();
        System.out.print("Enter Customer Email: ");
        String customerEmail = scanner.nextLine();
        System.out.print("Enter Customer Phone: ");
        String customerPhone = scanner.nextLine();
        System.out.println("--------------------------");
        System.out.println("--------------------------");

        // customer object
        Customer customer = new Customer(customerId, customerName, customerEmail, customerPhone);

        
        System.out.print("Enter Order ID: ");
        String orderId = scanner.nextLine();
        Date orderDate = new Date();

        Order order = new Order(orderId, orderDate);
        order.addGarment(garment);

        customer.placeOrder(order);

        System.out.println("--------------------------");
        order.printOrderDetails();
        
        scanner.close();
    }

}

class Garment {

    public String id;
    public String name;
    public String description;
    public String size;
    public String color;
    public double price;
    public int stockQuantity;
    public Fabric fabric;

    public Garment(String id, String name, String description, String size, String color, double price, int stockQuantity, Fabric fabric) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.size = size;
        this.color = color;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.fabric = fabric;
    }

    void updateStock(int quantity) {
        this.stockQuantity = quantity;
    }

    double calculateDiscountPrice(double discountPercentage) {
        double discount = price * (discountPercentage / 100);
        return price - discount;
    }
}

class Fabric {

    public String id;
    public String type;
    public String color;
    public double pricePerMeter;

    public Fabric(String id, String type, String color, double pricePerMeter) {
        this.id = id;
        this.type = type;
        this.color = color;
        this.pricePerMeter = pricePerMeter;
    }

    double calculateCost(double meters) {
        double newPrice = pricePerMeter * meters;
        return newPrice;
    }
}

class Supplier {

    public String id;
    public String name;
    public String contactInfo;
    //List
    public List<Fabric> suppliedFabric;

    public Supplier(String id, String name, String contactInfo) {
        this.id = id;
        this.name = name;
        this.contactInfo = contactInfo;
        this.suppliedFabric = new ArrayList<>();
    }
    
    void addFabric(Fabric fabric) {
        suppliedFabric.add(fabric);
    }

    List<Fabric> getSuppliedFabrics() {
        return suppliedFabric;
    }
}

class Order {

    public String orderId;
    public Date orderDate;
    public List<Garment> garments;
    private double totalAmount;

    public Order(String orderId, Date orderDate) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.garments = new ArrayList<>();
    }

    void addGarment(Garment garment) {
        garments.add(garment);
    }

    double calculateTotalAmount() {
        for (Garment g : garments) {
            totalAmount = totalAmount + g.price;
        }
        return totalAmount;
    }

    void printOrderDetails() {
        System.out.println("--------------------------");
        System.out.println("Order Details");
        System.out.println("--------------------------");
        for (Garment g : garments) {
            System.out.println("Name: " + g.name);
            System.out.println("Price: " + g.price);
            System.out.println("Description: " + g.description);
            System.out.println("--------------------------");
        }
    }
}

class Customer {

    public String customerId;
    public String name;
    public String email;
    public String phone;
    public List<Order> orders;

    public Customer(String customerId, String name, String email, String phone) {
        this.customerId = customerId;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.orders = new ArrayList<>();
    }

    void placeOrder(Order order) {
        order.printOrderDetails();
        System.out.println("Order Placed");
    }

   public List<Order> viewOrders() {
       return orders;
   }
}

class Inventory {

    List<Garment> garments;

    public Inventory() {
        this.garments = new ArrayList<>();
    }

    void addGarment(Garment garment) {
        garments.add(garment);
    }

    
    void removeGarment(String id) {
        for (int i = 0; i < garments.size(); i++) {
            Garment garment = garments.get(i);
            if (garment.id == id) {
                garments.remove(i);
                break;
            }
        }
    }

    Garment findGarment(String id) {
        for (Garment g : garments) {
            if(g.id == id)
                return g;
        }
        return null;
    }
}

