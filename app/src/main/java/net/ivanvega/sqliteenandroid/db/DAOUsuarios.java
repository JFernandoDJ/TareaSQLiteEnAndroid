package net.ivanvega.sqliteenandroid.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DAOUsuarios {

    private SQLiteDatabase _ad ;

    public DAOUsuarios(Context ctx) {
          _ad =
                  new MiAdaptadorUsuariosConexion(ctx).getWritableDatabase();
    }

    public long delete(int position)
    {
        String[] args = new String[]{(position+"")};
        return _ad.delete("usuarios", "_id=?", args);

    }


    public long add(Usuario u)
    {

        ContentValues cv = new ContentValues();

        cv.put(MiAdaptadorUsuariosConexion.COLUMNS_USUARIOS[1], u.getNombre() );
        cv.put(MiAdaptadorUsuariosConexion.COLUMNS_USUARIOS[2], u.getTelefono() );
        cv.put(MiAdaptadorUsuariosConexion.COLUMNS_USUARIOS[3], u.getEmail());
        cv.put(MiAdaptadorUsuariosConexion.COLUMNS_USUARIOS[4], u.getRed_social() );
        cv.put(MiAdaptadorUsuariosConexion.COLUMNS_USUARIOS[5], u.getFecha() + "");

        return _ad.insert(
                MiAdaptadorUsuariosConexion.TABLES_DB[0],
                null,
                cv);

    }

    public long   update (Usuario u){
        ContentValues cv = new ContentValues();
        cv.put(MiAdaptadorUsuariosConexion.COLUMNS_USUARIOS[1], u.getNombre() );
        cv.put(MiAdaptadorUsuariosConexion.COLUMNS_USUARIOS[2], u.getTelefono() );
        cv.put(MiAdaptadorUsuariosConexion.COLUMNS_USUARIOS[3], u.getEmail());
        cv.put(MiAdaptadorUsuariosConexion.COLUMNS_USUARIOS[4], u.getRed_social() );
        cv.put(MiAdaptadorUsuariosConexion.COLUMNS_USUARIOS[5], (u.getFecha() + ""));

        _ad.update(MiAdaptadorUsuariosConexion.TABLES_DB[0],
                cv,
                "_id=?",
                new String[]{String.valueOf( u.getId())}
                );

        return 0;
    }

    public List<Usuario > getAll(){
        List <Usuario> lst = new ArrayList<Usuario>();
         Cursor c = _ad.query(
                MiAdaptadorUsuariosConexion.TABLES_DB[0],
                MiAdaptadorUsuariosConexion.COLUMNS_USUARIOS,
                null, null,null,null,null
        );
        //Cursor c = _ad.rawQuery("SELECT * FROM usuarios Where _id = 2", null);
        //Log.i("mal", (lst.size() + ""));

         while(c.moveToNext()){

             lst.add(
               new Usuario(c.getInt(0), c.getString(1),
                       c.getString(2), c.getString(3),
                       c.getString(4), new Date(c.getString(5)))
             );

         }
        return lst;
    }

    public Cursor getAllC(){


        //Cursor c = _ad.rawQuery("SELECT * FROM usuarios Where _id = 2", null);

        Cursor c = _ad.query(
                MiAdaptadorUsuariosConexion.TABLES_DB[0],
                MiAdaptadorUsuariosConexion.COLUMNS_USUARIOS,
                null, null,null,null,null
        );



        return c;
    }

    public Cursor getAll(String nombre){


        Cursor c = _ad.rawQuery("SELECT * FROM usuarios Where nombre = \"" + nombre + "\"", null);

       /* Cursor c = _ad.query(
                MiAdaptadorUsuariosConexion.TABLES_DB[0],
                MiAdaptadorUsuariosConexion.COLUMNS_USUARIOS,
                null, null,null,null,null
        );*/



        return c;
    }

    public List<Usuario > getAllP(String nombre){
        List <Usuario> lst = new ArrayList<Usuario>();

        Cursor c = _ad.rawQuery("SELECT * FROM usuarios Where nombre = \"" + nombre + "\"", null);

        while(c.moveToNext()){

            lst.add(
                    new Usuario(c.getInt(0), c.getString(1),
                            c.getString(2), c.getString(3),
                            c.getString(4), new Date(c.getString(5)))
            );

        }
        return lst;
    }

}
