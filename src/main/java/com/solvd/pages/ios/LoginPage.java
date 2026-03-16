package com.solvd.pages.ios;

import com.solvd.pages.common.LoginPageBase;
import com.solvd.pages.common.ProductPageBase;
import com.solvd.utils.TimeoutConstants;
import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@DeviceType(pageType = DeviceType.Type.IOS_PHONE, parentClass = LoginPageBase.class)
public class LoginPage extends LoginPageBase {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginPage.class);

    @FindBy(xpath = "//*[@name='test-Username']")
    private ExtendedWebElement usernameInput;

    @FindBy(xpath = "//*[@name='test-Password']")
    private ExtendedWebElement passwordInput;

    @FindBy(xpath = "//*[@name='test-LOGIN']")
    private ExtendedWebElement loginButton;

    @FindBy(xpath = "//*[@name='test-Error message']")
    private ExtendedWebElement errorMessageText;

    public LoginPage(WebDriver driver) {
        super(driver);
        setUiLoadedMarker(loginButton);
    }

    @Override
    public ProductPageBase login(String username, String password) {
        LOGGER.info("Logging in with username: {}", username);
        usernameInput.type(username);
        passwordInput.type(password);
        loginButton.click();
        LOGGER.info("Login button clicked for username: {}", username);
        return initPage(getDriver(), ProductPageBase.class);
    }

    @Override
    public LoginPageBase loginExpectingFailure(String username, String password) {
        LOGGER.info("Attempting negative login with username: {}", username);
        usernameInput.type(username);
        passwordInput.type(password);
        loginButton.click();
        LOGGER.info("Login button clicked for negative login scenario");
        return initPage(getDriver(), LoginPageBase.class);
    }

    @Override
    public boolean isLoginScreenDisplayed() {
        return usernameInput.isElementPresent(TimeoutConstants.LONG_TIMEOUT)
                && passwordInput.isElementPresent(TimeoutConstants.LONG_TIMEOUT)
                && loginButton.isElementPresent(TimeoutConstants.LONG_TIMEOUT);
    }

    @Override
    public String getErrorMessageText() {
        errorMessageText.isElementPresent(TimeoutConstants.SHORT_TIMEOUT);
        return errorMessageText.getText().trim();
    }
}