// This interface was used in the class FindTemperatureFromCelsiusToFahrenheit.java which is in the same folder

package useful_examples;

@FunctionalInterface 
public interface TemperatureConverter {

    double convert(double temperature);    
}

