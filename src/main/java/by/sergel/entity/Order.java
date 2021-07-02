package by.sergel.entity;

import java.time.LocalDateTime;
import java.util.Objects;

public class Order extends AbstractEntity {
    private LocalDateTime reservationDate;
    private LocalDateTime returnDate;
    private OrderStatus status;
    private User customer;
    private Book book;

    public LocalDateTime getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(LocalDateTime reservationDate) {
        this.reservationDate = reservationDate;
    }

    public LocalDateTime getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDateTime returnDate) {
        this.returnDate = returnDate;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public User getCustomer() {
        return customer;
    }

    public void setCustomer(User customer) {
        this.customer = customer;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Order || super.equals(o))) {
            return false;
        }
        Order order = (Order) o;
        return Objects.equals(reservationDate, order.reservationDate) &&
                Objects.equals(returnDate, order.returnDate) &&
                status == order.status &&
                Objects.equals(customer, order.customer) &&
                Objects.equals(book, order.book);
    }

    @Override
    public int hashCode() {
        int c = 61;
        int result = c * super.hashCode();
        result += c * (reservationDate == null ? 0 : reservationDate.hashCode());
        result += c * (returnDate == null ? 0 : returnDate.hashCode());
        result += c * customer.getId();
        result += c * book.getId(); //FIXME use full hashcode or no?
        return result;
    }
}
