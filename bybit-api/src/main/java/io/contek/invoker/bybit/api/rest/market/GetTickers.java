package io.contek.invoker.bybit.api.rest.market;

import io.contek.invoker.bybit.api.common._Ticker;
import io.contek.invoker.bybit.api.rest.common.ResponseWrapper;
import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.rest.RestContext;
import io.contek.invoker.commons.rest.RestParams;

import javax.annotation.concurrent.NotThreadSafe;
import java.util.List;

import static io.contek.invoker.bybit.api.rest.market.GetTickers.Response;

@NotThreadSafe
public final class GetTickers extends MarketRestRequest<Response> {

  private String symbol;

  GetTickers(IActor actor, RestContext context) {
    super(actor, context);
  }

  public GetTickers setSymbol(String symbol) {
    this.symbol = symbol;
    return this;
  }

  @Override
  protected String getEndpointPath() {
    return "/v2/public/tickers";
  }

  @Override
  protected RestParams getParams() {
    RestParams.Builder builder = RestParams.newBuilder();

    if (symbol != null) {
      builder.add("symbol", symbol);
    }

    return builder.build();
  }

  @Override
  protected Class<Response> getResponseType() {
    return Response.class;
  }

  @NotThreadSafe
  public static final class Response extends ResponseWrapper<List<_Ticker>> {}
}
