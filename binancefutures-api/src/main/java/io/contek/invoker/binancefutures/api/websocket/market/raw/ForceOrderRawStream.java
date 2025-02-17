package io.contek.invoker.binancefutures.api.websocket.market.raw;

import io.contek.invoker.binancefutures.api.websocket.market.ForceOrderEvent;
import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.websocket.WebSocketContext;

import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.ThreadSafe;

import static io.contek.invoker.binancefutures.api.websocket.common.constants.WebSocketChannelKeys.forceOrder;

@ThreadSafe
public final class ForceOrderRawStream extends RawStream<ForceOrderEvent> {

  public ForceOrderRawStream(Id id, IActor actor, WebSocketContext context) {
    super(id, actor, context);
  }

  @Immutable
  public static final class Id extends MarketWebSocketRawChannelId<ForceOrderEvent> {

    private Id(String symbol) {
      super(forceOrder(symbol));
    }

    public static Id of(String symbol) {
      return new Id(symbol);
    }

    @Override
    protected Class<ForceOrderEvent> getType() {
      return ForceOrderEvent.class;
    }
  }
}
