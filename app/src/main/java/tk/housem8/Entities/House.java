package tk.housem8.Entities;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by Administrador on 14/06/2017.
 */

public class House {

    private Integer id;
    private String country;
    private String city;
    private String street;
    private String cp;
    private Integer number;
    private Integer floor;
    private String apartment;
    private String other;
    private double squareMeters;
    private Date fechaCreacion;
    private Date fechaModificacion;
    private Date fechaBorrado;
    //private String activo;
    //private String _links;
    //private List<Cost> costList;
   // private List<Room> roomList;

    public House(){

    };


    public House(JSONObject houseJson) throws JSONException {

        this.id = (Integer) houseJson.get("id");
        this.country= (String) houseJson.get("country");
        this.city= (String) houseJson.get("city");
        this.street=(String) houseJson.get("street");
        this.cp=(String) houseJson.get("cp");
        this.number= (Integer) houseJson.get("number");
        this.floor= (Integer) houseJson.get("floor");
        this.apartment=(String) houseJson.get("apartment");
        this.other=(String) houseJson.get("other");
        this.squareMeters= (double) houseJson.get("squareMeters");

    }

    public House(Integer id, String country, String city, String street, String cp, Integer number, Integer floor, String apartment, String other, float squareMeters) {
        this.id = id;
        this.country = country;
        this.city = city;
        this.street = street;
        this.cp = cp;
        this.number = number;
        this.floor = floor;
        this.apartment = apartment;
        this.other = other;
        this.squareMeters = squareMeters;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public String getApartment() {
        return apartment;
    }

    public void setApartment(String apartment) {
        this.apartment = apartment;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public double getSquareMeters() {
        return squareMeters;
    }

    public void setSquareMeters(double squareMeters) {
        this.squareMeters = squareMeters;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public Date getFechaBorrado() {
        return fechaBorrado;
    }

    public void setFechaBorrado(Date fechaBorrado) {
        this.fechaBorrado = fechaBorrado;
    }
/*
    public String getActivo() {
        return activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
    }

    public String get_links() {
        return _links;
    }

    public void set_links(String _links) {
        this._links = _links;
    }
    */
}
