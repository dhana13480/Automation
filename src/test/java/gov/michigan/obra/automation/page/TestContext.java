package gov.michigan.obra.automation.page;

public class TestContext {
	
	// ThreadLocal ensures thread safety when tests run in parallel
    private static ThreadLocal<String> createdConsumerSSN = new ThreadLocal<>();

    // Store the SSN
    public static void setCreatedConsumerSSN(String ssn) {
        createdConsumerSSN.set(ssn);
    }

    // Retrieve the SSN
    public static String getCreatedConsumerSSN() {
        return createdConsumerSSN.get();
    }

    // Clear the stored SSN after test
    public static void clear() {
        createdConsumerSSN.remove();
    }

}
