package com.harvic.tryeventbus2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.harvic.other.FirstEvent;
import com.harvic.other.SecondEvent;
import com.harvic.other.ThirdEvent;

import de.greenrobot.event.EventBus;

public class MainActivity extends Activity {

	Button btn;
	TextView tv;
	EventBus eventBus;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		EventBus.getDefault().register(this);//注册

		btn = (Button) findViewById(R.id.btn_try);
		tv = (TextView)findViewById(R.id.tv);
		btn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getApplicationContext(),
						SecondActivity.class);
				startActivity(intent);
			}
		});
	}

	//接受消息的实现
	public void onEventMainThread(FirstEvent event) {
		String msg = "onEventMainThread " + event.getMsg();
		Log.d("LogForCat", "onEventMainThread" + event.getMsg());
		tv.setText(msg);
		Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
	}

	//SecondEvent接收函数一
	public void onEventMainThread(SecondEvent event) {
		String msg = "onEventMainThread " + event.getMsg();
		Log.d("LogForCat", "onEventMainThread" + event.getMsg());
		//tv.setText(msg);
		Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
	}
	//SecondEvent接收函数二
	public void onEventBackgroundThread(SecondEvent event){
		String msg = "onEventBackgroundThread " + event.getMsg();
		Log.d("LogForCat", "onEventBackgroundThread" + event.getMsg());
		//tv.setText(msg);
		//Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
	}
	//SecondEvent接收函数三
	public void onEventAsync(SecondEvent event){
		String msg = "onEventAsync " + event.getMsg();
		Log.d("LogForCat", "onEventAsync" + event.getMsg());
		//tv.setText(msg);
		//Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
	}

	public void onEvent(ThirdEvent event) {
		String msg = "OnEvent " + event.getMsg();
		Log.d("LogForCat", "OnEvent" + event.getMsg());
		tv.setText(msg);
		Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		EventBus.getDefault().unregister(this);
	}
}
