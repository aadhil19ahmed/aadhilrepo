package com.example.aadhilahmed.mapboxdeliveries1;

import com.example.aadhilahmed.mapboxdeliveries1.Models.Tasks;
import com.mapbox.mapboxsdk.annotations.PolylineOptions;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

/**
 * Created by aadhil.ahmed on 27-Oct-17.
 */

public class CircleOperations {
    public static class CircleImplementer{
        static PolylineOptions polylineOptions;
        public static void drawCircle(MapboxMap map, LatLng position,int color,double radiusMeters){
            polylineOptions=new PolylineOptions();
            polylineOptions.color(color);
            polylineOptions.width(5f);  //change the line width here
            polylineOptions.addAll(getCirclePoints(position,radiusMeters));
            map.addPolyline(polylineOptions);
        }

        public static void clearCircle(MapboxMap map){
            map.removeAnnotation(polylineOptions.getPolyline());
        }

        private static ArrayList<LatLng> getCirclePoints(LatLng position, double radius) {
            int degreesBetweenPoints = 5; // change here for shape
            int numberOfPoints = (int) Math.floor(360 / degreesBetweenPoints);
            double distRadians = radius / 6371000.0; // earth radius in meters
            double centerLatRadians = position.getLatitude() * Math.PI / 180;
            double centerLonRadians = position.getLongitude() * Math.PI / 180;
            ArrayList<LatLng> polygons = new ArrayList<>(); // array to hold all the points
            for (int index = 0; index < numberOfPoints; index++) {
                double degrees = index * degreesBetweenPoints;
                double degreeRadians = degrees * Math.PI / 180;
                double pointLatRadians = Math.asin(sin(centerLatRadians) * cos(distRadians)
                        + cos(centerLatRadians) * sin(distRadians) * cos(degreeRadians));
                double pointLonRadians = centerLonRadians + Math.atan2(sin(degreeRadians)
                                * sin(distRadians) * cos(centerLatRadians),
                        cos(distRadians) - sin(centerLatRadians) * sin(pointLatRadians));
                double pointLat = pointLatRadians * 180 / Math.PI;
                double pointLon = pointLonRadians * 180 / Math.PI;
                LatLng point = new LatLng(pointLat, pointLon);
                polygons.add(point);
            }
            // add first point at end to close circle
            polygons.add(polygons.get(0));
            return polygons;
        }
    }

//    public static int checkForPointsInsideCircle(List<Tasks> address,LatLng centre,float radiusinKm){
//        int no_of_PointsInside=0;
//        double distFromCenter;
//        double centerLat=centre.getLatitude();
//        double centerLong=centre.getLongitude();
//        for(int i=0;i<address.size();i++){
//            double Xpoint=address.get(i).getGeometry()[1];
//            double Ypoint=address.get(i).getGeometry()[0];
//
//            distFromCenter=distance(centerLat,centerLong,Ypoint,Xpoint);
//
//            boolean result=distanceComparer(distFromCenter,radiusinKm);
//            if(result==true){
//                no_of_PointsInside++;
//            }
//        }
//        return no_of_PointsInside;
//    }

    public static List<LatLng> checkForPointsInsideCircle(List<Tasks> address,LatLng centre,float radiusinKm){
        List<LatLng> insideCoordinates=new ArrayList<>();
        double distFromCenter;
        double centerLat=centre.getLatitude();
        double centerLong=centre.getLongitude();
        for(int i=0;i<address.size();i++){
            double Xpoint=address.get(i).getGeometry()[1];
            double Ypoint=address.get(i).getGeometry()[0];

            distFromCenter=distance(centerLat,centerLong,Ypoint,Xpoint);

            boolean result=distanceComparer(distFromCenter,radiusinKm);
            if(result==true){
                insideCoordinates.add(new LatLng(address.get(i).getGeometry()[0],address.get(i).getGeometry()[1]));
            }
        }
        return insideCoordinates;
    }

    private static double distance(double lat1, double lon1, double lat2, double lon2) {
        double theta = lon1 - lon2;
        double distinMiles = Math.sin(deg2rad(lat1))
                * Math.sin(deg2rad(lat2))
                + Math.cos(deg2rad(lat1))
                * Math.cos(deg2rad(lat2))
                * Math.cos(deg2rad(theta));
        distinMiles = Math.acos(distinMiles);
        distinMiles = rad2deg(distinMiles);
        distinMiles = distinMiles * 60 * 1.1515;
        double distinKm=distinMiles/0.6213;
        return (distinKm);
    }
    private static double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    private static double rad2deg(double rad) {
        return (rad * 180.0 / Math.PI);
    }

    private static boolean distanceComparer(double distFromCenter, float radiusInKm) {
        if(distFromCenter<=radiusInKm){
            return true;
        }else {
            return false;
        }
    }
}
