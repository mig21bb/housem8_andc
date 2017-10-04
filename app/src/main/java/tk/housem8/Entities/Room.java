package tk.housem8.Entities;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrador on 27/09/2017.
 */

public class Room {

    private Integer id;
    private float squareMeters;
    private Boolean windows;
    private Date fechaCreacion;
    private Date fechaModificacion;
    private Date fechaBorrado;
    private boolean activo;
    private List<Ocupation> ocupationList;
    private House houseId;

    //private Integer houseId;

    private RoomClass roomClass;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public float getSquareMeters() {
        return squareMeters;
    }

    public void setSquareMeters(float squareMeters) {
        this.squareMeters = squareMeters;
    }

    public Boolean getWindows() {
        return windows;
    }

    public void setWindows(Boolean windows) {
        this.windows = windows;
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

    public List<Ocupation> getOcupationList() {
        return ocupationList;
    }

    public void setOcupationList(List<Ocupation> ocupationList) {
        this.ocupationList = ocupationList;
    }

    public House getHouseId() {
        return houseId;
    }

    public void setHouseId(House houseId) {
        this.houseId = houseId;
    }

    public RoomClass getRoomClass() {
        return roomClass;
    }

    public void setRoomClass(RoomClass roomClass) {
        this.roomClass = roomClass;
    }
}
