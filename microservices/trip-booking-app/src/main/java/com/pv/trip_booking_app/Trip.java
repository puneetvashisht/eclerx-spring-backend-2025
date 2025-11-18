
package com.pv.trip_booking_app;

import lombok.Data;

@Data
public class Trip {
    private int id;
    private String destination;
    private String startDate;
    private String endDate;
    private Double price;
    private Integer port;

}
