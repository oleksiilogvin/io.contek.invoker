package io.contek.invoker.ftx.api.websocket.market;

import io.contek.invoker.ftx.api.common._Market;

import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.NotThreadSafe;
import javax.annotation.concurrent.ThreadSafe;

import static io.contek.invoker.ftx.api.websocket.common.constants.WebSocketChannelKeys._ticker;

@ThreadSafe
public final class MarketsChannel
    extends WebSocketMarketChannel<MarketsChannel.Id, MarketsChannel.Message> {

  MarketsChannel(MarketsChannel.Id id) {
    super(id);
  }

  @Override
  public Class<MarketsChannel.Message> getMessageType() {
    return MarketsChannel.Message.class;
  }

  @Immutable
  public static final class Id extends WebSocketMarketChannelId<MarketsChannel.Message> {

    private Id(String market) {
      super(_ticker, market);
    }

    public static MarketsChannel.Id of(String market) {
      return new MarketsChannel.Id(market);
    }
  }

  @NotThreadSafe
  public static final class Data extends _Market {}

  @NotThreadSafe
  public static final class Message extends WebSocketMarketChannelMessage<Data> {}
}
