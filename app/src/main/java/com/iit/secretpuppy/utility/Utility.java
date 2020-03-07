package com.iit.secretpuppy.utility;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.inputmethod.InputMethodManager;

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

    public static String getShowBreedName (String formattingString) {

        return (String) DogCategories.getInstance().getShowBreeds().get(DogCategories.getInstance().getBreeds().indexOf(formattingString));
    }

    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager =
                (InputMethodManager) activity.getSystemService(
                        Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(
                activity.getCurrentFocus().getWindowToken(), 0);
    }
}
