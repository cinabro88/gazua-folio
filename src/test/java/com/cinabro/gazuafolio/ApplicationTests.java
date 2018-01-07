package com.cinabro.gazuafolio;

import org.junit.Test;

import java.time.ZoneId;
import java.time.ZonedDateTime;

public class ApplicationTests {

    @Test
    public void test() {
        ZonedDateTime dateTime = ZonedDateTime.now(ZoneId.of("UTC"));
        System.out.println(dateTime);
        System.out.println(dateTime.toEpochSecond());
    }

}
