package com.app.my.keroberos;

import java.io.Serializable;

import com.app.my.keroberos.R.id;
import com.app.my.player.Player;
import com.app.my.player.Counter;
import com.app.my.player.Score;
import com.app.my.util.MyActivity;
import com.app.my.util.MyRunnable;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class ResultsActivity extends MyActivity implements OnClickListener, OnCheckedChangeListener, OnItemSelectedListener{

	private static final String TAG = "InputActivity";
	private static final int THROW_COUNT_MAX = 3;
	private static final int ROUND_COUNT_MAX = 8;
	private static final String KEY_ROUNDS = "rounds";

	private Player player;
	private Handler guiHandler;
	private Counter throwCounter;
	private Score score;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_results);

		// 初期化処理
		player = new Player();
		guiHandler = new Handler();
		throwCounter = new Counter(THROW_COUNT_MAX);
		score = new Score();

		// ボタンを生成
		Button button_resolve = (Button) findViewById(R.id.btn_resolve);
		button_resolve.setOnClickListener(this);

		// ラジオボタングループを生成
		RadioGroup radioGroup = (RadioGroup) findViewById(R.id.scale_facter);
		radioGroup.setOnCheckedChangeListener(this);
//		RadioButton radioButton_select = (RadioButton)findViewById(radioGroup.getCheckedRadioButtonId());

		// スピナーを生成
		String[] strings = {"50", "20", "19", "18", "17", "16", "15", "14", "13", "12", "11", "10"
				, "09", "08", "07", "06", "05", "04", "03", "02", "01"};

		Spinner spinner = (Spinner)findViewById(R.id.score_spiner);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, strings);

		spinner.setAdapter(adapter);

		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setPrompt("以下のリストから選択してください。");
		spinner.setSelection(0);

		spinner.setOnItemSelectedListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_results, menu);
		return true;
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
		// TODO 自動生成されたメソッド・スタブ
		Spinner spinner = (Spinner) parent;
		String item = (String) spinner.getSelectedItem();
		Toast.makeText(this, String.format("%sが選択されました。", item), Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		RadioButton rbtn = (RadioButton)findViewById(checkedId);
		Toast.makeText(ResultsActivity.this, "onCheckedChanged() : " + rbtn.getText(), Toast.LENGTH_SHORT).show();

	}

	@Override
	public void onClick(View arg0) {
		Log.d(TAG, "onClick");

		String str = getSpinnerItem() + "-" + getRadioGroupSelected();
		int value = score.analyzedValue(str);
		int total = score.add(str);
		String totalString = String.valueOf(total);

		TextView totalScore = (TextView)findViewById(id.total_score);
		TextView firstThrow = (TextView)findViewById(id.first_throw_txt);


		setText(totalString, totalScore);
		setText(String.valueOf(value), firstThrow);
		throwCounter.add();

		if (throwCounter.isMax()) {
			Intent intent = makeIntent(KEY_ROUNDS);
//			startActivity(intent);
		}

	}

	private void setText(final String text, TextView textView) {

		Runnable runnable = new MyRunnable(textView, text);
		guiHandler.post(runnable);
	}

	private String getRadioGroupSelected() {
		RadioGroup radioGroup = (RadioGroup) findViewById(R.id.scale_facter);
		radioGroup.setOnCheckedChangeListener(this);
		RadioButton radioButton_select = (RadioButton)findViewById(radioGroup.getCheckedRadioButtonId());

		return (String) radioButton_select.getText();
	}

	private String getSpinnerItem() {
		Spinner spinner = (Spinner)findViewById(id.score_spiner);
		String item = (String) spinner.getSelectedItem();
		return item;
	}

	private Intent makeIntent(String key) {
		Counter roundCount = (Counter) getPrevCount(key, ROUND_COUNT_MAX);

		roundCount.add();

//		Intent next_intent = new Intent(ResultsActivity.this, ResultsActivity.class);
		Intent next_intent = new Intent(ResultsActivity.this, PlayActivity.class);
		next_intent.putExtra(key, roundCount);

		return next_intent;
	}


}
