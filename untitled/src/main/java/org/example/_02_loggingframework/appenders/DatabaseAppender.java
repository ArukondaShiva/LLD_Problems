package org.example._02_loggingframework.appenders;

import org.example._02_loggingframework.core.LogAppender;
import org.example._02_loggingframework.core.LogFormatter;
import org.example._02_loggingframework.core.LogLevel;
import org.example._02_loggingframework.core.LogMessage;
import org.example._02_loggingframework.formatters.SimpleFormatter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseAppender implements LogAppender {


    private LogLevel level;
    private LogFormatter formatter;
    private Connection connection;
    private String tableName;
    private PreparedStatement insertStatement;


    public DatabaseAppender(String tableName){
        this(tableName,LogLevel.DEBUG);
    }

    public DatabaseAppender(String tableName,LogLevel level){
        this.tableName = tableName;
        this.level = level;
        this.formatter = new SimpleFormatter();
        this.connection = null;
    }


    public void setConnection(Connection connection){
        this.connection = connection;
        initializeStatement();
    }


    private void initializeStatement(){
        if(connection!=null){
            try{
                String sql = "INSERT INTO " + tableName + " (timestamp, level, message, source) VALUES (?, ?, ?, ?)";
                insertStatement = connection.prepareStatement(sql);
            }catch (SQLException e){
                System.err.println("Failed to initialize DatabaseAppender: " + e.getMessage());
            }
        }
    }


    @Override
    public void append(LogMessage message) {

        if(isEnabled(message.getLevel()) || connection==null || insertStatement==null){
            return;
        }

        try {
            insertStatement.setTimestamp(1, java.sql.Timestamp.from(message.getTimestamp()));
            insertStatement.setString(2, message.getLevel().toString());
            insertStatement.setString(3, message.getMessage());
            insertStatement.setString(4, message.getSource());
            insertStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Failed to write to database: " + e.getMessage());
        }

    }

    @Override
    public void setLevel(LogLevel level) {
        this.level = level;
    }

    @Override
    public LogLevel getLevel() {
        return level;
    }

    @Override
    public boolean isEnabled(LogLevel level) {
        return level.isGreaterOrEqual(this.level);
    }

    @Override
    public void setFormatter(LogFormatter formatter) {
        this.formatter = formatter;
    }

    @Override
    public LogFormatter getFormatter() {
        return formatter;
    }

    public String getTableName(){
        return tableName;
    }

    public void close(){
        if(insertStatement!=null){
            try{
                insertStatement.close();
            }catch (SQLException e){
                System.err.println("Failed to close statement: " + e.getMessage());
            }
        }
    }


}
