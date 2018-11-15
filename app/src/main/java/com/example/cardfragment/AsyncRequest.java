package com.example.cardfragment;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

public class AsyncRequest extends AsyncTask<Void,Void,List<Day>> {
    @Override
    protected List<Day> doInBackground(Void... params) {
        String [] stringDayArray;
        //"http://192.168.1.4/raspisanie/index.php?option=mInfo&gruppa=4kmm&nday=1"
        try {
            stringDayArray = new String[2];
            for (int i = 1; i < 3; i++) {
                String stringDay = "";
                URL url;
                url = new URL("http://192.168.1.4/raspisanie/index.php?option=mInfo&gruppa=4kit&nday="+Integer.toString(i));
                URLConnection con1 = url.openConnection();
                BufferedReader reader = new BufferedReader(new InputStreamReader(con1.getInputStream()));
                String line = "";
                while ((line = reader.readLine()) != null) {
                    stringDay += line;
                }
                stringDay=stringDay.replaceFirst("<pre>","").replaceFirst("</pre>","");
                stringDayArray[i-1]=stringDay;
            }
            List<Day> days = new DaysFromJSONarray(stringDayArray).create();
            return days;
        }
        catch (Exception e){
            Log.i("myapp",e.toString());
        }
        return null;
    }
}
