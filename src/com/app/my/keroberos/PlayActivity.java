package com.app.my.keroberos;

import com.app.my.keroberos.R.id;
import com.app.my.player.Counter;
import com.app.my.player.Score;
import com.app.my.util.MyActivity;

import android.os.Bundle;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class PlayActivity extends MyActivity implements OnClickListener{
	private static final int ROUND_COUNT_MAX = 8;
	private static final String KEY_ROUNDS = "rounds";
	private static final String KEY_SCORES = "scores";
	private static final String TAG = "PlayActivity";
	private Score score;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_play);

		Button next_btn = (Button)findViewById(id.btn_next);
		next_btn.setOnClickListener(this);

		Intent intent = getIntent();

		score = setTextTotalScore(intent, KEY_SCORES);
//		score = (Score) intent.getSerializableExtra(KEY_SCORES);
//		String total_score = score.toString();
//		Log.d(TAG, total_score + " : " + String.valueOf(score.throwList));
//
//		TextView totalScore = (TextView)findViewById(id.total_score);
//		totalScore.setText(total_score);

		Counter rounds = (Counter) intent.getSerializableExtra(KEY_ROUNDS);
		String count = rounds.toString();
		TextView roundCount = (TextView) findViewById(id.round_count);
		roundCount.setText(count);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_play, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		Log.d(TAG, "onClick");


		if (v.getId() == id.btn_next) {
			Intent intent = makeIntent();
//			intent.putExtra(KEY_SCORES, score);
			if (intent != null) {
				startActivity(intent);
			}
		}
	}

	private Intent makeIntent() {
		Counter roundCount = (Counter) getPrevCount(KEY_ROUNDS, ROUND_COUNT_MAX);

//		round_count.add();

		Intent nextIntent = new Intent(this, ResultsActivity.class);
		nextIntent.putExtra(KEY_ROUNDS, roundCount);
		nextIntent.putExtra(KEY_SCORES, score);

		if (roundCount.isMax()) {
			nextIntent = null;
		}
		return nextIntent;
	}
}
