package com.DeStilleGast.YAACH4DB.Internal;

import com.DeStilleGast.YAACH4DB.Interfaces.ILogger;

/**
 * Created by DeStilleGast on 10-2-2018.
 */
public class SimpleLogger implements ILogger {
    @Override
    public void info(String message) {
        System.out.println(String.format("[INFO] %s", message));
    }

    @Override
    public void ok(String message) {
        System.out.println(String.format("[OK] %s", message));
    }

    @Override
    public void error(String message) {
        System.out.println(String.format("[ERROR] %s", message));
    }
}
