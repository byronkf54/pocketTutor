package com.example.pockettutor;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class backgroundWorker extends AsyncTask<String, Void, String> {
    private Context context;
    boolean success = false;
    private AlertDialog.Builder builder;
    private String result = "";

    backgroundWorker(Context context) {
        this.context = context;
    }

    @Override
    protected String doInBackground(String ... params) {
        String type = params[0];
        try {
            String login_url = "http://82.14.146.3:80/" + type + ".php";
            URL url = new URL(login_url);

            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);

            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, StandardCharsets.UTF_8));
            String post_data;

            // login/register
            if (params[4].equals("0")) {
                post_data = URLEncoder.encode("UID", "UTF-8") + "=" + URLEncoder.encode(params[1], "UTF-8") + "&" +
                        URLEncoder.encode("Username", "UTF-8") + "=" + URLEncoder.encode(params[2], "UTF-8") + "&" +
                        URLEncoder.encode("Password", "UTF-8") + "=" + URLEncoder.encode(params[3], "UTF-8");
            }
            // lesson request info
            else {
                post_data = URLEncoder.encode("UID", "UTF-8") + "=" + URLEncoder.encode(params[1], "UTF-8") + "&" +
                        URLEncoder.encode("Username", "UTF-8") + "=" + URLEncoder.encode(params[2], "UTF-8") + "&" +
                        URLEncoder.encode("Password", "UTF-8") + "=" + URLEncoder.encode(params[3], "UTF-8");
            }

            bufferedWriter.write(post_data);
            bufferedWriter.flush();
            bufferedWriter.close();
            outputStream.close();

            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.ISO_8859_1));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                result += line;
            }
            bufferedReader.close();
            inputStream.close();
            httpURLConnection.disconnect();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    private void ShowMessageDialog(String str, String buttonText){
        builder.setMessage(str);
        builder.setCancelable(false);
        builder.setNeutralButton(buttonText, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                if (success) {
                    Intent intent = new Intent(context, LessonRequest.class);
                    context.startActivity(intent);
                }
                else {
                    Intent intent = new Intent(context, addStudent.class);
                    context.startActivity(intent);
                }
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    @Override
    protected void onPreExecute() {
        builder = new AlertDialog.Builder(context);
    }

    @Override
    protected void onPostExecute(String result) {
        String str;
        String buttonText;
        if (result.equals("0")) {
            success = false;
            buttonText = "Try again";
            str = "Username already exists please choose another";
        }
        else {
            success = true;
            buttonText = "Login";
            str = result;
        }
        ShowMessageDialog(str, buttonText);
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
