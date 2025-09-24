package org.example._02_loggingframework.core;

import com.sun.security.jgss.GSSUtil;
import org.example._02_loggingframework.appenders.ConsoleAppender;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LoggerImpl implements Logger{


    private final String name;
    private LogLevel level;
    private final List<LogAppender> appenders;
    private final List<LogFilter> filters;


    public LoggerImpl(){
        this("DefaultLogger");
    }

    public LoggerImpl(String name){
        this(name,true);
    }

    public LoggerImpl(String name,boolean addDefaultAppender){

        this.name = name;
        this.level = LogLevel.DEBUG;
        this.appenders = Collections.synchronizedList(new ArrayList<>());
        this.filters = Collections.synchronizedList(new ArrayList<>());

        if(addDefaultAppender){
            addAppender(new ConsoleAppender());
        }
    }

    public LoggerImpl(String name,LogConfiguration config){
        this(name);
        this.level = config.getRootLevel();
    }

    @Override
    public void debug(String message) {
        log(LogLevel.DEBUG,message);
    }

    @Override
    public void info(String message) {
        log(LogLevel.INFO,message);
    }

    @Override
    public void warning(String message) {
        log(LogLevel.WARNING,message);
    }

    @Override
    public void error(String message) {
        log(LogLevel.ERROR,message);
    }

    @Override
    public void fatal(String message) {
        log(LogLevel.FATAL,message);
    }

    @Override
    public synchronized void log(LogLevel level, String message) {

        System.out.println("=="+level+"=="+this.level+"==");

        // Check if message should be logged based on level
        if (!level.isGreaterOrEqual(this.level)) {
            return;
        }

        // Create log message
        LogMessage logMessage = new LogMessage.Builder()
                .level(level)
                .message(message)
                .source(getClass().getName())
                .build();


        // Apply filters
        for (LogFilter filter : filters) {
            if (!filter.shouldLog(logMessage)) {
                return; // Message filtered out
            }
        }


        // Send to all appenders
        for (LogAppender appender : appenders) {
            if (appender.isEnabled(level)) {
                appender.append(logMessage);
            }
        }


    }

    @Override
    public void setLevel(LogLevel level) {
        this.level = level;
    }

    @Override
    public void addAppender(LogAppender appender) {
        appenders.add(appender);
    }

    @Override
    public void addFilter(LogFilter filter) {
        filters.add(filter);
    }

    @Override
    public void removeFilter(LogFilter filter) {
        filters.remove(filter);
    }

    @Override
    public List<LogAppender> getAppenders() {
        return new ArrayList<>(appenders);
    }

    @Override
    public List<LogFilter> getFilters() {
        return new ArrayList<>(filters);
    }
}
