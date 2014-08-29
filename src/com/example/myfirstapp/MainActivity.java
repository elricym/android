package com.example.myfirstapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity {
	
	
	TextView logView;
	String logText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        logView = (TextView)findViewById(R.id.logView);
        logText = (String) logView.getText();
        logView.setText(logText+"MainActivity has been created.\n");
        Button b = (Button)findViewById(R.id.button1);
        final TextView textView = (TextView)findViewById(R.id.helloWorld);
        b.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Toast.makeText(getApplicationContext(),"Hey there!", Toast.LENGTH_SHORT).show();
				textView.setText("Hello World!");
			}
		});
    }
    
    @Override
    protected void onStart(){
    	super.onStart();
    	logText = (String) logView.getText();
        logView.setText(logText+"MainActivity has started.\n");
    }
    
    @Override
    protected void onResume() {
        super.onResume();
        logText = (String) logView.getText();
        logView.setText(logText+"MainActivity is resuming.\n");
    }
    @Override
    protected void onPause() {
        super.onPause();
        logText = (String) logView.getText();
        logView.setText(logText+"MainActivity paused.\n");
    }
    @Override
    protected void onStop() {
        super.onStop();
        logText = (String) logView.getText();
        logView.setText(logText+"MainActivity has stopped.\n");
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        logText = (String) logView.getText();
        logView.setText(logText+"MainActivity has been destroyed.\n");
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
