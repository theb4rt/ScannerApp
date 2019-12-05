package com.b4rt.scannerapp.ui.wordpress;

import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.b4rt.scannerapp.Activities.MainActivity;
import com.b4rt.scannerapp.Activities.menuActivity;
import com.b4rt.scannerapp.Models.PersonaByIdModel;
import com.b4rt.scannerapp.Models.ResponseOkModel;
import com.b4rt.scannerapp.R;
import com.b4rt.scannerapp.Service.ApiServiceGenerator;
import com.b4rt.scannerapp.Service.ApiServices;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WordpressFragment extends Fragment implements View.OnClickListener {

    private WordpressViewModel mViewModel;
    private Integer tipo;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        //  SharedPreferences preferences = this.getActivity().getSharedPreferences("pref", getContext().MODE_PRIVATE);
        //final String token = getActivity().getIntent().getExtras().getString("imagen_nombre");

        final Integer user_id = getActivity().getIntent().getExtras().getInt("user_id");
        final String token = getActivity().getIntent().getExtras().getString("token");


        mViewModel =
                ViewModelProviders.of(this).get(WordpressViewModel.class);
        View root = inflater.inflate(R.layout.fragment_wordpress, container, false);


        Button btn_launch_attack = root.findViewById(R.id.btn_wordpress_scan);
        RadioGroup radioGroup = (RadioGroup) root.findViewById(R.id.radio_btn_wordpress);
        EditText txtUrl = (EditText) root.findViewById(R.id.txt_wp_scan);
        TextView txt_output_web_Scan = (TextView)root.findViewById(R.id.txt_output_web_Scan);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.radio_enum_user:
                        tipo = 1;
                        break;
                    case R.id.radio_enum_themes:
                        tipo = 2;
                        break;
                    case R.id.radio_enum_plugins:
                        tipo = 3;
                        break;
                    case R.id.radio_agressive:
                        tipo = 3;


                }

            }
        });

        btn_launch_attack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String urltoscanwp = txtUrl.getText().toString();

                Log.d("CKICK", "tipo es: " + tipo);

                ApiServices service = ApiServiceGenerator.createService(ApiServices.class);
                service.launch_scan(tipo, urltoscanwp, token, user_id).enqueue(new Callback<ResponseOkModel>() {
                    @Override
                    public void onResponse(Call<ResponseOkModel> call, Response<ResponseOkModel> response) {

                        Log.w("full_json ", new GsonBuilder().setPrettyPrinting().create().toJson(response));
                        if (response.isSuccessful()) {
                            Log.w("full_json ", new GsonBuilder().setPrettyPrinting().create().toJson(response));

                            //  Log.w("full_json ", new GsonBuilder().setPrettyPrinting().create().toJson(response));


                            ResponseOkModel responseOkModels = response.body();

                            String imagen_nombre = responseOkModels.getMessage();

                            String data = responseOkModels.getData().getPath_output_wordpress();

                            String full_url = ApiServices.API_BASE_URL + "media/Outputs/wpscan/" + data;

                            Toast.makeText(getContext(), "URL, Reporte: "+full_url,
                                    Toast.LENGTH_LONG).show();

                            txt_output_web_Scan.setText(full_url);


                        }

                    }


                @Override
                public void onFailure (Call < ResponseOkModel > call, Throwable t){


                    Log.e("AQUII", "error:" + t.getMessage());

                }
            });


        }
    });


        return root;
}

    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.btn_wordpress_scan) {


        }


    }
}
