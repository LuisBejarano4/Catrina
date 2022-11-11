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
import mx.itson.catrina.entidades.Cuenta;
import mx.itson.catrina.entidades.Movimiento;
import mx.itson.catrina.entidades.ResumenMovimiento;
import mx.itson.catrina.enumerador.Tipo;

/**
 *
 * @author Luis Bejarano
 */
public class Botones {
    JLabel lblSaldoIni;
    JLabel lblDepositos;
    JLabel lblRetiros;
    JLabel lblSaldoFin;
    JLabel lblSaldoFinP;
    
    JLabel lblNombre;
    JLabel lblRfc;
    JLabel lblDomicilio;
    JLabel lblCiudad;
    JLabel lblCp;
    JLabel lblCuenta;
    JLabel lblClabe;
    JLabel lblMoneda;
    
    JLabel lblCuentaContable;
    JLabel lblCuenta2;
    JLabel lblClabe2;
    JLabel lblMoneda2;
    
    javax.swing.JTable tblMovimientos;
    
    public Botones(JLabel _SaldoIni, JLabel _Depositos, JLabel _Retiros, JLabel _SaldoFin, 
            JLabel _SaldoFinP, javax.swing.JTable _tbl) {
        lblSaldoIni = _SaldoIni;
        lblDepositos = _Depositos;
        lblRetiros = _Retiros;
        lblSaldoFin = _SaldoFin;
        lblSaldoFinP = _SaldoFinP;
        tblMovimientos = _tbl;
    }   
    
    public Botones(JLabel _Nombre, JLabel _Rfc, JLabel _Domicilio, 
            JLabel _Ciudad, JLabel _Cp, JLabel _Cuenta, JLabel _Clabe, 
            JLabel _Moneda, javax.swing.JTable _tbl,JLabel _CuentaContable,
            JLabel _Cuenta2, JLabel _Clabe2, JLabel _Moneda2
 ){
        lblNombre = _Nombre;
        lblRfc = _Rfc;
        lblDomicilio = _Domicilio;
        lblCiudad = _Ciudad;
        lblCp = _Cp;
        lblCuenta = _Cuenta;
        lblClabe = _Clabe;
        lblMoneda = _Moneda;
        lblCuentaContable = _CuentaContable;
        lblCuenta2 = _Cuenta2;
        lblClabe2 = _Clabe2;
        lblMoneda2 = _Moneda2;
        
        tblMovimientos = _tbl;
        
    }
       public void MesActionPerformed(Cuenta cuenta, int mes) {                                       
        Locale local = new Locale("es","MX");
        NumberFormat formatoMoneda = NumberFormat.getCurrencyInstance(local);
        
        List<Movimiento> movimientosFiltrados = new ArrayList<Movimiento>();
        
        if(cuenta != null){
            movimientosFiltrados = cuenta.getMovimientosFiltrados(mes);
           
            double saldoInicial = ResumenMovimiento.getSaldoInicial(cuenta.getMovimientos(), mes);
            lblSaldoIni.setText(String.valueOf(formatoMoneda.format(saldoInicial)));
            
            ResumenMovimiento.setSubtotal(movimientosFiltrados, saldoInicial);
            
           double totalDepositos = ResumenMovimiento.getDepositos(movimientosFiltrados);
            lblDepositos.setText(formatoMoneda.format(totalDepositos));
            
            double totalRetiros = ResumenMovimiento.getRetiros(movimientosFiltrados);
            lblRetiros.setText(formatoMoneda.format(totalRetiros));
            
            double saldoFinal = ResumenMovimiento.setSaldoFinal(movimientosFiltrados, saldoInicial, totalDepositos, totalRetiros);
            lblSaldoFin.setText(formatoMoneda.format(saldoFinal));
            lblSaldoFinP.setText(formatoMoneda.format(saldoFinal));
            this.desplegarValoresTabla(movimientosFiltrados);
            
        }
            
              
    }                                      

           public Cuenta SeleccionarActionPerformed(Cuenta cuenta, Component parent) {                                               

        try{

            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));

            if(fileChooser.showOpenDialog(parent)== JFileChooser.APPROVE_OPTION){
                //Si se seleccina un archivo
                File archivo = fileChooser.getSelectedFile();
                byte archivoBytes[] = Files.readAllBytes(archivo.toPath());
                String contenido = new String(archivoBytes, StandardCharsets.UTF_8);

                //la clase que engloba todas las demas clases sera desearializada
                cuenta = new Cuenta().deserializar(contenido);

                lblNombre.setText(cuenta.getCliente().getNombre());
                lblRfc.setText(cuenta.getCliente().getRfc());
                lblDomicilio.setText(cuenta.getCliente().getDomicilio());
                lblCiudad.setText(cuenta.getCliente().getCiudad());
                lblCp.setText(cuenta.getCliente().getCp());
                lblCuenta.setText(cuenta.getCuenta());
                lblClabe.setText(cuenta.getClabe());
                lblMoneda.setText(cuenta.getMoneda());
                lblCuentaContable.setText(cuenta.getCuenta());
                lblCuenta2.setText(cuenta.getCuenta());
                lblClabe2.setText(cuenta.getCuenta());
                lblMoneda2.setText(cuenta.getCuenta());
                
                
                this.desplegarValoresTabla(cuenta.getMovimientos());
            }
        }catch(Exception ex){
            System.err.print("Ocurrió un error" + ex.getMessage());
        }
        return cuenta;
    }
    
       private void desplegarValoresTabla(List<Movimiento> movimientos){
        Locale local = new Locale("es","MX");
        NumberFormat formatoMoneda = NumberFormat.getCurrencyInstance(local);
            
        DateFormat formatoFecha = new SimpleDateFormat("dd/MMMM/yyyy");
            
        DefaultTableModel movimientosTabla = (DefaultTableModel)tblMovimientos.getModel();
        movimientosTabla.setRowCount(0);
            
            for(Movimiento m: movimientos){
                if(m.getTipo() == Tipo.DEPOSITO){
                    movimientosTabla.addRow(new Object[] {formatoFecha.format(m.getFecha()), m.getDescripcion(), formatoMoneda.format(m.getCantidad()), formatoMoneda.format(0), formatoMoneda.format(m.getSubtotal())});
                    
                }else if(m.getTipo() == Tipo.RETIRO){
                    movimientosTabla.addRow(new Object[] {formatoFecha.format(m.getFecha()), m.getDescripcion(), formatoMoneda.format(0), formatoMoneda.format(m.getCantidad()), formatoMoneda.format(m.getSubtotal())});
                }
              
                
            }
    } 
    
    
    
}
