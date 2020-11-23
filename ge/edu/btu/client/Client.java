package ge.edu.btu.client;

import ge.edu.btu.common.Commands;
import ge.edu.btu.common.Product;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        Socket socket = new Socket("localhost", 8000);

        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

        while (true) {
            System.out.println("1. პოდუქტების ნახვა");
            System.out.println("2. ყიდვა");
            System.out.println("3. შეკვეთების ნახვა");
            System.out.println("0. გასვლა");

            String option = scanner.nextLine();
            if (option.equals("0")) {
                out.writeObject(Commands.EXIT);
                out.close();
                in.close();
                socket.close();
                break;
            }
            Map<Integer, Long> result;
            switch (option) {
                case "1" :
                    out.writeObject(Commands.GET_ALL_PRODUCTS);
                    List<Product> products = (List<Product>) in.readObject();
                    for (Product product : products) {
                        System.out.println(product);
                    }
                    break;
                case "2" :
                    System.out.println("პროდუქტის ID:");
                    int number = Integer.parseInt(scanner.nextLine());

                    out.writeObject(Commands.BUY);
                    out.writeObject(number);
                    out.flush();
                    out.reset();
                    break;
                case "3" :
                    out.writeObject(Commands.GET_ORDERS);
                    result = (Map<Integer, Long>) in.readObject();
                    for (int n : result.keySet()) {
                        System.out.println(n + " ->  " + result.get(n));
                    }
                    out.flush();
                    out.reset();
                    break;
                default:
                    System.out.println("აირჩიეთ 1,2,3 ან 0");
            }
        }
    }
}