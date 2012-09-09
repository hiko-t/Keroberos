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
	private static int count ;
	private static int max_count;

	public Counter( int max) {
		max_count = max;
		count = 0;
	}

	public void clear() {
		count = 0;
	}

	public int add() {
		count += 1;
		Log.d(TAG, String.valueOf(count) + "/" + String.valueOf(max_count));
		return count;
	}

	public boolean isMax() {
		if (count >= max_count ) return true;
		return false;
	}

}
