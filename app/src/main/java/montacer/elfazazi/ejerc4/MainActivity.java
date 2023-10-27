package montacer.elfazazi.ejerc4;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import montacer.elfazazi.ejerc4.databinding.ActivityMainBinding;
import montacer.elfazazi.ejerc4.modelos.Inmueble;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    private ActivityResultLauncher<Intent> launcherInmuebles;
    private ActivityResultLauncher<Intent> launcherEditInmuebles;
    private ArrayList<Inmueble> listaInmuebles;

    private int posicion;

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
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == RESULT_OK){
                            if (result.getData() != null && result.getData().getExtras() != null){
                                Inmueble inmueble = (Inmueble) result.getData().getExtras().getSerializable("INMUEBLE");
                                listaInmuebles.add(inmueble);
                                //Toast.makeText(MainActivity.this, inmueble.toString(), Toast.LENGTH_SHORT).show();
                                mostrarInmueble();
                            }
                        }else{
                            Toast.makeText(MainActivity.this, "accion cancelada", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );

        launcherEditInmuebles = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == RESULT_OK){
                            if (result.getData() != null && result.getData().getExtras() != null) {
                                //pulsaron editar
                                Inmueble inmueble = (Inmueble) result.getData().getExtras().getSerializable("INMUEBLE");
                                listaInmuebles.set(posicion, inmueble );
                                mostrarInmueble();
                            }else{
                                //pulsaron borrar
                                listaInmuebles.remove(posicion);
                                mostrarInmueble();
                            }
                        }
                    }
                }
            );
    }

    private void mostrarInmueble() {
        binding.contentMain.contenedorMain.removeAllViews();

        for (Inmueble inmueble:listaInmuebles){
            LayoutInflater layoutInflater = LayoutInflater.from(MainActivity.this);

            View inmuebleView = layoutInflater.inflate(R.layout.inmueble_file_view, null);

            TextView txtDireccion = inmuebleView.findViewById(R.id.lbDireccionInmuebleView);
            TextView txtNumero = inmuebleView.findViewById(R.id.lbNumeroInmuebleView);
            TextView txtCiudad = inmuebleView.findViewById(R.id.lbCiudadInmuebleView);
            RatingBar rbValoracion = inmuebleView.findViewById(R.id.rbValoracionInmuebleView);

            txtDireccion.setText(inmueble.getDireccion());
            txtNumero.setText(String.valueOf(inmueble.getNumero()));
            txtCiudad.setText(inmueble.getCiudad());
            rbValoracion.setRating(inmueble.getValoracion());

            inmuebleView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //enviar inmueble
                    Intent intent = new Intent(MainActivity.this, EditInmuebleActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("INMUEBLE", inmueble);
                    intent.putExtras(bundle);

                    posicion = listaInmuebles.indexOf(inmueble);
                    //recibir inmueble modificado o la orden de eliminar
                    launcherEditInmuebles.launch(intent); //para enviar informacion es el intent y el bundle con un new,
                    //para recibirlo en la nueva actividad es con un intent y bundle pero con get en lugar de new; y
                    //para tratar la informacion que devuelva la actvidad es con el launcher.
                }
            });

            //añadimos la nueva vista inmuebleView al content main que al principio le habiamos borrado todas las vistas
            binding.contentMain.contenedorMain.addView(inmuebleView);
        }

    }
}