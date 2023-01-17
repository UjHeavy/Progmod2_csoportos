package com.example.demo;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
class EmployeeController {
    @Autowired
    private final EmployeeRepository repository;

    EmployeeController(EmployeeRepository repository) {
        this.repository = repository;
    }


    // Aggregate root
    // tag::get-aggregate-root[]

    //@GetMapping("/employees/cica")
    //List<Employee> all() {
    //    return repository.findAll();
    //}

    @GetMapping("/messages")
    List<Employee> all() {
        Employee tempEmployee;
        List<Employee> listEmployee =new ArrayList<>();

        for (long i = 1; i <= repository.count(); i++) {
            tempEmployee = repository.findById(i).orElseThrow();
            //tempEmployee.setStatus(MessageStatusEnum.SEEN);
                listEmployee.add(tempEmployee);
        }
        return listEmployee;
    }

    @GetMapping("/messages/users/{getter}")
    @ResponseBody
    List<Employee> all2(@PathVariable String getter) {
        Employee tempEmployee;
        List<Employee> listEmployee =new ArrayList<>();

        for (long i = 1; i <= repository.count(); i++) {
            tempEmployee = repository.findById(i).orElseThrow();
            if (Objects.equals(tempEmployee.getGetter(), getter)) {
                tempEmployee.setStatus(MessageStatusEnum.SEEN);
                repository.save(tempEmployee);
                listEmployee.add(tempEmployee);
            }
        }
        return listEmployee;
    }
    // end::get-aggregate-root[]

    @PostMapping("/messages/users/{setter}")
    Employee newEmployee(@RequestBody Employee newEmployee, @PathVariable String setter) {
        newEmployee.setStatus(MessageStatusEnum.SENT);
        newEmployee.setSetter(setter);

        return repository.save(newEmployee);
    }

    // Single item

    @GetMapping("/messages/{id}")
    Employee one(@PathVariable Long id) {

        return repository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    @PutMapping("/messages/{id}")
    Employee replaceEmployee(@RequestBody Employee newEmployee, @PathVariable Long id) {

        return repository.findById(id)
                .map(employee -> {
                    employee.setSetter(newEmployee.getSetter());
                    employee.setGetter(newEmployee.getGetter());
                    employee.setMessage(newEmployee.getMessage());
                    return repository.save(employee);
                })
                .orElseGet(() -> {
                    newEmployee.setId(id);
                    return repository.save(newEmployee);
                });
    }

    @DeleteMapping("/messages/{id}")
    void deleteEmployee(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
