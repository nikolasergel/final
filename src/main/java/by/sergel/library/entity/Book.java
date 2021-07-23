package by.sergel.library.entity;

import java.io.InputStream;
import java.time.Year;
import java.util.Objects;

public class Book extends AbstractEntity {
    private String name;
    private Year year;
    private short quantity;
    private InputStream picture;
    private String language;
    private String publisher;
    private Author author;
    private String genre;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Year getYear() {
        return year;
    }

    public void setYear(Year year) {
        this.year = year;
    }

    public short getQuantity() {
        return quantity;
    }

    public void setQuantity(short quantity) {
        this.quantity = quantity;
    }

    public InputStream getPicture() {
        return picture;
    }

    public void setPicture(InputStream picture) {
        this.picture = picture;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Book || super.equals(o))) {
            return false;
        }
        Book book = (Book) o;
        return  quantity == book.quantity &&
                Objects.equals(name, book.name) &&
                Objects.equals(year, book.year) &&
                Objects.equals(picture, book.picture) &&
                Objects.equals(language, book.language) &&
                Objects.equals(publisher, book.publisher) &&
                Objects.equals(genre, book.genre) &&
                Objects.equals(author, book.author);
    }

    @Override
    public int hashCode() {
        int result = 37 * super.hashCode();
        result = result * 41 + quantity;
        result += name == null ? 0 : name.hashCode();
        result += year == null ? 0 : year.hashCode();
        result += picture == null ? 0 : picture.hashCode(); //FIXME InputStream hashcode
        result += language == null ? 0 : language.hashCode();
        result += publisher == null ? 0 : publisher.hashCode();
        result += author == null ? 0 : author.hashCode();
        result += genre == null ? 0 : genre.hashCode();
        return result;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Book{ ").append(super.toString())
                .append(", name='").append(name).append('\'')
                .append(", year=").append(year)
                .append(", quantity=").append(quantity)
                .append(", picture=").append(picture)
                .append(", language='").append(language).append('\'')
                .append(", publisher='").append(publisher).append('\'')
                .append(", author=").append(author)
                .append(", genre='").append(genre).append("' }");
        return builder.toString();
    }
}
