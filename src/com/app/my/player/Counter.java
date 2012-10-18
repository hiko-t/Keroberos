package com.app.my.player;


import java.io.Serializable;

import android.content.Intent;
import android.util.Log;

public class Counter implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private static final String TAG = "Counter";
	private int count ;
	private int max_count;

	public Counter( int max) {
		max_count = max;
		count = 0;
	}

	public void clear() {
		count = 0;
	}

	public int add() {
		if (!isMax()) {
			count += 1;
		}
		return count;
	}

	public boolean isMax() {
		Log.d(TAG, String.valueOf(count) + "/" + String.valueOf(max_count));
		if (count >= max_count ) return true;
		return false;
	}

	public int getCount() {
		return count;
	}

	public String toString() {
		String count_str = String.valueOf(count);
		String max_count_str = String.valueOf(max_count);
		return count_str + "/" + max_count_str;
	}


}
