package net.ivanvega.sqliteenandroid.db;

import android.text.method.DateTimeKeyListener;

import java.util.Date;

public class Usuario {
     int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    String nombre;
    String telefono;
    String email;
    Date fecha;

    public Usuario(int id, String nombre, String telefono, String email, Date fecha) {
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
        this.fecha = fecha;
    }

    public Usuario(int id, String nombre, String telefono, String email, String red_social, Date fecha) {
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
        this.red_social = red_social;
        this.fecha = fecha;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getRed_social() {
        return red_social;
    }

    public void setRed_social(String red_social) {
        this.red_social = red_social;
    }

    String red_social;
}
