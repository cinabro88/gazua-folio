package com.cinabro.gazuafolio.accounts;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class BinancePrice {
    private String symbol;
    private BigDecimal price;
}
