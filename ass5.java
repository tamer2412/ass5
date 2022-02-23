package com.company;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws SQLException {
        ArrayList<Cart> Cart=new ArrayList<>();
        Cart.add(new Cart("Coupon for next purchasing\n", 1));
        Precious diamond = new Precious("Diamond", 75000, 1);
        Precious sapphire = new Precious("Sapphire", 50000, 2);
        Precious ruby = new Precious("Ruby", 37500, 25);
        String allprecious = diamond.name + "\n" + sapphire.name + "\n" + ruby.name;
        Semiprecious garnet = new Semiprecious("Garnet", 25000, 15);
        Semiprecious turquoise = new Semiprecious("Turquoise", 17000, 20);
        Semiprecious moonstone = new Semiprecious("Moonstone", 50000, 5);
        String allsemi = garnet.name + "\n" + turquoise.name + "\n" + moonstone.name;
        Scanner str = new Scanner(System.in);
        System.out.println("What you want to buy?\n1.Precious\n2.Semi-precious\n0. Exit and show cart");
        int x =0;

        String jdbcUrl = "jdbc:postgresql://localhost:5432/postgres";
        String login = "postgres";
        String password = "17besori";
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(jdbcUrl, login, password);
            System.out.println("Connected to PostgreSQL server");
            //connection.close();
        } catch (SQLException e) {
            System.out.println("Error in connecting to PostgreSQL server");
            e.printStackTrace();
        }
        //String sql = "INSERT INTO ass4 (name, price)" + " VALUES ('Reptile', 1400)";
        Statement statement = connection.createStatement();
        //statement.executeUpdate(sql);
        while (true){
            x= str.nextInt();
            switch (x) {
                case 1 -> {
                    System.out.println("U choose precious. We have: \n" + allprecious + "\nWhat you want to buy???");
                    int pre = str.nextInt();
                    switch (pre) {
                        case 1 -> {
                            System.out.println("Diamond's price is " + diamond.price + "/" + diamond.weight + " carat. How many you need?");
                            int q = str.nextInt();
                            String qq = "INSERT INTO ass5 (name, weight, price) VALUES ('Diamond', " + diamond.weight * q + "," + diamond.price * q + ")";
                            statement.executeUpdate(qq);
                            int dd = q * diamond.price;
                            Cart.add(new Cart(diamond.name, dd));
                        }
                        case 2 -> {
                            System.out.println("Sapphire's price is " + sapphire.price + "/" + sapphire.weight + " carat. How many you need?");
                            int s = str.nextInt();
                            String ss = "INSERT INTO ass5 (name, weight, price) VALUES ('Sapphire', " + sapphire.weight * s + "," + sapphire.price * s + ")";
                            statement.executeUpdate(ss);
                            int sss = s * sapphire.price;
                            Cart.add(new Cart(sapphire.name, sss));
                        }
                        case 3 -> {
                            System.out.println("Ruby's price is " + ruby.price + "/" + ruby.weight + " carat. How many you need?");
                            int ru = str.nextInt();
                            String rub = "INSERT INTO ass5 (name, weight, price) VALUES ('Ruby', " + ruby.weight * ru + "," + ruby.price * ru + ")";
                            statement.executeUpdate(rub);
                            int rrr = ru * sapphire.price;
                            Cart.add(new Cart(sapphire.name, rrr));
                        }
                        case 4 -> {
                            break;
                        }
                    }
                    break;
                }
                case 2 -> {
                    System.out.println("From semi-precious we have: \n" + allsemi + ". What you need?");
                    int spre = str.nextInt();
                    switch (spre) {
                        case 1 -> {
                            System.out.println("Garnet's price is " + garnet.price + "/" + garnet.weight + " carat. How many you need?");
                            int g = str.nextInt();
                            String gg = "INSERT INTO ass5 (name, weight, price) VALUES ('Garnet', " + garnet.weight * g + "," + garnet.price * g + ")";
                            statement.executeUpdate(gg);
                            int ggg = g * garnet.price;
                            Cart.add(new Cart(garnet.name, ggg));
                        }
                        case 2 -> {
                            System.out.println("Turquoise's price is " + turquoise.price + "/" + turquoise.weight + " carat. How many you need?");
                            int tur = str.nextInt();
                            String turq = "INSERT INTO ass5 (name, weight, price) VALUES ('Turquoise', " + turquoise.weight * tur + "," + turquoise.price * tur + ")";
                            statement.executeUpdate(turq);
                            int dd = tur * turquoise.price;
                            Cart.add(new Cart(turquoise.name, dd));
                        }
                        case 3 -> {
                            System.out.println("Moonstone's price is " + moonstone.price + "/" + moonstone.weight + " carat. How many you need?");
                            int moonst = str.nextInt();
                            String moonstadd = "INSERT INTO ass5 (name, weight, price) VALUES ('Ruby', " + moonstone.weight * moonst + "," + moonstone.price * moonst + ")";
                            statement.executeUpdate(moonstadd);
                            int rrr = moonst * moonstone.price;
                            Cart.add(new Cart(moonstone.name, rrr));
                        }
                    }
                    break;
                }
                case 0 -> {
                    for (Cart el : Cart) {
                        System.out.println(el.getName() + " " + el.getAmount() + " ");
                    }
                    System.exit(0);
                }
                default -> {
                    System.out.println("Invalid error, try again");
                }
            }
            System.out.println("What you want to buy?\n1.Precious\n2.Semi-precious\n0. Exit and show cart");
        }
    }
}

class Precious{
    public String name;
    public int price;
    public int weight;
    public Precious(String name, int price, int weight){
        this.name=name;
        this.price=price;
        this.weight=weight;
    }
}
class Semiprecious{
    public String name;
    public int price;
    public int weight;
    public Semiprecious(String name, int price, int weight){
        this.name=name;
        this.price=price;
        this.weight=weight;
    }
}
class Cart{
    private String name;
    private int amount;
    public Cart(String name, int amount) {
        this.name = name;
        this.amount = amount;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAmount() {
        return amount;
    }
    public void setAmount(int amount) {
        this.amount = amount;
    }
}
