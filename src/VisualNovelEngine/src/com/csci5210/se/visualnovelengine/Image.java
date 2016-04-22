package VisualNovelEngine;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.view.View;
import android.view.View.OnClickListener;

public class Image {

	ImageView image;
	
	public void displayImage(String imageFile){
		
		// Load a bitmap from the drawable folder
		Bitmap bMap = BitmapFactory.decodeResource(getResources(), R.drawable.my_image);
		// Resize the bitmap to 150x100 (width x height)
		Bitmap bMapScaled = Bitmap.createScaledBitmap(bMap, 150, 100, true);
		// Loads the resized Bitmap into an ImageView
		image = (ImageView) findViewById(R.id.imageFile);
		image.setImageBitmap(bMapScaled);
		
	}
	
	public void changeImage(String imageFile){
		
		// Load a bitmap from the drawable folder
		Bitmap bMap = BitmapFactory.decodeResource(getResources(), R.drawable.my_image);
		// Resize the bitmap to 150x100 (width x height)
		Bitmap bMapScaled = Bitmap.createScaledBitmap(bMap, 150, 100, true);
		// Loads the resized Bitmap into an ImageView
		image.setImageResource(R.drawable.imageFile);
		image.setImageBitmap(bMapScaled);
		
	}

}