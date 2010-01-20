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
	private SensorManager sManager;
	
	/** Called when the activity is first created. */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		mGLSurfaceView = new GLSurfaceView(this);
		mGLSurfaceView.setRenderer(new CubeRenderer(false));
		setContentView(mGLSurfaceView);

		sManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
	}

	@Override
	protected void onResume() {
		super.onResume();
		mGLSurfaceView.onResume();
		Log.i(TAG,"Resuming... ");
		
		List<Sensor> liste = sManager.getSensorList(Sensor.TYPE_ALL);
		for (int a = 0; a < liste.size(); a++) {
			Log.i(TAG,"Sensor " + a + ": " + liste.get(a).getName());
			sManager.registerListener(sensorEventListener, liste.get(a),
				SensorManager.SENSOR_DELAY_NORMAL);
		}
	}

	@Override
	protected void onPause() {
		Log.i(TAG,"Pausing... ");
		super.onPause();
		mGLSurfaceView.onPause();
	}

	private SensorEventListener sensorEventListener = new SensorEventListener() {

		public void onAccuracyChanged(Sensor sensor, int accuracy) {
		}

		public void onSensorChanged(SensorEvent e) {
			switch( e.sensor.getType() ) {
			case Sensor.TYPE_ACCELEROMETER:
				synchronized (this) {
					Log.i(TAG, "Accelerometer Sensor event: " + e.values.toString() );
				}
			case Sensor.TYPE_ORIENTATION:
				synchronized (this) {
					Log.i(TAG, "Orientation Sensor event: " + e.values.toString() );
				}
			}
		}

	};
}