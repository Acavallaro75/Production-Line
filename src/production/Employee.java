package production;

import java.util.regex.Pattern;

public class Employee {

  private StringBuilder name = new StringBuilder();

  private String userName;

  private String password;

  private String email;

  Employee(String name, String password) {
    this.name.append(name);
    this.password = password;
    checkName(name);
    isValidPassword(password);
  }

  private void setUserName(String userName) {
    if (userName.equalsIgnoreCase("default")) {
      this.userName = userName;
    } else {
      String[] splitName = userName.split(" ");
      String firstName = splitName[0];
      char firstInitial = firstName.charAt(0);
      if (splitName.length > 1) {
        String lastName = splitName[splitName.length - 1];
        this.userName = firstInitial + "" + lastName;
      }
    }
  }

  private void setEmail(String email) {
    if (email.equalsIgnoreCase("user@oracleacademy.Test")) {
      this.email = email;
    } else {
      this.email = email.replace(' ', '.').toLowerCase() + "@oracleacademy.Test";
    }
  }

  private void checkName(String name) {
    if (name.contains(" ")) {
      setUserName(name);
      setEmail(name);
    } else {
      setUserName("Default");
      setEmail("user@oracleacademy.Test");
    }
  }

  private void isValidPassword(String password) {
    final String passwordVerification = "^(.*[a-z])(.*[A-Z])(.*[@#!$%^&+=])$";
    final Pattern pattern = Pattern.compile(passwordVerification);
    if (pattern.matcher(password).matches()) {
      this.password = password;
    } else {
      this.password = "pw";
    }
  }

  /**
   * The toString() method prints the values of an object to the console. This is what it performs
   * here.
   *
   * @return returns a sentence with all the appropriate values for the proper attributes.
   */
  public String toString() {
    return "Employee details"
        + "\nName : "
        + this.name
        + "\nUsername : "
        + this.userName.toLowerCase()
        + "\nEmail : "
        + this.email
        + "\nInitial Password : "
        + this.password;
  }
}
