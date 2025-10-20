package dmit2015.faces;

import org.openqa.selenium.support.FindBy;
import org.primefaces.selenium.component.DatePicker;
import org.primefaces.selenium.component.InputText;

import java.time.LocalDate;

public class WeatherFormPage {

//    @FindBy(id="dialogs:city")
//    private InputText cityInput;

    @FindBy(id="dialogs:date")
    private DatePicker datePicker;

//    @FindBy(id="dialogs:celsius")
//    private InputText celsiusInput;

    public void setForecastDate(LocalDate date) {
        datePicker.setValue(date);
    }

//    public void setCity(String city) {
//        cityInput.setValue(city);
//    }
//
//    public void setCelsius(String celsius) {
//        celsiusInput.setValue(celsius);
//    }
}
