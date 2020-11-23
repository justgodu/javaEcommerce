package ge.edu.btu.server;

import ge.edu.btu.common.Product;

import java.util.List;
import java.util.Map;

public interface EcommerceService {

    void initProducts(List<Product> products);

    List<Product> getAllProducts();

    void buy(int prod_id);

    Map<Integer, Long> getPayments();
}
