package by.sergel.entity;

import java.util.Objects;

public class Author extends AbstractEntity{
    private String firstName;
    private String lastName;
    private String patronymic;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Author || super.equals(o))) {
            return false;
        }
        Author author = (Author) o;
        return  Objects.equals(firstName, author.firstName) &&
                Objects.equals(lastName, author.lastName) &&
                Objects.equals(patronymic, author.patronymic);
    }

    @Override
    public int hashCode() {
        int c = 47;
        int result = c * super.hashCode();
        result += c * (firstName == null ? 0 : firstName.hashCode());
        result += c * (lastName == null ? 0 : lastName.hashCode());
        result += c * (patronymic == null ? 0 : patronymic.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Author{ ").append(super.toString())
                .append(", firstName='").append(firstName).append('\'')
                .append(", lastName='").append(lastName).append('\'')
                .append(", patronymic='").append(patronymic).append('\'').append(" }");
        return builder.toString();
    }
}
