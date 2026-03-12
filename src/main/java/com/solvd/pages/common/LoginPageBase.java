package com.solvd.pages.common;

import com.solvd.utils.TimeoutConstants;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class LoginPageBase extends AbstractPage {

    @FindBy(xpath = "//*[@content-desc='test-Username']")
    private ExtendedWebElement usernameInput;

    @FindBy(xpath = "//*[@content-desc='test-Password']")
    private ExtendedWebElement passwordInput;

    @FindBy(xpath = "//*[@content-desc='test-LOGIN']")
    private ExtendedWebElement loginButton;

    @FindBy(xpath = "//*[@content-desc='test-Error message']//android.widget.TextView")
    private ExtendedWebElement errorMessageText;

    public LoginPageBase(WebDriver driver) {
        super(driver);
        setUiLoadedMarker(loginButton);
    }

    public ProductPageBase login(String username, String password) {
        usernameInput.type(username);
        passwordInput.type(password);
        loginButton.click();
        return initPage(getDriver(), ProductPageBase.class);
    }

    public LoginPageBase loginExpectingFailure(String username, String password) {
        usernameInput.type(username);
        passwordInput.type(password);
        loginButton.click();
        return initPage(getDriver(), LoginPageBase.class);
    }

    public boolean isLoginScreenDisplayed() {
        return usernameInput.isElementPresent(TimeoutConstants.LONG_TIMEOUT)
                && passwordInput.isElementPresent(TimeoutConstants.LONG_TIMEOUT)
                && loginButton.isElementPresent(TimeoutConstants.LONG_TIMEOUT);
    }

    public String getErrorMessageText() {
        errorMessageText.isElementPresent(TimeoutConstants.SHORT_TIMEOUT);
        return errorMessageText.getText().trim();
    }
}