/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blogics;

import java.sql.ResultSet;
import java.sql.SQLException;
import services.log.LogTypes;
import services.log.Logs;

/**
 *
 * @author Riccardo
 */
public class Campo {
    private long id;
    private String nome;
    private long id_struttura;
    private String nome_struttura;

    public String getNome_struttura() {
        return nome_struttura;
    }

    public void setNome_struttura(String nome_struttura) {
        this.nome_struttura = nome_struttura;
    }
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public long getId_struttura() {
        return id_struttura;
    }

    public void setId_struttura(long id_struttura) {
        this.id_struttura = id_struttura;
    }
    
    public Campo(long id_struttura, String nome){
        this.id_struttura = id_struttura;
        this.nome = nome;
    }
    
    public Campo(ResultSet rs){
        try {
                this.id = rs.getInt("ID");
            } catch (SQLException sqle) {
                Logs.printLog(LogTypes.WARNING, sqle.toString());
            }
        try {
            this.nome = rs.getString("name");
        } catch (SQLException sqle) {
            Logs.printLog(LogTypes.WARNING, sqle.toString());
        }
        try {
            this.id_struttura = rs.getInt("id_struttura");
        } catch (SQLException sqle) {
            Logs.printLog(LogTypes.WARNING, sqle.toString());
        }
    }
    
}
