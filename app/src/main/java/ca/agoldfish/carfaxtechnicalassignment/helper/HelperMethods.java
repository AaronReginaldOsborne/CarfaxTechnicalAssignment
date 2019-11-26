package ca.agoldfish.carfaxtechnicalassignment.helper;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import static ca.agoldfish.carfaxtechnicalassignment.CarListActivity.CALL_DEALER_PERMISSION_CODE;

public class HelperMethods {

    public static void makeCall(Context context, Activity activity, String phoneNumber){

        if(ContextCompat.checkSelfPermission(context,
                Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED){
            Toast.makeText(activity, "Making call" , Toast.LENGTH_SHORT).show();
            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse("tel:"+"1234567890"));
            activity.startActivity(callIntent);
        }
        else{
            requestPhonePermission(context,activity);
        }
    }

    public static void requestPhonePermission(Context context, final Activity activity){
        if(ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.CALL_PHONE)){

            new AlertDialog.Builder(context)
                    .setTitle("Permission needed")
                    .setMessage("This permission is needed to make phone calls")
                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(activity ,new String[]{Manifest.permission.CALL_PHONE}, CALL_DEALER_PERMISSION_CODE);
                        }
                    })
                    .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .create().show();
        }
        else{
            ActivityCompat.requestPermissions(activity,new String[]{Manifest.permission.CALL_PHONE}, CALL_DEALER_PERMISSION_CODE);
        }
    }
}
