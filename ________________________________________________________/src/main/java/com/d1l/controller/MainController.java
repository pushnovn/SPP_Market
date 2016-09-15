package com.d1l.controller;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class MainController extends ActionSupport {
    @Override
    public String execute() throws Exception {
        return Action.SUCCESS;
    }

    public String about() {
        return Action.SUCCESS;
    }

}