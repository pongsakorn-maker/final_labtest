package com.example.demo.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Data
@NoArgsConstructor
@Table(
// uniqueConstraints = @UniqueConstraint(columnNames = {"PERSON_ID"})
)
public class Person {

    @GeneratedValue
    @Id
    private Long id;

    @NotNull
    @Pattern(regexp = "[BMD]\\d{7}") // personalId ต้องขึ้นต้นด้วย B หรือ M หรือ D แล้วตามด้วย d คือ เลขฐาน 10 และ
                                     // {7} คือ 7 ตัว
    private String personalId; // (รหัสประจำตัวบุคคล)

    @NotNull // NotNull คือ name ห้ามเป็น null
    @Size(min = 0, max = 50) // Size(min = 0,max = 50) คือ ความยาวสตริงของ name ต้องไม่น้อยกว่า 0
                             // และไม่มากกว่า 50
    private String name; // (ชื่อ)

    @NotNull
    @Email // Email คือ จะต้องให้ฟีลด์ email เป็น email ที่มี @ และ . เช่น
           // B6005795@sut.ac.th
    private String email; // (อีเมลล์)

    @NotNull
    @Min(100) // Min(100) คือการกำหนดค่าที่เป็นตัวเลขขั้นต่ำ ในที่นี้คือ 100 เซนติเมคร
    private int hight; // (ความสูง)

    @NotNull
    @Max(20) // Max(20) คือการกำหนดค่าที่เป็นตัวเลขขั้นสูงสุด ในที่นี้คือ 20 กิโลกรัม
    private Double inventoryweight; // (น้ำหนักกระเป๋า)
}
