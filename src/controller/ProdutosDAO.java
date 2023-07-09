package controller;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author Adm
 */
import model.ProdutosDTO;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Set;
import javax.swing.JOptionPane;

public class ProdutosDAO {

    Connection conn;
    PreparedStatement pstm;
    ResultSet rs;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();

    public void cadastrarProduto(ProdutosDTO produto) {
        conn = new ConectaDAO().connectDB();
        try {
            pstm = conn.prepareStatement("INSERT INTO produtos (nome,valor,status) VALUES (?, ?, ?)");
            pstm.setString(1, produto.getNome());
            pstm.setDouble(2, produto.getValor());
            pstm.setString(3, produto.getStatus());
            pstm.executeUpdate();
            JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<ProdutosDTO> listarProdutos() {
        conn = new ConectaDAO().connectDB(); 

        try {
            pstm = conn.prepareStatement("SELECT * FROM produtos");
            rs = pstm.executeQuery();

            while (rs.next()) {
                ProdutosDTO produto = new ProdutosDTO();
                produto.setId(rs.getInt("id"));
                produto.setNome(rs.getString("nome"));
                produto.setValor(rs.getInt("valor"));
                produto.setStatus(rs.getString("status"));
                
                listagem.add(produto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listagem;
    }

}
