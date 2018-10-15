package net.ivanvega.sqliteenandroid;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import net.ivanvega.sqliteenandroid.db.DAOUsuarios;
import net.ivanvega.sqliteenandroid.db.MiAdaptadorUsuariosConexion;
import net.ivanvega.sqliteenandroid.db.Usuario;

import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button agregar;
    ListView lsv;
    EditText edtBuscar;
    List<Usuario> lst;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        agregar = findViewById(R.id.btnAgregar);
        lsv = findViewById(R.id.lstvLista);
        edtBuscar = findViewById(R.id.edtBuscar);
    }

    public void onClicBuscar(View v){

        DAOUsuarios dao = new DAOUsuarios(this);
        lst =  dao.getAllP(edtBuscar.getText().toString());
//Toast.makeText(v.getContext(),"jjj",Toast.LENGTH_SHORT).show();
        Cursor c =  dao.getAll(edtBuscar.getText().toString());
        edtBuscar.setText("");
        SimpleCursorAdapter adp = new SimpleCursorAdapter(
                this, android.R.layout.simple_list_item_2 ,
                c , MiAdaptadorUsuariosConexion.COLUMNS_USUARIOS,
                new int[]{android.R.id.text1, android.R.id.text2},
                SimpleCursorAdapter.NO_SELECTION
        );

        lsv.setAdapter(adp);
        lsv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, final View view, final int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setTitle("Opciones");
                CharSequence[] opciones = {"Eliminar"};
                builder.setItems(opciones, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(which == 0){
                            DAOUsuarios du = new DAOUsuarios(getApplicationContext());
                            long resul = du.delete(lst.get(position).getId());
                            if(resul > 0) {
                                Toast.makeText(getApplicationContext(), "Eliminacion Exitosa", Toast.LENGTH_SHORT).show();
                                btnCargar_click(view);
                            }
                            Toast.makeText(getApplicationContext(), lst.get(position).getId() + "", Toast.LENGTH_SHORT).show();
                        }else{

                        }
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();
                return false;
            }

        });
    }

    public void onClicAgregar(View v){
        Intent i = new Intent(this,ActivityAgregar.class);
        startActivity(i);
    }

    public void btnCargar_click(View v){

        DAOUsuarios dao = new DAOUsuarios(this);
        lst =  dao.getAll();
        for (Usuario item: lst) {
            Log.d("USUARIO: " ,  String.valueOf( item.getId()));
            Log.d("USUARIO: " , item.getNombre());
            //Toast.makeText(v.getContext(),item.getFecha().toString(),Toast.LENGTH_SHORT).show();
        }

        Cursor c =  dao.getAllC();

        SimpleCursorAdapter adp = new SimpleCursorAdapter(
                this, android.R.layout.simple_list_item_2 ,
                c , MiAdaptadorUsuariosConexion.COLUMNS_USUARIOS,
                new int[]{android.R.id.text1, android.R.id.text2},
                SimpleCursorAdapter.NO_SELECTION
        );

        lsv.setAdapter(adp);
        lsv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, final View view, final int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setTitle("Opciones");
                CharSequence[] opciones = {"Eliminar"};
                builder.setItems(opciones, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(which == 0){
                            DAOUsuarios du = new DAOUsuarios(getApplicationContext());
                            long resul = du.delete(lst.get(position).getId());
                            if(resul > 0) {
                                Toast.makeText(getApplicationContext(), "Eliminacion Exitosa", Toast.LENGTH_SHORT).show();
                                btnCargar_click(view);
                            }
                        }else{

                        }
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();
                return false;
            }

        });
    }

}
