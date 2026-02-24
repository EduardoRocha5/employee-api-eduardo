package br.com.tiago.schermack.projeto_teste_automatizado.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import br.com.tiago.schermack.projeto_teste_automatizado.dto.EmployeeRequestDTO;
import br.com.tiago.schermack.projeto_teste_automatizado.dto.EmployeeResponseDTO;
import br.com.tiago.schermack.projeto_teste_automatizado.entity.Employee;
import br.com.tiago.schermack.projeto_teste_automatizado.repository.EmployeeRepository;

@SpringBootTest
@Transactional
public class EmployeeServiceIntegration {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    public void deveRetornarFuncionarioCriado() {

        // arrange
        EmployeeRequestDTO requestDTO = new EmployeeRequestDTO("Eduardo", "eduardo@email.com");

        // act
        EmployeeResponseDTO responseDTO = employeeService.create(requestDTO);

        // asserts
        assertEquals(1L, responseDTO.id());
        assertEquals("Eduardo", responseDTO.firstName());
        assertEquals("eduardo@email.com", responseDTO.email());

    }

    @Test
    public void deveAtualizarFuncionario() {

        // arrange
        EmployeeRequestDTO requestDTO = new EmployeeRequestDTO("Bendito", "eduardo@email.com");

        Employee employeeSaved = new Employee("Joao", "joao@email.com");
        employeeRepository.save(employeeSaved);

        employeeRepository.findById(employeeSaved.getId());

        // act
        EmployeeResponseDTO responseDTO = employeeService.update(employeeSaved.getId(), requestDTO);

        // asserts
        assertEquals(1L, responseDTO.id());
        assertEquals("Bendito", responseDTO.firstName());
        assertEquals("eduardo@email.com", responseDTO.email());

    }

}
