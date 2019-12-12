package robinsun.com.ecommercedemo;

import android.app.Application;

import me.yokeyword.fragmentation.*;
import me.yokeyword.fragmentation.helper.ExceptionHandler;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by robinsun on 18/10/17.
 */

public class HatApplication extends Application {
    private static HatApplication instance;

    public static HatApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                        .setDefaultFontPath("fonts/SanFranciscoDisplayRegular.ttf")
//                        .setFontAttrId(R.attr.fontPath)
                        .build()
        );

        Fragmentation.builder()
                .stackViewMode(Fragmentation.NONE)
                .debug(false)
                .handleException(new ExceptionHandler() {
                    @Override
                    public void onException(Exception e) {
                        e.printStackTrace();
                    }
                })
                .install();
    }


}
