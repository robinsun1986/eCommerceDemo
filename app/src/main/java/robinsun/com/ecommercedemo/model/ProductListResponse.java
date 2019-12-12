package robinsun.com.ecommercedemo.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by robinsun on 19/10/17.
 */

public class ProductListResponse implements Serializable {
    private List<Product> products;

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
