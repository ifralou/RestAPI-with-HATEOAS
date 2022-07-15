package org.ifralou.resttry.DTOs;

import org.ifralou.resttry.persistency.entities.Address;

import java.time.LocalDate;
import java.util.List;

public record CustomerDTO(int id, String name, LocalDate birthdate, Address address) { }
