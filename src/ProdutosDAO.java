/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */

import java.awt.List;
import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.SQLException;


public class ProdutosDAO {
    
    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
    public void cadastrarProduto (ProdutosDTO produto){
        
        String sql = "INSERT INTO produtos (nome, valor, status) VALUES (?, ?, ?)";
    
        try {
            conn = new conectaDAO().connectDB();
            prep = conn.prepareStatement(sql);
            prep.setString(1, produto.getNome());
            prep.setInt(2, produto.getValor());
            prep.setString(3, produto.getStatus());

            prep.executeUpdate();

            JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar produto: " + e.getMessage());
        } finally {
            try {
                if (prep != null) prep.close();
                if (conn != null) conn.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erro ao fechar conexão: " + ex.getMessage());
            }
        }     
    }
    
    public ArrayList<ProdutosDTO> listarProdutos(){
         listagem.clear(); 
         String sql = "SELECT * FROM produtos"; 

        try {
            conn = new conectaDAO().connectDB();
            prep = conn.prepareStatement(sql);
            resultset = prep.executeQuery();

            while (resultset.next()) {
                ProdutosDTO produto = new ProdutosDTO();
                produto.setId(resultset.getInt("id"));
                produto.setNome(resultset.getString("nome"));
                produto.setValor(resultset.getInt("valor"));
                produto.setStatus(resultset.getString("status"));

                listagem.add(produto);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar produtos: " + e.getMessage());
        } finally {
            try {
                if (resultset != null) resultset.close();
                if (prep != null) prep.close();
                if (conn != null) conn.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erro ao fechar conexão: " + ex.getMessage());
            }
        }
        return listagem;
    }
    
    public void venderProduto(int idProduto) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = new conectaDAO().connectDB();
            String sql = "UPDATE produtos SET status = ? WHERE id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, "Vendido");
            stmt.setInt(2, idProduto);

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Produto vendido com sucesso.");
            } else {
                System.out.println("Produto não encontrado.");
            }
        } catch (SQLException erro) {
            System.out.println("Erro ao vender produto: " + erro.getMessage());
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.out.println("Erro ao fechar conexão: " + e.getMessage());
            }
        }
    }
    
      public ArrayList<ProdutosDTO> listarProdutosVendidos() {
        ArrayList<ProdutosDTO> listaProdutosVendidos = new ArrayList<>();
        String sql = "SELECT * FROM produtos WHERE status = 'Vendido'";

        try (Connection conn = new conectaDAO().connectDB();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                ProdutosDTO produto = new ProdutosDTO();
                produto.setId(rs.getInt("id"));
                produto.setNome(rs.getString("nome"));
                produto.setValor(rs.getInt("valor"));
                produto.setStatus(rs.getString("status"));
                listaProdutosVendidos.add(produto);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar produtos vendidos: " + e.getMessage());
        }

        return listaProdutosVendidos;
    }
      
}

