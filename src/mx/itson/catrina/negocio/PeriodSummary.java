/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.itson.catrina.negocio;

import java.util.List;
import mx.itson.catrina.enumerador.Type;

/**
 * Contiene métodos para realizar diversas operaciones.
 * @author Fernanda Barrón
 */
public class PeriodSummary {
    

    /**
     * Agrega al saldo incial de los valores anteriores al mes seleccionado dependiento del tipo de movimiento.
     * @param transactions
     * @param month
     * @return El saldo inicial.
     */
    public static double getInitialBalance(List<Transaction> transactions, int month){
        double initialBalance = 0;
        
        for(Transaction transaction : transactions){
            
            if(transaction.getDate().getMonth() < month && transaction.getType() == Type.DEPOSIT){
                initialBalance += transaction.getAmount();
                
            }else if(transaction.getDate().getMonth() < month && transaction.getType()== Type.WITHDRAWAL){
                initialBalance -= transaction.getAmount();
            }
        }
        
        
        return initialBalance;
        
    }
    /**
     * Da valor a subtotal tomando el saldo inicial y dependiendo del tipo de movimiento suma o resta una cantidad.
     * @param transactions
     * @param initialBalance
     */
    public static void setSubtotal(List<Transaction> transactions, double initialBalance){
        double subTotal = initialBalance;
 
        for(Transaction transaction : transactions){
            if(transaction.getType() == Type.DEPOSIT){
                subTotal += transaction.getAmount();
                
            }else if(transaction.getType() == Type.WITHDRAWAL){
                subTotal -= transaction.getAmount();
            }
            
            transaction.setSubtotal(subTotal);
            
        }
        
    }
    /**
     * Obtiene todos los depósitos y los guarda en una variable.
     * @param transactions
     * @return Total de depositos a partir de una lista de movimientos.
     */
    public static double getDeposits(List<Transaction> transactions){
        double totalDeposits = 0;
        for(Transaction transaction : transactions){
            if(transaction.getType() == Type.DEPOSIT){
                totalDeposits += transaction.getAmount();
                
            }
        }
        return totalDeposits;
        
    }
    /**
     * Obtiene todos los retiros y los guarda en una variable.
     * @param transactions
     * @return Total de retiros a partir de una lista de movimientos.
     */
    public static double getWithdrawals(List<Transaction> transactions){
        double totalWithdrawals = 0;
        for(Transaction transaction : transactions){
            if(transaction.getType() == Type.WITHDRAWAL){
                totalWithdrawals += transaction.getAmount();
                
            }
            
        }
        
        return totalWithdrawals;
        
    }
    /**
     * Suma al saldo inicial los depositos y resta los retiros.
     * @param transactions
     * @param initialBalance
     * @param totalDeposits
     * @param totalWithdrawals
     
     * @return Un saldo final
     */
    public static double setFinalBalance(List<Transaction> transactions, double initialBalance, double totalDeposits, double totalWithdrawals){
        double finalBalance = (initialBalance + totalDeposits - totalWithdrawals);
        return finalBalance;
        
        
        
    }
}


