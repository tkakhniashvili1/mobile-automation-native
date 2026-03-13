package com.solvd.pages.android;

import com.solvd.pages.common.ProductDetailPageBase;
import com.zebrunner.carina.utils.factory.DeviceType;
import org.openqa.selenium.WebDriver;

@DeviceType(pageType = DeviceType.Type.ANDROID_PHONE, parentClass = ProductDetailPageBase.class)
public class ProductDetailPage extends ProductDetailPageBase {

    public ProductDetailPage(WebDriver driver) {
        super(driver);
    }
}