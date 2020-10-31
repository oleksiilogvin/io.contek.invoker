package io.contek.invoker.ftx.api.rest;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import io.contek.invoker.commons.api.actor.IActor;
import io.contek.invoker.commons.api.actor.ratelimit.RateLimitQuota;
import io.contek.invoker.commons.api.actor.security.ICredential;
import io.contek.invoker.commons.api.rest.*;

import javax.annotation.concurrent.NotThreadSafe;
import java.time.Clock;

import static com.google.common.net.UrlEscapers.urlFragmentEscaper;
import static io.contek.invoker.commons.api.rest.RestMediaType.JSON;
import static io.contek.invoker.ftx.api.ApiFactory.RateLimits.ONE_REST_PUBLIC_REQUEST;

@NotThreadSafe
public abstract class RestRequest<R> extends BaseRestRequest<R> {

  private final RestContext context;
  private final Clock clock;

  protected RestRequest(IActor actor, RestContext context) {
    super(actor);
    this.context = context;
    clock = actor.getClock();
  }

  protected abstract RestMethod getMethod();

  protected abstract String getEndpointPath();

  protected abstract RestParams getParams();

  @Override
  protected final ImmutableList<RateLimitQuota> getRequiredQuotas() {
    return ONE_REST_PUBLIC_REQUEST;
  }

  @Override
  protected final RestCall createCall(ICredential credential) {
    RestMethod method = getMethod();
    switch (method) {
      case GET:
      case DELETE:
        String paramsString = buildParamsString();
        return RestCall.newBuilder()
            .setUrl(buildUrlString(paramsString))
            .setMethod(method)
            .setHeaders(generateHeaders(paramsString, "", credential))
            .build();
      case POST:
      case PUT:
        RestMediaBody body = JSON.createBody(getParams());
        return RestCall.newBuilder()
            .setUrl(buildUrlString(""))
            .setMethod(method)
            .setHeaders(generateHeaders("", body.getStringValue(), credential))
            .setBody(body)
            .build();
      default:
        throw new IllegalStateException(getMethod().name());
    }
  }

  private ImmutableMap<String, String> generateHeaders(
      String paramsString, String bodyString, ICredential credential) {
    if (credential.isAnonymous()) {
      return ImmutableMap.of();
    }
    String ts = Long.toString(clock.millis());
    String payload = ts + getEndpointPath() + paramsString + bodyString;
    String signature = credential.sign(payload);
    return ImmutableMap.<String, String>builder()
        .put("FTX-KEY", credential.getApiKeyId())
        .put("FTX-SIGN", signature)
        .put("FTX-TS", ts)
        .build();
  }

  private String buildParamsString() {
    RestParams params = getParams();
    if (params.isEmpty()) {
      return "";
    }
    return "?" + urlFragmentEscaper().escape(params.getQueryString());
  }

  private String buildUrlString(String paramsString) {
    return context.getBaseUrl() + getEndpointPath() + paramsString;
  }
}
