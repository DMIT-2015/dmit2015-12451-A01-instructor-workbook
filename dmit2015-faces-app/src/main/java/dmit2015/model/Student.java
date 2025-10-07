package dmit2015.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.datafaker.Faker;

import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
public class Student {

    @Id
    @Column(name="studid", unique = true, nullable = false)
    private String id;
    @NotBlank
    private String name;
    @NotBlank
    private String courseTermSection;
    @Column(columnDefinition = "bytea")
    private byte[] picture;

    // copy constructor
    public Student(Student other) {
        this.id = other.id;
        this.name = other.name;
        this.courseTermSection = other.courseTermSection;
        this.picture = other.picture;
    }

    public static Student copyOf(Student other) {
        return new Student(other);
    }

    public static Student of(Faker faker) {
        var newStudent = new Student();
        newStudent.setId(UUID.randomUUID().toString());
        newStudent.setName(faker.name().fullName());
        newStudent.setCourseTermSection("DMIT2015-1251-E01");
        return newStudent;
    }



}