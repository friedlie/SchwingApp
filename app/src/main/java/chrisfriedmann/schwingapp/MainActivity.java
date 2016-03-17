package chrisfriedmann.schwingapp;

/**
 * Created by chrisfriedmann on 15.03.16.
 */
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.concurrent.TimeUnit;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

import static java.lang.Thread.*;

/*
public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
*/
public class MainActivity extends Activity {
    /**
     * Called when the activity is first created.
     */

    SensorManager mgr;
    TextView tx, ty, tz, maxx;
    float max_x, max_y, max_z;


   final float[] arryX = new float[10000];
    final float[] arryY = new float[10000];
     final float[] arryZ = new float[10000];
    int i = 0;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_main);



        tx = (TextView) findViewById(R.id.tvx);
        ty = (TextView) findViewById(R.id.tvy);
        tz = (TextView) findViewById(R.id.tvz);
        maxx = (TextView) findViewById(R.id.tvmaxx);
        max_x = 0;
        max_y = 0;
        max_z = 0;
        mgr = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mgr.registerListener(listener,
                mgr.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_FASTEST);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mgr.unregisterListener(listener);
        // TODO CSV ertsellen verschicken

    }

    private SensorEventListener listener = new SensorEventListener() {


        @Override
        public void onSensorChanged(SensorEvent event) {
            if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
                float current_x, current_y, current_z, x, y, z;

                        arryX[i] = event.values[0];
                        arryY[i] = event.values[1];
                        arryZ[i] = event.values[2];

                    i++;

                if(i > 100){
                    onDestroy();
                }

                // update textviews
                tx.setText(
                        "\n\nx:\n"
                                + arryX[1] + "\n"
                                + arryX[20] + "\n"
                                + arryX[50] + "\n"
                                + arryX[95] + "\n"
                                + max_x + "\n"
                                + "\ny:\n"
                                + arryY[1] + "\n"
                                + arryY[200] + "\n"
                                + arryY[500] + "\n"
                                + arryY[1000] + "\n"

                                + "\nz:\n"
                                + arryZ[1] + "\n"
                                + arryZ[200] + "\n"
                                + arryZ[500] + "\n"
                                + arryZ[1000] + "\n"
                );

            }

        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
            // unused

        }
    };

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://chrisfriedmann.schwingapp/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://chrisfriedmann.schwingapp/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}