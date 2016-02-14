package ca.uqac.core;

import org.jdom2.Element;

public class Click {
    private String username;
    private String application;
    private double x;
    private double y;

    protected Click(Element event) {
        this.username = event.getChild("user").getText();
        this.application = event.getChild("application").getText();
        this.x = Double.parseDouble(event.getChild("x").getText());
        this.y = Double.parseDouble(event.getChild("y").getText());
    }

    public String getUsername() {
        return this.username;
    }
    public boolean relateToSameUser(String otherUsername) {
        return this.username.equals(otherUsername);
    }

    public String getApplication() {
        return this.application;
    }

    public boolean relateToSameApplication(String otherApplication) {
        return this.application.equals(otherApplication);
    }

    public double getX() {
        return this.x;
    }

    public boolean hasDifferentXCoordinates(double anotherX) {
        return this.x != anotherX;
    }

    public boolean hasSameXCoordinates(double anotherX) {
        return this.x == anotherX;
    }

    public double getY() {
        return y;
    }
}
