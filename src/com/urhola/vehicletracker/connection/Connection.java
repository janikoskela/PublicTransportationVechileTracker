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
package com.urhola.vehicletracker.connection;

import com.urhola.vehicletracker.connection.mattersoft.MatterSoftLiveHelsinki;
import com.urhola.vehicletracker.exception.ConnectionException;
import com.urhola.vehicletracker.request.GetVehiclesRequest;
import com.urhola.vehicletracker.request.Request;
import com.urhola.vehicletracker.resource.vehicle.Vehicle;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

/**
 *
 * @author janikoskela
 */
public abstract class Connection {

    public abstract List<Vehicle> getVechiles(GetVehiclesRequest request) throws ConnectionException;
    
    public static Connection getConnection(Request request) {
        //TODO: select api based on location coordinates
        return new MatterSoftLiveHelsinki();
    }
    
    protected String readStream(InputStream in) throws IOException {
        String line;
        BufferedReader r = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        while((line = r.readLine()) != null) 
                sb.append(line);
        return sb.toString();
    }
}
