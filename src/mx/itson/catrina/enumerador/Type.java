/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package mx.itson.catrina.enumerador;

import com.google.gson.annotations.SerializedName;

/**
 * Clase enumerador para el tipo de movimiento que se realice.
 * @author Fernanda Barrón
 */
public enum Type {
    
    @SerializedName("1")
    DEPOSIT, 
    @SerializedName("2")
    WITHDRAWAL,
    
}
