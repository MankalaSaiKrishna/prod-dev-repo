
public class DBObject {
private String userName;
public String getUserName() {
	return userName;
}
public void setUserName(String userName) {
	this.userName = userName;
}
public String getToEmailId() {
	return toEmailId;
}
public void setToEmailId(String toEmailId) {
	this.toEmailId = toEmailId;
}
public String getDateOfBirth() {
	return DateOfBirth;
}
public void setDateOfBirth(String dateOfBirth) {
	DateOfBirth = dateOfBirth;
}
private String toEmailId;
private String DateOfBirth;
private String emailSentYear;
public String getEmailSentYear() {
	return emailSentYear;
}
public void setEmailSentYear(String emailSentYear) {
	this.emailSentYear = emailSentYear;
	System.out.println("Test Newlines");
	int a=4;
	System.out.println("a"+a);
}

}
