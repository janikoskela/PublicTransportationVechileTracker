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

/**
 *
 * @author janikoskela
 */
public abstract class Request {
    private String longitude1;
    private String latitude1;
    private String longitude2;
    private String latitude2;

    public Request(String longitude1, String latitude1, String longitude2, String latitude2) {
        this.longitude1 = longitude1;
        this.latitude1 = latitude1;
        this.longitude2 = longitude2;
        this.latitude2 = latitude2;
    }

    public String getLongitude1() {
        return longitude1;
    }

    public String getLatitude1() {
        return latitude1;
    }

    public String getLongitude2() {
        return longitude2;
    }

    public String getLatitude2() {
        return latitude2;
    }
    
}
