package tk.housem8.Entities;

import android.app.Application;
import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

import tk.housem8.R;

/**
 * Created by Administrador on 26/09/2017.
 */

public class Cost {

    private Integer id;
    private String description;
    private Integer period;
    private String datetime;
    //
    private Float amount;
    private String fechaCreacion;
    private String fechaModificacion;
    private Date fechaBorrado;
    private boolean activo;

    private String costFamily;
    private String house;
    private String mate;
    private String commerce;

    SimpleDateFormat RESTformat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPeriod() {
        return period;
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {

        this.datetime = RESTformat.format(datetime);
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {

        this.datetime = RESTformat.format(fechaCreacion);
    }

    public String getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Date fechaModificacion) {


        this.datetime = RESTformat.format(fechaModificacion);

    }

    public Date getFechaBorrado() {
        return fechaBorrado;
    }

    public void setFechaBorrado(Date fechaBorrado) {
        this.fechaBorrado = fechaBorrado;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public String getCostFamily() {
        return costFamily;
    }

    public void setCostFamily(String url,Integer id) {
        this.costFamily = url+"costFamilies/"+id;
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String url,Integer id) {
        this.house =  url+"houses/"+id.toString();
    }

    public String getMate() {
        return mate;
    }

    public void setMate(String url,Integer id) {
        this.mate =  url+"mates/"+id;
    }

    public String getCommerce() {
        return commerce;
    }

    public void setCommerce(String url,Integer id) {
        this.commerce = url+"commerces/"+id;
    }
}
