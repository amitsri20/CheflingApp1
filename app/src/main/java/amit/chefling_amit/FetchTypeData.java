package amit.chefling_amit;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by amit on 10/28/2016.
 */

/*
 * Copyright (C) 2014 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


public class FetchTypeData extends AsyncTask<Void, Void, Void> {

    private final String LOG_TAG = FetchTypeData.class.getSimpleName();

    private final Context mContext;
    public static ArrayList<String> types = new ArrayList<String>();

    public FetchTypeData(Context context) {
        mContext = context;
    }

    private boolean DEBUG = true;

    private void getDataFromJson(String JsonStr)
            throws JSONException {


        try {
            JSONObject jsonObject = new JSONObject(JsonStr);
            Map<String,String> map = new HashMap<String,String>();
            Iterator iter = jsonObject.keys();
            while(iter.hasNext()){
                String key = (String)iter.next();
                String value = jsonObject.getString(key);
                map.put(key,value);
            }

            for (String value:map.values()
                 ) {
                Log.d("Values:",value);
                types.add(value);
            }



        } catch (JSONException e) {
            Log.e(LOG_TAG, e.getMessage(), e);
            e.printStackTrace();
        }

    }

    @Override
    protected Void doInBackground(Void... params) {

        // If there's no zip code, there's nothing to look up.  Verify size of params.
//        if (params.length == 0) {
//            return null;
//        }

        // These two need to be declared outside the try/catch
        // so that they can be closed in the finally block.
//        HttpURLConnection connection = null;
        BufferedReader reader = null;

        // Will contain the raw JSON response as a string.
        String JsonStr = null;

        int numDays = 14;
        StringBuffer jsonString;
        try {
            // Construct the URL for the OpenWeatherMap query
            // Possible parameters are avaiable at OWM's forecast API page, at
            // http://openweathermap.org/API#forecast
            final String BASE_URL =
                    "http://www.chefling.me/testapi/getRecipeType.php";
            String payload = "{\'RTid\':0}";
            URL url = new URL(BASE_URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");
            writer.write(payload);
            writer.close();

            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            jsonString = new StringBuffer();
            String line;
            while ((line = br.readLine()) != null) {
                jsonString.append(line);
            }
            br.close();
            connection.disconnect();
            getDataFromJson(jsonString.toString());
            Log.d("Received data:",jsonString.toString());
        }
        catch (JSONException e) {
            Log.e(LOG_TAG, e.getMessage(), e);
            e.printStackTrace();
        }
        catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }



        return null;
    }
}


