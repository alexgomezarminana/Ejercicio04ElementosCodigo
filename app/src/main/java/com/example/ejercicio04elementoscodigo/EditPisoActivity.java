package com.example.ejercicio04elementoscodigo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.ejercicio04elementoscodigo.configuraciones.Constantes;
import com.example.ejercicio04elementoscodigo.databinding.ActivityEditPisoBinding;
import com.example.ejercicio04elementoscodigo.modelos.Piso;

public class EditPisoActivity extends AppCompatActivity {

    private ActivityEditPisoBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);

        binding = ActivityEditPisoBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        // Todas las activities tienen un Intent que las crea
        Intent intentDelMain = getIntent();
        Bundle bundle = intentDelMain.getExtras();
        Piso piso = (Piso) bundle.getSerializable(Constantes.PISO);
        Log.d("INMU", piso.toString());

        //PASO 2: Mostrar los datos del INMU en
        binding.txtCiudadEditPiso.setText(piso.getCiudad());
        binding.txtNumeroEditPiso.setText(String.valueOf(piso.getNumero()));
        binding.txtCPEditPiso.setText(piso.getCp());
        binding.txtProvinciaEditPiso.setText(piso.getProvinvia());
        binding.txtDireccionEditPiso.setText(piso.getDireccion());
        binding.rbValoracionEditPiso.setRating(piso.getValoracionPiso());

        binding.BtnEditarEditPiso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Piso inmuebleUpdated = crearInmueble();
                if (inmuebleUpdated != null) {
                    Intent intent = new Intent();
                    Bundle bundle1 = new Bundle();
                    bundle1.putSerializable(Constantes.PISO, inmuebleUpdated);
                    int posicion = bundle.getInt(Constantes.POSICION);
                    bundle1.putInt(Constantes.POSICION, posicion);
                    intent.putExtras(bundle1);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        });

        binding.btnBorrarEditPiso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int posicion = bundle.getInt(Constantes.POSICION);
                Intent intent = new Intent();
                Bundle bundle1 = new Bundle();
                bundle1.putInt(Constantes.POSICION, posicion);
                intent.putExtras(bundle1);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

    }

    private Piso crearInmueble() {
        if (binding.txtDireccionEditPiso.getText().toString().isEmpty() ||
                binding.txtCiudadEditPiso.getText().toString().isEmpty() ||
                binding.txtCPEditPiso.getText().toString().isEmpty() ||
                binding.txtNumeroEditPiso.getText().toString().isEmpty() ||
                binding.txtProvinciaEditPiso.getText().toString().isEmpty() )
            return null;
        return new Piso(
                binding.txtDireccionEditPiso.getText().toString(),
                Integer.parseInt(binding.txtNumeroEditPiso.getText().toString()),
                binding.txtCPEditPiso.getText().toString(),
                binding.txtCiudadEditPiso.getText().toString(),
                binding.txtProvinciaEditPiso.getText().toString(),
                binding.rbValoracionEditPiso.getRating()
        );
    }
}