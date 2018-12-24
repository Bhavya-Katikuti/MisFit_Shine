package com.example.mechsrit.misfit_shine;


import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private static final int RC_SIGN_IN = 1991;
    private static final String TAG = MainActivity.class.getName();
    public  static GoogleSignInClient mGoogleSignInClient;
    SignInButton signInButton;
    SharedPreferences spf;
    private FirebaseAuth mAuth;
    ConnectivityManager checkConnection;
   // public static GoogleApiClient mGoogleApiClient;
    NetworkInfo networkInfo;
    AlertDialog.Builder builder;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spf = getSharedPreferences(getString(R.string.sign_in_det), MODE_PRIVATE);


            signInButton = findViewById(R.id.button_sign_in);
            signInButton.setEnabled(true);

            mAuth = FirebaseAuth.getInstance();


            GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestIdToken(getString(R.string.default_web_client_id))
                    .requestEmail()
                    .build();


            mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
            signInButton.setOnClickListener(this);


    }

    @Override
    protected void onStart() {
        super.onStart();

        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        Intent intent=getIntent();
        if (intent.hasExtra("s")) {
            mAuth = FirebaseAuth.getInstance();
            mAuth.signOut();
            mGoogleSignInClient.signOut();
            SharedPreferences.Editor editor=spf.edit();
            editor.clear();

        }else if (account != null) {
                Toast.makeText(this, getString(R.string.welcome_back) + account.getDisplayName(), Toast.LENGTH_SHORT).show();
                SharedPreferences.Editor e = spf.edit();
                e.putString("user_name", account.getDisplayName());
                e.putString("user_email", account.getEmail());
                e.putString("user_photo", account.getPhotoUrl().toString());
                e.apply();
                startActivity(new Intent(this, HomeActivity.class));
                finish();
            }


    }

    @Override
    public void onClick(View v) {

        checkConnection = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        networkInfo = checkConnection.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            signIn();
        }
        else {
            noInternet();
        }


    }

    public void noInternet() {
        builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.network_title);
        builder.setMessage(R.string.network_msg);
        builder.setPositiveButton(R.string.network_positive, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        builder.show();

    }


        private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }





    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == RC_SIGN_IN) {

            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> task) {
        spf = getSharedPreferences(getString(R.string.sign_in_det), MODE_PRIVATE);

        try {
            GoogleSignInAccount account = task.getResult(ApiException.class);
            SharedPreferences.Editor e = spf.edit();
            e.putString("user_name", account.getDisplayName());
            e.putString("user_email", account.getEmail());
            e.putString("user_photo", account.getPhotoUrl().toString());
            e.apply();
            startActivity(new Intent(MainActivity.this, HomeActivity.class));
            firebaseAuthWithGoogle(account);
        } catch (ApiException e) {

            Toast.makeText(this, getString(R.string.fail_msg), Toast.LENGTH_SHORT).show();
        }
    }



    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(MainActivity.this, user.getDisplayName() + getString(R.string.success_msg), Toast.LENGTH_SHORT).show();

                        } else {
                            Log.w(TAG, getString(R.string.sign_fail), task.getException());
                        }
                    }
                });
        finish();
    }

    public void next(View view) {
        Intent intent=new Intent(MainActivity.this,HomeActivity.class);
        startActivity(intent);
    }



}


