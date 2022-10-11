package com.example.ejercicio04elementoscodigo.modelos;

import java.io.Serializable;

public class Piso implements Serializable {
    //calle, numero, provincia, Valoracionpiso
    private String direccion;
    private int numero;
    private String ciudad;
    private String provinvia;
    private String cp;
    private float valoracionPiso;

    public Piso() {
    }

    public Piso(String direccion, int numero, String ciudad, String provinvia, String cp, float valoracionPiso) {
        this.direccion = direccion;
        this.numero = numero;
        this.ciudad = ciudad;
        this.provinvia = provinvia;
        this.cp = cp;
        this.valoracionPiso = valoracionPiso;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getProvinvia() {
        return provinvia;
    }

    public void setProvinvia(String provinvia) {
        this.provinvia = provinvia;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public float getValoracionPiso() {
        return valoracionPiso;
    }

    public void setValoracionPiso(float valoracionPiso) {
        this.valoracionPiso = valoracionPiso;
    }

    @Override
    public String toString() {
        return "piso{" +
                "direccion='" + direccion + '\'' +
                ", numero=" + numero +
                ", ciudad='" + ciudad + '\'' +
                ", provinvia='" + provinvia + '\'' +
                ", cp='" + cp + '\'' +
                ", valoracionPiso=" + valoracionPiso +
                '}';
    }
}
