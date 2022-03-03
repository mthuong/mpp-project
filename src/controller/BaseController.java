package controller;

import dataaccess.DataAccess;

public class BaseController {
    protected final DataAccess dataAccess;

    public BaseController(DataAccess dataAccess) {
        this.dataAccess = dataAccess;
    }
}
