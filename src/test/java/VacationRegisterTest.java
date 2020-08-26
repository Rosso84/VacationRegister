
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class VacationRegisterTest {

    @Test
    public void testIfDataIsExtracted() {
        String[] numberOfElementsExtractedFromLastRowInTable =
                VacationRegister.readFromFileAndPopulateTable(
                        "C:\\Users\\Rosso\\Desktop\\sysco\\newVaca\\vacation_short.csv");

        assertEquals(17, numberOfElementsExtractedFromLastRowInTable.length);

        String name = numberOfElementsExtractedFromLastRowInTable[0];
        String email = numberOfElementsExtractedFromLastRowInTable[1];

        assertEquals("Trine Mostad", name);
        assertEquals("trine.mostad@sysco.no", email);

    }

}
