package com.udacity.jwdnd.course1.cloudstorage.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

    @FindBy(id = "logoutButton")
    private WebElement logoutButton;

    @FindBy(id = "nav-notes-tab")
    private WebElement notesTab;

    @FindBy(id = "addNoteButton")
    private WebElement addNoteButton;

    @FindBy(id = "note-title")
    private WebElement modalNoteTitle;

    @FindBy(id = "note-description")
    private WebElement modalNoteDescription;

    @FindBy(id = "noteSaveChanges")
    private WebElement modalNoteSubmit;

    @FindBy(id = "nav-credentials-tab")
    private WebElement credentialsTab;

    @FindBy(id = "addCredentialButton")
    private WebElement addCredentialButton;

    @FindBy(id = "credential-url")
    private WebElement modalCredentialUrl;

    @FindBy(id = "credential-username")
    private WebElement modalCredentialUsername;

    @FindBy(id = "credential-password")
    private WebElement modalCredentialPassword;

    @FindBy(id = "credentialSaveChanges")
    private WebElement credentialSaveChanges;



    public HomePage(final WebDriver webDriver){
        PageFactory.initElements(webDriver,this);
    }

    public void clickLogoutButton(){
        this.logoutButton.click();
    }

    public void clickNotesTab(){
        this.notesTab.click();
    }

    public void clickAddNewNote(){
        this.addNoteButton.click();
    }

    public void setNoteTitle(final String noteTitle){
        this.modalNoteTitle.clear();
        this.modalNoteTitle.sendKeys(noteTitle);
    }

    public void setNoteDescription(final String noteDescription){
        this.modalNoteDescription.clear();
        this.modalNoteDescription.sendKeys(noteDescription);
    }

    public void submitNotesModal(){
        this.modalNoteSubmit.click();
    }

    public void clickCredentialTab(){
        this.credentialsTab.click();
    }

    public void clickAddNewCredential(){
        this.addCredentialButton.click();
    }

    public void setModalCredentialUrl(final String url){
        this.modalCredentialUrl.clear();
        this.modalCredentialUrl.sendKeys(url);
    }

    public void setModalCredentialUsername(final String username){
        this.modalCredentialUsername.clear();
        this.modalCredentialUsername.sendKeys(username);
    }

    public void setModalCredentialPassword(final String password){
        this.modalCredentialPassword.clear();
        this.modalCredentialPassword.sendKeys(password);
    }

    public String getModalCredentialPassword(){
        return this.modalCredentialPassword.getText();
    }

    public void submitCredentialsModal(){
        this.credentialSaveChanges.click();
    }

}
