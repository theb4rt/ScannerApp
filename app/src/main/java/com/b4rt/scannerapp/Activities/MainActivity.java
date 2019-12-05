package com.b4rt.scannerapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.b4rt.scannerapp.Metodos;
import com.b4rt.scannerapp.Models.LoginModel;
import com.b4rt.scannerapp.Models.Persona;
import com.b4rt.scannerapp.R;
import com.b4rt.scannerapp.Service.ApiServiceGenerator;
import com.b4rt.scannerapp.Service.ApiServices;
import com.b4rt.scannerapp.ui.profile.ProfileFragment;
import com.google.gson.GsonBuilder;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.b4rt.scannerapp.Models.PersonaByIdModel;

import androidx.annotation.NonNull;


import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btn_login;
    private List<Persona> personas;
    private static final String TAG = MainActivity.class.getSimpleName();
    private Integer persona_id;
    TextView edittxt_user;
    TextView edittxt_passwd;
    private EditText editTextUser, EditTextPasswd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edittxt_user = (TextView) findViewById(R.id.txtUsername);
        edittxt_passwd = (TextView) findViewById(R.id.txtPassword);
        btn_login = (Button) findViewById(R.id.btn_login);

//        username = edittxt_user.getText().toString().trim();
//        password = edittxt_passwd.getText().toString().trim();


        Dexter.withActivity(this)
                .withPermissions(
                        Manifest.permission.CAMERA,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.READ_EXTERNAL_STORAGE
                ).withListener(new MultiplePermissionsListener() {

            @Override
            public void onPermissionsChecked(MultiplePermissionsReport report) {

                Dexter.withActivity(MainActivity.this)
                        .withPermissions(
                                Manifest.permission.CAMERA,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                Manifest.permission.READ_EXTERNAL_STORAGE
                        ).withListener(new MultiplePermissionsListener() {


                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {


                        //LanzandoHome
                        if (isConnect(MainActivity.this)) {

                            findViewById(R.id.btn_login).setOnClickListener(MainActivity.this);


                        } else {
                            no_red();
                        }

                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {

                        noPermissions();


                    }
                }).check();


            }

            @Override
            public void onPermissionRationaleShouldBeShown(List<com.karumi.dexter.listener.PermissionRequest> permissions, PermissionToken token) {

                token.continuePermissionRequest();


            }
        }).check();


    }

    private void initialize() {

        ApiServices service = ApiServiceGenerator.createService(ApiServices.class);
        service.getPersonas().enqueue(new Callback<List<Persona>>() {


            @Override
            public void onResponse(Call<List<Persona>> call, Response<List<Persona>> response) {

                Log.d(TAG, "estoyaqui3: ");

                if (response.isSuccessful()) {

                    List<Persona> personas = response.body();

                    Log.d(TAG, "personas: " + personas);
                    Log.w("full_json ", new GsonBuilder().setPrettyPrinting().create().toJson(response));


                }

            }

            @Override
            public void onFailure(Call<List<Persona>> call, Throwable t) {
                Log.d(TAG, "estoyaqui5: ");
                Log.e(TAG, "error:" + t.getMessage());

            }
        });

    }


    protected void open_home() {

        String username = edittxt_user.getText().toString().trim();
        String password = edittxt_passwd.getText().toString().trim();

        ApiServices login = ApiServiceGenerator.createService(ApiServices.class);

        Log.d(TAG, "estoyaqui6: " + username + " : " + password);

        login.user_login(username, password).enqueue(new Callback<LoginModel>() {

            @Override
            public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {


                if (response.code() == 200) {

                    LoginModel login = response.body();
                    //  Log.w("full_json ", new GsonBuilder().setPrettyPrinting().create().toJson(response));
                    // String json_response = new GsonBuilder().setPrettyPrinting().create().toJson(response);
                    String token = login.getData().getToken();
                    Integer id = login.getData().getUser().getId();

                    ApiServices getPersona = ApiServiceGenerator.createService(ApiServices.class);
                    //Log.d(TAG, "estoyaqui55: ");

                    getPersona.send_user_id(id).enqueue(new Callback<List<PersonaByIdModel>>() {
                        @Override
                        public void onResponse(Call<List<PersonaByIdModel>> call, Response<List<PersonaByIdModel>> response) {


                            if (response.isSuccessful()) {
                                Log.w("full_json ", new GsonBuilder().setPrettyPrinting().create().toJson(response));

                                //  Log.w("full_json ", new GsonBuilder().setPrettyPrinting().create().toJson(response));


                                List<PersonaByIdModel> personaByIdModel = response.body();

                                String imagen_nombre = personaByIdModel.get(0).getImagen().getImagen_nombre();
                                String nombre = personaByIdModel.get(0).getNombre();
                                String apellido = personaByIdModel.get(0).getApellido();
                                Integer user_id = personaByIdModel.get(0).getUser_id();

                                String correo = personaByIdModel.get(0).getCorreo();
                                String musica = personaByIdModel.get(0).getMusica();
                                String imagen_path = ApiServices.API_BASE_URL + personaByIdModel.get(0).getImagen().getImagen_path();

                                //String url = ApiServices.API_BASE_URL  +especialidad.getMed_colegiatura();

                                //  Picasso.get().load(url).into(holder.foto_especialidad);
                                SharedPreferences preferences = getSharedPreferences("AuthScanner", MODE_PRIVATE);

                                SharedPreferences.Editor editor = preferences.edit();
                                editor.putString("token", token);
                                editor.putInt("user_id", user_id);
                                editor.apply();




                                Intent intent = new Intent(getApplicationContext(), menuActivity.class);
                                intent.putExtra("imagen_nombre", imagen_nombre);
                                intent.putExtra("nombre", nombre);
                                intent.putExtra("apellido", apellido);
                                intent.putExtra("user_id", user_id);
                                intent.putExtra("correo", correo);
                                intent.putExtra("imagen_path", imagen_path);
                                intent.putExtra("token", token);
                                intent.putExtra("musica", musica);
                                startActivity(intent);


                            } else {

                                Toast.makeText(MainActivity.this, "Mmm Mirá con la cara que te mira Conan, para mi que no tienes cuenta",
                                        Toast.LENGTH_LONG).show();
                            }

                        }

                        @Override
                        public void onFailure(Call<List<PersonaByIdModel>> call, Throwable t) {

                            Log.d(TAG, "estoyaqui77: " + t.getMessage());


                        }
                    });


                } else if (response.code() == 401) {


                    Toast.makeText(MainActivity.this, "Mmm Mirá con la cara que te mira Conan, para mi que no tienes cuenta",
                            Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<LoginModel> call, Throwable t) {

                Log.d(TAG, "estoyaqui7: ");
                Log.e(TAG, "error:" + t.getMessage());

            }
        });


    }

    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.btn_login) {

            open_home();

        }


    }


    protected void noPermissions() {

        Metodos metodos = new Metodos(this);
        AlertDialog dialog = metodos.alerta("Mensaje", "Por Favor, Acepte los permisos");
        dialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Aceptar",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        Intent intent = new Intent(MainActivity.this, MainActivity.class);

                        MainActivity main = new MainActivity();
                        main.startActivity(intent);
                        main.finishAffinity();

                        finish();
                    }
                });
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }

    protected void no_red() {
        Metodos metodos = new Metodos(this);
        AlertDialog dialog = metodos.alerta("Mensaje", "Por favor, conéctese a una red.");
        dialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Aceptar",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        finish();
                    }
                });
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }

    protected static boolean isConnect(Activity activity) {
        boolean flag = false;

        ConnectivityManager cwjManager = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cwjManager.getActiveNetworkInfo() != null)
            flag = cwjManager.getActiveNetworkInfo().isConnected();

        return flag;
    }

    private void get_persona_by_id(Integer persona_id) {
        Log.d(TAG, "estoyaqui2: ");


        ApiServices service = ApiServiceGenerator.createService(ApiServices.class);


        service.getPersonasById(persona_id).enqueue(new Callback<List<Persona>>() {


            @Override
            public void onResponse(Call<List<Persona>> call, Response<List<Persona>> response) {

                Log.d(TAG, "estoyaqui3: ");

                if (response.isSuccessful()) {

                    List<Persona> personas = response.body();


                    Log.d(TAG, "personas: " + personas);
                    Log.w("full_json ", new GsonBuilder().setPrettyPrinting().create().toJson(response));


                }

            }

            @Override
            public void onFailure(Call<List<Persona>> call, Throwable t) {
                Log.d(TAG, "estoyaqui5: ");
                Log.e(TAG, "error:" + t.getMessage());

            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}