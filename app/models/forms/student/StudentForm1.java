package models.forms.student;

import models.forms.FormUtils;
import models.person.ContactInformation;
import models.person.Student;
import play.data.format.Formats;
import play.data.validation.Constraints.*;

public class StudentForm1 {

    @Required
    @MinLength(message="Must be at least 2 letters", value=2)
    @MaxLength(message="Cannot be longer than 30 letters", value=30)
    @Pattern(value=FormUtils.LATIN_CHARACTERS_REGEX, message="Last name can only be latin alphabetic characters")
    public String lastName;
    @Required
    @MinLength(message="Must be at least 2 letters", value=2)
    @MaxLength(message="Cannot be longer than 20 letters", value=20)
    @Pattern(value=FormUtils.LATIN_CHARACTERS_REGEX, message="Last name can only be latin alphabetic characters")
    public String firstName;
    @Required
    @Pattern(value= FormUtils.DATE_REGEX, message="Please enter date as mm/dd/yyyy")
    public String dateOfBirth;
    @Required
    @MinLength(message="Must be at least 10 characters", value=10)
    @MaxLength(message="Cannot be longer than 200 characters", value=100)
    public String homeAddr;
    @MinLength(message="Must be at least 10 characters", value=10)
    @MaxLength(message="Cannot be longer than 200 characters", value=100)
    public String workAddr;
    @Pattern(value = FormUtils.PHONE_REGEX, message="Please enter phone number as 5556667777")
    public String workPhone;
    @Pattern(value = FormUtils.PHONE_REGEX, message="Please enter phone number as 5556667777")
    public String homePhone;
    @Pattern(value = FormUtils.PHONE_REGEX, message="Please enter phone number as 5556667777")
    public String cellPhone;

    public Student toStudent() {
        final Student s = new Student();
        s.setContactInformation(new ContactInformation(homeAddr, workAddr,
                firstName, lastName, workPhone, homePhone, cellPhone));
        s.setCurrentBalance(0);
        s.setDateOfBirth(dateOfBirth);

        return s;
    }
}
