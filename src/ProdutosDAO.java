/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ProdutosDAO {
    
    public Connection conexao;
    public PreparedStatement stm;
    public ResultSet rs;
    
    private String url = "jdbc:mysql://127.0.0.1:3306/uc11?useSSL=false";
    private String user = "root";
    private String password = "Gigi@2010";
   
    
    public boolean conectar(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexao = DriverManager.getConnection(url, user, password);
            return true;
        }
        catch(ClassNotFoundException |SQLException ex){
            
            return false;
            
        }
    }
    
    
 
    
    public int cadastrarProduto (ProdutosDTO produto){
        
        try{
            if(!produto.getNome().isEmpty() || !produto.getValor().toString().isEmpty()){
                
                 stm = conexao.prepareStatement("INSERT INTO produtos (id, nome, valor, status) VALUES (?,?, ?, ?)");
                 stm.setInt(1, produto.getId());
                 stm.setString(2, produto.getNome());
                 stm.setInt(3, produto.getValor());
                 stm.setString(4, produto.getStatus());
            
            
            }
            else{
                JOptionPane.showMessageDialog(null, "Os campos não podem ficar em branco");    
            }
            
           return stm.executeUpdate();
            
        }
        catch(SQLException ex){
            return ex.getErrorCode();
        }
        
    }
    
    
    public ArrayList<ProdutosDTO> listarProdutos(){
      try{
             ArrayList<ProdutosDTO> listagem = new ArrayList<>();
                     
            String sqlFiltro = "SELECT * FROM produtos";
            
            /*if(!filtro.isEmpty()){
                sqlFiltro =  sqlFiltro+ " WHERE nome like ?";   
            }
            
            stm = conexao.prepareStatement(sqlFiltro);
           
            if(!filtro.isEmpty()){
                stm.setString(1, "%" + filtro + "%");
            }
            */
            stm = conexao.prepareStatement(sqlFiltro);
             rs = stm.executeQuery();
             
             while(rs.next()){
                 ProdutosDTO produtoNaLista = new ProdutosDTO();
                 
                 produtoNaLista.setId(rs.getInt("id"));
                 produtoNaLista.setNome(rs.getString("nome"));
                 produtoNaLista.setValor(rs.getInt("valor"));
                 produtoNaLista.setStatus(rs.getString("status"));
                 
                 listagem.add(produtoNaLista);
                
             }
             return listagem;
        }
        
        
        catch(SQLException ex){
            return null;
        }
    }

    public int venderProduto(int id){
        ProdutosDTO produto = new ProdutosDTO();
        
        try{
            stm = conexao.prepareStatement("SELECT * FROM produtos WHERE id = ?");
            stm.setInt(1, id);
            rs = stm.executeQuery();
            
            if (rs.next()) {
            produto.setId(rs.getInt("id"));
            produto.setNome(rs.getString("nome"));
            produto.setValor(rs.getInt("valor"));
            produto.setStatus(rs.getString("status"));

            // Atualize o status do produto para "Vendido"
            produto.setStatus("Vendido");

            // Prepare a declaração de atualização
            stm = conexao.prepareStatement("UPDATE produtos SET status = ? WHERE id = ?");
            stm.setString(1, produto.getStatus());
            stm.setInt(2, produto.getId());

            return stm.executeUpdate();
        }
            
            return stm.executeUpdate();
            
        }
        catch(SQLException ex){
            
            return ex.getErrorCode();
        }
        
    }
    
    
    public void desconectar(){
        try{
            conexao.close();
        }
        catch(SQLException ex){
            System.out.println("Erro ao desconectar "+ ex.getMessage());
            
        }
    }
    
        
}

