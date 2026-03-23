package com.solvd.pages.common;

import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Interactive;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class DrawingPageBase extends AbstractPage {

    public DrawingPageBase(WebDriver driver) {
        super(driver);
    }

    public abstract boolean isCircleVisible();

    protected abstract double getCenterXRatio();

    protected abstract double getCenterYRatio();

    protected abstract double getRadiusRatio();

    public void drawCircle() {
        Dimension size = getDriver().manage().window().getSize();
        int centerX = (int) (size.getWidth() * getCenterXRatio());
        int centerY = (int) (size.getHeight() * getCenterYRatio());
        int radius = (int) (Math.min(size.getWidth(), size.getHeight()) * getRadiusRatio());

        List<Point> points = buildCirclePoints(centerX, centerY, radius, 24);

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence sequence = new Sequence(finger, 1);

        Point startPoint = points.get(0);

        sequence.addAction(finger.createPointerMove(
                Duration.ZERO,
                PointerInput.Origin.viewport(),
                startPoint.getX(),
                startPoint.getY()
        ));
        sequence.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));

        for (int i = 1; i < points.size(); i++) {
            Point point = points.get(i);
            sequence.addAction(finger.createPointerMove(
                    Duration.ofMillis(40),
                    PointerInput.Origin.viewport(),
                    point.getX(),
                    point.getY()
            ));
        }

        sequence.addAction(finger.createPointerMove(
                Duration.ofMillis(40),
                PointerInput.Origin.viewport(),
                startPoint.getX(),
                startPoint.getY()
        ));
        sequence.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        ((Interactive) getDriver()).perform(Collections.singletonList(sequence));
        pause(1);
    }

    private List<Point> buildCirclePoints(int centerX, int centerY, int radius, int segments) {
        List<Point> points = new ArrayList<>();

        for (int i = 0; i < segments; i++) {
            double angle = 2 * Math.PI * i / segments;
            int x = centerX + (int) Math.round(radius * Math.cos(angle));
            int y = centerY + (int) Math.round(radius * Math.sin(angle));
            points.add(new Point(x, y));
        }

        return points;
    }
}