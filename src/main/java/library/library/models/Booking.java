package library.library.models;

import java.util.Date;

public class Booking {
    private String isbn;
    private int claveUsuario;
    private String nss;
    // date
    private Integer diaPrestamo;

    private Integer mesPrestamo;

    private Integer añoPrestamo;


    public Booking(String isbn, int clave_usuario, String NSS, int dia_prestamo, int mes_prestamo, int año_prestamo) {
        this.isbn = isbn;
        this.claveUsuario = clave_usuario;
        this.nss = NSS;
        this.diaPrestamo = dia_prestamo;
        this.mesPrestamo = mes_prestamo;
        this.añoPrestamo = año_prestamo;
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

    public Integer getDiaPrestamo() {
        return diaPrestamo;
    }

    public Integer getMesPrestamo() {
        return mesPrestamo;
    }

    public Integer getAñoPrestamo() {
        return añoPrestamo;
    }
}
