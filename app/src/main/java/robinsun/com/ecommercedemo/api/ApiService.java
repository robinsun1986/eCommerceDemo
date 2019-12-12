package robinsun.com.ecommercedemo.api;

/**
 * Created by robinsun on 19/10/17.
 */

import io.reactivex.Observable;
import retrofit2.http.GET;
import robinsun.com.ecommercedemo.model.ProductListResponse;

public interface ApiService {
    @GET("/hats/products.json")
    Observable<ProductListResponse> getProductList();
}
