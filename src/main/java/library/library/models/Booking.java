package library.library.models;

import java.util.Date;

public class Booking {
    private String isbn;
    private int claveUsuario;
    private String nss;
    // date
    private Date fechaPrestamo;
    public Booking(String isbn, int clave_usuario, String NSS, int dias_prestamo, int mes_prestamo, int año_prestamo) {
        this.isbn = isbn;
        this.claveUsuario = clave_usuario;
        this.nss = NSS;
        this.fechaPrestamo = new Date(año_prestamo, mes_prestamo, dias_prestamo);
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getClaveUsuario() {
        return claveUsuario;
    }

    public void setClaveUsuario(int claveUsuario) {
        this.claveUsuario = claveUsuario;
    }

    public String getNss() {
        return nss;
    }

    public void setNss(String nss) {
        this.nss = nss;
    }

    public Date getFechaPrestamo() {
        return fechaPrestamo;
    }

    public void setFechaPrestamo(Date fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }
}
