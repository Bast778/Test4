package com.blot.bastien.test4;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class MyCustomPageAdapter extends PagerAdapter {
    private Context context;
    private LayoutInflater layoutInflater;
    private Table mTable;

    public MyCustomPageAdapter(Context context, Table mTable) {
        this.context = context;
        this.mTable = mTable;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);


    }


    @Override
    public int getCount() {
        return mTable.getSmiles().size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((LinearLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        View itemView = layoutInflater.inflate(R.layout.item, container, false);
        ImageView imageView = itemView.findViewById(R.id.imageView);
        imageView.setImageResource(mTable.smiles.get(position).getImages());
        itemView.setBackgroundColor(ContextCompat.getColor(context,mTable.smiles.get(position).getColors()));
        container.addView(itemView);

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }

    public Context getContext() {
        return context;
    }
}
