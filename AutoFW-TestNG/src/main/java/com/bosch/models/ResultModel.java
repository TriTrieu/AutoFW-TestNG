package com.bosch.models;

import org.openqa.selenium.WebDriver;

public class ResultModel {
    boolean result;
    String message;
    WebDriver driver;
    String elementText;

    public ResultModel() {
        super();
        this.result = true;
        this.message = "";
        this.driver = null;
    }

    public ResultModel(boolean result){
        super();
        this.result = result;
    }

    public ResultModel(boolean result, String message){
        super();
        this.result = result;
        this.message = message;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    public String getElementText() {
        return elementText;
    }

    public void setElementText(String elementText) {
        this.elementText = elementText;
    }
}
