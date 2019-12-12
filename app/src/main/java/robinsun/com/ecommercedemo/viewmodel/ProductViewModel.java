package robinsun.com.ecommercedemo.viewmodel;

import java.io.Serializable;
import java.util.List;

import robinsun.com.ecommercedemo.HatApplication;
import robinsun.com.ecommercedemo.R;

/**
 * Created by robinsun on 19/10/17.
 */

public class ProductViewModel implements Serializable {
    private String pageTitle = HatApplication.getInstance().getString(R.string.product_details_title);
    private String title;
    private String description;
    private String imageUrl;
    private List<String> sizes;
    private String sellerName;
    private String priceString;

    public String getPageTitle() {
        return pageTitle;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public List<String> getSizes() {
        return sizes;
    }

    public void setSizes(List<String> sizes) {
        this.sizes = sizes;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public String getPriceString() {
        return priceString;
    }

    public void setPriceString(String priceString) {
        this.priceString = priceString;
    }
}
