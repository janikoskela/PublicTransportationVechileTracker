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
package com.urhola.vehicletracker.connection.mattersoft;

import com.urhola.vehicletracker.connection.Connection;
import com.urhola.vehicletracker.exception.ConnectionException;
import static com.urhola.vehicletracker.parse.MatterSoftParser.parseGetVehiclesInputStream;
import com.urhola.vehicletracker.request.mattersoft.MatterSoftGetVehiclesRequest;
import com.urhola.vehicletracker.resource.vehicle.Vehicle;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URIBuilder;

/**
 *
 * @author janikoskela
 */
public class MatterSoftLiveHelsinki extends Connection {
    private final static String BASE_URL = "http://83.145.232.209:10001/";
    private final static int TIME_OUT_LENGTH = 5000;
    protected final static String REQUEST_METHOD_GET = "GET";
    
    private HttpURLConnection getOpenedConnection(List<NameValuePair> params, String responseMethod) throws ConnectionException {
        HttpURLConnection urlConnection;
        try {
            URIBuilder b = new URIBuilder(BASE_URL);
            b.addParameters(params);
            b.setCharset(Charset.forName("UTF-8"));
            URL url = b.build().toURL();
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setDoOutput(true);
            urlConnection.setRequestMethod(responseMethod);
            urlConnection.setReadTimeout(TIME_OUT_LENGTH);
            urlConnection.setConnectTimeout(TIME_OUT_LENGTH);
            urlConnection.connect();
            int responseCode = urlConnection.getResponseCode();
            if (responseCode != HttpURLConnection.HTTP_OK)
                throw new ConnectionException(urlConnection.getResponseMessage());
            return urlConnection;
        } catch (URISyntaxException|IOException ex) {
            throw new ConnectionException(ex);
        }
    }

    @Override
    public List<Vehicle> getVechiles(String longitude1, String longitude2, String latitude1, String latitude2) throws ConnectionException {
        MatterSoftGetVehiclesRequest req = new MatterSoftGetVehiclesRequest(longitude1, longitude2, latitude1, latitude2);
        List<NameValuePair> params = req.getParams();
        HttpURLConnection urlConnection = this.getOpenedConnection(params, REQUEST_METHOD_GET);
        InputStream in = null;
        try {
            in = urlConnection.getInputStream();
            return parseGetVehiclesInputStream(in);
        } catch (IOException e) {
            throw new ConnectionException(e);
        } finally {
            if (in != null)
                try {
                    in.close();
            } catch (IOException ex) {}
            urlConnection.disconnect();
        }
    }
}
