package com.iit.secretpuppy.utility.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.viewpager.widget.PagerAdapter;

import com.iit.secretpuppy.R;

import java.util.ArrayList;

public class ImageSlideAdapter extends PagerAdapter {

    private ArrayList<Drawable> images;
    private LayoutInflater inflater;
    private Context context;

    public ImageSlideAdapter(Context context, ArrayList<Drawable> images){
        this.context = context;
        this.images = images;
        inflater = LayoutInflater.from(context);

    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object){
        container.removeView((View)object);
    }

    @Override
    public int getCount() {
        return images.size();
    }

    @Override
    public Object instantiateItem(ViewGroup view, int position){
        View myImageLayout  = inflater.inflate(R.layout.image_slide, view, false);
        ImageView myImage   =  myImageLayout.findViewById(R.id.imgSlide);
        myImage.setImageDrawable(images.get(position));
        view.addView(myImageLayout, 0);
        return myImageLayout;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }
}
