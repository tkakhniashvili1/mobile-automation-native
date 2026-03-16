package com.solvd.components.ios;

import com.solvd.components.common.HeaderComponentBase;
import com.solvd.components.common.MenuComponentBase;
import com.solvd.pages.common.CartPageBase;
import com.solvd.utils.TimeoutConstants;
import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@DeviceType(pageType = DeviceType.Type.IOS_PHONE, parentClass = HeaderComponentBase.class)
public class HeaderComponent extends HeaderComponentBase {

    private static final Logger LOGGER = LoggerFactory.getLogger(HeaderComponent.class);

    @FindBy(xpath = "//*[@name='test-Menu']")
    private ExtendedWebElement menuButton;

    @FindBy(xpath = "//*[@name='test-Cart']")
    private ExtendedWebElement cartButton;

    @FindBy(xpath = "//*[@name='test-Cart badge']")
    private ExtendedWebElement cartBadge;

    public HeaderComponent(WebDriver driver) {
        super(driver);
    }

    @Override
    public CartPageBase openCart() {
        LOGGER.info("Opening cart page");
        cartButton.click();
        return initPage(getDriver(), CartPageBase.class);
    }

    @Override
    public boolean isCartBadgeCountDisplayed(int expectedCount) {
        if (cartBadge.isElementPresent(TimeoutConstants.SHORT_TIMEOUT)) {
            return cartBadge.getText().trim().equals(String.valueOf(expectedCount));
        }

        return !getDriver().findElements(
                By.xpath("//*[@name='" + expectedCount + "']")
        ).isEmpty();
    }

    @Override
    public MenuComponentBase openMenu() {
        LOGGER.info("Opening side menu");
        menuButton.click();
        return initPage(getDriver(), MenuComponentBase.class);
    }
}