// In this class I used the functional interface TemperatureConverter.java which is in the same folder as this class.

package useful_examples;

import java.util.Scanner;

public class FindTemperatureFromCelsiusToFahrenheit {
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter temperature in celsius: ");
        double temperatureInC = sc.nextDouble();


        TemperatureConverter covertFarenheit = (celsius) -> {
            double fareheit = ((9.0 / 5) * celsius) + 32;
            return fareheit;
        };

        System.out.println("\nTemperature in Celsius: " + temperatureInC);
        System.out.println("Temperature in Fahrenheit: " + covertFarenheit.convert(temperatureInC));

    }

}
