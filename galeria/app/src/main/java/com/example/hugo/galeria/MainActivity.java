package com.example.hugo.galeria;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity
{
    private ImageView imagem;
    private final int GALERIA = 1;
    private final int PERMISSAO_REQUEST = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(ContextCompat.checkSelfPermission(this,Manifest.permission.READ_EXTERNAL_STORAGE)
                !=  PackageManager.PERMISSION_GRANTED)
        {
            if(ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.READ_EXTERNAL_STORAGE))
            {}else
             {
                 ActivityCompat.requestPermissions(this,
                         new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                         PERMISSAO_REQUEST);
             }
        }

        imagem = (ImageView) findViewById(R.id.iv);
        Button galeria = (Button) findViewById(R.id.btn);
        galeria.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent  =  new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, GALERIA);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if  (resultCode==  RESULT_OK  &&  requestCode == GALERIA)
        {
            Uri  selectedImage = data.getData();
            String[]  filePath = {MediaStore.Images.Media.DATA};

            Cursor c = getContentResolver().query(selectedImage,filePath,  null,  null,  null);
            c.moveToFirst();

            int columnIndex = c.getColumnIndex(filePath[0]);
            String  picturePath = c.getString(columnIndex);
            c.close();

            Bitmap thumbnail = (BitmapFactory.decodeFile(picturePath));
            imagem.setImageBitmap(thumbnail);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        if(requestCode == PERMISSAO_REQUEST)
        {
            if(grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                // A permissão foi concedida. Pode continuar
            }else
            {
                //A permissão foi negada. Precisa ver o que deve ser desabilitado
            }

            return;
        }
    }
}
