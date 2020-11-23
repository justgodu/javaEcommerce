package ge.edu.btu.server;

import ge.edu.btu.common.Product;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {
    public static void main(String[] args) throws Exception{

        EcommerceService ecommerceService = new EcommerceServiceImpl();
        ecommerceService.initProducts(getTestProducts());
        ServerSocket serverSocket = new ServerSocket(8000);

        while(true){
            Socket socket = serverSocket.accept();
            SocketThread socketThread = new SocketThread(socket, ecommerceService);
            socketThread.start();

        }
    }

    private static List<Product> getTestProducts() {
        List<Product> testProducts = new ArrayList<>();
        testProducts.add(new Product("Kai shaurma", 1, 9));
        testProducts.add(new Product("Kai mexikuri", 2, 5));
        testProducts.add(new Product("Kai xinkali", 3, 1));
        testProducts.add(new Product("Kai mwvadi", 4, 15));
        testProducts.add(new Product("Uf Uf Uf", 5, 25));
        return  testProducts;
    }

}
