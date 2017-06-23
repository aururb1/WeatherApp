package com.example.x.weatherapp;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import com.example.x.weatherapp.data.JSONWeatherParser;
import com.example.x.weatherapp.data.WeatherHttpClient;
import com.example.x.weatherapp.model.Weather;

public class MainActivity extends ActionBarActivity {

    Weather weather = new Weather();
    ArrayList<Weather> listWeather = new ArrayList<>();

    RecyclerView recyclerView;
    MyCustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(!isConnected(MainActivity.this)) buildDialog(MainActivity.this).show();
        recyclerView = (RecyclerView) findViewById(R.id.recycleView);
        adapter = new MyCustomAdapter(this, listWeather);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public boolean isConnected(Context context){
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if(networkInfo != null && networkInfo.isConnectedOrConnecting()) {
            android.net.NetworkInfo wifi = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            android.net.NetworkInfo mobile = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

            if ((mobile != null && mobile.isConnectedOrConnecting()) || (wifi != null && wifi.isConnectedOrConnecting()))
                return true;
            else
                return false;
        } else
            return false;
    }

    public AlertDialog.Builder buildDialog(Context context){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setIcon(R.drawable.error);
        builder.setTitle("No Internet Connection");
        builder.setMessage("You need to have internet access. Press Ok to Exit");

        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        return builder;
    }

    public void renderWeatherData(String city){
        WeatherTask weatherTask = new WeatherTask();
        weatherTask.execute(new String[]{city + "&units=metric"});
    }

    private class WeatherTask extends AsyncTask<String, Void, Weather>{
        @Override
        protected Weather doInBackground(String... params) {
            try {
                String data = ((new WeatherHttpClient()).getWeatherData(params[0]));

                weather = JSONWeatherParser.getWeather(data);
                if(weather!=null)
                    listWeather.add(weather);
            }
            catch (Exception e){

            }
            return weather;
        }

        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        protected void onPostExecute(Weather weather) {
            adapter.notifyDataSetChanged();
        }
    }

    private void showInpuDialog(){
        if(!isConnected(MainActivity.this)) buildDialog(MainActivity.this).show();
        else {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("Choose City");
            final EditText cityInput = new EditText(MainActivity.this);
            cityInput.setInputType(InputType.TYPE_CLASS_TEXT);
            cityInput.setHint("Vilnius,LT");
            builder.setView(cityInput);
            builder.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    renderWeatherData(cityInput.getText().toString());
                }
            });
            builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialog) {
                    Context context = getApplicationContext();
                    Toast.makeText(context, "You did not press Submit button!", Toast.LENGTH_LONG).show();
                }
            });
            builder.show();
        }
    }

    @Override
    public  boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if (id == R.id.change_cityId){
            showInpuDialog();
        }
        return super.onOptionsItemSelected(item);
    }
}
