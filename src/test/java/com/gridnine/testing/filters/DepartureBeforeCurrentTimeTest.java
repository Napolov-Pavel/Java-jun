package com.gridnine.testing.filters;

import com.gridnine.testing.test.Flight;
import com.gridnine.testing.test.Segment;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DepartureBeforeCurrentTimeTest {
    private DepartureBeforeCurrentTime sut;
    private Segment segmentTest;
    private Flight flightTest;
    private List<Segment> listTest;

    @BeforeEach
    void setUp() {
        sut = new DepartureBeforeCurrentTime();
        listTest = new ArrayList<>();
        flightTest = new Flight(listTest);
    }

    @AfterEach
    void tearDown() {
        sut = null;
        listTest = null;
        flightTest = null;
        segmentTest = null;
    }

    @Test
    void shouldProperlyCheckTrue() {
        segmentTest = new Segment(LocalDateTime.now().minusDays(1), LocalDateTime.now());
        listTest.add(segmentTest);

        boolean result = sut.check(flightTest);

        assertTrue(result);
    }

    @Test
    void shouldProperlyCheckFalse() {
        segmentTest = new Segment(LocalDateTime.now().plusDays(1), LocalDateTime.now());
        listTest.add(segmentTest);

        boolean result = sut.check(flightTest);

        assertFalse(result);
    }
}