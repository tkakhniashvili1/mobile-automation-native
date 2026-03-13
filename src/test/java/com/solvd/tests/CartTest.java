package com.solvd.tests;

import com.solvd.pages.common.CartPageBase;
import com.solvd.pages.common.LoginPageBase;
import com.solvd.pages.common.ProductPageBase;
import com.zebrunner.carina.core.AbstractTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CartTest extends AbstractTest {

    @Test
    public void verifyAddedProductIsShownInCart() {
        LoginPageBase loginPage = initPage(getDriver(), LoginPageBase.class);

        ProductPageBase productPage = loginPage.login("standard_user", "secret_sauce");
        Assert.assertTrue(productPage.isProductCardDisplayed(), "Product list is not displayed");

        String expectedProductName = productPage.getFirstProductTitleText();
        String expectedProductPrice = productPage.getFirstProductPriceText();

        productPage.addFirstProductToCart();
        CartPageBase cartPage = productPage.openCart();

        Assert.assertTrue(cartPage.isCartPageDisplayed(), "Cart screen is not opened");
        Assert.assertTrue(cartPage.isAddedProductDisplayedInCart(expectedProductName),
                "Added product is not displayed in the cart");
        Assert.assertEquals(cartPage.getFirstCartItemTitleText(), expectedProductName,
                "Product name does not match the selected item");
        Assert.assertEquals(cartPage.getFirstCartItemPriceText(), expectedProductPrice,
                "Product price does not match the selected item");
    }

    @Test
    public void verifyProductCanBeRemovedFromCart() {
        LoginPageBase loginPage = initPage(getDriver(), LoginPageBase.class);

        ProductPageBase productPage = loginPage.login("standard_user", "secret_sauce");
        Assert.assertTrue(productPage.isProductCardDisplayed(), "Product list is not displayed");

        String removedProductName = productPage.getFirstProductTitleText();

        productPage.addFirstProductToCart();
        CartPageBase cartPage = productPage.openCart();

        Assert.assertTrue(cartPage.isCartPageDisplayed(), "Cart screen is not opened");

        cartPage.removeFirstProductFromCart();

        Assert.assertTrue(cartPage.isCartBadgeNotDisplayed(), "Cart badge is not updated");
        Assert.assertTrue(cartPage.isRemovedItemNotDisplayed(removedProductName),
                "Removed item is still displayed in the cart");
    }
}