package com.solvd.tests;

import com.solvd.pages.common.LoginPageBase;
import com.solvd.pages.common.ProductPageBase;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginTest extends BaseMobileTest {

    @DataProvider(name = "invalidLoginData")
    public Object[][] invalidLoginData() {
        return new Object[][]{
                {"standard_user", "wrong_password", "Username and password do not match any user in this service."},
                {"", "secret_sauce", "Username is required"},
                {"standard_user", "", "Password is required"}
        };
    }

    @Test
    public void verifyLoginWithValidCredentials() {
        loginAsStandardUser();
    }

    @Test(dataProvider = "invalidLoginData")
    public void verifyLoginWithInvalidCredentials(String username, String password, String expectedErrorMessage) {
        LoginPageBase loginPage = openLoginPage();

        LoginPageBase loginPageAfterFailedLogin = loginPage.loginExpectingFailure(username, password);

        Assert.assertTrue(
                loginPageAfterFailedLogin.isLoginScreenDisplayed(),
                "User did not remain on the login screen"
        );
        Assert.assertEquals(
                loginPageAfterFailedLogin.getErrorMessageText(),
                expectedErrorMessage,
                "Unexpected error message"
        );
    }

    @Test
    public void verifyProductsAreDisplayedAfterLogin() {
        ProductPageBase productPage = loginAsStandardUser();

        Assert.assertTrue(
                productPage.areFirstProductCardsContentDisplayed(2),
                "First 2 product cards do not show name and price"
        );
    }

    @Test
    public void verifyLogoutFromTheApplication() {
        ProductPageBase productPage = loginAsStandardUser();

        LoginPageBase loginPageAfterLogout = productPage.logoutFromApplication();

        Assert.assertTrue(loginPageAfterLogout.isLoginScreenDisplayed(), "Login screen is not displayed after logout");
    }
}