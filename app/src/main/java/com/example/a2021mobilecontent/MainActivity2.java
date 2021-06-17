package com.example.a2021mobilecontent;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;

public class MainActivity2 extends AppCompatActivity {

    TextView jsonView;
    String ip2 = ((MainActivity3) MainActivity3.context_main).ip;


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        jsonView = findViewById(R.id.jsonObj);
        new JSONTask().execute("/http://\"+ip2+\".ngrok.io");
    }

    public class JSONTask extends AsyncTask<String,String,String> {

        @Override
        protected String doInBackground(String... urls) {
            JSONObject jsonObject = new JSONObject();




            
            try {
                jsonObject.accumulate("user_id", "androidTest");

            } catch (JSONException e) {
                e.printStackTrace();
            }

            try {
                jsonObject.accumulate("name", "yun");
            } catch (JSONException e) {
                e.printStackTrace();
            }


            HttpURLConnection con = null;
            BufferedReader reader = null;
            try {
                URL url = new URL(urls[0]);

                con = (HttpURLConnection)url.openConnection();
                con.connect();

                InputStream stream = con.getInputStream();

                reader = new BufferedReader(new InputStreamReader(stream));

                StringBuffer buffer = new StringBuffer();

                String line = "";

                while ((line = reader.readLine()) != null) buffer.append(line);


                return buffer.toString();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                con.disconnect();
                try {
                    if(reader != null)
                        reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            String[] array=s.split(",");
            for(int i=0;i<array.length;i++){
                System.out.println(array[i]);
            }
            int[] numss = Arrays.asList(array).stream().mapToInt(Integer::parseInt).toArray();
            System.out.println(numss.getClass().getName());
            jsonView.setText(numss[0]+1+"");

            jsonView.setText(s);



        }
    }
}