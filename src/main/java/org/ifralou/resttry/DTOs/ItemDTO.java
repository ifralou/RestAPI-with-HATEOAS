package org.ifralou.resttry.DTOs;

import java.math.BigDecimal;

public record ItemDTO(int id, int quantity, BigDecimal total, ProductDTO product) {}
