package com.DeStilleGast.YAACH4DB.Interfaces;

/**
 * Created by DeStilleGast on 10-2-2018.
 */
public interface ILogger {
    void info(String message); // Tells what it is doing
    void ok(String message); // Tells something did well
    void error(String message); // Tells something has failed
}
