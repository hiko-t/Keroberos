package com.app.my.keroberos;


import com.app.my.player.Counter;
import com.app.my.util.MyActivity;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class PlayActivity extends MyActivity implements OnClickListener{
	private static final int max = 8;
	private static final String KEY_ROUNDS = "rounds";
	private static final String TAG = "PlayActivity";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        Button next_btn = (Button)findViewById(R.id.btn_next);
        next_btn.setOnClickListener(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_play, menu);
        return true;
    }

	@Override
	public void onClick(View v) {
		Log.d(TAG, "onClick");
		Intent intent = makeIntent(KEY_ROUNDS);
//		startActivity(intent);
	}

	private Intent makeIntent(String key) {
		Counter round_count = (Counter) getPrevCount(key, max);

		round_count.add();

		Intent next_intent = new Intent(PlayActivity.this, ResultsActivity.class);
		next_intent.putExtra(key, round_count);

		if (round_count.isMax()) {
			next_intent = null;
		}
		return next_intent;
	}
}
