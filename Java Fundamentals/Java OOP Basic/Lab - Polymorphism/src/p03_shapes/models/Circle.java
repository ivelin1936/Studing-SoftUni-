package p03_shapes.models;

public class Circle extends Shape {

    private double radius;

    public Circle(double radius) {
        this.setRadius(radius);
    }

    private void setRadius(double radius) {
        this.radius = radius;
    }

    @Override
    public void calculatePerimeter() {
        super.setPerimeter(2 * Math.PI * this.radius);
    }

    @Override
    public void calculateArea() {
        super.setArea(Math.PI * Math.pow(this.radius, 2));
    }
}
