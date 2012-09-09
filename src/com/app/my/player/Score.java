package com.app.my.player;

import java.util.ArrayList;
import java.util.List;

import android.util.Log;

public class Score {
	private static final String TAG = "Score";
	private List<String> throwValues;
	private int total_score;

	public Score() {
		throwValues =new ArrayList<String>();
	}

	public int addTotal(String value_s) {
		throwValues.add(value_s);
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

	public List<String> getScores() {
		List<String> list = new ArrayList<String>();

		String total_score_s = String.valueOf(total_score);
		list.add(total_score_s);

		for (int i = 0; i < throwValues.size(); i++ ) {
			String value = throwValues.get(i);
			list.add(value);
		}

		return list;

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
