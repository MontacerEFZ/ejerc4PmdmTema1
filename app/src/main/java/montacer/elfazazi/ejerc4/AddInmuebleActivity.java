package montacer.elfazazi.ejerc4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import montacer.elfazazi.ejerc4.databinding.ActivityAddInmuebleBinding;

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
    }
}