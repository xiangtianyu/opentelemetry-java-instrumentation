package io.opentelemetry.javaagent.instrumentation.bjwzds;

import feign.RequestTemplate;
import io.opentelemetry.instrumentation.api.instrumenter.http.HttpClientAttributesGetter;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class FeignHttpAttributesGetter implements HttpClientAttributesGetter<RequestTemplate, Void> {
  @Nullable
  @Override
  public String url(RequestTemplate requestTemplate) {
    return requestTemplate.url();
  }

  @Nullable
  @Override
  public String flavor(RequestTemplate requestTemplate, @Nullable Void unused) {
    return "feign";
  }

  @Nullable
  @Override
  public String method(RequestTemplate requestTemplate) {
    return requestTemplate.method();
  }

  @Override
  public List<String> requestHeader(RequestTemplate requestTemplate, String name) {
    return new ArrayList<>();
  }

  @Nullable
  @Override
  public Integer statusCode(RequestTemplate requestTemplate, Void unused,
      @Nullable Throwable error) {
    return 200;
  }

  @Override
  public List<String> responseHeader(RequestTemplate requestTemplate, Void unused, String name) {
    return new ArrayList<>();
  }
}
