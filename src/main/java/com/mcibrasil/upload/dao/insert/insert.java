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
import java.sql.ResultSet;
import java.sql.SQLException;

public class insert {

    public int insert(String filePath) {
        

        int lastid = 0;
        conexao bancoconexao = new conexao();

        //String filePath = "C:/uploads/mci_brazil.PNG";
        try {
            Connection conexao = bancoconexao.getConnection();

            String sql = "INSERT INTO files (emailuser, date, file, path) values (?, ?, ?, ?)";
            PreparedStatement statement = conexao.prepareStatement(sql);
            statement.setString(1, "paulo.bezerra@mci-group.com");
            statement.setString(2, "now()");
            InputStream inputStream = new FileInputStream(new File(filePath));
            statement.setBlob(3, inputStream);
            statement.setString(4, filePath);

            int row = statement.executeUpdate();
            java.sql.Statement st = conexao.createStatement();

            String score = "SELECT LAST_INSERT_ID() as file_id";

            ResultSet result = st.executeQuery(score);

            while (result.next()) {

                lastid = result.getInt("file_id");

            }
            if (row > 0) {
                System.out.println("Arquivo inserido com sucesso.");
            }
            conexao.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return lastid;
    }
}
