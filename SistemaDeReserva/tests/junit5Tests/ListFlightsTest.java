package junit5Tests;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Target(METHOD)
@Retention(RUNTIME)
@Tag("ListFlights")
@Test
public @interface ListFlightsTest {

}
