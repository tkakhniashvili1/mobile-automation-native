package com.solvd.pages.ios;

import com.solvd.components.common.HeaderComponentBase;
import com.solvd.pages.common.ProductDetailPageBase;
import com.solvd.pages.common.ProductPageBase;
import com.solvd.utils.TimeoutConstants;
import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@DeviceType(pageType = DeviceType.Type.IOS_PHONE, parentClass = ProductPageBase.class)
public class ProductPage extends ProductPageBase {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductPage.class);

    @FindBy(xpath = "**/XCUIElementTypeOther[`name == \"test-Item\"`][1]")
    private ExtendedWebElement firstProductCard;

    @FindBy(xpath = "(//*[@name='test-Item title'])[1]")
    private ExtendedWebElement firstProductTitle;

    @FindBy(xpath = "(//*[@name='test-ADD TO CART'])[1]")
    private ExtendedWebElement firstAddToCartButton;

    @FindBy(xpath = "(//*[@name='test-REMOVE'])[1]")
    private ExtendedWebElement firstRemoveButton;

    @FindBy(xpath = "**/XCUIElementTypeOther[`name == \"test-Item\"`]")
    private List<ExtendedWebElement> productCards;

    @FindBy(xpath = "//*[@name='test-Item title']")
    private List<ExtendedWebElement> productTitles;

    @FindBy(xpath = "//*[@name='test-Price']")
    private List<ExtendedWebElement> productPrices;

    @FindBy(xpath = "(//*[@name='test-Price'])[1]")
    private ExtendedWebElement firstProductPrice;

    @FindBy(xpath = "//*[@name='test-Image Container']")
    private List<ExtendedWebElement> productImages;

    public ProductPage(WebDriver driver) {
        super(driver);
        setUiLoadedMarker(firstProductCard);
        header = initPage(getDriver(), HeaderComponentBase.class);
    }

    @Override
    public boolean isProductCardOpened() {
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
        String productName = getFirstProductTitleText();
        LOGGER.info("Opening product details for product: {}", productName);
        firstProductCard.click();
        return initPage(getDriver(), ProductDetailPageBase.class);
    }

    @Override
    public void addFirstProductToCart() {
        String productName = getFirstProductTitleText();
        LOGGER.info("Adding product to cart: {}", productName);
        firstAddToCartButton.click();
        LOGGER.info("Add to cart clicked for product: {}", productName);
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