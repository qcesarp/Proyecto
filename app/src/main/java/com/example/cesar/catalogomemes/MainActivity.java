package com.example.cesar.catalogomemes;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.Gallery;
import android.widget.ImageView;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;


public class MainActivity extends Activity {

    ImageView imagenSeleccionada;
    Gallery gallery;
    EditText et;
    Bitmap icon;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et = (EditText) findViewById(R.id.Etext);
        imagenSeleccionada = (ImageView) findViewById(R.id.seleccionada);

        final Integer[] imagenes = {R.drawable.a, R.drawable.b, R.drawable.c,
                R.drawable.d, R.drawable.e, R.drawable.f,
                R.drawable.g, R.drawable.h, R.drawable.i, R.drawable.j,
                R.drawable.k, R.drawable.l, R.drawable.m, R.drawable.n, R.drawable.o,
                R.drawable.p, R.drawable.r, R.drawable.s, R.drawable.t, R.drawable.u};

            gallery = (Gallery) findViewById(R.id.gallery);
            gallery.setAdapter(new GalleryAdapter(this, imagenes));
            gallery.setOnItemClickListener(new OnItemClickListener() {
                public void onItemClick(AdapterView parent, View v, int position, long id) {
                    imagenSeleccionada.setImageBitmap(BitmapUtils.decodeSampledBitmapFromResource(getResources(), imagenes[position], 300, 0));
                    icon = BitmapFactory.decodeResource(getResources(), imagenes[position]);
                    }
                });

		    gallery.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

			    @Override
			    public void onItemSelected(AdapterView parent, View v, int position, long id)
			    {
		            imagenSeleccionada.setImageBitmap(BitmapUtils.decodeSampledBitmapFromResource(getResources(), imagenes[position], 400, 0));
			    }

			    @Override
			    public void onNothingSelected(AdapterView<?> arg0)
			    {
				    // TODO Auto-generated method stub
                }
            }
        );
    }

     public void enviar(View v){
        String message = et.getText().toString();
         File f = new File( Environment.getExternalStorageDirectory() + File.separator);
         try {
             ByteArrayOutputStream bytes = new ByteArrayOutputStream();
             icon.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
             f.createNewFile();
             FileOutputStream fo = new FileOutputStream(f);
             fo.write(bytes.toByteArray());
         } catch (IOException e) {
             Log.e("ERROR", e.getMessage());
         }

         Intent share = new Intent(Intent.ACTION_SEND);
         share.setType("image/jpeg");
         share.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(f));
         share.putExtra(Intent.EXTRA_TEXT, message);
         startActivity(Intent.createChooser(share, "Compartir"));
    }

    public void acecarDe(View v){
        Intent intent = new Intent(this,SecondActivity.class);
        startActivity(intent);
    }

    public void salir(View v) {
        finish();
    }

}