package robinsun.com.ecommercedemo.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemClick;
import in.srain.cube.views.GridViewWithHeaderAndFooter;
import robinsun.com.ecommercedemo.R;
import robinsun.com.ecommercedemo.adapter.ProductListAdapter;
import robinsun.com.ecommercedemo.viewmodel.ProductListViewModel;
import robinsun.com.ecommercedemo.viewmodel.ProductViewModel;


public class ProductListFragment extends BaseFragment implements ProductListViewModel.Callback {
    private static final String ARG_VIEW_MODEL = "viewModel";

    @BindView(R.id.btnBack)
    ImageButton btnBack;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.gvProducts)
    GridViewWithHeaderAndFooter gvProducts;
    @BindView(R.id.swipeRefresh)
    SwipeRefreshLayout swipeRefresh;

    private ProductListAdapter mAdapter;
    private ProductListViewModel mViewModel;

    private boolean mWithinSecondBackTap;
    private Handler mHandler = new Handler();
    private Toast mToast;

    public ProductListFragment() {
        // Required empty public constructor
    }

    public static ProductListFragment newInstance() {
        ProductListFragment fragment = new ProductListFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new ProductListViewModel();
        mViewModel.setCallback(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_product_list, container, false);
        ButterKnife.bind(this, view);
        setupUI(inflater);
        mViewModel.performGetProductList();

        return view;
    }

    @Override
    public void onDestroyView() {
        mViewModel.dispose();
        super.onDestroyView();
    }

    private void setupUI(LayoutInflater inflater) {
        btnBack.setVisibility(View.GONE);
        tvTitle.setText(mViewModel.getPageTitle());

        swipeRefresh.setColorSchemeResources(R.color.colorPrimary);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mViewModel.refreshData();
            }
        });

        View headerView = inflater.inflate(R.layout.product_grid_header_footer, null);
        View footerView = inflater.inflate(R.layout.product_grid_header_footer, null);
        gvProducts.addHeaderView(headerView);
        gvProducts.addFooterView(footerView);
    }

    @Override
    public boolean onBackPressedSupport() {
        if (mWithinSecondBackTap) {
            if (mToast != null) {
                mToast.cancel();
            }
            mContext.finish();
            System.exit(0);
        } else {
//            UIUtils.showToast("Press back again to exit", true);
//            Toast.makeText(mContext, getString(R.string.press_back_again_to_exit), 1000);
            mWithinSecondBackTap = true;
            mToast = Toast.makeText(mContext, getString(R.string.press_back_again_to_exit), Toast.LENGTH_SHORT);
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mWithinSecondBackTap = false;
                }
            }, 2000);
            mToast.show();
        }

        return true;
    }

    @OnItemClick(R.id.gvProducts)
    void onProductItemCLick(AdapterView<?> parent, View view, int position, long id) {
        ProductViewModel productViewModel = mAdapter.getItem(position);
        start(ProductDetailsFragment.newInstance(productViewModel));
    }

    // ProductListViewModel.Callback
    @Override
    public void onGetProductListSuccess() {
        swipeRefresh.setRefreshing(false);
        mAdapter = new ProductListAdapter(mContext, mViewModel.getProductViewModels());
        gvProducts.setAdapter(mAdapter);
    }

    @Override
    public void onGetProductListError(String errMsg) {
        swipeRefresh.setRefreshing(false);
        Toast.makeText(mContext, errMsg, Toast.LENGTH_LONG);
    }

    @Override
    public void showLoadingAnimation() {
        swipeRefresh.setRefreshing(true);
    }
}
