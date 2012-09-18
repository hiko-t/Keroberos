package com.app.my.player;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import android.util.Log;

public class Score implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private static final String TAG = "Score";
	public List<String> throwList;
	private int total_score;

	public Score() {
		throwList =new ArrayList<String>();
	}

	public int addTotal(String value_s) {
		throwList.add(value_s);
		int value = analyzedValue(value_s);
		total_score += value;
		return total_score;
	}

	public String toString() {
		return String.valueOf(total_score);
	}

	public String[] toString(int round, int throwMax) {
		String[] result = null;
		for (int count_i = 0; count_i < throwMax; count_i++) {
			result[count_i] = throwList.get(round * throwMax + count_i);
		}
		return result;

	}

	public int analyzedValue(String value) {
		String[] values = value.split("-");
		String numString = values[0];
		int num = Integer.valueOf(numString);
		int scaleFacter = exchangeScaleFacter(values[1]);
		if (num == 50 ) {
			scaleFacter = 1;
		}
		int result = num * scaleFacter;
		Log.d(TAG, numString + " * " + String.valueOf(scaleFacter) + " = " + String.valueOf(result));
		return result;
	}

	public List<String> getScores() {
		List<String> list = new ArrayList<String>();

		String total_score_s = String.valueOf(total_score);
		list.add(total_score_s);

		for (int i = 0; i < throwList.size(); i++ ) {
			String value = throwList.get(i);
			list.add(value);
		}

		return list;

	}

	public void clearThrowValues() {
		throwList =new ArrayList<String>();
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
