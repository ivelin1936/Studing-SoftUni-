package p02_vehiclesExtension.models;

public interface IVenhile {

    String drive(double distance);

    String drive(double distance, boolean isEmpty);

    void refuel(double liters);
}
