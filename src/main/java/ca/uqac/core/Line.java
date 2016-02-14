package ca.uqac.core;

// Assuming line equation is ax + b
public class Line {

    private double a;
    private double b;
    private Click extremity1;
    private Click extremity2;
    private final boolean sameX;

    public Line(Click current) {
        this.sameX = true;
    }

    public Line(Click current, Click previous) {

        double currentX = current.getX();
        double currentY = current.getY();

        double previousX = previous.getX();
        double previousY = previous.getY();

        this.sameX = false;

        this.a = (currentY - previousY) / (currentX - previousX);
        this.b = currentY - a * currentX;
    }

    public boolean match(Click antePrevious) {
        double antePreviousX = antePrevious.getX();
        double antePreviousY = antePrevious.getY();

        return (antePreviousX * this.a + b) == antePreviousY;
    }

    public void findExtremities(Click current, Click previous, Click antePrevious) {
        if(this.sameX) {
            findExtremitiesForSameX(current, previous, antePrevious);
        } else {
            findExtremitiesForDifferentX(current, previous, antePrevious);
        }
    }

    private void findExtremitiesForDifferentX(Click current, Click previous, Click antePrevious) {

        double currentX = current.getX();
        double previousX = previous.getX();
        double antePreviousX = antePrevious.getX();

        if (current.getX() > previousX && previousX > antePreviousX) {
            extremity1 = antePrevious;
            extremity2 = current;
        } else {
            extremity1 = previous;
            extremity2 = currentX > antePreviousX ? current : antePrevious;
        }
    }

    private void findExtremitiesForSameX(Click current, Click previous, Click antePrevious) {

        double currentY = current.getY();
        double previousY = previous.getY();
        double antePreviousY = antePrevious.getY();

        if(currentY > previousY && previousY > antePreviousY) {
            extremity1 = antePrevious;
            extremity2 = current;
        } else {
            extremity1 = previous;
            extremity2 = currentY > antePreviousY ? current : antePrevious;
        }
    }

    public int getFirstExtremityCoordinateX() {
        return (int) this.extremity1.getX();
    }

    public int getFirstExtremityCoordinateY() {
        return (int) this.extremity1.getY();
    }

    public int getSecondExtremityCoordinateX() {
        return (int) this.extremity2.getX();
    }

    public int getSecondExtremityCoordinateY() {
        return (int) this.extremity2.getY();
    }
}
