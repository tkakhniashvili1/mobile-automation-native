package com.solvd.components.android;

import com.solvd.components.common.ProductListItemComponentBase;
import com.solvd.pages.common.ProductDetailPageBase;
import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

@DeviceType(pageType = DeviceType.Type.ANDROID_PHONE, parentClass = ProductListItemComponentBase.class)
public class ProductListItemComponent extends ProductListItemComponentBase {

    @FindBy(xpath = ".//*[@content-desc='test-Item title']")
    private ExtendedWebElement productTitle;

    @FindBy(xpath = ".//*[@content-desc='test-Price']")
    private ExtendedWebElement productPrice;

    @FindBy(xpath = ".//android.widget.ImageView")
    private ExtendedWebElement productImage;

    @FindBy(xpath = ".//*[@content-desc='test-ADD TO CART']")
    private ExtendedWebElement addToCartButton;

    @FindBy(xpath = ".//*[@content-desc='test-REMOVE']")
    private ExtendedWebElement removeButton;

    public ProductListItemComponent(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    @Override
    public boolean isProductCardOpened() {
        return isUIObjectPresent();
    }

    @Override
    public boolean isProductTitleDisplayed() {
        return productTitle.isElementPresent();
    }

    @Override
    public boolean isProductPriceDisplayed() {
        return productPrice.isElementPresent();
    }

    @Override
    public boolean isProductImageDisplayed() {
        return productImage.isElementPresent();
    }

    @Override
    public String getProductTitleText() {
        return productTitle.getText();
    }

    @Override
    public String getProductPriceText() {
        return productPrice.getText();
    }

    @Override
    public ProductDetailPageBase openProductDetails() {
        productTitle.click();
        return initPage(getDriver(), ProductDetailPageBase.class);
    }

    @Override
    public void addProductToCart() {
        addToCartButton.click();
    }

    @Override
    public boolean isButtonUpdatedToRemoveState() {
        return removeButton.isElementPresent();
    }

    @Override
    public boolean isValidProductCard() {
        return isProductCardOpened()
                && isProductTitleDisplayed()
                && isProductPriceDisplayed();
    }
}