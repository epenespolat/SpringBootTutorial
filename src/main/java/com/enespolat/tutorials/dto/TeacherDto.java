package com.enespolat.tutorials.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Log4j2
public class TeacherDto {
    private Long teacherId;
    @NotEmpty(message = "Ad Soyad alanını boş geçemezsiniz! ")
    @Size(min = 1, max = 255)
    private String teacherNameSurname;
    @NotEmpty(message = "Email alanını boş geçemezsiniz! ")
    @Email(message = "uygun formatta mail adresi giriniz!")
    private String teacherEmail;
    @NotEmpty(message = "Şifre alanını boş geçemezsiniz! ")
    @Size(min = 5, max = 28, message = " Şifre Uzunluğu Minimum 5, Maksimum 28 olmalıdır")
    private String teacherPassword;

}
