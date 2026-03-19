package com.solvd.tests;

import com.solvd.components.common.CartItemComponentBase;
import com.solvd.components.common.ProductListItemComponentBase;
import com.solvd.pages.common.CartPageBase;
import com.solvd.pages.common.ProductPageBase;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CartTest extends BaseMobileTest {

    @Test
    public void verifyAddedProductIsShownInCart() {
        ProductPageBase productPage = loginAsStandardUser();
        ProductListItemComponentBase firstProductItem = productPage.getFirstProductItem();

        String expectedProductName = firstProductItem.getProductTitleText();
        String expectedProductPrice = firstProductItem.getProductPriceText();

        firstProductItem.addProductToCart();
        CartPageBase cartPage = productPage.openCart();

        Assert.assertTrue(cartPage.isPageOpened(), "Cart page is not opened");

        CartItemComponentBase firstCartItem = cartPage.getCartItem(0);

        Assert.assertTrue(cartPage.isPageOpened(), "Cart page is not opened");
        Assert.assertEquals(
                firstCartItem.getProductTitle(),
                expectedProductName,
                "Product name does not match the selected item"
        );
        Assert.assertEquals(
                firstCartItem.getProductPrice(),
                expectedProductPrice,
                "Product price does not match the selected item"
        );
    }

    @Test
    public void verifyProductCanBeRemovedFromCart() {
        ProductPageBase productPage = loginAsStandardUser();
        ProductListItemComponentBase firstProductItem = productPage.getFirstProductItem();

        String removedProductName = firstProductItem.getProductTitleText();

        firstProductItem.addProductToCart();
        CartPageBase cartPage = productPage.openCart();

        Assert.assertTrue(cartPage.isPageOpened(), "Cart page is not opened");

        cartPage.getCartItem(0).removeProduct();

        Assert.assertTrue(cartPage.isCartBadgeHidden(), "Cart badge is not updated");
        Assert.assertTrue(
                cartPage.isRemovedItemNotDisplayed(removedProductName),
                "Removed item is still displayed in the cart"
        );
    }
}