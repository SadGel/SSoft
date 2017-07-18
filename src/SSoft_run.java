import java.util.HashMap;

public class SSoft_run {

    public static void main(String[] args) {

        HashMap propertyList;

        propertyList = Property.Read();

        Html_page.generate(propertyList);



    }

}
