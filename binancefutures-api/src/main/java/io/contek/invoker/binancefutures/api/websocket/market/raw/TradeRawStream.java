package io.contek.invoker.binancefutures.api.websocket.market.raw;

import io.contek.invoker.binancefutures.api.websocket.market.TradeEvent;
import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.websocket.WebSocketContext;

import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.ThreadSafe;

import static io.contek.invoker.binancefutures.api.websocket.common.constants.WebSocketChannelKeys.trade;

@ThreadSafe
public final class TradeRawStream extends RawStream<TradeEvent> {

  public TradeRawStream(Id id, IActor actor, WebSocketContext context) {
    super(id, actor, context);
  }

  @Immutable
  public static final class Id extends MarketWebSocketRawChannelId<TradeEvent> {

    private Id(String symbol) {
      super(trade(symbol));
    }

    public static Id of(String symbol) {
      return new Id(symbol);
    }

    @Override
    protected Class<TradeEvent> getType() {
      return TradeEvent.class;
    }
  }
}
