package gov.michigan.obra.automation.page.testdata;

import com.github.javafaker.Faker;
import org.testng.annotations.DataProvider;
public class UserDataProvider {

	@DataProvider(name = "User Data", parallel = true)
	public Object[][] userDataProvider() {
		Object[][] userDataSet = { { generateUserData() } };

		return userDataSet;

	}

	public UserData generateUserData() {
		Faker faker = new Faker();
		return UserData.builder().ssn(faker.number().digits(9)).firstName(faker.name().firstName()).lastName(faker.name().lastName()).email(faker.internet().emailAddress())
				.currAddress(faker.address().fullAddress()).permAddress(faker.address().fullAddress())
				.age(faker.number().digit()).salary(faker.number().digits(5)).department(faker.company().name())
				.date(faker.date().birthday()).city(faker.address().city()).zip(faker.address().zipCode()).state(faker.address().zipCode()).phoneNumber(faker.phoneNumber().cellPhone())
				.build();
	}

}
