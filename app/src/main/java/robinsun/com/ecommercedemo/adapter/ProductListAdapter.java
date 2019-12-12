package robinsun.com.ecommercedemo.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import robinsun.com.ecommercedemo.R;
import robinsun.com.ecommercedemo.utils.UIUtils;
import robinsun.com.ecommercedemo.viewmodel.ProductViewModel;

/**
 * Created by robinsun on 18/10/17.
 */
public class ProductListAdapter extends BaseArrayAdapter<ProductViewModel> {

    static class ViewHolder {
        @BindView(R.id.ivProduct)
        ImageView ivProduct;
        @BindView(R.id.tvSeller)
        TextView tvSeller;
        @BindView(R.id.tvProductName)
        TextView tvProductName;
        @BindView(R.id.tvProductPrice)
        TextView tvProductPrice;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    public ProductListAdapter(@NonNull Context context, @NonNull List objects) {
        super(context, R.layout.item_product, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = mInflater.inflate(mLayout, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }

        final ProductViewModel productViewModel = data.get(position);
        viewHolder = (ViewHolder) convertView.getTag();
        viewHolder.tvProductName.setText(productViewModel.getTitle());
        viewHolder.tvProductPrice.setText(productViewModel.getPriceString());
        viewHolder.tvSeller.setText(productViewModel.getSellerName());
        UIUtils.loadImage(mContext, viewHolder.ivProduct, productViewModel.getImageUrl(), R.mipmap.placeholder);
        return convertView;
    }
}
