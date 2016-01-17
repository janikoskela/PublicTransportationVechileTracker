/*
 * Copyright (C) 2015 janikoskela
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.urhola.vehicletracker.resource.vehicle;

import com.urhola.vehicletracker.resource.Resource;
import com.urhola.vehicletracker.resource.vehicle.mattersoft.KutsuPlus;
import java.io.Serializable;

/**
 *
 * @author janikoskela
 */
public class Vehicle extends Resource implements Serializable {
    private static final long serialVersionUID = 7526472295622776147L;
    private String id;
    private String routeId;
    private String shortRouteId;
    private Double latitude;
    private Double longitude;
    private Double bearingDegrees;
    private String direction;
    private String previousStop;
    private String stop;
    private String currentStop;
    private int departureId;
    private String operator;
    private String name;
    private Double speed;
    private Double acceleration;

    public String getShortRouteId() {
        return shortRouteId;
    }

    public void setShortRouteId(String shortRouteId) {
        this.shortRouteId = shortRouteId;
    }
    
    public boolean isBus() {
        return this instanceof Bus;
    }
    
    public boolean isFerry() {
        return this instanceof Ferry;
    }
    
    public boolean isMetro() {
        return this instanceof Metro;
    }
    
    public boolean isTrain() {
        return this instanceof Train;
    }
    
    public boolean isTram() {
        return this instanceof Tram;
    }
    
    public boolean isKutsuPlus() {
        return this instanceof KutsuPlus;
    }
    
    public boolean isUnknown() {
        return this instanceof Unknown;
    }

    public String getStop() {
        return stop;
    }

    public void setStop(String stop) {
        this.stop = stop;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRouteId() {
        return routeId;
    }

    public void setRouteId(String routeId) {
        this.routeId = routeId;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getBearingDegrees() {
        return bearingDegrees;
    }

    public void setBearingDegrees(Double bearingDegrees) {
        this.bearingDegrees = bearingDegrees;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getPreviousStop() {
        return previousStop;
    }

    public void setPreviousStop(String previousStop) {
        this.previousStop = previousStop;
    }

    public String getCurrentStop() {
        return currentStop;
    }

    public void setCurrentStop(String currentStop) {
        this.currentStop = currentStop;
    }

    public int getDepartureId() {
        return departureId;
    }

    public void setDepartureId(int departureId) {
        this.departureId = departureId;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getSpeed() {
        return speed;
    }

    public void setSpeed(Double speed) {
        this.speed = speed;
    }

    public Double getAcceleration() {
        return acceleration;
    }

    public void setAcceleration(Double acceleration) {
        this.acceleration = acceleration;
    }
    
}
