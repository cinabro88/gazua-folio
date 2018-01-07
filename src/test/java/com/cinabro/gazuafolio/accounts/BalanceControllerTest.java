package com.cinabro.gazuafolio.accounts;

import com.webcerebrium.binance.api.BinanceApi;
import com.webcerebrium.binance.api.BinanceApiException;
import com.webcerebrium.binance.datatype.BinanceSymbol;
import com.webcerebrium.binance.datatype.BinanceWalletAsset;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.*;

public class BalanceControllerTest {
    @Test
    public void getAllBalances() throws Exception, BinanceApiException {
        BinanceApi binanceApi = new BinanceApi("rDnzDQHvBbR903PNsDZTI5akPuE0hXnIMQ9sbFO4EQcKa4fYNI6U1niYF2uRaXw6", "yFR3IGY9MXfIKfNiA0BGnyGLirYtSpCAcGj2AJaU6jwRJFEFswQSVsMpKTzvIq5F");
        binanceApi.balancesMap();
        binanceApi.allPrices();

    }

}