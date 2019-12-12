package robinsun.com.ecommercedemo.view;

import android.animation.Animator;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.apmem.tools.layouts.FlowLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import robinsun.com.ecommercedemo.R;
import robinsun.com.ecommercedemo.utils.UIUtils;
import robinsun.com.ecommercedemo.viewmodel.CheckoutConfirmViewModel;

/**
 * Created by robinsun on 19/10/17.
 */

public class CheckoutConfirmView extends LinearLayout {

    @BindView(R.id.tvCheckoutPrice)
    TextView tvCheckoutPrice;
    @BindView(R.id.tvCheckoutName)
    TextView tvCheckoutName;
    @BindView(R.id.ivCheckoutClose)
    ImageView ivCheckoutClose;
    @BindView(R.id.vgCheckoutSizes)
    FlowLayout vgCheckoutSizes;
    @BindView(R.id.btnCheckoutDone)
    Button btnCheckoutDone;
    @BindView(R.id.ivCheckoutImage)
    ImageView ivCheckoutImage;
    @BindView(R.id.btnCheckoutQtyMinus)
    Button btnCheckoutQtyMinus;
    @BindView(R.id.tvCheckoutQty)
    TextView tvCheckoutQty;
    @BindView(R.id.btnCheckoutQtyPlus)
    Button btnCheckoutQtyPlus;
    @BindView(R.id.vgCheckoutConfirm)
    View vgCheckoutConfirm;

    private Context mContext;
    private boolean mInitialized;
    private CheckoutConfirmViewModel mViewModel;
    private int mCheckoutConfirmContainerHeight;

    public boolean isInitialized() {
        return mInitialized;
    }

    public void setViewModel(CheckoutConfirmViewModel viewModel) {
        this.mViewModel = viewModel;
        updateUI();
        mInitialized = true;
    }

    private void updateUI() {
        tvCheckoutName.setText(mViewModel.getProductTitle());
        tvCheckoutPrice.setText(mViewModel.getPriceString());
        tvCheckoutQty.setText(mViewModel.getQuantityString());
        UIUtils.loadImage(mContext, ivCheckoutImage, mViewModel.getImageUrl(), R.mipmap.placeholder);

        vgCheckoutSizes.removeAllViews();
        if (mViewModel.getSizes() != null) {
            for (final String size : mViewModel.getSizes()) {
                ViewGroup container;
                if (size.equals(mViewModel.getSelectedSize())) {
                    container = (ViewGroup) inflate(mContext, R.layout.size_tag_selected, null);
                } else {
                    container= (ViewGroup) inflate(mContext, R.layout.size_tag, null);
                }
                Button button = (Button) container.findViewById(R.id.btnSizeTag);
                button.setText(size);
                button.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mViewModel.setSelectedSize(size);
                        updateUI();
                    }
                });
                vgCheckoutSizes.addView(container);
            }
        }
    }

    public CheckoutConfirmView(Context context) {
        super(context);
        init(context);
    }

    public CheckoutConfirmView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CheckoutConfirmView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mContext = context;
        inflate(mContext, R.layout.view_checkout_confirm, this);
        ButterKnife.bind(this);

        ViewTreeObserver.OnGlobalLayoutListener listener = new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                mCheckoutConfirmContainerHeight = vgCheckoutConfirm.getHeight();
                vgCheckoutConfirm.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                vgCheckoutConfirm.setY(mCheckoutConfirmContainerHeight);
            }
        };
        vgCheckoutConfirm.getViewTreeObserver().addOnGlobalLayoutListener(listener);
    }

    public void show() {
        vgCheckoutConfirm.setVisibility(VISIBLE);
        ViewPropertyAnimator animator = vgCheckoutConfirm.animate();
        animator.setListener(null);
        animator.translationY(0);
    }

    private void dismiss() {
        ViewPropertyAnimator animator = vgCheckoutConfirm.animate();
        animator.setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                vgCheckoutConfirm.setVisibility(GONE);
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
        animator.translationY(mCheckoutConfirmContainerHeight);
    }

    @OnClick(R.id.btnCheckoutDone)
    void onCheckoutDoneClicked() {
        dismiss();
    }

    @OnClick(R.id.ivCheckoutClose)
    void onCheckoutCloseClicked() {
        dismiss();
    }

    @OnClick(R.id.btnCheckoutQtyMinus)
    void onQuantityMinusClicked() {
        mViewModel.minusQuantity();
        updateUI();
    }

    @OnClick(R.id.btnCheckoutQtyPlus)
    void onQuantityPlusClicked() {
        mViewModel.plusQuantity();
        updateUI();
    }
}
