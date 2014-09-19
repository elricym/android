package com.example.dayactivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import android.R.string;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		final Button b = (Button) findViewById(R.id.button1);
		final TextView tv = (TextView) findViewById(R.id.textView1);

		// activity listener
		b.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				new AsyncTask<Void, Void, String>() {

					@Override
					protected String doInBackground(Void... params) {
						Socket s = null;
						BufferedReader br = null; // buffer for receiving
						StringBuffer sBuffer = null; // buffer for sending
						try {
							s = new Socket("eece261.hopto.org", 1313); // connecting
																		// sever

							br = new BufferedReader(new InputStreamReader(s
									.getInputStream()));
							String line = null;
							line = br.readLine();
							OutputStreamWriter osw = new OutputStreamWriter(s
									.getOutputStream());

							// sending request with time zone
							sBuffer = new StringBuffer();
							sBuffer.append("CST\n");
							// request must be followed with a empty line.
							sBuffer.append("\n");
							// write request to buffer
							osw.write(sBuffer.toString());
							osw.flush(); // request sent

							// getting ascii time
							String timeString = new String();
							String tempString = null; // temporary string
							timeString = "";
							for (int i = 0; i < 15; i++) {
								tempString = br.readLine();
								timeString = timeString + tempString + "\n";
							}
							return timeString;

						} catch (UnknownHostException e) {
							e.printStackTrace();
						} catch (IOException e) {
							e.printStackTrace();
						} finally {
							if (br != null)
								try {
									br.close();
								} catch (IOException e) {
								}
						}

						return "Nothing received";
					}

					@Override
					protected void onPostExecute(String line) {
						tv.setText(line);
					}

				}.execute();

			}
		});
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
