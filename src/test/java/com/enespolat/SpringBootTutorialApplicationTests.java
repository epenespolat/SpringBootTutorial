package com.enespolat;

import com.enespolat.data.entity.EmployeeEntity;
import com.enespolat.data.repository.EmployeeRepository;
import com.enespolat.test.TestCrud;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SpringBootTutorialApplicationTests implements TestCrud {

    @Autowired
    EmployeeRepository employeeRepository;

    @Test
    @Override
    public void testCreate() {
        EmployeeEntity employeeEntity = EmployeeEntity.
                builder()
                .firstName("Enes Test")
                .lastName("Polat Test")
                .emailId("enes_110@hotmail.com")
                .build();
        employeeRepository.save(employeeEntity);

        // nesne null ise assertionError hatası gönder!
        assertNotNull(employeeRepository.findById(1L).get());
    }

    @Test
    @Override
    public void testList() {
        List<EmployeeEntity> list = employeeRepository.findAll();
        assertThat(list).size().isGreaterThan(0);
    }
    @Test
    @Override
    public void testFindById() {
        EmployeeEntity employeeEntity = employeeRepository.findById(1L).get();

        assertEquals("Enes Test", employeeEntity.getFirstName());

    }
    @Test
    @Override
    public void testUpdate() {
        EmployeeEntity employeeEntity = employeeRepository.findById(1L).get();

        employeeEntity.setFirstName("Enes46 Test");
        employeeRepository.save(employeeEntity);

        assertNotEquals("Enes Test", employeeRepository.findById(1L).get().getFirstName());

    }

    @Test
    @Override
    public void testDelete() {
        employeeRepository.deleteById(1L);
        assertThat(employeeRepository.existsById(1L)).isFalse();

    }
}
