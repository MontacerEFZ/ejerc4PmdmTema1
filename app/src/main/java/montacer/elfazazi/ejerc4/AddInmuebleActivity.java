package montacer.elfazazi.ejerc4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import montacer.elfazazi.ejerc4.databinding.ActivityAddInmuebleBinding;
import montacer.elfazazi.ejerc4.modelos.Inmueble;

public class AddInmuebleActivity extends AppCompatActivity {
    private ActivityAddInmuebleBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityAddInmuebleBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnCancelarInmueble.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });

        binding.btnCrearInmueble.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Inmueble inmueble = crearInmueble();

                if (inmueble == null){
                    Toast.makeText(AddInmuebleActivity.this, "faltan datos", Toast.LENGTH_SHORT).show();
                }else{
                    Intent intent = new Intent();
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("INMUEBLE", inmueble);
                    intent.putExtras(bundle);
                    setResult(RESULT_OK, intent);
                }
            }
        });
    }

    private Inmueble crearInmueble() {
        if (binding.txtDireccionInmueble.getText().toString().isEmpty()){
            return null;
        }
        if (binding.txtNumeroInmueble.getText().toString().isEmpty()){
            return null;
        }
        if (binding.txtCiudadInmueble.getText().toString().isEmpty()){
            return null;
        }
        if (binding.txtProvinciaInmueble.getText().toString().isEmpty()){
            return null;
        }
        if (binding.txtCpInmueble.getText().toString().isEmpty()){
            return null;
        }
        if (binding.txtCpInmueble.getText().toString().isEmpty()){
            return null;
        }

        int numero = Integer.parseInt(binding.txtNumeroInmueble.getText().toString());
        int cp = Integer.parseInt(binding.txtCpInmueble.getText().toString());

        Inmueble inmueble = new Inmueble(binding.txtDireccionInmueble.getText().toString(),
                numero, binding.txtCiudadInmueble.getText().toString(),
                binding.txtProvinciaInmueble.getText().toString(),
                cp, binding.rbValoracionInmueble.getRating());

        return inmueble;
    }
}