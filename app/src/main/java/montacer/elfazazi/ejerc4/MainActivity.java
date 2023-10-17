package montacer.elfazazi.ejerc4;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

import montacer.elfazazi.ejerc4.databinding.ActivityMainBinding;
import montacer.elfazazi.ejerc4.modelos.Inmueble;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    private ActivityResultLauncher<Intent> launcherInmuebles;
    private ArrayList<Inmueble> listaInmuebles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        listaInmuebles = new ArrayList<>();

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launcherInmuebles.launch(new Intent(MainActivity.this, AddInmuebleActivity.class));
            }
        });
        
        inicializarLauncher();
    }

    private void inicializarLauncher() {
        launcherInmuebles = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == RESULT_OK){
                            if (result.getData() != null && result.getData().getExtras() != null){
                                //Inmueble inmueble = (Inmueble) result.getData().getExtras().getSerializable("INMUEBLE");
                                //listaInmuebles.add(inmueble);
                            }
                        }else{
                            Toast.makeText(MainActivity.this, "accion cancelada", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );
    }
}