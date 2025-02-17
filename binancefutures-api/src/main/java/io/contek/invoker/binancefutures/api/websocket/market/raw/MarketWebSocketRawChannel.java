package io.contek.invoker.binancefutures.api.websocket.market.raw;

import io.contek.invoker.binancefutures.api.websocket.common.WebSocketEventData;
import io.contek.invoker.commons.websocket.AnyWebSocketMessage;
import io.contek.invoker.commons.websocket.BaseWebSocketChannel;
import io.contek.invoker.commons.websocket.SubscriptionState;
import io.contek.invoker.commons.websocket.WebSocketSession;

import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

import static io.contek.invoker.commons.websocket.SubscriptionState.SUBSCRIBED;
import static io.contek.invoker.commons.websocket.SubscriptionState.UNSUBSCRIBED;

@ThreadSafe
public final class MarketWebSocketRawChannel<Data extends WebSocketEventData>
    extends BaseWebSocketChannel<MarketWebSocketRawChannelId<Data>, Data, Data> {

  MarketWebSocketRawChannel(MarketWebSocketRawChannelId<Data> id) {
    super(id);
  }

  @Override
  public final Class<Data> getMessageType() {
    return getId().getType();
  }

  @Override
  protected final Data getData(Data message) {
    return message;
  }

  @Override
  protected final SubscriptionState subscribe(WebSocketSession session) {
    return SUBSCRIBED;
  }

  @Override
  protected final SubscriptionState unsubscribe(WebSocketSession session) {
    return UNSUBSCRIBED;
  }

  @Nullable
  @Override
  protected final SubscriptionState getState(AnyWebSocketMessage message) {
    return null;
  }

  @Override
  protected final void reset() {}
}
