/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.itson.catrina.ui;

import java.awt.Component;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableModel;
import mx.itson.catrina.enumerador.Type;
import mx.itson.catrina.negocio.AccountStatement;
import mx.itson.catrina.negocio.PeriodSummary;
import mx.itson.catrina.negocio.Transaction;

/**
 * La clase Buttons contiene los métodos para mostrar la información del estado de cuenta en la tabla.
 * @author Fernanda Barrón
 */
public class Buttons {
    
    JLabel lblInitialBalance;
    JLabel lblDeposit;                           
    JLabel lblWithdrawals;
    JLabel lblFinalBalance;
    JLabel lblFinalBalanceP;
    
    JLabel lblName;
    JLabel lblRfc;
    JLabel lblAddress;
    JLabel lblCity;
    JLabel lblZipCode;
    

    JLabel lblAccount;
    JLabel lblAccount2;
    JLabel lblClabe2;
    JLabel lblCurrency2;
    
     javax.swing.JTable tblTransactions;
    
    public Buttons(JLabel _InitialBalance, JLabel _Deposit, JLabel _Withdrawals, JLabel _FinalBalance, 
            JLabel _FinalBalanceP, javax.swing.JTable _tbl) {
        lblInitialBalance = _InitialBalance;
        lblDeposit = _Deposit;
        lblWithdrawals = _Withdrawals;
        lblFinalBalance = _FinalBalance;
        lblFinalBalanceP = _FinalBalanceP;
        tblTransactions = _tbl;
    }   
   
     public Buttons(JLabel _Name, JLabel _Rfc, JLabel _Address, 
            JLabel _City, JLabel _ZipCode, javax.swing.JTable _tbl,JLabel _Account,
            JLabel _Account2, JLabel _Clabe2, JLabel _Currency2
 ){
        lblName = _Name;
        lblRfc = _Rfc;
        lblAddress = _Address;
        lblCity = _City;
        lblZipCode = _ZipCode;
        lblAccount = _Account;
        lblAccount2 = _Account2;
        lblClabe2 = _Clabe2;
        lblCurrency2 = _Currency2;
        
        tblTransactions = _tbl;
        
    }
     
     /**
     * Actualiza las etiquetas y la tabla con la información financiera para un mes específico.
     *
     * @param accountStatement Estado de cuenta.
     * @param month Mes para filtrar las transacciones.
     */
     public void MonthActionPerformed(AccountStatement accountStatement, int month) {                                       
        Locale local = new Locale("es","MX");
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(local);
        
        List<Transaction> filteredTransactions = new ArrayList<Transaction>();
        
        if(accountStatement != null){
            filteredTransactions = accountStatement.getFilteredTransactions(month);
           
            double initialBalance = PeriodSummary.getInitialBalance(accountStatement.getTransactions(), month);
            lblInitialBalance.setText(String.valueOf(currencyFormat.format(initialBalance)));
            
            PeriodSummary.setSubtotal(filteredTransactions, initialBalance);
            
           double totalDeposits = PeriodSummary.getDeposits(filteredTransactions);
            lblDeposit.setText(currencyFormat.format(totalDeposits));
            
            double totalWithdrawals = PeriodSummary.getWithdrawals(filteredTransactions);
            lblWithdrawals.setText(currencyFormat.format(totalWithdrawals));
            
            double finalBalance = PeriodSummary.setFinalBalance(filteredTransactions, initialBalance, totalDeposits, totalWithdrawals);
            lblInitialBalance.setText(currencyFormat.format(finalBalance));
            lblFinalBalanceP.setText(currencyFormat.format(finalBalance));
            this.desplegarValoresTabla(filteredTransactions);
            
        }
    
     }
     /**
     * Permite al usuario seleccionar un archivo para cargar un objeto y actualiza el estado de cuenta.
     *
     * @param accountStatement Estado de cuenta actual.
     * @return El nuevo estado de cuenta cargado desde el archivo.
     */
           public AccountStatement SeleccionarActionPerformed(AccountStatement accountStatement, Component parent) {                                               

        try{

            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));

            if(fileChooser.showOpenDialog(parent)== JFileChooser.APPROVE_OPTION){
                //Si se selecciona un archivo
                File archivo = fileChooser.getSelectedFile();
                byte archivoBytes[] = Files.readAllBytes(archivo.toPath());
                String contenido = new String(archivoBytes, StandardCharsets.UTF_8);

                //La clase que engloba todas las demás clases será desearializada
                accountStatement = new AccountStatement().deserializar(contenido);

                lblName.setText(accountStatement.getCustomer().getName());
                lblRfc.setText(accountStatement.getCustomer().getRfc());
                lblAddress.setText(accountStatement.getCustomer().getAddress());
                lblCity.setText(accountStatement.getCustomer().getCity());
                lblZipCode.setText(accountStatement.getCustomer().getZipCode());
                lblAccount.setText(accountStatement.getAccount());
                lblAccount2.setText(accountStatement.getAccount());
                lblClabe2.setText(accountStatement.getClabe());
                lblCurrency2.setText(accountStatement.getCurrency());
                
                
                this.desplegarValoresTabla(accountStatement.getTransactions());
            }
        }catch(Exception ex){
            System.err.print("Ocurrió un error" + ex.getMessage());
        }
        return accountStatement;
    }
    
     /**
     * Despliega los valores de las transacciones en la tabla.
     *
     * @param transactions Lista de transacciones.
     */
        private void desplegarValoresTabla(List<Transaction> transactions){
        Locale local = new Locale("es","MX");
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(local);
            
        DateFormat formatoFecha = new SimpleDateFormat("dd/MMMM/yyyy");
            
        DefaultTableModel transactionsTable = (DefaultTableModel)tblTransactions.getModel();
        transactionsTable.setRowCount(0);
            
            for(Transaction m: transactions){
                if(m.getType()== Type.DEPOSIT){
                    transactionsTable.addRow(new Object[] {formatoFecha.format(m.getDate()), (m.getDescription()), currencyFormat.format(m.getAmount()), currencyFormat.format(0), currencyFormat.format(m.getSubtotal())});
                    
                }else if(m.getType() == Type.WITHDRAWAL){
                    transactionsTable.addRow(new Object[] {formatoFecha.format(m.getDate()), (m.getDescription()), currencyFormat.format(0), currencyFormat.format(m.getAmount()), currencyFormat.format(m.getSubtotal())});
                }
              
                
            }
    } 
    
}
