package models.course;
import models.course.Semester.Season;

public class CourseOffering {

	private int courseOfferingId;
	private Course course;
	private Semester semester;
	private short section;

    // bean for play
    public CourseOffering() {

    }

	// when retrieving from database, has student
	public CourseOffering(final int courseOfferingId, final Course course,
			final Semester semester, final short section) {

		this.courseOfferingId = courseOfferingId;
		this.course = course;
		this.semester = semester;
		this.section = section;
	}

//TODO add department in the constructor	
	// when creating before adding to database
	public CourseOffering(final Course course, final Semester semester,
                          final short section) {

		courseOfferingId = -1;
		this.course = course;
		this.semester = semester;
		this.section = section;
	}

	public int getCourseOfferingId() {
		return courseOfferingId;
	}

	public void setCourseOfferingId(int courseOfferingId) {
		this.courseOfferingId = courseOfferingId;
	}
	

	public Course getCourse() {
		return course;
	}
	
	public void setCourse(Course course) {
		this.course = course;
	}

	public Semester getSemester() {
		return semester;
	}

	public void setSemester(Semester semester) {
		this.semester = semester;
	}

	public short getSectionNumber() {
		return section;
	}

	public void setSectionNumber(short section) {
		this.section = section;
	}
	
	@Override
	public String toString() {
		return "student: " + courseOfferingId;
	}
}
