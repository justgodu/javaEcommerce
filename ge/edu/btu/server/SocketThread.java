package ge.edu.btu.server;

import ge.edu.btu.common.Commands;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class SocketThread extends Thread{
    private Socket socket;
    private ObjectOutputStream out;
    private ObjectInputStream in;

    private EcommerceService ecommerceService;

    public SocketThread(Socket socket, EcommerceService ecommerceService) throws Exception {
        this.out = new ObjectOutputStream(socket.getOutputStream());
        this.in = new ObjectInputStream(socket.getInputStream());
        this.socket = socket;
        this.ecommerceService = ecommerceService;
    }

    @Override
    public void run() {
        boolean finished = false;
        while (!finished) {
            try {
                Commands commands = (Commands) in.readObject();
                switch (commands) {
                    case GET_ALL_PRODUCTS:
                        out.writeObject(ecommerceService.getAllProducts());
                        out.flush();
                        out.reset();
                        break;
                    case BUY:
                        Integer prod_id = (Integer) in.readObject();
                        ecommerceService.buy(prod_id);
                        break;
                    case GET_ORDERS:
                        out.writeObject(ecommerceService.getPayments());
                        out.flush();
                        out.reset();
                        break;
                    case EXIT:
                        out.close();
                        in.close();
                        socket.close();
                        finished = true;
                        break;
                }
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }
}
