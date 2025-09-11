package dmit2015.faces;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

@Named("iphone17")
@RequestScoped
public class HelloBean {

    private String userInput;

    public String getMessage() {
        return "Hello, " + userInput;
    }

    public void submit() {
        userInput = "out of stock";
    }

    public void setMessage(String userInput) {
        this.userInput = userInput;
    }

    public String getUserInput() {
        return userInput;
    }
    public void setUserInput(String userInput) {}
}
