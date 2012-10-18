package com.app.my.util;

import java.io.Serializable;
import java.net.URISyntaxException;

import com.app.my.keroberos.R.id;
import com.app.my.player.Counter;
import com.app.my.player.Score;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.widget.TextView;

public class MyActivity extends Activity {

	/**
	 * インテントから前のカウンタを取得する
	 *
	 * @param key
	 * @param max
	 * @return
	 */
	protected Serializable getPrevCount(String key, int max) {
		Intent intent = getIntent();
		Serializable serializable = intent.getSerializableExtra(key);

		if (serializable == null) {
			serializable = new Counter(max);
		}

		return serializable;
	}

	/**
	 * @param intent
	 * @param key
	 * @return
	 */
	protected Score setTextTotalScore(Intent intent, String key) {
		Score score = (Score) intent.getSerializableExtra(key);
		if (score == null) {
			score = new Score();
		}

		String total_score = score.toString();
//		Log.d(TAG, total_score + " : " + String.valueOf(score.throwList));

		TextView totalScore = (TextView)findViewById(id.total_score);
		totalScore.setText(total_score);

		return score;
	}
}
