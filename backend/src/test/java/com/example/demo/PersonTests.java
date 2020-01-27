package com.example.demo;

import com.example.demo.entity.Person;
import com.example.demo.repository.PersonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class PersonTests {

    private Validator validator;

    // ตัวอย่างการ Autowired
    @Autowired
    private PersonRepository personRepository;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    // ตั้งชื่อ test ให้สอดคล้องกับสิ่งที่ต้อง test
    @Test
    void testPersonDataIsOk() { // ใส่ข้อมูลในทุกฟีลด์ให้ครบเพื่อเทสว่าข้อมูลที่เราบันทึกนั้นถูกต้องทุกประการ
                                // และสามารถบันทึกได้
        Person person = new Person();
        person.setPersonalId("B6005795"); // personalId ต้องขึ้นต้นด้วย B หรือ M หรือ D แล้วตามด้วย d คือ เลขฐาน 10 และ
                                          // {7} คือ 7 ตัว
        person.setName("Pongsakorn"); // Size(min = 0,max = 50) คือ ความยาวสตริงของ name ต้องไม่น้อยกว่า 0
                                      // และไม่มากกว่า 50
        person.setEmail("B6005795@sut.ac.th"); // Email คือ จะต้องให้ฟีลด์ email เป็น email ที่มี @ และ . เช่น
                                               // B6005795@sut.ac.th
        person.setHight(168); // Min(100) คือการกำหนดค่าที่เป็นตัวเลขขั้นต่ำ ในที่นี้คือ 100 เซนติเมคร
        person.setInventoryweight(15.00); // Max(20) คือการกำหนดค่าที่เป็นตัวเลขขั้นสูงสุด ในที่นี้คือ 20 กิโลกรัม
        person = personRepository.saveAndFlush(person);

        Optional<Person> found = personRepository.findById(person.getId()); // สร้างตัวแปรชื่อว่า found
        // ที่ดึงค่ามาจาก person ที่เพิ่งถูกบันทึกไปใน บรรทัด saveAndFlush
        assertEquals("B6005795", found.get().getPersonalId());
        assertEquals("Pongsakorn", found.get().getName());
        assertEquals("B6005795@sut.ac.th", found.get().getEmail());
        assertEquals(168, found.get().getHight());
        assertEquals(15, found.get().getInventoryweight());
    }

}
