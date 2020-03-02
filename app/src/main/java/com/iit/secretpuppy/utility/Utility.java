package com.iit.secretpuppy.utility;

import android.content.Context;
import android.graphics.drawable.Drawable;

public class Utility {


    public static Drawable getDrawable(Context mContext, String name) {
        int resourceId = mContext.getResources().getIdentifier(name, "drawable", mContext.getPackageName());
        return mContext.getResources().getDrawable(resourceId);
    }
}
