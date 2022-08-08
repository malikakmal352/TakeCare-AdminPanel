package com.example.takecareadminpanels;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.cardview.widget.CardView;

public class ConnectionReceiver extends BroadcastReceiver {
    Context mContext;

    @Override
    public void onReceive(Context context, Intent intent) {
        mContext = context;
        if(isConnecteds(context)){
            // cardView.setVisibility(cardView.INVISIBLE);
        }else {
            mContext.startActivity(new Intent (mContext.getApplicationContext(),Home_page.class));
            showDialog();
            Toast.makeText(context,"Off",Toast.LENGTH_SHORT).show();
        }
    }

    public boolean isConnecteds(Context context){
        try{
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = cm.getActiveNetworkInfo();
            return (networkInfo != null &&  ((NetworkInfo) networkInfo).isConnected());

        }catch (NullPointerException e){
            e.printStackTrace();
            return false;
        }


    }

    public void showDialog(){
        AlertDialog.Builder builder1 = new AlertDialog.Builder(mContext);
        builder1.setCancelable(false);
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.alert_dialog_layout,null);
        Button btnok = view.findViewById(R.id.okay);
        builder1.setView(view);

        final Dialog dialog = builder1.create();

        btnok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog.dismiss();
                System.exit(1);

                mContext.startActivity(new Intent(mContext.getApplicationContext(),this.getClass()));

            }
        });

        dialog.show();

    }
}
