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
package com.urhola.vehicletracker.parse;

import com.urhola.vehicletracker.resource.vehicle.Bus;
import com.urhola.vehicletracker.resource.vehicle.Ferry;
import com.urhola.vehicletracker.resource.vehicle.Metro;
import com.urhola.vehicletracker.resource.vehicle.Train;
import com.urhola.vehicletracker.resource.vehicle.Tram;
import com.urhola.vehicletracker.resource.vehicle.Unknown;
import com.urhola.vehicletracker.resource.vehicle.Vehicle;
import com.urhola.vehicletracker.resource.vehicle.mattersoft.KutsuPlus;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author janikoskela
 */
public class MatterSoftParser {
    
    private final static String TYPE_VEHICLE_METRO = "metro";
    private final static String TYPE_VEHICLE_BUS = "bus";
    private final static String TYPE_VEHICLE_TRAIN = "train";
    private final static String TYPE_VEHICLE_TRAM = "tram";
    private final static String TYPE_VEHICLE_FERRY = "ferry";
    private final static String TYPE_VEHICLE_KUTSU_PLUS = "kutsu_plus";
    private final static String TYPE_VEHICLE_UNKNOWN = "unknown";
    
    public static String getVehicleType(String id) {
        try {
            if (id.contains("metro"))
                return TYPE_VEHICLE_METRO;
            if (id.contains("RHKL"))
                return TYPE_VEHICLE_TRAM;
            return TYPE_VEHICLE_UNKNOWN;
        } catch (Exception e) {
            return TYPE_VEHICLE_UNKNOWN;
        }
    }
    
    public static String getShortRouteId(String routeId) {
        return getShortRouteId(routeId, "");
    }
    
    public static String getShortRouteId(String routeId, String vehicleType) {
        try {
            String shortRouteId = "";
            switch(vehicleType) {
                case TYPE_VEHICLE_METRO:
                    shortRouteId = routeId;
                    break;
                case TYPE_VEHICLE_BUS:
                    shortRouteId = routeId;
                    break;
                case TYPE_VEHICLE_TRAIN:
                    shortRouteId = routeId;
                    break;
                case TYPE_VEHICLE_TRAM:
                    char firstIndex = routeId.charAt(2);
                    if (firstIndex != '0')
                        shortRouteId = String.valueOf(firstIndex);
                    shortRouteId += String.valueOf(routeId.charAt(3));
                    break;
                case TYPE_VEHICLE_FERRY:
                    shortRouteId = routeId;
                    break;
                case TYPE_VEHICLE_KUTSU_PLUS:
                    shortRouteId = routeId;
                    break;
                case TYPE_VEHICLE_UNKNOWN:
                default:
                    shortRouteId = routeId;
                    break;
            }
            return shortRouteId;
        } catch (Exception e) {
            if (routeId == null)
                return "";
            return routeId;
        }
    }

    public static List<Vehicle> parseGetVehiclesInputStream(InputStream in) throws IOException {
        //Id, route, lat, lng, bearing, direction, previous stop, current stop, departure
        List<Vehicle> vehicles = new ArrayList<>();
        int valueCounter = 0;
        String id = null, route = null, latitude = null, longitude = null, bearing = null, direction = null, previous = null, stop = null, currentStop = null, departure = null;
        String line;
        BufferedReader r = new BufferedReader(new InputStreamReader(in));
        String value = "";
        while((line = r.readLine()) != null) {
            int lineLength = line.length();
            for (int i = 0; i < lineLength; i++) {
                char c = line.charAt(i);
                if (c != ';') {
                    value += c;
                }
                else {
                    if (valueCounter == 0)
                        id = value;
                    else if (valueCounter == 1)
                        route = value;
                    else if (valueCounter == 2)
                        longitude = value;
                    else if (valueCounter == 3)
                        latitude = value;
                    else if (valueCounter == 4)
                        bearing = value;
                    else if (valueCounter == 5)
                        direction = value;
                    else if (valueCounter == 6)
                        previous = value;
                    else if (valueCounter == 7)
                        stop = value;
                    else if (valueCounter == 8)
                        currentStop = value;
                    else if (valueCounter == 9)
                        departure = value;
                    value = "";
                    valueCounter++;
                }
            }
            Vehicle vehicle;
            switch(getVehicleType(id)) {
                case TYPE_VEHICLE_METRO:
                    vehicle = new Metro();
                    break;
                case TYPE_VEHICLE_BUS:
                    vehicle = new Bus();
                    break;
                case TYPE_VEHICLE_FERRY:
                    vehicle = new Ferry();
                    break;
                case TYPE_VEHICLE_KUTSU_PLUS:
                    vehicle = new KutsuPlus();
                    break; 
                case TYPE_VEHICLE_TRAIN:
                    vehicle = new Train();
                    break;
                case TYPE_VEHICLE_UNKNOWN:
                default:
                    vehicle = new Unknown();
                    break;
                case TYPE_VEHICLE_TRAM:
                    vehicle = new Tram();
                    break;
            }
            vehicle.setId(id);
            vehicle.setRouteId(route);
            if (vehicle instanceof Tram)
                vehicle.setShortRouteId(getShortRouteId(route, TYPE_VEHICLE_TRAM));
            else
                vehicle.setShortRouteId(getShortRouteId(route));
            try {
                vehicle.setLatitude(Double.valueOf(latitude));
            } catch (Exception e) {}
            try {
                vehicle.setLongitude(Double.valueOf(longitude));
            } catch (Exception e) {}
            try {
                vehicle.setBearingDegrees(Double.valueOf(bearing));
            } catch (Exception e) {}
            vehicle.setDirection(direction);
            vehicle.setPreviousStop(previous);
            vehicle.setCurrentStop(currentStop);
            try {
                vehicle.setDepartureId(Integer.parseInt(departure));
            } catch (Exception e) {}
            vehicle.setStop(stop);
            
            vehicles.add(vehicle);
            valueCounter = 0;
        }
        return vehicles;
    }
}
