package tk.housem8.Entities;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrador on 26/09/2017.
 */

public class Commerce {

    private Integer id;
    private String name;
    private String coordinates;
    private String logo;
    //private CostFamily costFamily;
    private Date fechaCreacion;
    private Date fechaModificacion;
    private Date fechaBorrado;
    private boolean activo;

    //private List<Cost> costList;

    public Commerce(JSONObject json) throws JSONException {

        this.id = (Integer) json.get("id");
        this.name= (String) json.get("name");
        if(!json.get("coordinates").equals(JSONObject.NULL)) {
            this.coordinates = (String) json.get("coordinates");
        }
        if(!json.get("logo").equals(JSONObject.NULL)) {
            this.logo = (String) json.get("logo");
        }


    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
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

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    @Override
    public String toString() {
        return name;
    }
}
