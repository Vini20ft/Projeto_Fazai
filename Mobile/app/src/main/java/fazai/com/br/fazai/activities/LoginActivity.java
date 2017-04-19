package fazai.com.br.fazai.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.Arrays;

import fazai.com.br.fazai.Constante;
import fazai.com.br.fazai.R;

public class LoginActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {

    private LoginButton loginButton;
    private CallbackManager callbackManager;

    private GoogleApiClient googleApiClient;
    private SignInButton signInButton;
    private Button fb;

    public static final int SIGN_IN_CODE = 777;

    // ShredPreferences
    public SharedPreferences sharedPreferences;
    private String senha;

    // fim ShredPreferences

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // ShredPreferences para acessar só uma vez o login

        sharedPreferences = getSharedPreferences(Constante.getPrefName(), MODE_PRIVATE);
        senha = sharedPreferences.getString("senha", "");

            if(senha == "0" || senha == ""){
                VerifyCurrentUser();
                // SharedPreferences
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("senha", "1");
                editor.commit();
                // fim SharedPreferences


                callbackManager = CallbackManager.Factory.create();
                GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                        .requestEmail()
                        .build();

                googleApiClient = new GoogleApiClient.Builder(this).enableAutoManage(this, this)
                        .addApi(Auth.GOOGLE_SIGN_IN_API)
                        .build();

                init();


                loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        goMainScreen();
                    }

                    @Override
                    public void onCancel() {
                        Toast.makeText(getApplicationContext(), R.string.cancel_login, Toast.LENGTH_SHORT).show();
                        // SharedPreferences
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("senha", "0");
                        editor.commit();
                        // fim SharedPreferences

                    }

                    @Override
                    public void onError(FacebookException error) {
                        Toast.makeText(getApplicationContext(), R.string.error_login, Toast.LENGTH_SHORT).show();
                        // SharedPreferences
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("senha", "0");
                        editor.commit();
                        // fim SharedPreferences

                    }

                });

               /* signInButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
                        startActivityForResult(intent, SIGN_IN_CODE);
                    }
                });*/
            } else{
                SharedPreferences sharedPreferences = getSharedPreferences(Constante.getPrefName(), MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("senha", "1");
                editor.commit();

                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            }
        }


    private void VerifyCurrentUser() {
        if (AccessToken.getCurrentAccessToken() != null || (googleApiClient != null && googleApiClient.isConnected())) {
            Toast.makeText(getApplicationContext(), "Teste", Toast.LENGTH_SHORT).show();
            goMainScreen();
        }
    }

    private void goMainScreen() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    public void init() {
        loginButton = (LoginButton) findViewById(R.id.login_button);
        fb = (Button) findViewById(R.id.fb);
        signInButton = (SignInButton) findViewById(R.id.signInButton);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);

        if (requestCode == SIGN_IN_CODE) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }

    private void handleSignInResult(GoogleSignInResult result) {
        if (result.isSuccess()) {
            goMainScreen();
        } else {
            Toast.makeText(getApplicationContext(), R.string.error_login, Toast.LENGTH_SHORT).show();
        }
    }



    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    //metodo click FacebookLogin
    public void onClickFacebook(View v) {
        if (v == fb) {
            loginButton.performClick();
        }
    }

    //metodo click googlePlusLogin
    public void onClickGooglePlus(View v){
        Intent intent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
        startActivityForResult(intent, SIGN_IN_CODE);

    }



}

