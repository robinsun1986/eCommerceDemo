package robinsun.com.ecommercedemo.viewmodel;

import java.io.Serializable;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import robinsun.com.ecommercedemo.HatApplication;
import robinsun.com.ecommercedemo.R;
import robinsun.com.ecommercedemo.api.ApiClient;
import robinsun.com.ecommercedemo.model.ProductListResponse;
import robinsun.com.ecommercedemo.model.Converter;

/**
 * Created by robinsun on 18/10/17.
 */

public class ProductListViewModel implements Serializable {
    public interface Callback {
        void showLoadingAnimation();

        void onGetProductListSuccess();

        void onGetProductListError(String errMsg);
    }

    private String pageTitle = HatApplication.getInstance().getString(R.string.product_list_title);
    private List<ProductViewModel> productViewModels;
    private Callback mCallback;
    private CompositeDisposable disposableBag = new CompositeDisposable();

    public void setCallback(Callback mCallback) {
        this.mCallback = mCallback;
    }

    public String getPageTitle() {
        return pageTitle;
    }

    public List<ProductViewModel> getProductViewModels() {
        return productViewModels;
    }

    public void setProductViewModels(List<ProductViewModel> productViewModels) {
        this.productViewModels = productViewModels;
    }

    public void performGetProductList() {
        if (mCallback != null) {
            mCallback.showLoadingAnimation();
        }

        Disposable disposable = ApiClient.getInstance().getProductList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<ProductListResponse>() {
                    @Override
                    public void onNext(@NonNull ProductListResponse productListResponse) {
                        Converter.convert(productListResponse, ProductListViewModel.this);
                        if (mCallback != null) {
                            mCallback.onGetProductListSuccess();
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        if (mCallback != null) {
                            mCallback.onGetProductListError(e.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {
                    }
                });
        disposableBag.add(disposable);
    }

    public void refreshData() {
        performGetProductList();
    }

    public void dispose() {
        disposableBag.dispose();
    }
}
