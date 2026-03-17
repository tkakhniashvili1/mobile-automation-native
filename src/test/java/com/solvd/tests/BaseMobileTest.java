package com.solvd.tests;

import com.solvd.pages.common.LoginPageBase;
import com.solvd.pages.common.ProductPageBase;
import com.zebrunner.carina.core.AbstractTest;
import org.testng.Assert;

public abstract class BaseMobileTest extends AbstractTest {

    protected LoginPageBase openLoginPage() {
        LoginPageBase loginPage = initPage(getDriver(), LoginPageBase.class);
        Assert.assertTrue(loginPage.isLoginScreenDisplayed(), "Login screen is not displayed");
        return loginPage;
    }

    protected ProductPageBase loginAsStandardUser() {
        LoginPageBase loginPage = openLoginPage();
        ProductPageBase productPage = loginPage.login("standard_user", "secret_sauce");
        Assert.assertTrue(productPage.isProductCardOpened(), "Product list is not displayed");
        return productPage;
    }
}