package com.example.jomin.hospitality;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class ServerRequests  extends ActionBarActivity {
    ProgressDialog progressDialog;
    public static final int CONNECTION_TIMEOUT = 1000 * 15;
    public static final String SERVER_ADDRESS = "http://ee331c30.ngrok.io/hospitality/PHP/";

    public ServerRequests(Context context) {

        progressDialog = new ProgressDialog(context);
        progressDialog.setCancelable(false);
        progressDialog.setTitle("Processing...");
        progressDialog.setMessage("Please wait...");
    }

    public void storeUserDataInBackground(User user,GetUserCallback userCallBack) {
        progressDialog.show();
        new StoreUserDataAsyncTask(user, userCallBack).execute();
    }

    public void postUserDataInBackground(User user,GetUserCallback userCallBack) {
        progressDialog.show();
        new postUserDataAsyncTask(user, userCallBack).execute();
    }

    public void fetchUserDataAsyncTask(User user, GetUserCallback userCallBack) {
        progressDialog.show();
        new fetchUserDataAsyncTask(user, userCallBack).execute();
    }

    public void getUserProfile(GetUserCallback userCallBack, String email){
        progressDialog.show();
        new getUserProfileDataAsyncTask(userCallBack, email).execute();
    }

    public void postReminderDataInBackground(ReminderObject r, GetUserCallback userCallBack, String email) {
        progressDialog.show();
        new postReminderDataAsyncTask(r, userCallBack, email).execute();
    }

    public void getReminders(GetUserCallback userCallBack, String email){
        progressDialog.show();
        new getRemindersDataAsyncTask(userCallBack, email).execute();
    }

    public void postDeleteReminderDataInBackground(String i, GetUserCallback userCallBack) {
        progressDialog.show();
        new postDeleteReminderDataAsyncTask(i, userCallBack).execute();
    }

    public class getRemindersDataAsyncTask extends AsyncTask<Void, Void,  List<ReminderObject>> {
        ReminderObject obj;
        GetUserCallback userCallBack;
        String email;

        public getRemindersDataAsyncTask( GetUserCallback userCallBack, String email) {
            this.userCallBack = userCallBack;
            this.email = email;
            this.obj = obj;
        }

        @Override
        protected  List<ReminderObject> doInBackground(Void... params) {

            List<ReminderObject> rems = new ArrayList<ReminderObject>();

            HttpParams httpRequestParams = getHttpRequestParams();

            HttpClient client = new DefaultHttpClient(httpRequestParams);
            HttpGet get = new HttpGet(SERVER_ADDRESS + "reminder.php?email=" + email);




            try {
                get.setParams(httpRequestParams);

                HttpResponse httpResponse = client.execute(get);

                HttpEntity entity = httpResponse.getEntity();
                String result = EntityUtils.toString(entity);

                try {
                    JSONArray jObject = new JSONArray(result);


                    for (int i=0; i < jObject.length(); i++)
                    {


                        try {
                            String date = jObject.getJSONObject(i).getString("date");
                            String time = jObject.getJSONObject(i).getString("time");
                            String type = jObject.getJSONObject(i).getString("type");
                            int id = jObject.getJSONObject(i).getInt("id");



                               rems.add(new ReminderObject(type, date, time, id));

                        } catch (JSONException e) {

                        }



                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            return rems;
        }

        private HttpParams getHttpRequestParams() {
            HttpParams httpRequestParams = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(httpRequestParams,
                    CONNECTION_TIMEOUT);
            HttpConnectionParams.setSoTimeout(httpRequestParams,
                    CONNECTION_TIMEOUT);
            return httpRequestParams;
        }

        @Override
        protected void onPostExecute( List<ReminderObject> obj) {
            super.onPostExecute(obj);
            progressDialog.dismiss();
            userCallBack.done(obj);
        }

    }

    public class getUserProfileDataAsyncTask extends AsyncTask<Void, Void, User> {
        User user;
        GetUserCallback userCallBack;
        String email;

        public getUserProfileDataAsyncTask( GetUserCallback userCallBack, String email) {
            this.userCallBack = userCallBack;
            this.email = email;
        }

        @Override
        protected User doInBackground(Void... params) {
            ArrayList<NameValuePair> dataToSend = new ArrayList<>();


            HttpParams httpRequestParams = getHttpRequestParams();

            HttpClient client = new DefaultHttpClient(httpRequestParams);
            HttpGet get = new HttpGet(SERVER_ADDRESS + "profile.php?email=" + email);




            try {
                get.setParams(httpRequestParams);

                HttpResponse httpResponse = client.execute(get);

                HttpEntity entity = httpResponse.getEntity();
                String result = EntityUtils.toString(entity);
                JSONObject jObject = new JSONObject(result);

                if (jObject.length() != 0){
                    String name = jObject.getString("name");
                    String emailaddress = jObject.getString("emailaddress");
                    String password = jObject.getString("password");
                    String gender = jObject.getString("gender");
                    String contact = jObject.getString("contact");
                    user = new User(name,emailaddress,password, gender, contact );
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            return user;
        }

        private HttpParams getHttpRequestParams() {
            HttpParams httpRequestParams = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(httpRequestParams,
                    CONNECTION_TIMEOUT);
            HttpConnectionParams.setSoTimeout(httpRequestParams,
                    CONNECTION_TIMEOUT);
            return httpRequestParams;
        }

        @Override
        protected void onPostExecute(User returnedUser) {
            super.onPostExecute(returnedUser);
            progressDialog.dismiss();
            userCallBack.done(returnedUser);
        }

    }

    /**
     * parameter sent to task upon execution progress published during
     * background computation result of the background computation
     */

    public class StoreUserDataAsyncTask extends AsyncTask<Void, Void, Void> {
        User user;
        GetUserCallback userCallBack;

        public StoreUserDataAsyncTask(User user, GetUserCallback userCallBack) {
            this.user = user;
            this.userCallBack = userCallBack;
        }

        @Override
        protected Void doInBackground(Void... params) {
            ArrayList<NameValuePair> dataToSend = new ArrayList<>();
            dataToSend.add(new BasicNameValuePair("fullname", user.fullname));
            dataToSend.add(new BasicNameValuePair("email_address", user.emailaddress));
            dataToSend.add(new BasicNameValuePair("user_password", user.password));

            HttpParams httpRequestParams = getHttpRequestParams();

            HttpClient client = new DefaultHttpClient(httpRequestParams);
            HttpPost post = new HttpPost(SERVER_ADDRESS + "Register.php");




            try {
                post.setEntity(new UrlEncodedFormEntity(dataToSend));
                client.execute(post);
            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

        private HttpParams getHttpRequestParams() {
            HttpParams httpRequestParams = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(httpRequestParams,
                    CONNECTION_TIMEOUT);
            HttpConnectionParams.setSoTimeout(httpRequestParams,
                    CONNECTION_TIMEOUT);
            return httpRequestParams;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            progressDialog.dismiss();
            userCallBack.done();
        }

    }

    public class fetchUserDataAsyncTask extends AsyncTask<Void, Void, User> {
        User user;
        GetUserCallback userCallBack;

        public fetchUserDataAsyncTask(User user, GetUserCallback userCallBack) {
            this.user = user;
            this.userCallBack = userCallBack;
        }

        @Override
        protected User doInBackground(Void... params) {
            ArrayList<NameValuePair> dataToSend = new ArrayList<>();
            dataToSend.add(new BasicNameValuePair("email", user.emailaddress));
            dataToSend.add(new BasicNameValuePair("password", user.password));

            HttpParams httpRequestParams = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(httpRequestParams,
                    CONNECTION_TIMEOUT);
            HttpConnectionParams.setSoTimeout(httpRequestParams,
                    CONNECTION_TIMEOUT);

            HttpClient client = new DefaultHttpClient(httpRequestParams);
            HttpPost post = new HttpPost(SERVER_ADDRESS + "UserData.php");

            User returnedUser = null;

            try {
                post.setEntity(new UrlEncodedFormEntity(dataToSend));
                HttpResponse httpResponse = client.execute(post);

                HttpEntity entity = httpResponse.getEntity();
                String result = EntityUtils.toString(entity);
                JSONObject jObject = new JSONObject(result);

                if (jObject.length() != 0){
                    String name = jObject.getString("name");
                    String emailaddress = jObject.getString("emailaddress");
                    String password = jObject.getString("password");
                    String gender = jObject.getString("gender");
                    returnedUser = new User(name,emailaddress,password, gender );
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            return returnedUser;
        }

        @Override
        protected void onPostExecute(User returnedUser) {
            super.onPostExecute(returnedUser);
            progressDialog.dismiss();
            userCallBack.done(returnedUser);
        }
    }

    public class postUserDataAsyncTask extends AsyncTask<Void, Void, Void> {
        User user;
        GetUserCallback userCallBack;

        public postUserDataAsyncTask(User user, GetUserCallback userCallBack) {
            this.user = user;
            this.userCallBack = userCallBack;
        }

        @Override
        protected Void doInBackground(Void... params) {
            ArrayList<NameValuePair> dataToSend = new ArrayList<>();
            dataToSend.add(new BasicNameValuePair("gender", user.gender));
            dataToSend.add(new BasicNameValuePair("contact", user.contact));
            dataToSend.add(new BasicNameValuePair("email", user.emailaddress));
           // dataToSend.add(new BasicNameValuePair("language", user.languyage));
            // dataToSend.add(new BasicNameValuePair("contact", user.contact));

            HttpParams httpRequestParams = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(httpRequestParams,
                    CONNECTION_TIMEOUT);
            HttpConnectionParams.setSoTimeout(httpRequestParams,
                    CONNECTION_TIMEOUT);

            HttpClient client = new DefaultHttpClient(httpRequestParams);
            HttpPost post = new HttpPost(SERVER_ADDRESS + "ProfilePost.php");

            try {
                post.setEntity(new UrlEncodedFormEntity(dataToSend));
                HttpResponse httpResponse = client.execute(post);

                HttpEntity entity = httpResponse.getEntity();

                if (httpResponse.getStatusLine().getStatusCode() == 200)
                    return null;

            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void v) {
            progressDialog.dismiss();
            userCallBack.done();
        }
    }






    public class postReminderDataAsyncTask extends AsyncTask<Void, Void, Void> {
        ReminderObject r;
        GetUserCallback userCallBack;
        String email;

        public postReminderDataAsyncTask(ReminderObject r, GetUserCallback userCallBack, String email) {
            this.r = r;
            this.userCallBack = userCallBack;
            this.email = email;
        }

        @Override
        protected Void doInBackground(Void... params) {
            ArrayList<NameValuePair> dataToSend = new ArrayList<>();
            dataToSend.add(new BasicNameValuePair("type", r.type));
            dataToSend.add(new BasicNameValuePair("date", r.date));
            dataToSend.add(new BasicNameValuePair("time", r.time));
            dataToSend.add(new BasicNameValuePair("email", email));


            HttpParams httpRequestParams = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(httpRequestParams,
                    CONNECTION_TIMEOUT);
            HttpConnectionParams.setSoTimeout(httpRequestParams,
                    CONNECTION_TIMEOUT);

            HttpClient client = new DefaultHttpClient(httpRequestParams);
            HttpPost post = new HttpPost(SERVER_ADDRESS + "ReminderPost.php");

            try {
                post.setEntity(new UrlEncodedFormEntity(dataToSend));
                HttpResponse httpResponse = client.execute(post);

                HttpEntity entity = httpResponse.getEntity();

                if (httpResponse.getStatusLine().getStatusCode() == 200)
                    return null;

            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void v) {
            progressDialog.dismiss();
            userCallBack.done();
        }
    }



    public class postDeleteReminderDataAsyncTask extends AsyncTask<Void, Void, Void> {
        String r;
        GetUserCallback userCallBack;

        public postDeleteReminderDataAsyncTask(String r, GetUserCallback userCallBack) {
            this.r = r;
            this.userCallBack = userCallBack;
        }

        @Override
        protected Void doInBackground(Void... params) {
            ArrayList<NameValuePair> dataToSend = new ArrayList<>();
            dataToSend.add(new BasicNameValuePair("id", r));


            HttpParams httpRequestParams = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(httpRequestParams,
                    CONNECTION_TIMEOUT);
            HttpConnectionParams.setSoTimeout(httpRequestParams,
                    CONNECTION_TIMEOUT);

            HttpClient client = new DefaultHttpClient(httpRequestParams);
            HttpPost post = new HttpPost(SERVER_ADDRESS + "DeleteReminder.php");

            try {
                post.setEntity(new UrlEncodedFormEntity(dataToSend));
                HttpResponse httpResponse = client.execute(post);

                HttpEntity entity = httpResponse.getEntity();

                if (httpResponse.getStatusLine().getStatusCode() == 200)
                    return null;

            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void v) {
            progressDialog.dismiss();
            userCallBack.done();
        }
    }

}