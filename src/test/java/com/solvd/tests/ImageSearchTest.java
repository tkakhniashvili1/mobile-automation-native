package com.solvd.tests;

import com.solvd.pages.common.DrawingPageBase;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ImageSearchTest extends BaseMobileTest {

    @Test
    public void verifyDrawnCircleIsVisibleByImage() {
        DrawingPageBase drawingPage = initPage(getDriver(), DrawingPageBase.class);

        drawingPage.drawCircle();

        Assert.assertTrue(
                drawingPage.isCircleVisible(),
                "Drawn circle is not visible by image"
        );
    }
}