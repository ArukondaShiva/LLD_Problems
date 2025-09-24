package org.example._02_loggingframework.filter;

import org.example._02_loggingframework.core.LogFilter;
import org.example._02_loggingframework.core.LogLevel;
import org.example._02_loggingframework.core.LogMessage;

public class SourceFilter implements LogFilter {

    private String sourcePattern;
    private boolean include;
    private LogLevel level;


    public SourceFilter(String sourcePattern,boolean include){
        this.sourcePattern = sourcePattern;
        this.include = include;
        this.level = LogLevel.DEBUG;
    }


    @Override
    public boolean shouldLog(LogMessage message) {
        if(message.getSource()==null){
            return !include;
        }
        boolean matches = message.getSource().contains(sourcePattern);
        return include ? matches : !matches;
    }

    @Override
    public void setLevel(LogLevel level) {
        this.level = level;
    }

    @Override
    public LogLevel getLevel() {
        return level;
    }

    public String getSourcePattern(){
        return sourcePattern;
    }

    public void setSourcePattern(String sourcePattern){
        this.sourcePattern = sourcePattern;
    }


    public boolean isInclude(){
        return include;
    }

}
