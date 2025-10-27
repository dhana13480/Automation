package gov.michigan.obra.automation.page.testdata;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserData {

	private String ssn;

	private String firstName;
	private String lastName;
	private String email;
	private String currAddress;
	private String permAddress;
	private String age;
	private String salary;
	private String department;

	private String userId;
	private String password;
	private Date date;

	private String city;
	private String state;
	private String zip;

	private String phoneNumber;


}
