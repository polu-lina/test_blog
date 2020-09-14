package org.example;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class PostsTest {
    public static MainPage mainPage;
    public static WebDriver driver;

    @BeforeClass
    public static void setup() {
        System.setProperty("webdriver.chrome.driver", ConfProperties.getProperty("chromedriver"));
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(ConfProperties.getProperty("page"));
        mainPage = new MainPage(driver);
    }

    /**
     * Создание нового поста
     */
    @Test
    public void newPost(){
        String title = "some title";
        String text = "some text";
        mainPage.addTitle(title);
        mainPage.addSearchText(text);
        mainPage.clickSaveBtn();
        String message = mainPage.getMessage();
        Assert.assertEquals("Create new note: " + title + ' ' + text, message);
    }

    /**
     * Создание поста без заголовка
     */
    @Test
    public void newPostWithoutTitle(){
        String text = "some text";
        mainPage.addSearchText(text);
        mainPage.clickSaveBtn();
        String message = mainPage.getMessage();
        Assert.assertEquals("Create new note:  " + text, message);
    }

    /**
     * Создание поста без текста, выводится ошибка
     */
    @Test
    public void newPostWithoutText(){
        String title = "some title";
        mainPage.addTitle(title);
        mainPage.clickSaveBtn();
        String message = mainPage.getMessage();
        Assert.assertEquals("Add text", message);
    }

    /**
     * Пустые поля заголовка и тела поста, выводится ошибка
     */
    @Test
    public void EmptyTitleAndText(){
        mainPage.clickSaveBtn();
        String message = mainPage.getMessage();
        Assert.assertEquals("Add text", message);
    }

    /**
     * Пустое поле с поиском, выводится ошибка
     */
    @Test
    public void EmptySearchField(){
        mainPage.clickSearchBtn();
        String message = mainPage.getMessage();
        Assert.assertEquals("Add text", message);
    }

    /**
     * Поиск подстроки, которая не содержится ни в одном посте
     */
    @Test
    public void SubstrDontExist(){
        mainPage.addSearchText("abrakadabra");
        String message = mainPage.getMessage();
        Assert.assertEquals("Not found", message);
    }

    /**
     * Поиск подстроки в заголовке
     */
    @Test
    public void SearchByTitle(){
        mainPage.addSearchText("First");
        String message = mainPage.getMessage();
        Assert.assertEquals("1 notes found", message);
    }

    /**
     * Поиск подстроки в тексте
     */
    @Test
    public void SearchByText(){
        mainPage.addSearchText("Try writing something");
        String message = mainPage.getMessage();
        Assert.assertEquals("1 notes found", message);
    }

    /**
     * Поиск подстроки без учета регистра
     */
    @Test
    public void SearchCaseInsensitive(){
        mainPage.addSearchText("f");
        String message = mainPage.getMessage();
        Assert.assertEquals("1 notes found", message);
    }

    @AfterClass
    public static void Exit() {
        driver.quit();
    }
}
