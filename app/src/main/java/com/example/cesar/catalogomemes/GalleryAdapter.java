package com.example.cesar.catalogomemes;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
public class GalleryAdapter extends BaseAdapter {
    Context context;
    Integer[] imagenes;
    int background;

    SparseArray<Bitmap> imagenesEscaladas = new SparseArray<Bitmap>(7);

    public GalleryAdapter(Context context, Integer[] imagenes)
    {
        super();
        this.imagenes = imagenes;
        this.context = context;

        TypedArray typedArray = context.obtainStyledAttributes(R.styleable.Gallery1);
        background = typedArray.getResourceId(R.styleable.Gallery1_android_galleryItemBackground, 1);
        typedArray.recycle();
    }

    @Override
    public int getCount()
    {
        return imagenes.length;
    }

    @Override
    public Object getItem(int position)
    {
        return position;
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        ImageView imagen = new ImageView(context);

        if (imagenesEscaladas.get(position) == null)
        {
            Bitmap bitmap = BitmapUtils.decodeSampledBitmapFromResource(context.getResources(), imagenes[position], 120, 0);
            imagenesEscaladas.put(position, bitmap);
        }
        imagen.setImageBitmap(imagenesEscaladas.get(position));

        imagen.setBackgroundResource(background);

        return imagen;
    }
}
