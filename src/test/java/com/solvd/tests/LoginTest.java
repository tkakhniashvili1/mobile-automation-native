package com.solvd.tests;

import com.solvd.pages.common.LoginPageBase;
import com.solvd.pages.common.ProductPageBase;
import com.zebrunner.carina.core.AbstractTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends AbstractTest {

    @Test
    public void verifyLoginWithValidCredentials() {
        LoginPageBase loginPage = initPage(getDriver(), LoginPageBase.class);
        Assert.assertTrue(loginPage.isLoginScreenDisplayed(), "Login screen is not displayed");

        ProductPageBase productPage = loginPage.login("standard_user", "secret_sauce");
        Assert.assertTrue(productPage.isProductCardDisplayed(), "Product list is not displayed");
    }

    @Test
    public void verifyLoginWithInvalidPassword() {
        LoginPageBase loginPage = initPage(getDriver(), LoginPageBase.class);

        LoginPageBase loginPageAfterFailedLogin = loginPage.loginExpectingFailure("standard_user", "wrong_password");

        Assert.assertTrue(loginPageAfterFailedLogin.isLoginScreenDisplayed(), "User did not remain on the login screen");
        Assert.assertEquals(
                loginPageAfterFailedLogin.getErrorMessageText(),
                "Username and password do not match any user in this service.",
                "Unexpected error message for invalid password"
        );
    }

    @Test
    public void verifyLoginWithEmptyUsername() {
        LoginPageBase loginPage = initPage(getDriver(), LoginPageBase.class);

        LoginPageBase loginPageAfterFailedLogin = loginPage.loginExpectingFailure("", "secret_sauce");

        Assert.assertTrue(loginPageAfterFailedLogin.isLoginScreenDisplayed(), "User did not remain on the login screen");
        Assert.assertEquals(
                loginPageAfterFailedLogin.getErrorMessageText(),
                "Username is required",
                "Unexpected error message for empty username"
        );
    }

    @Test
    public void verifyLoginWithEmptyPassword() {
        LoginPageBase loginPage = initPage(getDriver(), LoginPageBase.class);

        LoginPageBase loginPageAfterFailedLogin = loginPage.loginExpectingFailure("standard_user", "");

        Assert.assertTrue(loginPageAfterFailedLogin.isLoginScreenDisplayed(), "User did not remain on the login screen");
        Assert.assertEquals(
                loginPageAfterFailedLogin.getErrorMessageText(),
                "Password is required",
                "Unexpected error message for empty password"
        );
    }

    @Test
    public void verifyProductsAreDisplayedAfterLogin() {
        LoginPageBase loginPage = initPage(getDriver(), LoginPageBase.class);

        ProductPageBase productPage = loginPage.login("standard_user", "secret_sauce");

        Assert.assertTrue(productPage.isProductCardDisplayed(), "At least one product card is not visible");
        Assert.assertTrue(productPage.areFirstProductCardsContentDisplayed(2),
                "First 2 product cards do not show name and price");
    }

    @Test
    public void verifyLogoutFromTheApplication() {
        LoginPageBase loginPage = initPage(getDriver(), LoginPageBase.class);

        ProductPageBase productPage = loginPage.login("standard_user", "secret_sauce");
        Assert.assertTrue(productPage.isProductCardDisplayed(), "Product list is not displayed");

        LoginPageBase loginPageAfterLogout = productPage.logoutFromApplication();

        Assert.assertTrue(loginPageAfterLogout.isLoginScreenDisplayed(), "Login screen is not displayed after logout");
    }
}