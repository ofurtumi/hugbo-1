package is.hi.hugbo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;

import is.hi.hugbo.model.Course;
import is.hi.hugbo.repositories.CourseRepository;

@SpringBootApplication
@PropertySource("classpath:application.properties")
public class HugboApplication {
  public static void main(String[] args) {
    SpringApplication.run(HugboApplication.class, args);
  }

  /**
   * Function to simplify saving courses to the database.
   * Makes sure to not save a course if it already exists.
   * 
   * @param course     - The course to save
   * @param repository - The repository to save the course to
   */
  private void saveIfExists(Course course, CourseRepository repository) {
    if (repository.findByCourseName(course.getCourseName()) == null) {
      repository.save(course);
    }
  }

  /**
   * On first run of the application, populate the database with some courses.
   * 
   * @param repository - The repository to save the courses to
   * @return A CommandLineRunner that will run on startup
   */
  @Bean
  public CommandLineRunner courses(CourseRepository repository) {
    return (args) -> {
      Course[] courses = {
          new Course("Háskólavöllur", new Integer[] { 4, 4, 3, 4, 5, 4, 3, 3, 5, 4, 3, 5, 4, 4, 3, 5, 4, 4 }),
          new Course("Hlíðarendavöllur", new Integer[] { 5, 4, 3, 4, 4, 3, 4, 5, 4, 5, 4, 3, 4, 4, 3, 4, 5, 4 }),
          new Course("Dimmivöllur", new Integer[] { 3, 4, 5, 4, 4, 5, 4, 4, 3, 5, 3, 5, 4, 4, 4, 4, 3, 4 }),
          new Course("Grafarholt", new Integer[] { 4, 4, 4, 4, 4, 3, 4, 3, 4, 5, 3, 5, 4, 4, 4, 4, 3, 4 }),
          new Course("Korpa", new Integer[] { 4, 4, 4, 4, 4, 3, 4, 4, 4, 5, 3, 5, 4, 4, 4, 4, 3, 4 }),
          new Course("Oddur", new Integer[] { 4, 3, 3, 4, 4, 4, 4, 4, 4, 5, 3, 5, 4, 5, 4, 4, 3, 4 })
      };

      for (Course course : courses) {
        saveIfExists(course, repository);
      }
    };
  }
}
