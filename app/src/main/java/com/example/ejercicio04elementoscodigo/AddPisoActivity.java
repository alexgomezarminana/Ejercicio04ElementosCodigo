package com.example.ejercicio04elementoscodigo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.ejercicio04elementoscodigo.configuraciones.Constantes;
import com.example.ejercicio04elementoscodigo.databinding.ActivityAddPisoBinding;
import com.example.ejercicio04elementoscodigo.modelos.Piso;

public class AddPisoActivity extends AppCompatActivity {

    private ActivityAddPisoBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityAddPisoBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        binding.btnCancelarAddPiso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });

        binding.btnCrearAddPiso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Piso piso = createPiso();
                if (piso != null) {
                    Bundle bundle = new Bundle();
                    bundle.putSerializable(Constantes.PISO, piso);
                    Intent intent = new Intent();
                    intent.putExtras(bundle);
                    setResult(RESULT_OK, intent);
                    finish();
                }else {
                    Toast.makeText(AddPisoActivity.this, "FALTAN DATOS", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private Piso createPiso() {
        if (binding.txtDireccionAddPiso.getText().toString().isEmpty()){
            return null;
        }
        if (binding.txtNumeroAddPiso.getText().toString().isEmpty()){
            return null;
        }
        if (binding.txtCiuadAddPiso.getText().toString().isEmpty()){
            return null;
        }
        if (binding.txtProvinciaAddPiso.getText().toString().isEmpty()){
            return null;
        }
        if (binding.txtCPAddPiso.getText().toString().isEmpty()){
            return null;
        }


        return new Piso(binding.txtDireccionAddPiso.getText().toString(),
                Integer.parseInt(binding.txtNumeroAddPiso.getText().toString()),
                binding.txtCiuadAddPiso.getText().toString(),
                binding.txtProvinciaAddPiso.getText().toString(),
                binding.txtCPAddPiso.getText().toString(),
                binding.rbValoracionAddPiso.getRating());
    }
}