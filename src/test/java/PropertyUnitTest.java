import org.junit.Test;
import org.rolap4j.common.Property;

/**
 * Created by andriantomanga on 11/05/16.
 *
 * @version 1.0-RELEASE
 * @since 1.0-RELEASE
 */

public class PropertyUnitTest {

    @Test
    public void toStringTest() {

        Property property = new Property();

        property.setType("type");
        property.setName("name");
        property.setColumn("COLUMN");

        // call toString
        System.out.println(property);
    }
}
