package org.com.cn.xu.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Loggers {
  private static final String LOGTYPE_NORMAL = "[NORMAL]";
  private Logger logger;
  private String type;

  public String getType() {
    return this.type.replace("[", "").replace("]", "");
  }

  private Loggers() {
    this.type = LOGTYPE_NORMAL;
  }

  private Loggers(String loggerType) {
    this.type = ("[" + loggerType + "]");
  }

  public static Loggers getLogger(String name) {
    Loggers logger = new Loggers();
    logger.logger = LoggerFactory.getLogger(name);
    return logger;
  }

  public static Loggers getLogger(String loggerType, String name) {
    Loggers logger = new Loggers(loggerType);
    logger.logger = LoggerFactory.getLogger(name);
    return logger;
  }

  public static Loggers getLogger(Class<?> clazz) {
    Loggers logger = new Loggers();
    logger.logger = LoggerFactory.getLogger(clazz);
    return logger;
  }

  public static Loggers getLogger(String loggerType, Class<?> clazz) {
    Loggers logger = new Loggers(loggerType);
    logger.logger = LoggerFactory.getLogger(clazz);
    return logger;
  }

  public void trace(Object message) {
    this.logger.trace(this.type + message);
  }
  
  public void trace(Object message, Throwable t) {
    this.logger.trace(this.type + message, t);
  }
  
  public boolean isTraceEnabled() {
    return this.logger.isTraceEnabled();
  }
  
  public void debug(Object message) {
    this.logger.debug(this.type + message);
  }

  public void debug(Object message, Throwable t) {
    this.logger.debug(this.type + message, t);
  }

  public void debug(String format, Object arg) {
    this.logger.debug(this.type + format, arg);
  }

  public void debug(String format, Object arg1, Object arg2) {
    this.logger.debug(this.type + format, arg1, arg2);
  }

  public void debug(String format, Object[] argArray) {
    this.logger.debug(this.type + format, argArray);
  }

  public void error(Object message) {
    this.logger.error(this.type + message);
  }

  public void error(Object message, Throwable t) {
    this.logger.error(this.type + message, t);
  }

  public void error(String format, Object[] argArray) {
    this.logger.error(this.type + format, argArray);
  }

  public void info(Object message) {
    this.logger.info(this.type + message);
  }

  public void info(Object message, Throwable t) {
    this.logger.info(this.type + message, t);
  }

  public boolean isDebugEnabled() {
    return this.logger.isDebugEnabled();
  }

  public boolean isInfoEnabled() {
    return this.logger.isInfoEnabled();
  }

  public void warn(Object message) {
    this.logger.warn(this.type + message);
  }

  public void warn(Object message, Throwable t) {
    this.logger.warn(this.type + message, t);
  }

  public static void main(String[] args) {
    Loggers logger = getLogger(Loggers.class);
    logger.debug("你好！debug日志");
    logger.info("你好！info日志");
    logger.warn("你好！warn日志");
    logger.error("你好！error日志");
  }
}
