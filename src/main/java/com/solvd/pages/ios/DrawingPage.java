package com.solvd.pages.ios;

import com.solvd.pages.common.DrawingPageBase;
import com.solvd.utils.TimeoutConstants;
import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.locator.ExtendedFindBy;
import org.openqa.selenium.WebDriver;

@DeviceType(pageType = DeviceType.Type.IOS_PHONE, parentClass = DrawingPageBase.class)
public class DrawingPage extends DrawingPageBase {

    @ExtendedFindBy(image = "images/circle.png")
    private ExtendedWebElement circleImage;

    public DrawingPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isCircleVisible() {
        return circleImage.isElementPresent(TimeoutConstants.MEDIUM_TIMEOUT);
    }

    @Override
    protected double getCenterXRatio() {
        return 0.50;
    }

    @Override
    protected double getCenterYRatio() {
        return 0.55;
    }

    @Override
    protected double getRadiusRatio() {
        return 0.18;
    }
}