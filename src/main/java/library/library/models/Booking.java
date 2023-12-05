package library.library.models;

import java.util.Date;

/**
 * Represents a booking for a book in the library.
 */
public class Booking {

    /**
     * The ISBN (International Standard Book Number) of the booked book.
     */
    private String isbn;

    /**
     * The unique identifier of the user making the booking.
     */
    private int claveUsuario;

    /**
     * The NSS (Número de Seguridad Social) of the user making the booking.
     */
    private String nss;

    /**
     * The day when the book is booked.
     */
    private Integer diaPrestamo;

    /**
     * The month when the book is booked.
     */
    private Integer mesPrestamo;

    /**
     * The year when the book is booked.
     */
    private Integer añoPrestamo;

    /**
     * Constructs a Booking object with the specified attributes.
     *
     * @param isbn           The ISBN of the booked book.
     * @param claveUsuario   The unique identifier of the user making the booking.
     * @param nss            The NSS of the user making the booking.
     * @param diaPrestamo    The day when the book is booked.
     * @param mesPrestamo    The month when the book is booked.
     * @param añoPrestamo    The year when the book is booked.
     */
    public Booking(String isbn, int claveUsuario, String nss, int diaPrestamo, int mesPrestamo, int añoPrestamo) {
        this.isbn = isbn;
        this.claveUsuario = claveUsuario;
        this.nss = nss;
        this.diaPrestamo = diaPrestamo;
        this.mesPrestamo = mesPrestamo;
        this.añoPrestamo = añoPrestamo;
    }

    /**
     * Gets the ISBN of the booked book.
     *
     * @return The ISBN of the booked book.
     */
    public String getIsbn() {
        return isbn;
    }

    /**
     * Sets the ISBN of the booked book.
     *
     * @param isbn The ISBN to set.
     */
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    /**
     * Gets the unique identifier of the user making the booking.
     *
     * @return The unique identifier of the user making the booking.
     */
    public int getClaveUsuario() {
        return claveUsuario;
    }

    /**
     * Gets the day when the book is booked.
     *
     * @return The day when the book is booked.
     */
    public Integer getDiaPrestamo() {
        return diaPrestamo;
    }

    /**
     * Gets the month when the book is booked.
     *
     * @return The month when the book is booked.
     */
    public Integer getMesPrestamo() {
        return mesPrestamo;
    }

    /**
     * Gets the year when the book is booked.
     *
     * @return The year when the book is booked.
     */
    public Integer getAñoPrestamo() {
        return añoPrestamo;
    }
}