package com.udacity.jwdnd.course1.cloudstorage.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignupPage {

    @FindBy(id = "inputFirstName")
    private WebElement firstName;

    @FindBy(id = "inputLastName")
    private WebElement lastName;

    @FindBy(id = "inputUsername")
    private WebElement userName;

    @FindBy(id = "inputPassword")
    private WebElement password;

    @FindBy(id = "submitButton")
    private WebElement submit;

    public SignupPage (final WebDriver webDriver){
        PageFactory.initElements(webDriver, this);
    }

    public void setfirstName(final String firstName){
        this.firstName.sendKeys(firstName);
    }

    public void setLastNameName(final String lastName){
        this.lastName.sendKeys(lastName);
    }

    public void setUserName(final String userName){
        this.userName.sendKeys(userName);
    }

    public void setpassword(final String password){
        this.password.sendKeys(password);
    }

    public void submit(){
        this.submit.click();
    }




}
