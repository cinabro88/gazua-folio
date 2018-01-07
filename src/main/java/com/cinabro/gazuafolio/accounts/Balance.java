package com.cinabro.gazuafolio.accounts;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.webcerebrium.binance.datatype.BinanceWalletAsset;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.*;

@Getter
@Setter
@Builder
public class Balance {
    private String asset;
    private String quantity;

    private String currency;
    private String averageBuyingPrice;
    private String totalBuyingPrice;

    // allPrices
    private String currentUnitPrice;
    private String currentTotalPrice;

    public static Balance parse(BinanceWalletAsset asset) {
        return builder()
                .asset(asset.getAsset())
                .quantity(asset.getFree().toString())
                .build();
    }

    public static List<Balance> parseList(JsonArray binanceAssets) {

        List<Balance> balances = new ArrayList<>();

        binanceAssets.forEach(asset -> {
            String name = asset.getAsJsonObject().get("asset").getAsString();
            BigDecimal free = asset.getAsJsonObject().get("free").getAsBigDecimal();
            if (free.compareTo(BigDecimal.ZERO) > 0) {
                balances.add(Balance.builder()
                        .asset(name)
                        .quantity(free.toString())
                        .build());
            }
        });
        return balances;
    }
}
