package com.solvd.pages.android;

import com.solvd.pages.common.LoginPageBase;
import com.solvd.pages.common.ProductPageBase;
import com.solvd.utils.TimeoutConstants;
import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

@DeviceType(pageType = DeviceType.Type.ANDROID_PHONE, parentClass = LoginPageBase.class)
public class LoginPage extends LoginPageBase {

    @FindBy(xpath = "//*[@content-desc='test-Username']")
    private ExtendedWebElement usernameInput;

    @FindBy(xpath = "//*[@content-desc='test-Password']")
    private ExtendedWebElement passwordInput;

    @FindBy(xpath = "//*[@content-desc='test-LOGIN']")
    private ExtendedWebElement loginButton;

    @FindBy(xpath = "//*[@content-desc='test-Error message']//android.widget.TextView")
    private ExtendedWebElement errorMessageText;

    public LoginPage(WebDriver driver) {
        super(driver);
        setUiLoadedMarker(loginButton);
    }

    @Override
    public ProductPageBase login(String username, String password) {
        usernameInput.type(username);
        passwordInput.type(password);
        loginButton.click();
        return initPage(getDriver(), ProductPageBase.class);
    }

    @Override
    public LoginPageBase loginExpectingFailure(String username, String password) {
        usernameInput.type(username);
        passwordInput.type(password);
        loginButton.click();
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