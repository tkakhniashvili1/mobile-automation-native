package com.solvd.tests;

import com.solvd.pages.common.CartPageBase;
import com.solvd.pages.common.LoginPageBase;
import com.solvd.pages.common.ProductDetailPageBase;
import com.solvd.pages.common.ProductPageBase;
import com.zebrunner.carina.core.AbstractTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ProductTest extends AbstractTest {

    @Test
    public void verifyProductDetailsPageOpensFromProductList() {
        LoginPageBase loginPage = initPage(getDriver(), LoginPageBase.class);

        ProductPageBase productPage = loginPage.login("standard_user", "secret_sauce");
        Assert.assertTrue(productPage.isProductCardOpened(), "Product list is not displayed");

        String expectedProductName = productPage.getFirstProductTitleText();
        ProductDetailPageBase productDetailPage = productPage.openFirstProductDetails();

        Assert.assertTrue(productDetailPage.isProductDetailsPageOpened(), "Product details page is not opened");
        Assert.assertTrue(productDetailPage.isSelectedProductNameDisplayed(expectedProductName),
                "Selected product name is not displayed");
        Assert.assertTrue(productDetailPage.isProductImagePriceAndDescriptionDisplayed(),
                "Product image, price, and description are not displayed");
    }

    @Test
    public void verifyProductCanBeAddedToCartFromProductList() {
        LoginPageBase loginPage = initPage(getDriver(), LoginPageBase.class);

        ProductPageBase productPage = loginPage.login("standard_user", "secret_sauce");
        Assert.assertTrue(productPage.isProductCardOpened(), "Product list is not displayed");

        String expectedProductName = productPage.getFirstProductTitleText();

        productPage.addFirstProductToCart();

        Assert.assertTrue(productPage.isCartBadgeCountDisplayed(1), "Cart badge did not increase by 1");
        Assert.assertTrue(productPage.isFirstProductButtonUpdatedToRemoveState(),
                "Button state was not updated after adding product to cart");

        CartPageBase cartPage = productPage.openCart();

        Assert.assertTrue(cartPage.isCartPageOpened(), "Cart page is not opened");
        Assert.assertTrue(cartPage.isAddedProductDisplayedInCart(expectedProductName),
                "Selected product is not added to cart");
    }
}