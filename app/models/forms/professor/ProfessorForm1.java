package models.forms.professor;

import models.forms.FormUtils;
import models.person.ContactInformation;
import models.person.Professor;
import models.person.Student;
import play.data.validation.Constraints;

public class ProfessorForm1 {

    @Constraints.Required
    @Constraints.MinLength(message="Must be at least 2 letters", value=2)
    @Constraints.MaxLength(message="Cannot be longer than 30 letters", value=30)
    @Constraints.Pattern(value= FormUtils.LATIN_CHARACTERS_REGEX, message="Last name can only be latin alphabetic characters")
    public String lastName;
    @Constraints.Required
    @Constraints.MinLength(message="Must be at least 2 letters", value=2)
    @Constraints.MaxLength(message="Cannot be longer than 20 letters", value=20)
    @Constraints.Pattern(value=FormUtils.LATIN_CHARACTERS_REGEX, message="Last name can only be latin alphabetic characters")
    public String firstName;
    @Constraints.Required
    @Constraints.Pattern(value= FormUtils.DEPARTMENT_REGEX, message="Please enter the 2 to 4 letter acryonym (e.g. CS)")
    public String department;
    @Constraints.Required
    @Constraints.Pattern(value= FormUtils.DATE_REGEX, message="Please enter date as mm/dd/yyyy")
    public String dateOfBirth;
    @Constraints.Required
    @Constraints.MinLength(message="Must be at least 10 characters", value=10)
    @Constraints.MaxLength(message="Cannot be longer than 200 characters", value=100)
    public String homeAddr;
    @Constraints.MinLength(message="Must be at least 10 characters", value=10)
    @Constraints.MaxLength(message="Cannot be longer than 200 characters", value=100)
    public String workAddr;
    @Constraints.Pattern(value = FormUtils.PHONE_REGEX, message="Please enter phone number as 5556667777")
    public String workPhone;
    @Constraints.Pattern(value = FormUtils.PHONE_REGEX, message="Please enter phone number as 5556667777")
    public String homePhone;
    @Constraints.Pattern(value = FormUtils.PHONE_REGEX, message="Please enter phone number as 5556667777")
    public String cellPhone;

    public Professor toProfessor() {
        final Professor p = new Professor();
        p.setContactInformation(new ContactInformation(homeAddr, workAddr,
                firstName, lastName, workPhone, homePhone, cellPhone));
        p.setDepartment(department);
        p.setDateOfBirth(dateOfBirth);

        return p;
    }
}
