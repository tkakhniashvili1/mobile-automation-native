package com.solvd.components.android;

import com.solvd.components.common.MenuComponentBase;
import com.solvd.pages.common.LoginPageBase;
import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

@DeviceType(pageType = DeviceType.Type.ANDROID_PHONE, parentClass = MenuComponentBase.class)
public class MenuComponent extends MenuComponentBase {

    @FindBy(xpath = "//*[@content-desc='test-LOGOUT']")
    private ExtendedWebElement logoutButton;

    public MenuComponent(WebDriver driver) {
        super(driver);
    }

    @Override
    public LoginPageBase logout() {
        logoutButton.click();
        return initPage(getDriver(), LoginPageBase.class);
    }
}