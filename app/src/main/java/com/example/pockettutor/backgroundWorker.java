package com.example.pockettutor;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

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
    public String result = "";
    private String UID;
    private String LoginRegister;

    backgroundWorker(Context context) {
        this.context = context;
    }

    @Override
    protected String doInBackground(String... params) {
        String type = params[0];
        UID = params[1];
        LoginRegister = params[4];
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
            if (params[4].equals("0") || params[4].equals("1")) {
                post_data = URLEncoder.encode("UID", "UTF-8") + "=" + URLEncoder.encode(UID, "UTF-8") + "&" +
                        URLEncoder.encode("Username", "UTF-8") + "=" + URLEncoder.encode(params[2], "UTF-8") + "&" +
                        URLEncoder.encode("Password", "UTF-8") + "=" + URLEncoder.encode(params[3], "UTF-8");
            }

            // lesson request info
            else if (params[4].equals("2")) {
                post_data = URLEncoder.encode("UID", "UTF-8") + "=" + URLEncoder.encode(UID, "UTF-8") + "&" +
                        URLEncoder.encode("Subject", "UTF-8") + "=" + URLEncoder.encode(params[2], "UTF-8") + "&" +
                        URLEncoder.encode("Topic", "UTF-8") + "=" + URLEncoder.encode(params[3], "UTF-8");
            } else {
                post_data = "";
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


    private void ShowMessageDialog(String str, String buttonText) {
        builder.setMessage(str);
        builder.setCancelable(false);
        builder.setNeutralButton(buttonText, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                if (success) {
                    // Login Register
                    switch (LoginRegister) {
                        // Student Login/Register
                        case "0": {
                            Intent intent = new Intent(context, LessonRequest.class);
                            context.startActivity(intent);
                            UserLocalStore ULS = new UserLocalStore(context);
                            ULS.storeData(UID);
                            break;
                        }
                        // Tutor Login/Register
                        case "1": {
                            Intent intent = new Intent(context, LessonDisplay.class);
                            context.startActivity(intent);
                            UserLocalStore ULS = new UserLocalStore(context);
                            ULS.storeData(UID);
                            break;
                        }
                        // Lesson Request
                        case "2": {
                            Intent intent = new Intent(context, LessonRequest.class);
                            context.startActivity(intent);
                            break;
                        }
                    }
                } else {
                    Intent intent = new Intent(context, context.getClass());
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
        buttonText = "Next";
        if (result.equals("0")) {
            success = false;
            if (LoginRegister.equals("0") || LoginRegister.equals("1")) {
                str = "Username already exists please choose another";
            } else {
                str = "You already have a lesson request.";
            }
        } else if (result.equals("-1")) {
            success = false;
            str = "Username or password incorrect";
        } else if (result.equals("-2")) {
            success = false;
            str = "No requests available at the moment";
        } else {
            success = true;
            str = result;
        }
        if (!LoginRegister.equals("3")) {
            ShowMessageDialog(str, buttonText);
        }
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
