package google.tamayo.christopher.bitacora;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.InputMismatchException;

public class MainActivity extends AppCompatActivity {

    private EditText et1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et1 = (EditText)findViewById(R.id.txt_datos);
        String archivos [] = fileList();

        if(ArchivoExiste(archivos, "bitacora.txt")){
            try {
                InputStreamReader archivo = new InputStreamReader(openFileInput("bitacora.txt"));
                BufferedReader br= new BufferedReader(archivo);
                String linea = br.readLine();
                String bitacoraCompleta = "";

                while(linea!=null){
                    bitacoraCompleta = bitacoraCompleta+linea+ "\n";
                    linea = br.readLine();

                }
                br.close();
                archivo.close();
                et1.setText(bitacoraCompleta);
            }catch (IOException e){

            }

        }
    }
    public boolean ArchivoExiste(String archivos [], String NombreArchivo){
        for (int i = 0; i<archivos.length; i++)
            if(NombreArchivo.equals(archivos[i]))
                return true;
            return false;

    }
    public void Guardar(View view){
        try {
            OutputStreamWriter archivo = new OutputStreamWriter(openFileOutput("bitacora.txt", Activity.MODE_PRIVATE));
            archivo.write(et1.getText().toString());
            archivo.flush();
            archivo.close();
        }catch (IOException e){

        }
        Toast.makeText(this, "Bitacora guardada correctamente", Toast.LENGTH_SHORT).show();
    }
}
