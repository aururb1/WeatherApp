package com.example.x.weatherapp;

import android.accessibilityservice.GestureDescription;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.icu.text.DateFormat;
import android.icu.text.DecimalFormat;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;

import Util.Utils;
import data.CityPreference;
import data.JSONWeatherParser;
import data.WeatherHttpClient;
import model.Weather;

public class MainActivity extends ActionBarActivity {

    Weather weather = new Weather();
    ArrayList<Weather> listWeather = new ArrayList<>();

    RecyclerView recyclerView;
    MyCustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recycleView);
        adapter = new MyCustomAdapter(this, listWeather);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
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
            super.onPostExecute(weather);

        }
    }

    private void showInpuDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Change City");
        final EditText cityInput = new EditText(MainActivity.this);
        cityInput.setInputType(InputType.TYPE_CLASS_TEXT);
        cityInput.setHint("Vilnius,LT");
        builder.setView(cityInput);
        builder.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                CityPreference cityPreference = new CityPreference(MainActivity.this);
                cityPreference.setCity(cityInput.getText().toString());
                String newCity = cityPreference.getCity();
                renderWeatherData(newCity);
            }
        });
        showAlert();
        builder.show();
    }

    public void showAlert(){
        AlertDialog.Builder myAlert = new AlertDialog.Builder(this);
        myAlert.setMessage("Looking For The City..")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setTitle("City")
                .create();
        myAlert.show();
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
