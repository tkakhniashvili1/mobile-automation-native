package com.solvd.components.common;

import com.solvd.pages.common.LoginPageBase;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;

public abstract class MenuComponentBase extends AbstractPage {

    public MenuComponentBase(WebDriver driver) {
        super(driver);
    }

    public abstract LoginPageBase logout();
}