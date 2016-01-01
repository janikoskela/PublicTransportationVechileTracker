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
package com.urhola.vehicletracker.request.mattersoft;

import com.urhola.vehicletracker.request.Title;

/**
 *
 * @author janikoskela
 */
public class MatterSoftGetVehiclesRequest extends MatterSoftRequest {
    @Title("type")
    private String type = "vehicles";
    
    /**
    * 0 = Request is for all the vehicles, 1 = Request is for all vehicles logged on routes.
    */
    @Title("lng1")
    private String longitude1;
    
    /**
    * 0 = Request is for all the vehicles, 1 = Request is for all vehicles logged on routes.
    */
    @Title("lat1")
    private String latitude1;
    
    /**
    * 0 = Request is for all the vehicles, 1 = Request is for all vehicles logged on routes.
    */
    @Title("lng2")
    private String longitude2;
    
    /**
    * 0 = Request is for all the vehicles, 1 = Request is for all vehicles logged on routes.
    */
    @Title("lat2")
    private String latitude2;

    public MatterSoftGetVehiclesRequest(String longitude1, String longitude2, String latitude1, String latitude2) {
        this.latitude1 = latitude1;
        this.latitude2 = latitude2;
        this.longitude1 = longitude1;
        this.longitude2 = longitude2;
    }
}
