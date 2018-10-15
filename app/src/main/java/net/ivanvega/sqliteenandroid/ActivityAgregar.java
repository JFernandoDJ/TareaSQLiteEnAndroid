package net.ivanvega.sqliteenandroid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import net.ivanvega.sqliteenandroid.db.DAOUsuarios;
import net.ivanvega.sqliteenandroid.db.Usuario;

import java.util.Date;

public class ActivityAgregar extends AppCompatActivity {

    EditText edtNombre;
    EditText edtTelefono;
    EditText edtEmail;
    EditText edtRedSocial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar);
        edtNombre = findViewById(R.id.edtNombre);
        edtTelefono = findViewById(R.id.edtTelefono);
        edtEmail = findViewById(R.id.edtEmail);
        edtRedSocial = findViewById(R.id.edtRedSocial);
    }

    public void btnI_click(View v){

        DAOUsuarios ado =
                new DAOUsuarios(getApplicationContext());
        Usuario u =  new Usuario(
                0,edtNombre.getText().toString(), edtTelefono.getText().toString(),
                edtEmail.getText().toString(), edtEmail.getText().toString(),
                new Date()
        );
        long result = ado.add(u);
        Toast.makeText(this, result+"",
                Toast.LENGTH_SHORT).show();

        /*Log.i("NN",(result + ""));
        if (result>0){
            Toast.makeText(this, "Adición exitosa",
                    Toast.LENGTH_LONG).show();
        }*/

        super.onBackPressed();
    }

}
