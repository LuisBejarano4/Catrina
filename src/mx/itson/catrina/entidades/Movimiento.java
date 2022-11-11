/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.itson.catrina.entidades;

import java.util.Date;
import java.util.List;
import mx.itson.catrina.enumerador.Tipo;

/**
 *Contiene atributos de un movimiento.
 * @author Luis Bejarano
 */
public class Movimiento {
    private Date fecha;
    private String desc;
    private double cantidad;
    private Tipo tipo;
    private double subtotal;

    /**
 * @return the subtotal
     */
    public double getSubtotal() {
        return subtotal;
    }

    /**
     * @param subtotal the subtotal to set
     */
    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }
    
    /**
     * @return the fecha
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    /**
     * @return the desc
     */
    public String getDescripcion() {
        return desc;
    }

    /**
     * @param descripcion the desc to set
     */
    public void setDescripcion(String descripcion) {
        this.desc = descripcion;
    }

    /**
     * @return the cantidad
     */
    public double getCantidad() {
        return cantidad;
    }

    /**
     * @param cantidad the cantidad to set
     */
    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }
    /**
     * @return the tipo
     */
    public Tipo getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }
        
    
    
    
    
    
}
