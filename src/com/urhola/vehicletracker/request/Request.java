/*
 * Copyright (C) 2016 janikoskela
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
package com.urhola.vehicletracker.request;

import com.urhola.vehicletracker.exception.ConnectionException;

/**
 *
 * @author janikoskela
 */
public abstract class Request {
    private Double southWestLongitude;
    private Double southWestLatitude;
    private Double northEastLongitude;
    private Double northEastLatitude;
    
    public Request() {}
    
    public Request(Double southWestLongitude, Double southWestLatitude, Double northEastLongitude, Double northEastLatitude) {
        this.southWestLongitude = southWestLongitude;
        this.southWestLatitude = southWestLatitude;
        this.northEastLongitude = northEastLongitude;
        this.northEastLatitude = northEastLatitude;
    }
    
    public abstract Object execute() throws ConnectionException;

    public void setSouthWestLongitude(Double longitude1) {
        this.southWestLongitude = longitude1;
    }

    public void setSouthWestLatitude(Double latitude1) {
        this.southWestLatitude = latitude1;
    }

    public void setNorthEastLongitude(Double longitude2) {
        this.northEastLongitude = longitude2;
    }

    public void setNorthEastLatitude(Double latitude2) {
        this.northEastLatitude = latitude2;
    }

    public Double getSouthWestLongitude() {
        return southWestLongitude;
    }

    public Double getSouthWestLatitude() {
        return southWestLatitude;
    }

    public Double getNorthEastLongitude() {
        return northEastLongitude;
    }

    public Double getNorthEastLatitude() {
        return northEastLatitude;
    }
}