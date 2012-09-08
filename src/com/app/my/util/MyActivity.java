package com.app.my.util;

import java.io.Serializable;
import java.net.URISyntaxException;

import com.app.my.player.Counter;

import android.app.Activity;
import android.content.Intent;

public class MyActivity extends Activity {

	/**
	 * インテントから前のカウンタを取得する
	 */
	protected Serializable getPrevCount(String key, int max) {
		Intent intent = getIntent();
		Serializable serializable = intent.getSerializableExtra(key);

		if (serializable == null) {
			serializable = new Counter(max);
		}

		return serializable;
	}
}
