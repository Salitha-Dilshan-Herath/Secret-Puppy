package com.iit.secretpuppy.utility;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.inputmethod.InputMethodManager;

public class Utility {


    //Get drawable using image name
    public static Drawable getDrawable(Context mContext, String name) {
        int resourceId = mContext.getResources().getIdentifier(name, "drawable", mContext.getPackageName());
        return mContext.getResources().getDrawable(resourceId);
    }

    //Animation for ImageView
    public static Animation flashingAnimation() {

        Animation anim = new AlphaAnimation(0.0f, 1.0f);
        anim.setDuration(100);
        anim.setStartOffset(20);
        anim.setRepeatMode(Animation.REVERSE);
        anim.setRepeatCount(Animation.INFINITE);

        return anim;
    }

    //get display breed name using image name
    public static String getShowBreedName (String formattingString) {

        return (String) DogCategories.getInstance().getShowBreeds().get(DogCategories.getInstance().getBreeds().indexOf(formattingString));
    }

    //When Touch Outside hide keyboard function
    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager =
                (InputMethodManager) activity.getSystemService(
                        Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(
                activity.getCurrentFocus().getWindowToken(), 0);
    }

    //User Score Mange section using SharedPref
    public static void saveLevel1Score(Context context, int score) {

        if (getLevel1Score(context) > score) {
            return;
        }

        //--SAVE Data
        SharedPreferences preferences = context.getSharedPreferences("Score", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("lv1", score);
        editor.commit();
    }

    public static int getLevel1Score (Context context) {

        SharedPreferences preferences = context.getSharedPreferences("Score", Context.MODE_PRIVATE);

        return  preferences.getInt("lv1", 0);
    }

    public static void saveLevel2Score(Context context, int score) {

        if (getLevel2Score(context) > score) {
            return;
        }

        //--SAVE Data
        SharedPreferences preferences = context.getSharedPreferences("Score", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("lv2", score);
        editor.commit();
    }

    public static int getLevel2Score (Context context) {

        SharedPreferences preferences = context.getSharedPreferences("Score", Context.MODE_PRIVATE);

        return  preferences.getInt("lv2", 0);
    }
}
