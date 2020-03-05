package com.iit.secretpuppy.utility;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;

public class Utility {


    public static Drawable getDrawable(Context mContext, String name) {
        int resourceId = mContext.getResources().getIdentifier(name, "drawable", mContext.getPackageName());
        return mContext.getResources().getDrawable(resourceId);
    }

    public static Animation flashingAnumation() {

        Animation anim = new AlphaAnimation(0.0f, 1.0f);
        anim.setDuration(100);
        anim.setStartOffset(20);
        anim.setRepeatMode(Animation.REVERSE);
        anim.setRepeatCount(Animation.INFINITE);

        return anim;
    }
}
