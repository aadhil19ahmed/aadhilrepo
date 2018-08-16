package com.example.aadhilahmed.mapboxdeliveries1.Activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.aadhilahmed.mapboxdeliveries1.LocationFetcher;
import com.example.aadhilahmed.mapboxdeliveries1.R;

import java.util.HashMap;

public class LoginActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Button mSignIn;
    private EditText mEmail,mPassword;
    private Spinner spinner;
    private String loginType;
    HashMap<String,String> managerDetails=new HashMap<>();
    HashMap<String,String> spDetails=new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        managerDetails.put("aadhil@gmail.com","123456");
        spDetails.put("ahmed@gmail.com","qwerty");

        //checks for internet connection and shows alert dialog
        if(isOnline(this)){

        }else {
            //Alert dialog code
            try{
                AlertDialog dialog=new AlertDialog.Builder(LoginActivity.this).create();

                dialog.setTitle("Error");
                dialog.setMessage("Internet is not available");
                dialog.setButton(DialogInterface.BUTTON_POSITIVE,"Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
                dialog.show();
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        //Attaching to its views
        mEmail=(EditText)findViewById(R.id.edtEmail);
        mPassword=(EditText)findViewById(R.id.edtPassword);
        mSignIn=(Button)findViewById(R.id.btnLogin);
        spinner=(Spinner)findViewById(R.id.spinnerLoginTpe);

        ArrayAdapter adapter=new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,getResources().getStringArray(R.array.login_types));
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
        init();

//        mSignIn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent=new Intent(LoginActivity.this,DeliveryBoyActivity.class);
//                startActivity(intent);
//            }
//        });
    }

    public boolean isOnline(Context context) {
        ConnectivityManager conMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = conMgr.getActiveNetworkInfo();

        if(netInfo == null || !netInfo.isConnected() || !netInfo.isAvailable()){
            return false;
        }
        return true;
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mEmail.getText().clear();
        mPassword.getText().clear();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    private void init() {
        mSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                String txtEmail=mEmail.getText().toString();
                String txtPassword=mPassword.getText().toString();

                if(!txtEmail.equals("")&&!txtPassword.equals("")) {
                    if (loginType.equals("Manager")) {
                        if (managerDetails.containsKey(txtEmail)) {
                            if (txtPassword.equals(managerDetails.get(txtEmail))) {
                                intent = new Intent(LoginActivity.this, ManagerActivity.class);
                                startActivity(intent);
                                Toast.makeText(LoginActivity.this,R.string.loginSuccess,Toast.LENGTH_SHORT).show();
                                finish();
                            }else {
                                Toast.makeText(LoginActivity.this,R.string.invaliduserAlert,Toast.LENGTH_SHORT).show();
                            }
                        }else {
                            Toast.makeText(LoginActivity.this,R.string.invaliduserAlert,Toast.LENGTH_SHORT).show();
                        }
                    }
                    else if (loginType.equals("deliveryboy")) {
                        if(spDetails.containsKey(txtEmail)) {
                            if(txtPassword.equals(spDetails.get(txtEmail))) {
                                intent = new Intent(LoginActivity.this, DeliveryBoyActivity.class);
                                startActivity(intent);
                                Toast.makeText(LoginActivity.this,R.string.loginSuccess,Toast.LENGTH_SHORT).show();
                                finish();
                            }else {
                                Toast.makeText(LoginActivity.this,R.string.invaliduserAlert,Toast.LENGTH_SHORT).show();

                            }
                        }
                        else {
                            Toast.makeText(LoginActivity.this,R.string.invaliduserAlert,Toast.LENGTH_SHORT).show();
                        }
                    }

                }else {
                    Toast.makeText(LoginActivity.this,R.string.textemptyAlert,Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            switch (position){
                case 0:
                    loginType="Manager";
                    break;
                case 1:
                    loginType="deliveryboy";
                    break;
            }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        Toast.makeText(this,R.string.toastEmptySpinnerAlert,Toast.LENGTH_SHORT).show();
    }
}
