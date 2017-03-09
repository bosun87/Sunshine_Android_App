package com.example.android.sunshine.app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends ActionBarActivity {

    public final static String EXTRA_MESSAGE = "com.mycompany.myfirstapp.MESSAGE";
    public final String TAG = "Main Activity";
    private final String FORECASTFRAGMENT_TAG = "TAG_FORECASTFRAGMENT";

    private String mLocation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.v(TAG,"entering onCreate");

        mLocation = Utility.getPreferredLocation(this);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new ForecastFragment(),FORECASTFRAGMENT_TAG)
                    .commit();
        }
    }

    public void onDestroy() {
        super.onDestroy();  // Always call the superclass

        Log.v(TAG,"entering onDestroy");
    }

    public void onPause() {
        super.onPause();  // Always call the superclass method first

        Log.v(TAG,"entering onPause");
    }

    public void onResume() {
        super.onResume();  // Always call the superclass method first
        Log.v(TAG,"entering onResume");

        String str = Utility.getPreferredLocation(this);
        if(str!=null&&!mLocation.equals(str)){

            ForecastFragment ff = (ForecastFragment)getSupportFragmentManager().findFragmentByTag(FORECASTFRAGMENT_TAG);
            if(ff!=null)

            ff.onLocationChanged();


            mLocation = str;
        }

    }

    protected void onStop() {
        super.onStop();  // Always call the superclass method first
        Log.v(TAG,"entering onStop");
    }

    protected void onStart() {
        super.onStart();  // Always call the superclass method first
        Log.v(TAG,"entering onStart");
    }

    protected void onRestart() {
        super.onRestart();  // Always call the superclass method first
        Log.v(TAG,"entering onRestart");
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

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent myIntent = new Intent(this,SettingsActivity.class);
            startActivity(myIntent);
            return true;
        }

        if(id==R.id.action_location) {

            Intent myIntent = new Intent(Intent.ACTION_VIEW);
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
            String location = prefs.getString(getString(R.string.pref_location_key),
                    getString(R.string.pref_location_default));

            Uri gmmIntentUri = Uri.parse("geo:0,0?q=" + Uri.encode(location+" "+"us"));
            myIntent.setData(gmmIntentUri);
            if (myIntent.resolveActivity(getPackageManager()) != null) {
                startActivity(myIntent);
            }
        }



        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */

}
