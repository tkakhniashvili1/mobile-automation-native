package com.solvd.pages.ios;

import com.solvd.pages.common.LoginPageBase;
import com.solvd.pages.common.ProductPageBase;
import com.solvd.utils.TimeoutConstants;
import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@DeviceType(pageType = DeviceType.Type.IOS_PHONE, parentClass = LoginPageBase.class)
public class LoginPage extends LoginPageBase {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginPage.class);

    @iOSXCUITFindBy(accessibility = "test-Username")
    private ExtendedWebElement usernameInput;

    @iOSXCUITFindBy(accessibility = "test-Password")
    private ExtendedWebElement passwordInput;

    @iOSXCUITFindBy(accessibility = "test-LOGIN")
    private ExtendedWebElement loginButton;

    @iOSXCUITFindBy(accessibility = "test-Error message")
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
    public boolean isLoginScreenDisplayed() {
        return loginButton.isElementPresent(TimeoutConstants.LONG_TIMEOUT);
    }

    @Override
    public String getErrorMessageText() {
        errorMessageText.isElementPresent(TimeoutConstants.SHORT_TIMEOUT);
        return errorMessageText.getText().trim();
    }
}