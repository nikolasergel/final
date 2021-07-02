
package by.sergel.entity;

import java.io.InputStream;
import java.util.Objects;

public class User extends AbstractEntity{
    private String login;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private String patronymic;
    private String phone;
    private InputStream picture;
    private UserStatus status;
    private UserRole role;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public InputStream getPicture() {
        return picture;
    }

    public void setPicture(InputStream picture) {
        this.picture = picture;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof User || super.equals(o))) {
            return false;
        }
        User user = (User) o;
        return  Objects.equals(login, user.login) &&
                Objects.equals(password, user.password) &&
                Objects.equals(email, user.email) &&
                Objects.equals(firstName, user.firstName) &&
                Objects.equals(lastName, user.lastName) &&
                Objects.equals(patronymic, user.patronymic) &&
                Objects.equals(picture, user.picture) &&
                Objects.equals(phone, user.phone) &&
                status == user.status &&
                role == user.role;
    }

    @Override
    public int hashCode() {
        int c = 31; //FIXME calculate InputStream hashcode
        int result = c * super.hashCode();
        result += login == null ? 0 : login.hashCode();
        result += password == null ? 0 : password.hashCode();
        result += email == null ? 0 : email.hashCode();
        result += firstName == null ? 0 : firstName.hashCode();
        result += lastName == null ? 0 : lastName.hashCode();
        result += patronymic == null ? 0 : patronymic.hashCode();
        result += phone == null ? 0 : phone.hashCode();
        result += picture == null ? 0 : picture.hashCode();
        result += status.ordinal() * c;
        result += role.ordinal() * c;
        return result;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("User{ ").append(super.toString())
                .append(", login='").append(login).append('\'')
                .append(", password='").append(password).append('\'')
                .append(", email='").append(email).append('\'')
                .append(", firstName='").append(firstName).append('\'')
                .append(", lastName='").append(lastName).append('\'')
                .append(", patronymic='").append(patronymic).append('\'')
                .append(", phone='").append(phone).append('\'')
                .append(", status=").append(status)
                .append(", role=").append(role).append(" }");
        return builder.toString();
    }
}
