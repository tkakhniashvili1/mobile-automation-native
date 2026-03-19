package com.solvd.tests;

import com.solvd.components.common.ProductListItemComponentBase;
import com.solvd.pages.common.CartPageBase;
import com.solvd.pages.common.ProductDetailPageBase;
import com.solvd.pages.common.ProductPageBase;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ProductTest extends BaseMobileTest {

    @Test
    public void verifyProductDetailsPageOpensFromProductList() {
        ProductPageBase productPage = loginAsStandardUser();
        ProductListItemComponentBase firstProductItem = productPage.getFirstProductItem();

        String expectedProductName = firstProductItem.getProductTitleText();
        ProductDetailPageBase productDetailPage = firstProductItem.openProductDetails();

        Assert.assertTrue(productDetailPage.isProductDetailsPageOpened(), "Product details page is not opened");
        Assert.assertTrue(
                productDetailPage.isSelectedProductNameDisplayed(expectedProductName),
                "Selected product name is not displayed"
        );
        Assert.assertTrue(productDetailPage.isProductImageDisplayed(), "Product image is not displayed");
        Assert.assertTrue(productDetailPage.isProductPriceDisplayed(), "Product price is not displayed");
        Assert.assertTrue(productDetailPage.isProductDescriptionDisplayed(), "Product description is not displayed");
    }

    @Test
    public void verifyProductCanBeAddedToCartFromProductList() {
        ProductPageBase productPage = loginAsStandardUser();
        ProductListItemComponentBase firstProductItem = productPage.getFirstProductItem();

        String expectedProductName = firstProductItem.getProductTitleText();

        firstProductItem.addProductToCart();

        Assert.assertTrue(productPage.isCartBadgeCountDisplayed(1), "Cart badge did not increase by 1");
        Assert.assertTrue(
                firstProductItem.isButtonUpdatedToRemoveState(),
                "Button state was not updated after adding product to cart"
        );

        CartPageBase cartPage = productPage.openCart();

        Assert.assertTrue(cartPage.isPageOpened(), "Cart page is not opened");
        Assert.assertTrue(
                cartPage.isAddedProductDisplayedInCart(expectedProductName),
                "Selected product is not added to cart"
        );
    }
}