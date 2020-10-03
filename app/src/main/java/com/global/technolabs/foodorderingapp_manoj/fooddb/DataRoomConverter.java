package com.global.technolabs.foodorderingapp_manoj.fooddb;

import androidx.room.TypeConverter;

import java.util.Date;

public class DataRoomConverter {
    @TypeConverter
    public static Date toDate(Long value) {

        return value == null ? null : new Date(value);
    }

    public static Long toLong(Date value) {
        return value == null ? null : value.getTime();
    }
}
