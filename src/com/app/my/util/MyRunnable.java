package com.app.my.util;

import android.widget.TextView;

public class MyRunnable implements Runnable{

	private TextView textView;
	private String text;

	public MyRunnable(TextView view, String string) {
		textView = view;
		text = string;
	}

	@Override
	public void run() {
		textView.setText(text);
	}

}
