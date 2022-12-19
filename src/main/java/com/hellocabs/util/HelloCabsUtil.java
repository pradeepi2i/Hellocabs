/*
 * <p>
 *   Copyright (c) All rights reserved Ideas2IT
 * </p>
 */

package com.hellocabs.util;

import com.hellocabs.configuration.LoggerConfiguration;
import com.hellocabs.constants.HelloCabsConstants;
import com.hellocabs.dto.RatingDto;
import com.hellocabs.enumeration.Status;
import com.hellocabs.exception.HelloCabsException;
import com.hellocabs.model.Cab;
import com.hellocabs.model.CabCategory;
import com.hellocabs.model.Ride;
import java.time.temporal.ChronoUnit;
import java.util.List;
import org.apache.log4j.Logger;

/**
 * <p>
 *   This Class is used as a helper class that contains calculation
 *   based on user input or that contains custom calculations which
 *   are in need for the entire application
 * </p>
 *
 * @author : Pradeep
 * created on 5/11/2022
 * @version 1.0
 *
 */
public class HelloCabsUtil {

    private static final Logger logger = LoggerConfiguration
            .getInstance(HelloCabsConstants.UTIL_CLASS);

    /**
     * <p>
     *   Method used to get the ride rating from user
     *   and calculate the average driver rating for the cab
     * </p>
     *
     * @param cab {@link Cab} cabDto in which rating has to be updated
     * @param ratingDto {@link RatingDto} ratingDto which has ride's rating
     * @return {@link Cab} updated driver rating
     *
     */
    public static Cab calculateAverageRating(Cab cab, RatingDto ratingDto) {

        if (null != cab.getRides()) {
            List<Double> ratings = cab.getRides().stream()
                    .map(Ride:: getRating).toList();
            Double averageRating = ratings.stream().mapToDouble(
                    rating -> rating).average().orElse(HelloCabsConstants.BASE_RATING);
            cab.setDriverRating(averageRating);
        } else {
            cab.setDriverRating(ratingDto.getRating());
        }
        return cab;
    }

    /**
     * <p>
     *   Calculate TravelFare by using time difference between
     *   PickUpTime And DropTime, if time difference exceeds basic
     *   fare then extra charge will be added for every additional hour
     *   Also if ride is booked in peak hour respective price has been
     *   calculated for base time and also for additional time
     * </p>
     *
     * @param ride {@link Ride} rideDto
     * @param cabCategory {@link CabCategory} id to be searched
     * @return {@link Double}returns RidePrice by Time Of Travel
     *
     */
    public static Double calculateTravelFare(Ride ride, CabCategory cabCategory) {

        if ((Status.DROPPED.toString()).equalsIgnoreCase(ride.getRideStatus())) {
            Long timeDifference = (ChronoUnit.HOURS.between(
                    ride.getRidePickedTime(), ride.getRideDroppedTime()));
            Double[] price = {cabCategory.getInitialFare(),
                    cabCategory.getExtraFarePerHour(), cabCategory.getPeakHourFare()};
            boolean isPeakHour = (Integer.toString(ride.getRideBookedTime().getHour())
                    .matches(HelloCabsConstants.PEAK_HOUR_REGEX));
            /*
             *   Calculate the difference between picked and dropped time,
             *   if difference is lesser than minimum booking hour and if
             *   ride booked time is peak hour then added the respective
             *   peak hour fare else return base fare
             *
             *   Incase, if time difference is greater than minimum booking hour,
             *   then if booked time is peak hour then add the additional fare and
             *   peak hour fare together with base fare
             *   else adds the additional fare with base fare
             */
            if (HelloCabsConstants.MINIMUM_BOOKING_HOUR > (timeDifference)) {
                return isPeakHour ? (price[0] + price[2]) : price[0];
            } else if (HelloCabsConstants.MINIMUM_BOOKING_HOUR < (timeDifference)) {
                Double fare = price[0] + ((timeDifference
                        - HelloCabsConstants.MINIMUM_BOOKING_HOUR) * price[1]);
                return isPeakHour ? (fare + price[2]) : fare;
            }
        }
        logger.info(HelloCabsConstants.CUSTOMER_NOT_DROPPED);
        /*throws exception stated that customer is not dropped yet*/
        throw new HelloCabsException(HelloCabsConstants.CUSTOMER_NOT_DROPPED);
    }
}
