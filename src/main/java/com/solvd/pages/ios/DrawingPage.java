package com.solvd.pages.ios;

import com.solvd.pages.common.DrawingPageBase;
import com.solvd.utils.TimeoutConstants;
import com.zebrunner.carina.utils.factory.DeviceType;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.InputStream;
import java.time.Duration;
import java.util.Base64;

@DeviceType(pageType = DeviceType.Type.IOS_PHONE, parentClass = DrawingPageBase.class)
public class DrawingPage extends DrawingPageBase {

    private static final String IMAGE_PATH = "images/circle-ios.png";

    private final By drawnCircle;

    public DrawingPage(WebDriver driver) {
        super(driver);
        this.drawnCircle = AppiumBy.image(loadImageAsBase64(IMAGE_PATH));
    }

    @Override
    public boolean isCircleVisible() {
        try {
            new WebDriverWait(getDriver(), Duration.ofSeconds(TimeoutConstants.MEDIUM_TIMEOUT))
                    .until(driver -> !driver.findElements(drawnCircle).isEmpty());
            return true;
        } catch (Exception e) {
            return false;
        }
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

    private String loadImageAsBase64(String resourcePath) {
        try (InputStream inputStream = Thread.currentThread()
                .getContextClassLoader()
                .getResourceAsStream(resourcePath)) {
            if (inputStream == null) {
                throw new RuntimeException("Image not found: " + resourcePath);
            }
            return Base64.getEncoder().encodeToString(inputStream.readAllBytes());
        } catch (Exception e) {
            throw new RuntimeException("Failed to load image: " + resourcePath, e);
        }
    }
}