/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.itson.catrina.negocio;

import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Fernanda Barrón
 * Clase para guardar los datos de cuenta.
 */
public class AccountStatement {

    private String account;
    private String clabe;
    private String currency;
    private Customer customer;
    private List<Transaction> transactions;
    
    /**
     * Pasa los datos de Json a un objeto llamado cuenta.
     * @param json Json a convertir
     * @return El objeto cuenta.
     */
  
    public AccountStatement deserializar(String json){
        AccountStatement accountData = new AccountStatement();
        try{
            Gson gson = new Gson();
            accountData = new Gson().fromJson(json, AccountStatement.class);
        }catch(Exception ex){
            System.err.print("Ocurrió un error: " + ex.getMessage());
        }
        return accountData;
    }
    /**
     * Añade movimientos a los movimientos filtrados cuando se selecciona el mes.
     * @param month
     * @return Estos movimientos ya están filtrados.
     */
    public List<Transaction> getFilteredTransactions(int month){
        List<Transaction> filteredTransactions = new ArrayList<>();
        for(Transaction transaction : transactions){
            if(transaction.getDate().getMonth() == month){
                filteredTransactions.add(transaction);
            }
        }
        // Acomoda la lista de movimientos por fecha.
        filteredTransactions.sort((transaction1, transaction2) -> transaction1.getDate().compareTo(transaction2.getDate()));
    
        return filteredTransactions;
    }
   
    
    /**
     * @return the account
     */
    public String getAccount() {
        return account;
    }

    /**
     * @param account the account to set
     */
    public void setAccount(String account) {
        this.account = account;
    }

    /**
     * @return the clabe
     */
    public String getClabe() {
        return clabe;
    }

    /**
     * @param clabe the clabe to set
     */
    public void setClabe(String clabe) {
        this.clabe = clabe;
    }

    /**
     * @return the currency
     */
    public String getCurrency() {
        return currency;
    }

    /**
     * @param currency the currency to set
     */
    public void setCurrency(String currency) {
        this.currency = currency;
    }

    /**
     * @return the customer
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * @param customer the customer to set
     */
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    /**
     * @return the transactions
     */
    public List<Transaction> getTransactions() {
        return transactions;
    }

    /**
     * @param transactions the transactions to set
     */
    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

}
