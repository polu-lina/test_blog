package org.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage {
    public WebDriver driver;
    public MainPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }


    @FindBy(xpath = "//*[@id=\"t1\"]")
    private WebElement newTitleField;

    @FindBy(xpath = "//*[@id=\"t2\"]")
    private WebElement newTextField;

    @FindBy(xpath = "//*[@id=\"t3\"]")
    private WebElement searchField;

    @FindBy(xpath = "/html/body/div[1]/fieldset/form/input[3]")
    private WebElement saveBtn;

    @FindBy(xpath = "/html/body/div[2]/fieldset/form/input[2]")
    private WebElement searchBtn;

    @FindBy(xpath = "/html/body/ul/form/input")
    private WebElement deleteBtn;

    @FindBy(xpath = "/html/body/p/span")
    private WebElement message;

    public String getMessage(){
        return message.getText();
    }
    public void addTitle(String title) {
        newTitleField.sendKeys(title);
    }

    public void addText(String text){
        newTextField.sendKeys(text);
    }

    public void clickSaveBtn(){
        saveBtn.click();
    }

    public void addSearchText(String text){
        searchField.sendKeys(text);
    }

    public void clickSearchBtn(){
        searchBtn.click();
    }

    public void clickDeleteBtn(){
        deleteBtn.click();
    }
}
