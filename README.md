# Vehicle-Tracker
So the idea is to create a library which can support multiple public transportation vehicle tracking APIs.

# Supported APIs
 - Helsinki region
  - HSL Live 1.6 by MatterSoft (http://www.mattersoft.fi/)

# Dependencies
Uses Apaches HttpComponents:
 - httpclient-4.5.1.jar
 - httpclient-cache-4.5.1.jar
 - httpclient-win-4.5.1.jar
 - httpcore-4.4.3.jar

# Examples
```
try {
    Double southWestLongitude = 23.0;
    Double southWestLatitude = 23.0;
    Double northEastLongitude = 60.0;
    Double northEastLatitude = 61.0;    
    GetVehiclesRequest req = new GetVehiclesRequest(southWestLongitude, southWestLatitude, northEastLongitude, northEastLatitude);
    List<Vehicle> v = VehicleTracker.getVechiles(req);
    System.out.println("NUMBER OF VEHICLES " + v.size());
    for (Vehicle vv : v) {
        System.out.println("ID: " + vv.getId());
        System.out.println("current stop: " + vv.getCurrentStop());
        System.out.println("direction: " + vv.getDirection());
        System.out.println("latitude: " + vv.getLatitude());
        System.out.println("longitude: " + vv.getLongitude());
        System.out.println("name: " + vv.getName());
        System.out.println("operator: " + vv.getOperator());
        System.out.println("prev stop: " + vv.getPreviousStop());
        System.out.println("route id: " + vv.getRouteId());
        System.out.println("stop: " + vv.getStop());
        System.out.println("acceleration: " + vv.getAcceleration());
        System.out.println("bearing degrees: " + vv.getBearingDegrees());
        System.out.println("departure id: " + vv.getDepartureId());
        System.out.println("speed: " + vv.getSpeed());
        System.out.println("is bus: " + vv.isBus());
        System.out.println("is ferry: " + vv.isFerry());
        System.out.println("is metro: " + vv.isMetro());
        System.out.println("is train: " + vv.isTrain());
        System.out.println("is tram: " + vv.isTram());
        System.out.println("is unknown: " + vv.isUnknown());
    }
} catch (ConnectionException ex) {
    //do something senseful with exception
    System.out.println(ex.getMessage());
}
```
