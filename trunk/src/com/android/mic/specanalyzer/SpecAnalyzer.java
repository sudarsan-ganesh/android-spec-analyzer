package com.android.mic.specanalyzer;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.util.Log;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.hardware.Sensor;

public class SpecAnalyzer extends Activity {

	private static final String TAG = "SpecAnalyzer";
	private GLSurfaceView mGLSurfaceView;

	/** Called when the activity is first created. */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Create our Preview view and set it as the content of our
		// Activity
		mGLSurfaceView = new GLSurfaceView(this);
		mGLSurfaceView.setRenderer(new CubeRenderer(false));
		setContentView(mGLSurfaceView);

		SensorManager sManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

		List<Sensor> liste = sManager.getSensorList(Sensor.TYPE_ALL);
		for (int a = 0; a < liste.size(); a++) {
			Log.i(TAG,"Sensor " + a + ": " + liste.get(a).getName());
		}
		sManager.registerListener(sensorEventListener, liste.get(0),
				SensorManager.SENSOR_DELAY_NORMAL);
	}

	@Override
	protected void onResume() {
		// Ideally a game should implement onResume() and onPause()
		// to take appropriate action when the activity looses focus
		super.onResume();
		mGLSurfaceView.onResume();
		Log.i(TAG,"Resuming... ");
	}

	@Override
	protected void onPause() {
		// Ideally a game should implement onResume() and onPause()
		// to take appropriate action when the activity looses focus
		Log.i(TAG,"Pausing... ");
		super.onPause();
		mGLSurfaceView.onPause();
	}

	private SensorEventListener sensorEventListener = new SensorEventListener() {

		public void onAccuracyChanged(Sensor sensor, int accuracy) {
		}

		public void onSensorChanged(SensorEvent e) {
			synchronized (this) {
				Log.i(TAG, "Sensor event: " + e.values[0] + " " + e.values[1]
						+ " " + e.values[2] + " ");

			}
		}

	};
}