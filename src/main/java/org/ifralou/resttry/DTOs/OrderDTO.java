package org.ifralou.resttry.DTOs;

import java.time.LocalDate;
import java.util.List;

public record OrderDTO(int id, LocalDate orderDate, List<ItemDTO> items) {}
