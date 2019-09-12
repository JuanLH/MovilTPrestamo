package com.app.moviltprestamo.entities;

public class PrestamoActivo {

    int id_prestamo,monto_prestamo,monto_pago,monto_pendiente,monto_pen_acum;
    String forma_prestamo,nombre_cliente,telefono_cliente,pagos;

    public int getId_prestamo() {
        return id_prestamo;
    }

    public void setId_prestamo(int id_prestamo) {
        this.id_prestamo = id_prestamo;
    }

    public int getMonto_prestamo() {
        return monto_prestamo;
    }

    public void setMonto_prestamo(int monto_prestamo) {
        this.monto_prestamo = monto_prestamo;
    }

    public int getMonto_pago() {
        return monto_pago;
    }

    public void setMonto_pago(int monto_pago) {
        this.monto_pago = monto_pago;
    }

    public int getMonto_pendiente() {
        return monto_pendiente;
    }

    public void setMonto_pendiente(int monto_pendiente) {
        this.monto_pendiente = monto_pendiente;
    }

    public int getMonto_pen_acum() {
        return monto_pen_acum;
    }

    public void setMonto_pen_acum(int monto_pen_acum) {
        this.monto_pen_acum = monto_pen_acum;
    }

    public String getForma_prestamo() {
        return forma_prestamo;
    }

    public void setForma_prestamo(String forma_prestamo) {
        this.forma_prestamo = forma_prestamo;
    }

    public String getNombre_cliente() {
        return nombre_cliente;
    }

    public void setNombre_cliente(String nombre_cliente) {
        this.nombre_cliente = nombre_cliente;
    }

    public String getTelefono_cliente() {
        return telefono_cliente;
    }

    public void setTelefono_cliente(String telefono_cliente) {
        this.telefono_cliente = telefono_cliente;
    }

    public String getPagos() {
        return pagos;
    }

    public void setPagos(String pagos) {
        this.pagos = pagos;
    }

}
