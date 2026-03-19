package com.solvd.tests;

import com.solvd.pages.common.LoginPageBase;
import com.solvd.pages.common.ProductPageBase;
import com.zebrunner.carina.utils.R;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginTest extends BaseMobileTest {

    @DataProvider(name = "invalidLoginData")
    public Object[][] invalidLoginData() {
        return new Object[][]{
                {
                        R.TESTDATA.get("valid.username"),
                        R.TESTDATA.get("invalid.password.wrong"),
                        R.TESTDATA.get("error.invalid.credentials")
                },
                {
                        R.TESTDATA.get("invalid.username.empty"),
                        R.TESTDATA.get("valid.password"),
                        R.TESTDATA.get("error.username.required")
                },
                {
                        R.TESTDATA.get("valid.username"),
                        R.TESTDATA.get("invalid.password.empty"),
                        R.TESTDATA.get("error.password.required")
                }
        };
    }

    @Test
    public void verifyLoginWithValidCredentials() {
        loginAsStandardUser();
    }

    @Test(dataProvider = "invalidLoginData")
    public void verifyLoginWithInvalidCredentials(String username, String password, String expectedErrorMessage) {
        LoginPageBase loginPage = openLoginPage();

        loginPage.login(username, password);

        Assert.assertTrue(
                loginPage.isLoginScreenDisplayed(),
                "User did not remain on the login screen"
        );

        Assert.assertEquals(
                loginPage.getErrorMessageText(),
                expectedErrorMessage,
                "Unexpected error message"
        );
    }

    @Test
    public void verifyProductsAreDisplayedAfterLogin() {
        ProductPageBase productPage = loginAsStandardUser();

        Assert.assertTrue(
                productPage.areFirstProductsValid(2),
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