package com.example.android.k17;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class DisplayMessageActivity extends AppCompatActivity {
    ImageButton btnTakePhoto;
    ImageView imagetaken;
    int a;
    private static final int CAM_REQUEST=1313;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);

        Intent intent=getIntent();


        String name = intent.getStringExtra(com.example.android.k17.MainActivity.A);
        String dob = intent.getStringExtra(com.example.android.k17.MainActivity.B);
        String dept = intent.getStringExtra(com.example.android.k17.MainActivity.C);
        String year = intent.getStringExtra(com.example.android.k17.MainActivity.D);
        TextView textView1 = (TextView) findViewById(R.id.text1);
        TextView textView2= (TextView) findViewById(R.id.text2);
        textView1.setSelected(true);
        ImageView img = (ImageView)findViewById(R.id.img);
        img.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("http://www.kurukshetra.org.in/"));
                startActivity(intent);
            }
        });







        btnTakePhoto = (ImageButton) findViewById(R.id.takeph);
        imagetaken=(ImageView) findViewById(R.id.image1);
        btnTakePhoto.setOnClickListener(new btnTakePhotoClicker());
        textView2.setTextSize(16);

        textView2.setTextColor(Color.BLUE);

        textView2.setText("Name : "+name+"\nDate of Birth : "+dob+"\nDept: "+dept+"\nYear: "+year);

        textView1.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);





    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode,resultCode,data);
        if(requestCode==CAM_REQUEST)
        {
            Bitmap thumbnail=(Bitmap) data.getExtras().get("data");
            imagetaken.setImageBitmap(thumbnail);



        }
    }
    class btnTakePhotoClicker implements Button.OnClickListener
    {
        public void onClick(View v)
        {
            Intent cameraintent= new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(cameraintent,CAM_REQUEST);
        }

    }
}

