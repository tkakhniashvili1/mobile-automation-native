package com.solvd.components.android;

import com.solvd.components.common.HeaderComponentBase;
import com.solvd.components.common.MenuComponentBase;
import com.solvd.pages.common.CartPageBase;
import com.solvd.utils.TimeoutConstants;
import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

@DeviceType(pageType = DeviceType.Type.ANDROID_PHONE, parentClass = HeaderComponentBase.class)
public class HeaderComponent extends HeaderComponentBase {

    @FindBy(xpath = "//*[@content-desc='test-Menu']")
    private ExtendedWebElement menuButton;

    @FindBy(xpath = "//*[@content-desc='test-Cart']")
    private ExtendedWebElement cartButton;

    @FindBy(xpath = "//*[@content-desc='test-Cart badge']")
    private ExtendedWebElement cartBadge;

    public HeaderComponent(WebDriver driver) {
        super(driver);
    }

    @Override
    public CartPageBase openCart() {
        cartButton.click();
        return initPage(getDriver(), CartPageBase.class);
    }

    @Override
    public boolean isCartBadgeCountDisplayed(int expectedCount) {
        if (cartBadge.isElementPresent(TimeoutConstants.SHORT_TIMEOUT)) {
            return cartBadge.getText().trim().equals(String.valueOf(expectedCount));
        }

        return !getDriver().findElements(
                By.xpath("//android.widget.TextView[@text='" + expectedCount + "']")
        ).isEmpty();
    }

    @Override
    public MenuComponentBase openMenu() {
        menuButton.click();
        return initPage(getDriver(), MenuComponentBase.class);
    }
}