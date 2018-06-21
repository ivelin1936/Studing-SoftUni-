package p03_shapes.models;

public class Rectangle extends Shape {

    private double height;
    private double width;

    public Rectangle(double height, double width) {
        this.setHeight(height);
        this.setWidth(width);
    }

    private void setHeight(double height) {
        this.height = height;
    }

    private void setWidth(double width) {
        this.width = width;
    }

    @Override
    public void calculatePerimeter() {
        super.setPerimeter(2 * (this.height + this.width));
    }

    @Override
    public void calculateArea() {
        super.setArea(this.width * this.height);
    }
}
