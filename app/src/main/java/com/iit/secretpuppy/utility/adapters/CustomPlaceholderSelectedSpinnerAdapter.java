package com.iit.secretpuppy.utility.adapters;

import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import androidx.core.content.res.ResourcesCompat;

import com.iit.secretpuppy.R;


public class CustomPlaceholderSelectedSpinnerAdapter implements SpinnerAdapter, ListAdapter {

    protected static final int DEFUALT_PLACEHOLDERS_COUNT = 1;
    protected SpinnerAdapter adapter;
    protected Context context;
    protected int placeholderLayout;
    protected int placeholderdDropdownLayout;
    protected LayoutInflater layoutInflater;

    private Typeface font ;

    public CustomPlaceholderSelectedSpinnerAdapter(
            SpinnerAdapter spinnerAdapter,
            int placeholderLayout, Context context) {
        this(spinnerAdapter, placeholderLayout, -1, context);

        font  = ResourcesCompat.getFont(context, R.font.machinegunk_nyqg);

    }


    public CustomPlaceholderSelectedSpinnerAdapter(SpinnerAdapter spinnerAdapter,
                                                   int placeholderLayout, int placeholderdDropdownLayout, Context context) {
        this.adapter = spinnerAdapter;
        this.context = context;
        this.placeholderLayout = placeholderLayout;
        this.placeholderdDropdownLayout = placeholderdDropdownLayout;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public final View getView(int position, View convertView, ViewGroup parent) {
        // This provides the View for the Selected Item in the Spinner, not
        // the dropdown (unless dropdownView is not set).
        if (position == 0) {
            return getNothingSelectedView(parent);
        }

        TextView textView = (TextView) adapter.getView(position - DEFUALT_PLACEHOLDERS_COUNT, null, parent);
        textView.setTypeface(font);
        textView.setTextColor(Color.WHITE);
        textView.setTextSize(24);
        return textView;
        // the convertView if possible.
    }


    protected View getNothingSelectedView(ViewGroup parent) {
        return layoutInflater.inflate(placeholderLayout, parent, false);
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        // Spinner does not support multiple view types
        if (position == 0) {
            return placeholderdDropdownLayout == -1 ?
                    new View(context) :
                    getNothingSelectedDropdownView(parent);
        }

        // Could re-use the convertView if possible, use setTag...
        return adapter.getDropDownView(position - DEFUALT_PLACEHOLDERS_COUNT, null, parent);
    }


    protected View getNothingSelectedDropdownView(ViewGroup parent) {
        return layoutInflater.inflate(placeholderdDropdownLayout, parent, false);
    }

    @Override
    public int getCount() {

        int count = adapter.getCount();
        return count == 0 ? 0 : count + DEFUALT_PLACEHOLDERS_COUNT;
    }

    @Override
    public Object getItem(int position) {
        return position == 0 ? null : adapter.getItem(position - DEFUALT_PLACEHOLDERS_COUNT);
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int position) {
        return position >= DEFUALT_PLACEHOLDERS_COUNT ? adapter.getItemId(position - DEFUALT_PLACEHOLDERS_COUNT) : position - DEFUALT_PLACEHOLDERS_COUNT;
    }

    @Override
    public boolean hasStableIds() {
        return adapter.hasStableIds();
    }

    @Override
    public boolean isEmpty() {
        return adapter.isEmpty();
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {
        adapter.registerDataSetObserver(observer);
    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {
        adapter.unregisterDataSetObserver(observer);
    }

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEnabled(int position) {
        return position != 0; // Don't allow the 'nothing selected'
        // item to be picked.
    }

}
