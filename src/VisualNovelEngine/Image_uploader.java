package com.example.test;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.net.Uri;
import android.os.Bundle;

public class Image_uploader extends Activity implements OnClickListener{
	Button img1, img2, img3, done;
	RadioButton btn1, btn2;
@Override
protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
	setContentView(R.layout.image_file);
	img1 = (Button) findViewById(R.id.img_1);
	img1.setOnClickListener(this);
	img2 = (Button) findViewById(R.id.img_2);
	img2.setOnClickListener(this);
	img3 = (Button) findViewById(R.id.img_3);
	img3.setOnClickListener(this);
	
	btn1 = (RadioButton) findViewById(R.id.radio00);
	btn1.setOnClickListener(this);
	btn2 = (RadioButton) findViewById(R.id.radio11);
	btn2.setOnClickListener(this);
	
	done = (Button) findViewById(R.id.back);
    done.setOnClickListener(this);
}

@Override
public void onClick(View v) {
	switch(v.getId())
	{
		case R.id.img_1:
		{
			Intent intent2 = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI); 
			startActivityForResult(intent2, 1); 
			break;
		}
		
		case R.id.img_2:
		{
			Intent intent2 = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI); 
			startActivityForResult(intent2, 2); 
			break;
		}
		
		case R.id.img_3:
		{
			Intent intent2 = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI); 
			startActivityForResult(intent2, 3); 
			break;
		}
		case R.id.back:
		{
			Intent play = new Intent(this, MainActivity.class);
			startActivity(play);
			break;
		}
		case R.id.radio0:
		{
			if(btn1.isChecked()){
				img1.setText("Upload For All");
				img2.setEnabled(false);
				img3.setEnabled(false);
				
			}
			break;
		}
		case R.id.radio1:
		{
			if(btn2.isChecked()){
				img1.setText("UPLOAD AUDIO 1");
				img2.setEnabled(true);
				img3.setEnabled(true);
			}
			break;
		}
	}
}

protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	// TODO Auto-generated method stub
	super.onActivityResult(requestCode, resultCode, data);
	
	switch(requestCode)
	{
	case 1:
	{
		Uri selImg = data.getData();
		img1.setText(selImg.toString());
		break;
	}
	case 2:
	{
		Uri selImg = data.getData();
		img2.setText(selImg.toString());
		break;
	}
	case 3:
	{
		Uri selImg = data.getData();
		img3.setText(selImg.toString());
		break;
	}
	}
	
	
}


}
