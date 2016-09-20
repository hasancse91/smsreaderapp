package com.hellohasan.smsreader.Activity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Toast;



import com.hellohasan.smsreader.R;

public class AboutUsActivity extends AppCompatActivity {

    private AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.002F);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                super.onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void callToDeveloper(View view) {
        view.startAnimation(buttonClick);

        new android.app.AlertDialog.Builder(this)

                .setTitle("ডেভেলপারদের সাথে ফোনালাপ")
                .setMessage("এই এপের ডেভেলপারদের সাথে ফোনে কথা বলতে চান?")
                .setPositiveButton("হ্যাঁ ", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        Intent callIntent = new Intent(Intent.ACTION_CALL);
                        callIntent.setData(Uri.parse("tel:01521101145"));
                        if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                            // TODO: Consider calling
                            //    ActivityCompat#requestPermissions
                            // here to request the missing permissions, and then overriding
                            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                            //                                          int[] grantResults)
                            // to handle the case where the user grants the permission. See the documentation
                            // for ActivityCompat#requestPermissions for more details.
                            return;
                        }
                        startActivity(callIntent);

                    }
                })
                .setNegativeButton("না", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // do nothing
                        dialog.dismiss();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();



    }

    public void FacebookActionMegaminds(View view){
        view.startAnimation(buttonClick);
        try{
            Intent webIntent=new Intent(Intent.ACTION_VIEW);
            String Url = "https://www.facebook.com/megamindsweb/";
            webIntent.setData(Uri.parse(Url));
            startActivity(webIntent);
        }
        catch (Exception e){
            Toast.makeText(getApplicationContext(), "Something wrong!", Toast.LENGTH_SHORT).show();

        }

    }
    public void emailAction(View view){
        view.startAnimation(buttonClick);

        Log.i("Send email", "");
//        String[] TO = {"hasan_cse91@yahoo.com","sunny_mhs@hotmail.com","chistyinfo@gmail.com","shakirahmed1996@gmail.com"};
        String[] CC = {""};
        Intent emailIntent = new Intent(Intent.ACTION_SEND);

        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");
//        System.out.println("dhuru ja   "+TO[0]);
        if(view.getId()==R.id.emailOakTeam){
            String[] TO = {"megamindscobd@gmail.com"};
            emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        }


        //emailIntent.putExtra(Intent.EXTRA_CC, CC);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "About of App");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Email Body");

        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            finish();
            Log.i("E-mail sent!", "");
        }
        catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }
    }
    public void WebActionMegaminds(View view) {
        view.startAnimation(buttonClick);
        try {
            Intent webIntent = new Intent(Intent.ACTION_VIEW);
            String Url = null;
            Url = "http://megaminds.co/";
            webIntent.setData(Uri.parse(Url));
            startActivity(webIntent);
        } catch (Exception e) {
            Toast.makeText(this, "Something wrong!", Toast.LENGTH_SHORT).show();

        }
    }
    public void playStoreShowAction(View view){
        view.startAnimation(buttonClick);
        try{
            String Url = "https://play.google.com/store/apps/developer?id=MEGAMINDS+Web+%26+IT+Solutions";

            Intent webIntent=new Intent(Intent.ACTION_VIEW);

            webIntent.setData(Uri.parse(Url));
            startActivity(webIntent);
        }
        catch (Exception e){
            Toast.makeText(this,"Something wrong. Report to Developer!", Toast.LENGTH_SHORT).show();
        }

    }

    public void appBajarShowAction(View view){
        view.startAnimation(buttonClick);
        try{
            String Url = "https://www.appbajar.com/bn/profile/megaminds/apps";
            Intent webIntent=new Intent(Intent.ACTION_VIEW);

            webIntent.setData(Uri.parse(Url));
            startActivity(webIntent);
        }
        catch (Exception e){
            Toast.makeText(this,"Something wrong. Report to Developer!", Toast.LENGTH_SHORT).show();
        }

    }
}
