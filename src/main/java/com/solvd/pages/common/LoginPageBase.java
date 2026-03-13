package com.solvd.pages.common;

import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;

public abstract class LoginPageBase extends AbstractPage {

    public LoginPageBase(WebDriver driver) {
        super(driver);
    }

    public abstract ProductPageBase login(String username, String password);

    public abstract LoginPageBase loginExpectingFailure(String username, String password);

    public abstract boolean isLoginScreenDisplayed();

    public abstract String getErrorMessageText();
}