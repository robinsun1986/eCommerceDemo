package robinsun.com.ecommercedemo.model;

import java.io.Serializable;

/**
 * Created by robinsun on 19/10/17.
 */

public class Seller implements Serializable {
    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
