package com.example.ejercicio04elementoscodigo;

import android.content.Intent;
import android.os.Bundle;

import com.example.ejercicio04elementoscodigo.configuraciones.Constantes;
import com.example.ejercicio04elementoscodigo.modelos.Piso;
import com.google.android.material.snackbar.Snackbar;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.view.LayoutInflater;
import android.view.View;


import com.example.ejercicio04elementoscodigo.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    private ActivityMainBinding binding;


    private ArrayList<Piso> pisosList;
    private ActivityResultLauncher<Intent> launcerCrearPiso;
    private ActivityResultLauncher<Intent> launcherModificarPiso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        pisosList = new ArrayList<>();
        inicializaLaunchers();

        setSupportActionBar(binding.toolbar);

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // TODO: lanzar la creación un nuevo objeto
                launcerCrearPiso.launch(new Intent(MainActivity.this, AddPisoActivity.class));

            }
        });
    }

    private void inicializaLaunchers() {
        launcerCrearPiso = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == RESULT_OK){
                            if (result.getData() != null ){
                                if (result.getData().getExtras() != null){
                                    if (result.getData().getExtras().getSerializable(Constantes.PISO) != null){
                                        //Estraigo el inmueble
                                        Piso piso = (Piso) result.getData().getExtras().getSerializable(Constantes.PISO);
                                        //Agrego el inmueble
                                        pisosList.add(piso);
                                        //MuestroLosImuebles
                                        muestraImueblesContenido();
                                    }else {
                                        Toast.makeText(MainActivity.this, "No hay datos", Toast.LENGTH_SHORT).show();
                                    }
                                }else {
                                    Toast.makeText(MainActivity.this, "No hay bundle en el intent", Toast.LENGTH_SHORT).show();
                                }
                            }else {
                                Toast.makeText(MainActivity.this, "No hay intent", Toast.LENGTH_SHORT).show();
                            }
                        }else {
                            Toast.makeText(MainActivity.this, "Ventana canelada", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );

        launcherModificarPiso = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == RESULT_OK) {
                            if (result.getData() != null && result.getData().getExtras() != null && result.getData().getExtras().getSerializable(Constantes.PISO) != null) {
                                Piso piso = (Piso) result.getData().getExtras().getSerializable(Constantes.PISO);
                                int posicion = result.getData().getExtras().getInt(Constantes.POSICION);
                                pisosList.set(posicion, piso);
                                muestraImueblesContenido();
                            }else {
                                if ( result.getData().getExtras() != null){
                                    int posicion = result.getData().getExtras().getInt(Constantes.POSICION);
                                    pisosList.remove(posicion);
                                    muestraImueblesContenido();
                                }
                            }
                        }
                    }
                }
        );
    }

    private void muestraImueblesContenido() {
        binding.contentMain.contenedor.removeAllViews();

        for (int i = 0; i < pisosList.size(); i++) {
            Piso piso = pisosList.get(i);

            View pisoView = LayoutInflater.from(MainActivity.this).inflate(R.layout.piso_model_view, null);

            TextView lblDireccion = pisoView.findViewById(R.id.lblCallePisoViewModel);
            TextView lblNumero = pisoView.findViewById(R.id.lblNumeroPisoViewModel);
            TextView lblCiudad = pisoView.findViewById(R.id.lblCiudadPisoViewModel);
            TextView lblProvincia = pisoView.findViewById(R.id.lblProvinciaPisoViewModel);
            RatingBar rbValoracion = pisoView.findViewById(R.id.rbValoracionPisoViewModel);

            lblDireccion.setText(piso.getDireccion());
            lblNumero.setText(String.valueOf(piso.getNumero()));
            lblCiudad.setText(piso.getCiudad());
            lblProvincia.setText(piso.getProvinvia());
            rbValoracion.setRating(piso.getValoracionPiso());


            int finalI = i;
            pisoView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MainActivity.this, EditPisoActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable(Constantes.PISO, piso);
                    bundle.putInt(Constantes.POSICION, finalI);
                    intent.putExtras(bundle);
                    launcherModificarPiso.launch(intent);
                }
            });

            binding.contentMain.contenedor.addView(pisoView);
        }
    }

}