package robinsun.com.ecommercedemo.viewmodel;

import java.util.List;

/**
 * Created by robinsun on 19/10/17.
 */

public class CheckoutConfirmViewModel {
    private String imageUrl;
    private String productTitle;
    private String priceString;
    private List<String> sizes;
    private String selectedSize;
    private int quantity = 1;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public String getPriceString() {
        return priceString;
    }

    public void setPriceString(String priceString) {
        this.priceString = priceString;
    }

    public List<String> getSizes() {
        return sizes;
    }

    public void setSizes(List<String> sizes) {
        this.sizes = sizes;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void minusQuantity() {
        if (quantity > 1) {
            quantity--;
        }
    }

    public void plusQuantity() {
        quantity++;
    }

    public String getQuantityString() {
        return "" + quantity;
    }

    public String getSelectedSize() {
        return selectedSize;
    }

    public void setSelectedSize(String selectedSize) {
        this.selectedSize = selectedSize;
    }
}
