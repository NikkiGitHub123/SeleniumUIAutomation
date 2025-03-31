package dtos;


public class RegisterUserDto {

    private String firstName;
    private String lastName;
    private String email;
    private String telephone;
    private String password;

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getEmail() {
        return this.email;
    }

    public String getTelephone() {
        return this.telephone;
    }

    public String getPassword() {
        return this.password;
    }

    public void setFirstName(String fname) {
        firstName = fname;
    }

    public void setLastName(String lname) {
        lastName = lname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTelephone(String phone) {
        telephone = phone;
    }

    public void setPassword(String pwd) {
        password = pwd;
    }
}
