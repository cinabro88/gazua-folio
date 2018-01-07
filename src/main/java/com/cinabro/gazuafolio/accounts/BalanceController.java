package com.cinabro.gazuafolio.accounts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("/members/{memberId}/balances")
public class BalanceController {

    @Autowired
    private BinanceExchangeService exchangeService;

    @GetMapping
    public List<Balance> getAllBalances() {
        return exchangeService.getBalances();
    }

}
