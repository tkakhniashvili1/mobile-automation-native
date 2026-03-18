package com.solvd.pages.android;

import com.solvd.pages.common.LoginPageBase;
import com.solvd.pages.common.ProductPageBase;
import com.solvd.utils.TimeoutConstants;
import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@DeviceType(pageType = DeviceType.Type.ANDROID_PHONE, parentClass = LoginPageBase.class)
public class LoginPage extends LoginPageBase {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginPage.class);

    @AndroidFindBy(accessibility = "test-Username")
    private ExtendedWebElement usernameInput;

    @AndroidFindBy(accessibility = "test-Password")
    private ExtendedWebElement passwordInput;

    @AndroidFindBy(accessibility = "test-LOGIN")
    private ExtendedWebElement loginButton;

    @AndroidFindBy(uiAutomator =
            "new UiSelector().description(\"test-Error message\")" +
                    ".childSelector(new UiSelector().className(\"android.widget.TextView\"))")
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
        return loginButton.isElementPresent(TimeoutConstants.LONG_TIMEOUT);
    }

    @Override
    public String getErrorMessageText() {
        errorMessageText.isElementPresent(TimeoutConstants.SHORT_TIMEOUT);
        return errorMessageText.getText().trim();
    }
}