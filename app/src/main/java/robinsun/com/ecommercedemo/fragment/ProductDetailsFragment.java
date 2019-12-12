package robinsun.com.ecommercedemo.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import robinsun.com.ecommercedemo.R;
import robinsun.com.ecommercedemo.model.Converter;
import robinsun.com.ecommercedemo.utils.UIUtils;
import robinsun.com.ecommercedemo.view.CheckoutConfirmView;
import robinsun.com.ecommercedemo.viewmodel.CheckoutConfirmViewModel;
import robinsun.com.ecommercedemo.viewmodel.ProductViewModel;

/**
 * Created by robinsun on 19/10/17.
 */

public class ProductDetailsFragment extends BaseFragment {
    private static final String ARG_PRODUCT_DETAILS = "productDetails";

    @BindView(R.id.btnBack)
    ImageButton btnBack;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.ivProduct)
    ImageView ivProduct;
    @BindView(R.id.tvProductPrice)
    TextView tvProductPrice;
    @BindView(R.id.tvProductDesc)
    TextView tvProductDesc;
    @BindView(R.id.tvProductTitle)
    TextView tvProductTitle;
    @BindView(R.id.tvSeller)
    TextView tvSeller;
    @BindView(R.id.vCheckoutConfirm)
    CheckoutConfirmView vCheckoutConfirm;

    private ProductViewModel mViewModel;

    public ProductDetailsFragment() {
        // Required empty public constructor
    }

    public static ProductDetailsFragment newInstance(ProductViewModel viewModel) {
        ProductDetailsFragment fragment = new ProductDetailsFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PRODUCT_DETAILS, viewModel);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mViewModel = (ProductViewModel) getArguments().getSerializable(ARG_PRODUCT_DETAILS);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_product_details, container, false);
        ButterKnife.bind(this, view);
        setupUI();

        return view;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public boolean onBackPressedSupport() {
        return super.onBackPressedSupport();
    }

    private void setupUI() {
        btnBack.setVisibility(View.VISIBLE);
        tvTitle.setVisibility(View.VISIBLE);
        tvTitle.setText(mViewModel.getPageTitle());
        tvProductTitle.setText(mViewModel.getTitle());
        tvProductDesc.setText(mViewModel.getDescription());
        tvProductPrice.setText(mViewModel.getPriceString());
        tvSeller.setText(mViewModel.getSellerName());
        UIUtils.loadImage(mContext, ivProduct, mViewModel.getImageUrl(), R.mipmap.placeholder);

        if (!vCheckoutConfirm.isInitialized()) {
            CheckoutConfirmViewModel checkoutConfirmViewModel = new CheckoutConfirmViewModel();
            Converter.convert(mViewModel, checkoutConfirmViewModel);
            vCheckoutConfirm.setViewModel(checkoutConfirmViewModel);
        }
    }

    @OnClick(R.id.btnBack)
    void onBackClick() {
        pop();
    }

    @OnClick(R.id.btnAddToCart)
    void onAddToCartClick() {
        vCheckoutConfirm.show();
    }

    @OnClick(R.id.btnCheckout)
    void onCheckoutClick() {
        vCheckoutConfirm.show();
    }
}
