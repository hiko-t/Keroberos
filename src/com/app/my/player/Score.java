package com.app.my.player;

import android.util.Log;
import android.widget.TextView;

public class Score {
	private static final String TAG = "Score";
	private String firstThrow;
	private String secondThrow;
	private String thirdThrow;
	private int total_score;

//	public void setTextView(String text, TextView text_view) {
//		add(text);
//		text_view.setText(total_score);
//	}

	public int add(String value_s) {
		int value = analyzedValue(value_s);
		total_score += value;
		return total_score;
	}

	public int analyzedValue(String value) {
		String[] values = value.split("-");
		String numString = values[0];
		int num = Integer.valueOf(numString);
		int scaleFacter = exchangeScaleFacter(values[1]);

		int result = num * scaleFacter;
		Log.d(TAG, numString + " * " + String.valueOf(scaleFacter) + " = " + String.valueOf(result));
		return result;
	}

	private int exchangeScaleFacter(String string_num) {
		int scale_facter = 0;
		Log.d(TAG, "exchangeScaleFacter : " + string_num);
		if (string_num.equals("Single")) scale_facter = 1;
		if (string_num.equals("Double")) scale_facter = 2;
		if (string_num.equals("Triple")) scale_facter = 3;

		return scale_facter;
	}

}
