package production;

import java.util.regex.Pattern;

/**
 * The Employee class is used to create Employees that are allowed to create production of products.
 *
 * @author: Andrew Cavallaro
 * @date: 12/03/2019
 */
class Employee {

  /** Field member "name" is the name of the employee. */
  private final StringBuilder name = new StringBuilder();

  /**
   * Field member "userName" is the username of the employee. It is the first initial of their first
   * name and their entire last name. If no last name is given, the default username is "default".
   */
  private String userName;

  /**
   * Field member "password" is the password of the employee. It has to have at a minimum: 1 capital
   * letter, 1 lowercase letter, and 1 special character. If the password doesn't fit this criteria,
   * it is automatically set to "pw".
   */
  private String password;

  /**
   * Field member "email" is the email of the employee. It is a combination of the employee's first
   * name and last name combined with a "." and combined with @oracleacademy.Test. If the "userName"
   * field was set to "default", the email will be set to "user@oracleacademy.Test".
   */
  private String email;

  /**
   * The Employee constructor has the following parameters: "name" and "password". It calls the
   * checkName() method and the isValidPassword() method. The "name" field will be appended with the
   * passed "name" argument.
   *
   * @param name of the employee taken from the name text field
   * @param password of the employee taken from the password text field
   */
  Employee(String name, String password) {
    this.name.append(name);
    checkName(name);
    isValidPassword(password);
  }

  /**
   * The getName() method is used to obtain the employee's name.
   *
   * @return the employee's name
   */
  public StringBuilder getName() {
    return name;
  }

  /**
   * The getUserName() method is used to obtain the employee's username.
   *
   * @return the employee's username
   */
  public String getUserName() {
    return userName.toLowerCase();
  }

  /**
   * The getPassword() method is used to obtain the employee's password.
   *
   * @return the employee's password
   */
  public String getPassword() {
    return password;
  }

  /**
   * The getEmail() method is used to obtain the employee's email.
   *
   * @return the employee's email
   */
  public String getEmail() {
    return email;
  }

  /**
   * The setUserName() method sets the employee's username. It is called by the checkName() method
   * and passes the "userName" value. If the passed value is "default", the "userName" is set to
   * default. Else, it will take the first initial of the passed value and the entire last name and
   * set it to the "userName" field member. Example: Tim Lee would have a username of tlee.
   *
   * @param userName passed by the checkName() method
   */
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

  /**
   * The setEmail() method sets the email for the employee. The passed value will take the first and
   * last name of the employee and combine them separated by a "." and "@oracleacademy.Test". Else,
   * the default value is "user@oracleacademy.Test".
   *
   * @param email passed by the checkName() method
   */
  private void setEmail(String email) {
    if (email.equalsIgnoreCase("user@oracleacademy.Test")) {
      this.email = email;
    } else {
      this.email = email.replace(' ', '.').toLowerCase() + "@oracleacademy.Test";
    }
  }

  /**
   * The checkName() method is used to check the name of the employee. If it contains a space, it
   * calls the setUserName() and setEmail() methods and passes in the name argument. If there is no
   * space in the name, the setUserName() and setEmail() methods are called and passed "Default" and
   * "user@oracleacademy.Test".
   *
   * @param name the employee's name
   */
  private void checkName(String name) {
    if (name.contains(" ")) {
      setUserName(name);
      setEmail(name);
    } else {
      setUserName("Default");
      setEmail("user@oracleacademy.Test");
    }
  }

  /**
   * The reverseString() method takes the employee's password and reverses it.
   *
   * @param password employee's password
   * @return the employee's password reversed
   */
  public String reverseString(String password) {
    if (password.isEmpty()) {
      return "";
    } else {
      return this.password = reverseString(password.substring(1)) + password.charAt(0);
    }
  }

  /**
   * The isValidPassword() method checks the passed value if it contains at the minimum: 1 uppercase
   * letter, 1 lowercase letter, and 1 special character. If it matches those constraints, the
   * "password" becomes the passed argument. If it doesn't fit that criteria, the "password" becomes
   * "pw".
   *
   * @param password passed by the constructor
   */
  private void isValidPassword(String password) {
    final String passwordVerification = "^(.*[a-z])(.*[A-Z])(.*[@#!$%^&+=])$";
    final Pattern pattern = Pattern.compile(passwordVerification);
    if (pattern.matcher(password).matches()) {
      reverseString(password);
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
