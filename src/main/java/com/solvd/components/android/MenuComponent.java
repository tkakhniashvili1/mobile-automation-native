package com.solvd.components.android;

import com.solvd.components.common.MenuComponentBase;
import com.solvd.pages.common.LoginPageBase;
import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@DeviceType(pageType = DeviceType.Type.ANDROID_PHONE, parentClass = MenuComponentBase.class)
public class MenuComponent extends MenuComponentBase {

    private static final Logger LOGGER = LoggerFactory.getLogger(MenuComponent.class);

    @AndroidFindBy(accessibility = "test-LOGOUT")
    private ExtendedWebElement logoutButton;

    public MenuComponent(WebDriver driver) {
        super(driver);
    }

    @Override
    public LoginPageBase logout() {
        LOGGER.info("Logging out from application");
        logoutButton.click();
        return initPage(getDriver(), LoginPageBase.class);
    }
}