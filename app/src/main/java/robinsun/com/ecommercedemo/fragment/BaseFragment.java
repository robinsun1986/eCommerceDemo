package robinsun.com.ecommercedemo.fragment;

import android.app.Activity;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by robinsun on 18/10/17.
 */

public class BaseFragment extends SupportFragment {

    protected Activity mContext;

    @Override
    public void onAttach(Activity activity) {
        mContext = activity;
        super.onAttach(activity);
    }
}
