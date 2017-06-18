package data;

import android.app.Activity;
import android.content.SharedPreferences;

/**
 * Created by X on 2017-06-16.
 */

public class CityPreference {
    SharedPreferences prefs;

    public CityPreference(Activity activity){
        prefs = activity.getPreferences(Activity.MODE_PRIVATE);
    }

    public String getCity(){
        return prefs.getString("city", "Vilnius,LT");
    }

    public void setCity(String city){
        prefs.edit().putString("city", city).commit();
    }
}
