package com.solvd.pages.android;

import com.solvd.components.common.HeaderComponentBase;
import com.solvd.pages.common.ProductDetailPageBase;
import com.solvd.pages.common.ProductPageBase;
import com.solvd.utils.TimeoutConstants;
import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.List;

@DeviceType(pageType = DeviceType.Type.ANDROID_PHONE, parentClass = ProductPageBase.class)
public class ProductPage extends ProductPageBase {

    @FindBy(xpath = "(//*[@content-desc='test-Item'])[1]")
    private ExtendedWebElement firstProductCard;

    @FindBy(xpath = "(//*[@content-desc='test-Item title'])[1]")
    private ExtendedWebElement firstProductTitle;

    @FindBy(xpath = "(//*[@content-desc='test-ADD TO CART'])[1]")
    private ExtendedWebElement firstAddToCartButton;

    @FindBy(xpath = "(//*[@content-desc='test-REMOVE'])[1]")
    private ExtendedWebElement firstRemoveButton;

    @FindBy(xpath = "//*[@content-desc='test-Item']")
    private List<ExtendedWebElement> productCards;

    @FindBy(xpath = "//*[@content-desc='test-Item title']")
    private List<ExtendedWebElement> productTitles;

    @FindBy(xpath = "//*[@content-desc='test-Price']")
    private List<ExtendedWebElement> productPrices;

    @FindBy(xpath = "(//*[@content-desc='test-Price'])[1]")
    private ExtendedWebElement firstProductPrice;

    @FindBy(xpath = "//*[@content-desc='test-Item']//android.widget.ImageView")
    private List<ExtendedWebElement> productImages;

    public ProductPage(WebDriver driver) {
        super(driver);
        setUiLoadedMarker(firstProductCard);
        header = initPage(getDriver(), HeaderComponentBase.class);
    }

    @Override
    public boolean isProductCardDisplayed() {
        return firstProductCard.isElementPresent(TimeoutConstants.LONG_TIMEOUT);
    }

    @Override
    public boolean areFirstProductCardsContentDisplayed(int count) {
        if (productCards.size() < count
                || productTitles.size() < count
                || productPrices.size() < count
                || productImages.size() < count) {
            return false;
        }

        for (int i = 0; i < count; i++) {
            if (!productCards.get(i).isElementPresent(TimeoutConstants.SHORT_TIMEOUT)
                    || !productTitles.get(i).isElementPresent(TimeoutConstants.SHORT_TIMEOUT)
                    || !productPrices.get(i).isElementPresent(TimeoutConstants.SHORT_TIMEOUT)
                    || !productImages.get(i).isElementPresent(TimeoutConstants.SHORT_TIMEOUT)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public String getFirstProductTitleText() {
        firstProductTitle.isElementPresent(TimeoutConstants.SHORT_TIMEOUT);
        return firstProductTitle.getText().trim().replaceAll("\\s+", " ");
    }

    @Override
    public ProductDetailPageBase openFirstProductDetails() {
        firstProductCard.click();
        return initPage(getDriver(), ProductDetailPageBase.class);
    }

    @Override
    public void addFirstProductToCart() {
        firstAddToCartButton.click();
    }

    @Override
    public boolean isFirstProductButtonUpdatedToRemoveState() {
        return firstRemoveButton.isElementPresent(TimeoutConstants.SHORT_TIMEOUT);
    }

    @Override
    public String getFirstProductPriceText() {
        firstProductPrice.isElementPresent(TimeoutConstants.SHORT_TIMEOUT);
        return firstProductPrice.getText().trim();
    }
}