package com.cinabro.gazuafolio.accounts;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;
import com.webcerebrium.binance.api.BinanceApi;
import com.webcerebrium.binance.api.BinanceApiException;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

@Service
public class BinanceExchangeService implements ExchangeService {

    private BinanceApi binanceApi = new BinanceApi();

    public void setBinanceApi(BinanceApi binanceApi) {
        this.binanceApi = binanceApi;
    }

    @Override
    public List<Balance> getBalances() {

        binanceApi.setApiKey("rDnzDQHvBbR903PNsDZTI5akPuE0hXnIMQ9sbFO4EQcKa4fYNI6U1niYF2uRaXw6");
        binanceApi.setSecretKey("yFR3IGY9MXfIKfNiA0BGnyGLirYtSpCAcGj2AJaU6jwRJFEFswQSVsMpKTzvIq5F");

        JsonArray binanceBalances = new JsonArray();
        try {
            binanceBalances = binanceApi.balances();
        } catch (BinanceApiException e) {
            e.printStackTrace();
        }

        List<Balance> balances = Balance.parseList(binanceBalances);


        JsonArray allPrices = new JsonArray();
        try {
            allPrices = binanceApi.allPrices();
        } catch (BinanceApiException e) {
            e.printStackTrace();
        }

        Type type = new TypeToken<List<BinancePrice>>() {}.getType();
        List<BinancePrice> records = new Gson().fromJson(allPrices, type);


        // TODO BTC는 ETH 혹은, USDT 기준으로 보여주고, 그 외는 어떤기준으로 보여줄지 받아서 보여줄것 (BTC or ETH)
        balances.forEach(b -> {
            Optional<BinancePrice> priceInfo = records.stream()
                    .filter(binancePrice -> binancePrice.getSymbol().equals(b.getAsset() + "BTC"))
                    .findFirst();

            if (priceInfo.isPresent()) {
                b.setCurrentUnitPrice(priceInfo.get().getPrice().toString());
            }
        });


        return balances;
    }
}
