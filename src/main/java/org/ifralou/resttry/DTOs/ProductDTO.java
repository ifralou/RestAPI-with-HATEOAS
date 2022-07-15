package org.ifralou.resttry.DTOs;

import java.math.BigDecimal;
import java.util.*;

public record ProductDTO(int id, String name, BigDecimal price, List<CategoryDTO> categories) {
}
