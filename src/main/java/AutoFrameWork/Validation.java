package AutoFrameWork;

import org.openqa.selenium.WebElement;

public class Validation {
    private String email;
    private String password;
    private String name;
    private String postcode;
    private String phoneNumber;

    public Validation(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getPostcode() {
        return postcode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public boolean validateEmail(String email){
        boolean isValid = true;
        return isValid;
    }

    public boolean validatePassword(String password){
        boolean isValid = true;
        if(password.length() < 5){
            System.out.println("Invalid password! At least 5 chars!");
            isValid = false;
        }
        return isValid;
    }

    public boolean validateNames(String name){
        boolean isValid = true;
        //- is not accepted as valid ????????????????????????????????????????????????????????????????????????????????????????
        String myPattern = "[A-Z][a-zA-Z\s.’-]*";
        if(!name.matches(myPattern)){
            System.out.println("Invalid name! Only letters!");
            isValid = false;
        }
        return isValid;
    }

    public boolean validatePhoneNumber(String phoneNumber){
        boolean isValid = true;
        String myPattern = "\\+\\d{3}[\\.\\s]\\d{3}[-\\.\\s]\\d{4}";
        if(!phoneNumber.matches(myPattern)){
            System.out.println("Invalid phone! The format is +420 256 3568!");
            isValid = false;
        }
        return isValid;
    }

    private boolean checkIfEmpty(WebElement element){
        boolean isEmpty = false;
        if (element.getAttribute("value").isEmpty()){
            System.out.println("Empty " + element.getText());
            return true;
        }
        return isEmpty;
    }


}
