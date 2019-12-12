package robinsun.com.ecommercedemo.utils;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by robinsun on 19/10/17.
 */

public class UIUtils {
    public static String getSafeString(String str) {
        return (str == null ? "" : str);
    }

    public static void loadImage(Context context, ImageView imageView, String url, int placeholder) {
        if (TextUtils.isEmpty(url)) return;

        url = url.replaceFirst("http", "https");
        Picasso.Builder builder = new Picasso.Builder(context);
        builder.listener(new Picasso.Listener()
        {
            @Override
            public void onImageLoadFailed(Picasso picasso, Uri uri, Exception exception)
            {
                exception.printStackTrace();
            }
        });

        builder.build()
                .load(url)
                .placeholder(placeholder)
                .fit()
                .into(imageView);
    }
}
