/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.itson.catrina.entidades;

import java.util.List;
import mx.itson.catrina.enumerador.Tipo;

/**
 *Contiene métodos para realizar diversas operaciones.
 * @author Luis Bejarano
 */
public class ResumenMovimiento {
    /**
     * Agrega al saldo incial los valores anteriores al mes seleccionado dependiento del tipo de movimiento.
     * @param movimientos
     * @param mes
     * @return El saldo inicial.
     */
    public static double getSaldoInicial(List<Movimiento> movimientos, int mes){
        double saldoInicial = 0;
        
        for(Movimiento movimiento : movimientos){
            
            if(movimiento.getFecha().getMonth() < mes && movimiento.getTipo() == Tipo.DEPOSITO){
                saldoInicial += movimiento.getCantidad();
                
            }else if(movimiento.getFecha().getMonth() < mes && movimiento.getTipo() == Tipo.RETIRO){
                saldoInicial -= movimiento.getCantidad();
            }
        }
        
        
        return saldoInicial;
        
    }
    /**
     * Da valor a subtotal tomando el saldo inicial y dependiendo del tipo de movimiento suma o resta una cantidad.
     * @param movimientos
     * @param saldoInicial 
     */
    public static void setSubtotal(List<Movimiento> movimientos, double saldoInicial){
        double subTotal = saldoInicial;
 
        for(Movimiento movimiento : movimientos){
            if(movimiento.getTipo() == Tipo.DEPOSITO){
                subTotal += movimiento.getCantidad();
                
            }else if(movimiento.getTipo() == Tipo.RETIRO){
                subTotal -= movimiento.getCantidad();
            }
            
            movimiento.setSubtotal(subTotal);
            
        }
        
    }
    /**
     * Obtiene todos los depositos y los guarda en una variable.
     * @param movimientos
     * @return Total de depositos a partir de una lista de movimientos.
     */
    public static double getDepositos(List<Movimiento> movimientos){
        double totalDepositos = 0;
        for(Movimiento movimiento : movimientos){
            if(movimiento.getTipo() == Tipo.DEPOSITO){
                totalDepositos += movimiento.getCantidad();
                
            }
        }
        return totalDepositos;
        
    }
    /**
     * Obtiene todos los retiros y los guarda en una variable.
     * @param movimientos
     * @return Total de retiros a partir de una lista de movimientos.
     */
    public static double getRetiros(List<Movimiento> movimientos){
        double totalRetiros = 0;
        for(Movimiento movimiento : movimientos){
            if(movimiento.getTipo() == Tipo.RETIRO){
                totalRetiros += movimiento.getCantidad();
                
            }
            
        }
        
        return totalRetiros;
        
    }
    /**
     * Suma al saldo inicial los depositos y resta los retiros.
     * @param movimientos
     * @param saldoInicial
     * @param totalDepositos
     * @param totalRetiros
     * @return Un saldo final
     */
    public static double setSaldoFinal(List<Movimiento> movimientos, double saldoInicial, double totalDepositos, double totalRetiros){
        double saldoFinal = (saldoInicial + totalDepositos - totalRetiros);
        return saldoFinal;
        
        
        
    }
}
