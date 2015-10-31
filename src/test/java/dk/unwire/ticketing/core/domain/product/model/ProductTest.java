package dk.unwire.ticketing.core.domain.product.model;

import dk.unwire.ticketing.core.domain.application.model.Application;
import dk.unwire.ticketing.core.domain.product.model.enums.TimeDefinition;
import org.junit.Before;
import org.junit.Test;

import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.HashSet;

import static org.junit.Assert.assertTrue;

public class ProductTest {

    private Product product;

    @Before
    public void setup() {
        TimePeriod timePeriod = TimePeriod.newBuilder()
                .withWeekDay(TimeDefinition.ALL_DAY)
                .withTimeZone("Europe/Copenhagen")
                .build();
        Collection<TimePeriod> timePeriods = new HashSet<>();
        timePeriods.add(timePeriod);

        this.product = Product.newBuilder()
                .withName("test1")
                .withType("type1")
                .withApplication(new Application())
                .withCertificateEnabled()
                .withProductVariant(3)
                .withBuyTimes(timePeriods)
                .build();
    }

    @Test
    public void testIsVoucherProduct() throws Exception {
        assertTrue(this.product.isVoucherProduct());
    }

    @Test
    public void isAllowedToBuy() {
        assertTrue(this.product.isAllowedToBuy(ZonedDateTime.now()));
    }
}