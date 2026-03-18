package com.solvd.pages.android;

import com.solvd.components.android.ProductListItemComponent;
import com.solvd.components.common.HeaderComponentBase;
import com.solvd.components.common.ProductListItemComponentBase;
import com.solvd.pages.common.ProductPageBase;
import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

@DeviceType(pageType = DeviceType.Type.ANDROID_PHONE, parentClass = ProductPageBase.class)
public class ProductPage extends ProductPageBase {

    @AndroidFindBy(uiAutomator = "new UiSelector().description(\"test-Cart drop zone\")")
    private ExtendedWebElement productPageMarker;

    @FindBy(xpath = "//*[@content-desc='test-Item']")
    private List<ProductListItemComponent> productItems;

    public ProductPage(WebDriver driver) {
        super(driver);
        this.header = initPage(driver, HeaderComponentBase.class);
    }

    @Override
    public boolean isPageOpened() {
        return productPageMarker.isElementPresent();
    }

    @Override
    public List<ProductListItemComponentBase> getProductItems() {
        return new ArrayList<>(productItems);
    }
}