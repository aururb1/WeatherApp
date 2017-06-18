package com.example.x.weatherapp;

import android.content.DialogInterface;
import android.icu.text.DateFormat;
import android.icu.text.DecimalFormat;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import Util.Utils;
import model.Weather;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by X on 2017-06-17.
 */

public class MyCustomAdapter extends RecyclerView.Adapter<MyCustomAdapter.MyViewHolder> {
    private Context context;

    private ArrayList<Weather> data;

    private LayoutInflater inflater;

    private int previousPosition = 0;

    public MyCustomAdapter(Context context, ArrayList<Weather> data) {

        this.context = context;
        this.data = data;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int position) {

        View view = inflater.inflate(R.layout.row, parent, false);

        MyViewHolder holder = new MyViewHolder(view);

        return holder;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onBindViewHolder(MyViewHolder myViewHolder, final int position) {
        try {
            String iconFormat = data.get(position).currentCondition.getIcon();
            String imageUrl = Utils.ICON_URL + iconFormat;

            loadImageFromUrl(imageUrl, myViewHolder.iconView);

            DateFormat df = DateFormat.getTimeInstance();

            String sunriseDate = df.format(new Date(data.get(position).place.getSunrise()));
            String sunsetDate = df.format((new Date(data.get(position).place.getSunset())));
            String updateDate = df.format(new Date((data.get(position).place.getLastupdate())));

            DecimalFormat decimalFormat = new DecimalFormat("#.#");
            String tempFormat = decimalFormat.format(data.get(position).currentCondition.getTemperature());

            myViewHolder.cityName.setText(data.get(position).place.getCity() + "," + data.get(position).place.getCountry());
            myViewHolder.temp.setText("" + tempFormat + "C");
            myViewHolder.humidity.setText("Humidity: " + data.get(position).currentCondition.getHumidity() + "%");
            myViewHolder.pressure.setText("Pressure: " + data.get(position).currentCondition.getPressure() + "hPa");
            myViewHolder.wind.setText("Wind: " + data.get(position).wind.getSpeed() + "mps");
            myViewHolder.sunrise.setText("Sunrise: " + sunriseDate + " AM");
            myViewHolder.sunset.setText("Sunset: " + sunsetDate + " PM");
            myViewHolder.updated.setText("Last Updated :" + updateDate + " PM");
            myViewHolder.description.setText("Condition: " + data.get(position).currentCondition.getCondition() + "(" +
                    data.get(position).currentCondition.getDescription() + ")");
        }
        catch(Exception e){

        }

        if(position > previousPosition){ // We are scrolling DOWN

            AnimationUtil.animate(myViewHolder, true);

        }else{ // We are scrolling UP

            AnimationUtil.animate(myViewHolder, false);


        }

        previousPosition = position;


        final int currentPosition = position;
        final Weather infoData = data.get(position);

        myViewHolder.iconView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        myViewHolder.iconView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                Toast.makeText(context, "Successfully Removed! ", Toast.LENGTH_SHORT).show();

                removeItem(infoData);

                return true;
            }


        });


    }

    private void loadImageFromUrl(String imageUrl, ImageView iconView){
        Picasso.with(context).load(imageUrl).placeholder(R.drawable.climate)
                .error(R.drawable.climate)
                .into(iconView, new com.squareup.picasso.Callback() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError() {

                    }
                });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView cityName;
        TextView temp;
        ImageView iconView;
        TextView description;
        TextView humidity;
        TextView pressure;
        TextView wind;
        TextView sunrise;
        TextView sunset;
        TextView updated;

        public MyViewHolder(View itemView) {
            super(itemView);

            cityName = (TextView) itemView.findViewById(R.id.cityText);
            temp = (TextView) itemView.findViewById(R.id.tempText);
            iconView = (ImageView) itemView.findViewById(R.id.thumbnailIcon);
            description = (TextView) itemView.findViewById(R.id.cloudText);
            humidity = (TextView) itemView.findViewById(R.id.humidText);
            pressure = (TextView) itemView.findViewById(R.id.pressureText);
            wind = (TextView) itemView.findViewById(R.id.windText);
            sunrise = (TextView) itemView.findViewById(R.id.riseText);
            sunset = (TextView) itemView.findViewById(R.id.setText);
            updated = (TextView) itemView.findViewById(R.id.updateText);

        }
    }

    private void removeItem(Weather infoData) {

        int currPosition = data.indexOf(infoData);
        data.remove(currPosition);
        notifyItemRemoved(currPosition);
    }

    private void addItem(int position, Weather infoData) {

        data.add(position, infoData);
        notifyItemInserted(position);
    }
}
