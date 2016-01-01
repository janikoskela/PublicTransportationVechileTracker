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

import com.urhola.vehicletracker.request.Request;
import com.urhola.vehicletracker.request.Title;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

/**
 *
 * @author janikoskela
 */
public class MatterSoftRequest extends Request {
    /**
    * 0 = Request is for all the vehicles, 1 = Request is for all vehicles logged on routes.
    */
    @Title("online")
    private int online = 0;
    
    /**
    * Request is for vehicles stated and separated by an underline, one request can include a maximum of 10 id’s.
    */
    @Title("ids")
    private List<Integer> ids;
    
    /**
    * Request is for vehicles stated and separated by an underline that go to the direction given after the dot, one request can include a maximum of 10 id’s.
    */
    @Title("lines")
    private List<String> lines;
    
    /**
    * Request is for vehicles on stated line.
    */
    @Title("line")
    private String line;
    
    /**
    * In which direction the requested vehicles go.
    */
    @Title("direction")
    private int direction;
    
    /**
    * Number of values to be returned.
    */
    @Title("count")
    private int count;

    public int getOnline() {
        return online;
    }

    public void setOnline(int online) {
        this.online = online;
    }

    public List<Integer> getIds() {
        return ids;
    }

    public void setIds(List<Integer> ids) {
        this.ids = ids;
    }

    public List<String> getLines() {
        return lines;
    }

    public void setLines(List<String> lines) {
        this.lines = lines;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
    
    @Override
    public List<NameValuePair> getParams() {
        List<NameValuePair> keyValuePairs = new ArrayList<>();
        Field[] fields = this.getClass().getDeclaredFields();
        for (Field field : fields) {
            try {
                field.setAccessible(true);
                String key = field.getAnnotation(Title.class).value();
                String value = null;
                if (field.get(this) instanceof List) {
                    for (Object obj : (List<?>)field.get(this)) {
                        String objValue = (String)obj;
                        if (value == null)
                            value = objValue;
                        else
                            value += "_" + objValue;
                    }
                } else {
                    value = field.get(this).toString();
                }
                keyValuePairs.add(new BasicNameValuePair(key, value));
            } catch (IllegalArgumentException | IllegalAccessException | NullPointerException e) {
                e.printStackTrace();
            }
        }
        return keyValuePairs;
    }
}
