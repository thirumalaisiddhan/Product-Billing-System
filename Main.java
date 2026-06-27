package Project;
import java.util.*;
import java.util.Scanner;
class Product {
    String name;
    int quantity;
    double price;
    Product(String name, int quantity, double price) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }
}

class BillItem {
    String name;
    int quantity;
    double price;
    double total;

    BillItem(String name, int quantity, double price) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.total = quantity * price;
    }
}

public class Main {

    ArrayList<Product> products = new ArrayList<>();
    ArrayList<BillItem> bill = new ArrayList<>();

    void addProduct(String name, int quantity, double price) {
        products.add(new Product(name, quantity, price));
        System.out.println("Product Added Successfully.");
    }

    void viewProducts() {

        if (products.isEmpty()) {
            System.out.println("No Products Available.");
            return;
        }

        System.out.println("\n------ Available Products ------");
        System.out.println("No\tProduct\tQty\tPrice");

        for (int i = 0; i < products.size(); i++) {
            Product p = products.get(i);
            System.out.println((i + 1) + "\t" + p.name + "\t" + p.quantity + "\t" + p.price);
        }
    }

    void buyProduct(int index, int qty) {

        if (index < 0 || index >= products.size()) {
            System.out.println("Invalid Product Number.");
            return;
        }

        Product p = products.get(index);

        if (qty > p.quantity) {
            System.out.println("Insufficient Stock.");
            return;
        }

        p.quantity = p.quantity - qty;

        bill.add(new BillItem(p.name, qty, p.price));

        System.out.println("Product Added To Bill.");
    }

    void displayBill() {

        if (bill.isEmpty()) {
            System.out.println("No Items Purchased.");
            return;
        }

        double grandTotal = 0;

        System.out.println("\n========== BILL ==========");
        System.out.println("Product\tQty\tPrice\tTotal");

        for (BillItem b : bill) {

            System.out.println(b.name + "\t" +
                    b.quantity + "\t" +
                    b.price + "\t" +
                    b.total);

            grandTotal += b.total;
        }

        System.out.println("--------------------------");
        System.out.println("Grand Total : " + grandTotal);
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Main obj = new Main();

        while (true) {

            System.out.println("\n===== PRODUCT BILLING SYSTEM =====");
            System.out.println("1. Add Product");
            System.out.println("2. View Products");
            System.out.println("3. Buy Product");
            System.out.println("4. Display Bill");
            System.out.println("5. Exit");
            System.out.print("Enter Choice : ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:

                    System.out.print("Enter Product Name : ");
                    String name = sc.nextLine();

                    System.out.print("Enter Quantity : ");
                    int quantity = sc.nextInt();

                    System.out.print("Enter Price : ");
                    double price = sc.nextDouble();

                    obj.addProduct(name, quantity, price);
                    break;

                case 2:

                    obj.viewProducts();
                    break;

                case 3:

                    obj.viewProducts();

                    if (obj.products.isEmpty()) {
                        break;
                    }

                    System.out.print("Enter Product Number : ");
                    int no = sc.nextInt();

                    System.out.print("Enter Quantity to Buy : ");
                    int buyQty = sc.nextInt();

                    obj.buyProduct(no - 1, buyQty);
                    break;

                case 4:

                    obj.displayBill();
                    break;

                case 5:

                    System.out.println("Thank You...");
                    sc.close();
                    return;

                default:

                    System.out.println("Invalid Choice.");
            }
        }
    }
}