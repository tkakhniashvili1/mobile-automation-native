package com.solvd.pages.ios;

import com.solvd.components.common.HeaderComponentBase;
import com.solvd.components.common.ProductListItemComponentBase;
import com.solvd.components.ios.ProductListItemComponent;
import com.solvd.pages.common.ProductPageBase;
import com.solvd.utils.TimeoutConstants;
import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

@DeviceType(pageType = DeviceType.Type.IOS_PHONE, parentClass = ProductPageBase.class)
public class ProductPage extends ProductPageBase {

    @iOSXCUITFindBy(accessibility = "test-Cart drop zone")
    private ExtendedWebElement productPageMarker;

    @FindBy(xpath = "//*[@name='test-Item']")
    private List<ProductListItemComponent> productItems;

    public ProductPage(WebDriver driver) {
        super(driver);
        this.header = initPage(driver, HeaderComponentBase.class);
    }

    @Override
    public boolean isPageOpened() {
        return productPageMarker.isElementPresent(TimeoutConstants.LONG_TIMEOUT);
    }

    @Override
    public List<ProductListItemComponentBase> getProductItems() {
        return new ArrayList<>(productItems);
    }
}