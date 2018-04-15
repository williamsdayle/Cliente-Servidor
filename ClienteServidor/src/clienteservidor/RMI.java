/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clienteservidor;
import java.rmi.RemoteException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author willi
 */
public class RMI extends UnicastRemoteObject implements Servico {

    OperacoesBanco bd;

    public RMI() throws RemoteException {
        
        super();
        bd  = new OperacoesBanco();
        
    }

    public static void main(String[] args) throws SQLException {
            
        try {
            RMI servidor = new RMI();            
            Registry registro = LocateRegistry.createRegistry(8080);
            registro.bind("TesteCurry", servidor);
            System.out.println("Usuario conectado");
            
            

        } catch (Exception ex) {
            System.out.println("Não foi possivel estabelecer conexao " + ex.getMessage());
        }

    }
    @Override
    public void sacar(String numConta, String valor) throws SQLException,RemoteException{
        bd.sacar(numConta, valor);
        transacoes( "Cliente fez uma transacao de "+valor+" para sua conta => "+numConta);
    }
    @Override
    public void depositar(String numConta, String valor) throws SQLException,RemoteException{
    bd.depositar(numConta, valor);
     transacoes( "Cliente fez uma transacao de "+valor+" para sua conta => "+numConta);
    
    }
    @Override
    public void editarDados(String num_conta,String saldo)throws RemoteException{
        try {
            bd.updateDados(num_conta, saldo);
        } catch (SQLException ex) {
            Logger.getLogger(RMI.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Consegui atualizar");
    
    }

    @Override
    public void connectionBanc() throws RemoteException {
        try {
            bd.conexao();
        } catch (SQLException ex) {
            Logger.getLogger(RMI.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Conectado");

    }
    @Override
    public void desconectar()throws SQLException,RemoteException{
    bd.desconecta();
    }


    @Override
    public void gravarDados(String nome, String numconta, String saldo, String senha) throws RemoteException, SQLException {
        bd.escreverBanco(nome, numconta, saldo,senha);
        transacoes( "O cliente => "+nome+ " fez uma transacao de "+saldo+" para sua conta => "+numconta);

    }
    @Override
    public String pegarDados(String numConta)throws RemoteException{
        String valor = "";
        try {
            valor =  bd.pegarDados(numConta);
        } catch (SQLException ex) {
            Logger.getLogger(RMI.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    return valor;
    }
    @Override
    public String senha(String numConta) throws SQLException, RemoteException{
    return bd.pegarSenha(numConta);
    
    }
    @Override
    public String saldo(String numConta) throws SQLException,RemoteException{
    return bd.pegarSaldo(numConta);
    }
    @Override
    public String nome(String numConta) throws SQLException,RemoteException{
    return bd.pegarNome(numConta);
    }
    
     public void transacoes(String valor){
         System.out.println(valor);
     
     }   
        
        
}
