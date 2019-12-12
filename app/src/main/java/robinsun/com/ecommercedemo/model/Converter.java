package robinsun.com.ecommercedemo.model;

import java.util.ArrayList;
import java.util.List;

import robinsun.com.ecommercedemo.utils.UIUtils;
import robinsun.com.ecommercedemo.viewmodel.CheckoutConfirmViewModel;
import robinsun.com.ecommercedemo.viewmodel.ProductListViewModel;
import robinsun.com.ecommercedemo.viewmodel.ProductViewModel;

/**
 * Created by robinsun on 19/10/17.
 */

public class Converter {
    public static void convert(ProductListResponse response, ProductListViewModel productListViewModel) {
        List<ProductViewModel> productViewModels = new ArrayList<>();

        if (response != null && response.getProducts() != null) {
            for (Product product : response.getProducts()) {
                ProductViewModel productViewModel = new ProductViewModel();
                convert(product, productViewModel);
                productViewModels.add(productViewModel);
            }
        }

        productListViewModel.setProductViewModels(productViewModels);
    }

    public static void convert(Product product, ProductViewModel productViewModel) {
        productViewModel.setTitle(UIUtils.getSafeString(product.getTitle()));
        productViewModel.setDescription(UIUtils.getSafeString(product.getDescription()));
        productViewModel.setImageUrl(product.getImageUrl());
        if (product.getSeller() != null) {
            productViewModel.setSellerName(UIUtils.getSafeString(product.getSeller().getName()));
        }
        productViewModel.setSizes(product.getSizes());
        String priceString = String.format("$%.2f", product.getPrice());
        productViewModel.setPriceString(priceString);
    }

    public static void convert(ProductViewModel productViewModel, CheckoutConfirmViewModel checkoutConfirmViewModel) {
        checkoutConfirmViewModel.setProductTitle(productViewModel.getTitle());
        checkoutConfirmViewModel.setImageUrl(productViewModel.getImageUrl());
        checkoutConfirmViewModel.setPriceString(productViewModel.getPriceString());
        checkoutConfirmViewModel.setSizes(productViewModel.getSizes());
    }
}
