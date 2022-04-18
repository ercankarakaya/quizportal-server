package com.ercan.utils;

import org.apache.logging.log4j.Logger;
import org.springframework.boot.logging.LogLevel;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.StringJoiner;

public class LogUtils {

    private LogUtils(){throw new IllegalAccessError("LogUtils");}

    public static void log(Logger logger, LogLevel level, String message) {
        switch (level) {
            case DEBUG:
                logger.debug(message);
            case TRACE:
                logger.trace(message);
            case WARN:
                logger.warn(message);
            case ERROR:
                logger.error(message);
            case FATAL:
                logger.error(message);
            default:
                logger.info(message);
        }
    }


    public static String entry(String methodName, boolean showArgs, String[] params, Object[] args) {
        StringJoiner message = new StringJoiner(" ")
                .add("Started").add(methodName).add("method");
        if (showArgs && Objects.nonNull(params) && Objects.nonNull(args) && params.length == args.length) {
            Map<String, Object> values = new HashMap<>(params.length);
            for (int i = 0; i < params.length; i++) {
                values.put(params[i], args[i]);
            }
            message.add("with args:")
                    .add(values.toString());
        }
        return message.toString();
    }


    public static String exit(String methodName, String duration, Object result, boolean showResult, boolean showExecutionTime) {
        StringJoiner message = new StringJoiner(" ")
                .add("Finished").add(methodName).add("method");
        if (showExecutionTime) {
            message.add("in").add(duration);
        }
        if (showResult) {
            message.add("with return:").add(result.toString());
        }
        return message.toString();
    }

}
