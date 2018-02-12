package com.DeStilleGast.YAACH4DB.Internal;

import com.DeStilleGast.YAACH4DB.Interfaces.ILogger;

/**
 * Created by DeStilleGast on 12-2-2018.
 */
public class NoLogger implements ILogger {

    @Override
    public void info(String message) { }

    @Override
    public void ok(String message) { }

    @Override
    public void error(String message) { }
}
