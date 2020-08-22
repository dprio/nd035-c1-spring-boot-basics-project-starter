package com.udacity.jwdnd.course1.cloudstorage.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {


    @FindBy(id = "inputUsername")
    private WebElement username;

    @FindBy(id = "inputPassword")
    private WebElement password;

    @FindBy(id = "submitButton")
    private WebElement submitButton;

    @FindBy(id = "signupLink")
    private WebElement signupLink;

    public LoginPage (final WebDriver webDriver){
        PageFactory.initElements(webDriver, this);
    }

    public void setUsername(final String username) {
        this.username.sendKeys(username);
    }

    public void setPassword(final String password){
        this.password.sendKeys(password);
    }

    public void submitLogin(){
        this.submitButton.click();
    }

    public void clickSignupLink(){
        this.signupLink.click();
    }
}
