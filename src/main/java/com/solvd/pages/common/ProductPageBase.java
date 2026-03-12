package com.solvd.pages.common;

import com.solvd.utils.TimeoutConstants;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ProductPageBase extends AbstractPage {

    @FindBy(xpath = "(//*[@content-desc='test-Item'])[1]")
    private ExtendedWebElement firstProductCard;

    @FindBy(xpath = "//*[@content-desc='test-Item']")
    private List<ExtendedWebElement> productCards;

    @FindBy(xpath = "//*[@content-desc='test-Item title']")
    private List<ExtendedWebElement> productTitles;

    @FindBy(xpath = "//*[@content-desc='test-Price']")
    private List<ExtendedWebElement> productPrices;

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
}