package com.example.tpdm_u3_practica1_angelcruz;

public class Aplicaciones {
    String aplicador, fechaaplicacion, aula, horainicio, horafin;

    public Aplicaciones(String aplicador, String fechaaplicacion, String aula, String horainicio, String horafin){
        this.aplicador=aplicador;
        this.fechaaplicacion=fechaaplicacion;
        this.aula=aula;
        this.horainicio=horainicio;
        this.horafin=horafin;
    }//constructor

    public String getAplicador(){
        return aplicador;
    }

    public void setAplicador(String aplicador){
        this.aplicador=aplicador;
    }

    public String getFechaaplicacion(){
        return fechaaplicacion;
    }

    public void setFechaaplicacion(String fechaaplicacion) {
        this.fechaaplicacion = fechaaplicacion;
    }

    public String getAula() {
        return aula;
    }

    public void setAula(String aula) {
        this.aula = aula;
    }

    public String getHorainicio() {
        return horainicio;
    }

    public void setHorainicio(String horainicio) {
        this.horainicio = horainicio;
    }

    public String getHorafin() {
        return horafin;
    }

    public void setHorafin(String horafin) {
        this.horafin = horafin;
    }
}
