package com.hellohasan.smsreader.Activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.NativeExpressAdView;

import com.hellohasan.smsreader.HelperClasses.KeyNameClass;
import com.hellohasan.smsreader.HelperClasses.NetworkCheckingClass;
import com.hellohasan.smsreader.HelperClasses.Preferences;
import com.hellohasan.smsreader.Interface.HttpResponseInterface;
import com.hellohasan.smsreader.R;
import com.hellohasan.smsreader.VolleyNetworkingClass.HttpConnectionClass;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.Key;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements HttpResponseInterface {

    TextView textView;
    EditText phoneNumber;
    EditText apiLinkEditText;
    String phoneNumberString;
    String apiLinkString;
    StringBuilder smsBuilder;

    Preferences preference;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        preference = Preferences.getInstance(this);

        phoneNumberString = preference.getUserPhoneNumber();
        apiLinkString = preference.getApiLink();

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Sending");
        progressDialog.setMessage("SMS Data Sending to server...");

        phoneNumber = (EditText) findViewById(R.id.phoneNumber);
        apiLinkEditText = (EditText) findViewById(R.id.apiLink);
        textView = (TextView) findViewById(R.id.textView);

        phoneNumber.setText(phoneNumberString);
        apiLinkEditText.setText(apiLinkString);

        addVisible();


    }

    private void SmsRead(String phoneAddress) {

        smsBuilder = new StringBuilder();
        final String SMS_URI_INBOX = "content://sms/inbox";
        final String SMS_URI_ALL = "content://sms/";
        try {
            Uri uri = Uri.parse(SMS_URI_INBOX);
            String[] projection = new String[]{"_id", "address", "person", "body", "date", "type"};
            Cursor cur = getContentResolver().query(uri, projection, "address='"+phoneAddress+"'", null, "date desc");
            if (cur.moveToFirst()) {
                int index_Address = cur.getColumnIndex("address");
                int index_Person = cur.getColumnIndex("person");
                int index_Body = cur.getColumnIndex("body");
                int index_Date = cur.getColumnIndex("date");
                int index_Type = cur.getColumnIndex("type");
                do {
                    String strAddress = cur.getString(index_Address);
                    int intPerson = cur.getInt(index_Person);
                    String strbody = cur.getString(index_Body);
                    long longDate = cur.getLong(index_Date);
                    int int_Type = cur.getInt(index_Type);

                    smsBuilder.append("[ ");
                    smsBuilder.append(strAddress + ", ");
                    smsBuilder.append(intPerson + ", ");
                    smsBuilder.append(strbody + ", ");
                    smsBuilder.append(longDate + ", ");
                    smsBuilder.append(int_Type);
                    smsBuilder.append(" ]\n\n");


//                    String pid = cur.getString(0);
//                    Toast.makeText(getApplicationContext(), pid, Toast.LENGTH_LONG).show();
//                    String deleteUri = "content://sms/conversations/" + pid;
//                    getApplicationContext().getContentResolver().delete(Uri.parse(deleteUri), null, null);

                } while (cur.moveToNext());

                if (!cur.isClosed()) {
                    cur.close();
                    cur = null;
                }

                if(NetworkCheckingClass.isNetworkAvailable(this)){

                    progressDialog.show();

                    Map<String, String> data = new HashMap<>();

                    data.put(KeyNameClass.MESSAGE, smsBuilder.toString());

                    System.out.println("Data: "+data);

                    HttpConnectionClass.requestHandler(getApplicationContext(), KeyNameClass.SEND_DATA,data,this);

                }


            } else {
                smsBuilder.append("no result!");
                textView.setText(smsBuilder);
                Toast.makeText(getApplicationContext(), "There are no SMS. Or phone number invalid!", Toast.LENGTH_LONG).show();
            } // end if
        } catch (
                SQLiteException ex
                )

        {
            Log.d("SQLiteException", ex.getMessage());
        }
    }

    public void buttonAction(View view) {

        if(view.getId()==R.id.phoneNumberSubmitButton){

            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

            phoneNumberString = phoneNumber.getText().toString();
            apiLinkString = apiLinkEditText.getText().toString();

            if(phoneNumberString.startsWith("01")){
                phoneNumberString = "+88" + phoneNumberString;
            }
            else if(phoneNumberString.startsWith("8801")){
                phoneNumberString = "+" + phoneNumberString;
            }

            phoneNumber.setText(phoneNumberString);
            apiLinkEditText.setText(apiLinkString);
            preference.setUserPhoneNumber(phoneNumberString);
            preference.setApiLink(apiLinkString);

            Toast.makeText(getApplicationContext(), "Phone and API link saved", Toast.LENGTH_LONG).show();

        }
        else if(view.getId()==R.id.sendDataButton){

            SmsRead(phoneNumberString);
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);

        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.action_developer:
                Intent intentContact = new Intent(MainActivity.this, AboutUsActivity.class);
                startActivity(intentContact);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void addVisible() {

        LinearLayout adLinearLayout=(LinearLayout)findViewById(R.id.adMainActivity);

        if(NetworkCheckingClass.isNetworkAvailable(getApplicationContext())) {

            assert adLinearLayout != null;
            adLinearLayout.setVisibility(View.VISIBLE);

            NativeExpressAdView  mAdView;

            mAdView = (NativeExpressAdView) findViewById(R.id.add_view_native);

            AdRequest adRequest = new AdRequest.Builder()
                    .addTestDevice("0A937A3A7E33A40C17B27564FFA5EBC1") //Hasan's device
                    .addTestDevice("70A137903010A95B2C7EB8FF56D6F0CD") //Sunny's device
                    .build();
            assert mAdView != null;
            mAdView.loadAd(adRequest);

        }
        else {
            assert adLinearLayout != null;
            adLinearLayout.setVisibility(View.GONE);
            Toast.makeText(getApplicationContext(), "Gone", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void actionOnResponse(JSONObject obj) {

        progressDialog.dismiss();

        try {

            if(obj.getBoolean(KeyNameClass.SUCCESS)) {
                textView.setText(smsBuilder);
            }
            else {
                textView.setText("no result!");
            }

            Toast.makeText(getApplicationContext(), obj.getString(KeyNameClass.MESSAGE), Toast.LENGTH_LONG).show();

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
