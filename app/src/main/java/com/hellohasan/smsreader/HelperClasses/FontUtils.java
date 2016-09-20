package com.hellohasan.smsreader.HelperClasses;

import android.content.Context;
import android.graphics.Typeface;

/**
 * Created by Md. Abdur Rahim on 27-Apr-16.
 */
public class FontUtils {

    public static Typeface getRobotoLight(Context context) {
        Typeface font =  Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Light.ttf");
        return font;
    }

    public static Typeface getRobotoThin(Context context) {
        Typeface font =  Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Thin.ttf");
        return font;
    }

    public static Typeface getRobotoMedium(Context context) {
        Typeface font =  Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Medium.ttf");
        return font;
    }

    public static Typeface getBanglaFont(Context context) {
        Typeface font =  Typeface.createFromAsset(context.getAssets(), "fonts/kalpurush ANSI.ttf");
        return font;
    }
}
