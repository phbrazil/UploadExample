/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcibrasil.upload.dao.insert;

/**
 *
 * @author paulo.bezerra
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class insert {

    public void insert(String filePath) {
        
        System.out.println(filePath);

        conexao bancoconexao = new conexao();

        //String filePath = "C:/uploads/mci_brazil.PNG";

        try {
            Connection conexao = bancoconexao.getConnection();

            String sql = "INSERT INTO files (emailuser, date, file) values (?, ?, ?)";
            PreparedStatement statement = conexao.prepareStatement(sql);
            statement.setString(1, "paulo.bezerra@mci-group.com");
            statement.setString(2, "now()");
            InputStream inputStream = new FileInputStream(new File(filePath));
            statement.setBlob(3, inputStream);

            int row = statement.executeUpdate();
            if (row > 0) {
                System.out.println("A file was inserted with success.");
            }
            conexao.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
