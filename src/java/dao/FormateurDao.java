package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.Personne;
import model.Formateur;

public class FormateurDao implements FormateurHome {

    private Connection connection;

    public FormateurDao() {
    }
}
