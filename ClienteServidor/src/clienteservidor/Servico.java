/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clienteservidor;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;

/**
 *
 * @author willi
 */
public interface Servico extends Remote{
     public void connectionBanc()throws RemoteException;
    public void gravarDados(String nome, String numconta, String saldo, String senha) throws RemoteException, SQLException;
    public String pegarDados(String numConta)throws RemoteException;
    public void editarDados(String num_conta,String saldo)throws RemoteException;
    public void sacar(String numConta, String valor) throws SQLException,RemoteException;
    public void depositar(String numConta, String valor) throws SQLException,RemoteException;
    public void desconectar()throws SQLException,RemoteException;
    public String senha(String numConta) throws SQLException, RemoteException;
    public String saldo(String numConta) throws SQLException,RemoteException;
    public String nome(String numConta) throws SQLException,RemoteException;
    
}
