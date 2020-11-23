package ge.edu.btu.server;

import ge.edu.btu.common.Product;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EcommerceServiceImpl implements EcommerceService {

    private List<Product> products;
    private Map<Integer, Long> result = new HashMap<>();
    @Override
    public void initProducts(List<Product> products) {
        this.products = products;
        for (Product product : products) {
            result.put(product.getID(), 0L);
        }
    }

    @Override
    public List<Product> getAllProducts() {
        return products;
    }

    @Override
    public void buy(int prod_id) {
        if (result.containsKey(prod_id)) {
            result.put(prod_id, result.get(prod_id) + 1);
        }
    }

    @Override
    public Map<Integer, Long> getPayments() {
        return result;
    }
}
