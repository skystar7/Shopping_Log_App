package mobi.hoopoe.android.shoppinglog.model;

/**
 * Created by Ahmad on 3/16/2016.
 */
public class MySingleton {
    private static MySingleton ourInstance = new MySingleton();
    private String testData;

    public String getTestData() {
        return testData;
    }

    public void setTestData(String testData) {
        this.testData = testData;
    }

    public static MySingleton getInstance() {
        return ourInstance;
    }

    private MySingleton() {
    }


}
