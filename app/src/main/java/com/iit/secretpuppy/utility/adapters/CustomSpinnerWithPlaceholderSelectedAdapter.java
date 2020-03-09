package com.iit.secretpuppy.utility.adapters;


//MARK: References https://stackoverflow.com/questions/867518/how-to-make-an-android-spinner-with-initial-text-select-one
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


public class CustomSpinnerWithPlaceholderSelectedAdapter implements SpinnerAdapter, ListAdapter {

    protected static final int DEFAULT_PLACEHOLDERS_COUNT = 1;
    protected SpinnerAdapter adapter;
    protected Context context;
    protected int placeholderLayout;
    protected int placeholderDropdownLayout;
    protected LayoutInflater layoutInflater;

    private Typeface font ;

    public CustomSpinnerWithPlaceholderSelectedAdapter(
            SpinnerAdapter spinnerAdapter,
            int placeholderLayout, Context context) {
        this(spinnerAdapter, placeholderLayout, -1, context);

        font  = ResourcesCompat.getFont(context, R.font.machinegunk_nyqg);

    }


    public CustomSpinnerWithPlaceholderSelectedAdapter(SpinnerAdapter spinnerAdapter,
                                                       int placeholderLayout, int placeholderDropdownLayout, Context context) {
        this.adapter = spinnerAdapter;
        this.context = context;
        this.placeholderLayout = placeholderLayout;
        this.placeholderDropdownLayout = placeholderDropdownLayout;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public final View getView(int position, View convertView, ViewGroup parent) {

        if (position == 0) {
            return getNothingSelectedView(parent);
        }

        TextView textView = (TextView) adapter.getView(position - DEFAULT_PLACEHOLDERS_COUNT, null, parent);
        textView.setTypeface(font);
        textView.setTextColor(Color.WHITE);
        textView.setTextSize(24);
        return textView;
    }


    protected View getNothingSelectedView(ViewGroup parent) {
        return layoutInflater.inflate(placeholderLayout, parent, false);
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        // Spinner does not support multiple view types
        if (position == 0) {
            return placeholderDropdownLayout == -1 ?
                    new View(context) :
                    getNothingSelectedDropdownView(parent);
        }

        // Could re-use the convertView if possible, use setTag...
        return adapter.getDropDownView(position - DEFAULT_PLACEHOLDERS_COUNT, null, parent);
    }


    protected View getNothingSelectedDropdownView(ViewGroup parent) {
        return layoutInflater.inflate(placeholderDropdownLayout, parent, false);
    }

    @Override
    public int getCount() {

        int count = adapter.getCount();
        return count == 0 ? 0 : count + DEFAULT_PLACEHOLDERS_COUNT;
    }

    @Override
    public Object getItem(int position) {
        return position == 0 ? null : adapter.getItem(position - DEFAULT_PLACEHOLDERS_COUNT);
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
        return position >= DEFAULT_PLACEHOLDERS_COUNT ? adapter.getItemId(position - DEFAULT_PLACEHOLDERS_COUNT) : position - DEFAULT_PLACEHOLDERS_COUNT;
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
