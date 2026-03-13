package com.solvd.pages.common;

import com.solvd.utils.TimeoutConstants;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ProductPageBase extends AbstractPage {

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

    @FindBy(xpath = "//*[@content-desc='test-Cart']")
    private ExtendedWebElement cartButton;

    @FindBy(xpath = "//*[@content-desc='test-Menu']")
    private ExtendedWebElement menuButton;

    @FindBy(xpath = "//*[@content-desc='test-LOGOUT']")
    private ExtendedWebElement logoutButton;

    public ProductPageBase(WebDriver driver) {
        super(driver);
        setUiLoadedMarker(firstProductCard);
    }

    public boolean isProductCardDisplayed() {
        return firstProductCard.isElementPresent(TimeoutConstants.LONG_TIMEOUT);
    }

    public boolean areFirstProductCardsContentDisplayed(int count) {
        if (productCards.size() < count
                || productTitles.size() < count
                || productPrices.size() < count) {
            return false;
        }

        for (int i = 0; i < count; i++) {
            if (!productCards.get(i).isElementPresent(TimeoutConstants.SHORT_TIMEOUT)
                    || !productTitles.get(i).isElementPresent(TimeoutConstants.SHORT_TIMEOUT)
                    || !productPrices.get(i).isElementPresent(TimeoutConstants.SHORT_TIMEOUT)) {
                return false;
            }
        }

        return true;
    }

    public String getFirstProductTitleText() {
        firstProductTitle.isElementPresent(TimeoutConstants.SHORT_TIMEOUT);
        return firstProductTitle.getText().trim().replaceAll("\\s+", " ");
    }

    public ProductDetailPageBase openFirstProductDetails() {
        firstProductCard.click();
        return initPage(getDriver(), ProductDetailPageBase.class);
    }

    public void addFirstProductToCart() {
        firstAddToCartButton.click();
    }

    public boolean isFirstProductButtonUpdatedToRemoveState() {
        return firstRemoveButton.isElementPresent(TimeoutConstants.SHORT_TIMEOUT);
    }

    public boolean isCartBadgeCountDisplayed(int expectedCount) {
        return !getDriver().findElements(
                By.xpath("//*[@content-desc='test-Cart']//*[@text='" + expectedCount + "']")
        ).isEmpty();
    }

    public String getFirstProductPriceText() {
        firstProductPrice.isElementPresent(TimeoutConstants.SHORT_TIMEOUT);
        return firstProductPrice.getText().trim();
    }

    public CartPageBase openCart() {
        cartButton.click();
        return initPage(getDriver(), CartPageBase.class);
    }

    public LoginPageBase logoutFromApplication() {
        menuButton.click();
        logoutButton.click();
        return initPage(getDriver(), LoginPageBase.class);
    }
}