package br.com.tiago.schermack.projeto_teste_automatizado.service.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.tiago.schermack.projeto_teste_automatizado.dto.EmployeeRequestDTO;
import br.com.tiago.schermack.projeto_teste_automatizado.dto.EmployeeResponseDTO;
import br.com.tiago.schermack.projeto_teste_automatizado.entity.Employee;
import br.com.tiago.schermack.projeto_teste_automatizado.repository.EmployeeRepository;
import jakarta.persistence.EntityNotFoundException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

@SpringBootTest
class EmployeeServiceTest {

    @InjectMocks
    private EmployeeService employeeService;

    @Mock
    private EmployeeRepository employeeRepository;

    @Test
    @DisplayName("Deve retornar o empregado criado")
    public void showToCreateEmployeeReturnReponseDTO() {

        // Arrange ()
        EmployeeRequestDTO requestDTO = new EmployeeRequestDTO("Eduardo", "eduardo@email.com");
        Employee employeeSaved = new Employee(requestDTO.firstName(), requestDTO.email());
        employeeSaved.setId(1L);

        when(employeeRepository.save(any(Employee.class))).thenReturn(employeeSaved);

        // act
        EmployeeResponseDTO responseDTO = employeeService.create(requestDTO);

        // Asserts
        assertEquals(1L, responseDTO.id());
        assertEquals("Eduardo", responseDTO.firstName());
        assertEquals("eduardo@email.com", responseDTO.email());

        verify(employeeRepository, times(1)).save(any(Employee.class));

    }

    @Test
    public void deveRetornarUpdateComSucesso() {

        // arrange
        EmployeeRequestDTO requestDTO = new EmployeeRequestDTO("Eduardo", "eduardo@email.com");
        Employee employeeSaved = new Employee("Joao", "joao@email.com");
        employeeSaved.setId(1L);

        when(employeeRepository.findById(employeeSaved.getId())).thenReturn(Optional.of(employeeSaved));

        // act
        EmployeeResponseDTO responseDTO = employeeService.update(employeeSaved.getId(), requestDTO);

        // asserts
        assertEquals(1L, responseDTO.id());
        assertEquals("Eduardo", responseDTO.firstName());
        assertEquals("eduardo@email.com", responseDTO.email());

    }

    @Test
    public void deveRetornarExcecaoCasoNaoExistirId() {

        // arrange
        EmployeeRequestDTO requestDTO = new EmployeeRequestDTO("Eduardo", "eduardo@email.com");

        when(employeeRepository.findById(1L))
                .thenReturn(Optional.empty());

        // act
        assertThrows(EntityNotFoundException.class,
                () -> employeeService.update(1L, requestDTO));

        // assert
        verify(employeeRepository).findById(1L);
    }

    @Test
    public void deveExcluirOFuncionarioQuandoTiverID() {

        // arrange
        Employee employee = new Employee("Eduardo", "eduardo@email.com");
        employee.setId(1L);

        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));

        // act

        employeeService.delete(1L);

        // asserts

        verify(employeeRepository).findById(1L);
        verify(employeeRepository).delete(employee);

    }

}