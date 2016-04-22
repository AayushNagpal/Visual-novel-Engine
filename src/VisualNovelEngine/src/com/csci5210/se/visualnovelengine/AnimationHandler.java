package com.csci5210.se.visualnovelengine;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class AnimationHandler {
	public enum AnimationType { NOTHING, FADEINWITHTEXT, FADEIN, FADEOUTWITHTEXT, FADEOUT };

	public static void fadeIn(Context context, ImageView imageView) {
		Animation fadeInAnimation = AnimationUtils.loadAnimation(context, R.anim.fadein);
		imageView.startAnimation(fadeInAnimation);
	}

	public static void fadeIn(Context context, ImageView imageView, TextView textView, Button button) {
		Animation fadeInAnimation = AnimationUtils.loadAnimation(context, R.anim.fadein);

		imageView.startAnimation(fadeInAnimation);
		textView.startAnimation(fadeInAnimation);
		button.startAnimation(fadeInAnimation);
	}

	public static void fadeOut(Context context, ImageView imageView) {
		Animation fadeInAnimation = AnimationUtils.loadAnimation(context, R.anim.fadeout);
		imageView.startAnimation(fadeInAnimation);
	}

	public static void fadeOut(Context context, ImageView imageView, TextView textView, Button button) {
		Animation fadeInAnimation = AnimationUtils.loadAnimation(context, R.anim.fadeout);

		imageView.startAnimation(fadeInAnimation);
		textView.startAnimation(fadeInAnimation);
		button.startAnimation(fadeInAnimation);
	}

	@SuppressLint("DefaultLocale")
	public static void execute(AnimationType what, Context context, final ImageView imageView, final TextView textView, Button button, final int resource) {
		switch(what) {
			case NOTHING:
			break;
			case FADEINWITHTEXT:
				textView.setVisibility(View.GONE);

				fadeIn(context, imageView, textView, button);

				imageView.setImageResource(resource);
				textView.setVisibility(View.VISIBLE);

				break;
			case FADEIN:
				fadeIn(context, imageView);

				imageView.setImageResource(resource);

				break;
			case FADEOUTWITHTEXT:
				textView.setVisibility(View.VISIBLE);

				fadeOut(context, imageView, textView, button);

			    new Handler().postDelayed(new Runnable() {
			         public void run() {
						imageView.setImageResource(0);
						textView.setVisibility(View.GONE);
			         }
			    }, 2000);

				break;
			case FADEOUT:
				fadeOut(context, imageView);

			    new Handler().postDelayed(new Runnable() {
			         public void run() {
						imageView.setImageResource(0);
			         }
			    }, 2000);

				break;
		}
	}
}
