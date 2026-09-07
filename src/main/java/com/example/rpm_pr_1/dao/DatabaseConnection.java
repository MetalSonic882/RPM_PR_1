package com.example.rpm_pr_1.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    // Параметры подключения к вашей БД MySQL
    private static final String URL = "jdbc:mysql://localhost:3306/student_db?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    private static final String USER = "root";       // Ваш логин от MySQL
    private static final String PASSWORD = "KJ{t,fysq123";   // Ваш пароль от MySQL

    private static Connection connection = null;

    // Приватный конструктор, чтобы нельзя было создать объект через new
    private DatabaseConnection() {}

    public static Connection getConnection() throws SQLException {
        // Если подключения еще нет или оно было закрыто, создаем новое
        if (connection == null || connection.isClosed()) {
            try {
                // Явная загрузка драйвера MySQL (необходима для некоторых версий)
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
            } catch (ClassNotFoundException e) {
                System.err.println("Драйвер MySQL JDBC не найден!");
                e.printStackTrace();
                throw new SQLException(e);
            }
        }
        return connection;
    }

    // Метод для безопасного закрытия подключения при выходе из приложения
    public static void closeConnection() {
        if (connection != null) {
            try {
                if (!connection.isClosed()) {
                    connection.close();
                    System.out.println("Подключение к БД успешно закрыто.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
