package robinsun.com.ecommercedemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by androiddev on 2/09/2016.
 */
public class BaseArrayAdapter<T> extends ArrayAdapter<T> {
    protected final Context mContext;
    protected final LayoutInflater mInflater;
    protected final int mLayout;
    protected List<T> data;

    public BaseArrayAdapter(Context context, int layout, List<T> data) {
        super(context, layout, data);
        this.mContext = context;
        this.mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.data = data;
        this.mLayout = layout;
    }

    public void addItems(List<T> newItems) {
        if (null == newItems || newItems.size() <= 0) {
            return;
        }

        if (null == data) {
            data = new ArrayList<>();
        }

        data.addAll(newItems);
        notifyDataSetChanged();
    }

    public void clearItems() {
        data.clear();
        notifyDataSetChanged();
    }

    public void replaceWithItems(List<T> newItems) {
        clearItems();
        addItems(newItems);
    }

    public List<T> getData() {
        return data;
    }
}
