package com.example.tpdm_u3_practica1_angelcruz;

public class Alumnos {
    String id, nocontrol, nombre, apellidos, carrera, fechaaplicacion;

    public Alumnos(String nc, String nom, String ape, String carr, String fec){
        this.nocontrol=nc;
        this.nombre=nom;
        this.apellidos=ape;
        this.carrera=carr;
        this.fechaaplicacion=fec;
    }//constructor

    public Alumnos(){}

    public String getId(){
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNocontrol() {
        return nocontrol;
    }

    public void setNocontrol(String nocontrol) {
        this.nocontrol = nocontrol;
    }

    public String getNombre(){
        return nombre;
    }

    public void setNombre(String nombre){
        this.nombre=nombre;
    }

    public String getApellidos(){
        return apellidos;
    }

    public void setApellidos(String apellidos){
        this.apellidos=apellidos;
    }

    public String getCarrera(){
        return carrera;
    }

    public void setCarrera(String carrera){
        this.carrera=carrera;
    }

    public String getFechaaplicacion(){
        return fechaaplicacion;
    }

    public void setFechaaplicacion(String fechaaplicacion){
        this.fechaaplicacion=fechaaplicacion;
    }
}//class
