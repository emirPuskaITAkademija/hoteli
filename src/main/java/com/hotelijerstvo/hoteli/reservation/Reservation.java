package com.hotelijerstvo.hoteli.reservation;

import com.hotelijerstvo.hoteli.reservation.guest.Guest;
import com.hotelijerstvo.hoteli.reservation.room.Room;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "reservation")
public class Reservation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;

    @JoinColumn(name = "id_guest", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Guest mainGuest;

    @JoinColumn(name = "id_room", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Room room;

    @Basic(optional = false)
    @Column(name = "from_date")
    private LocalDateTime fromDate;

    @Basic(optional = false)
    @Column(name = "to_date")
    private LocalDateTime toDate;

    @Basic(optional = false)
    private boolean checkin;

    @Basic(optional = false)
    @Column(name = "checkin_date_time")
    private LocalDateTime checkinDateTime;

    @Basic(optional = false)
    private boolean checkout;

    @Basic(optional = false)
    @Column(name = "checkout_date_time")
    private LocalDateTime checkoutDateTime;

    public Reservation() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Guest getMainGuest() {
        return mainGuest;
    }

    public void setMainGuest(Guest mainGuest) {
        this.mainGuest = mainGuest;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public LocalDateTime getFromDate() {
        return fromDate;
    }

    public void setFromDate(LocalDateTime fromDate) {
        this.fromDate = fromDate;
    }

    public LocalDateTime getToDate() {
        return toDate;
    }

    public void setToDate(LocalDateTime toDate) {
        this.toDate = toDate;
    }

    public boolean isCheckin() {
        return checkin;
    }

    public void setCheckin(boolean checkin) {
        this.checkin = checkin;
    }

    public LocalDateTime getCheckinDateTime() {
        return checkinDateTime;
    }

    public void setCheckinDateTime(LocalDateTime checkinDateTime) {
        this.checkinDateTime = checkinDateTime;
    }

    public boolean isCheckout() {
        return checkout;
    }

    public void setCheckout(boolean checkout) {
        this.checkout = checkout;
    }

    public LocalDateTime getCheckoutDateTime() {
        return checkoutDateTime;
    }

    public void setCheckoutDateTime(LocalDateTime checkoutDateTime) {
        this.checkoutDateTime = checkoutDateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reservation that = (Reservation) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
