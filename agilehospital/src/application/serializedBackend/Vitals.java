package application.serializedBackend;

import java.io.Serializable;

public class Vitals implements Serializable {

    private double height;
    private double weight;
    private double temp;
    private double bp;

    public Vitals (double height, double weight, double temp, double bp) {
        this.height = height;
        this.weight = weight;
        this.temp = temp;
        this.bp = bp;
    }

    public double[] getVitals() {
        double[] data = {height, weight, temp, bp};
        return data;
    }
}
