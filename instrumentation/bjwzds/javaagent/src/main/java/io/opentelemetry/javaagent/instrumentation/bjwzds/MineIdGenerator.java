package io.opentelemetry.javaagent.instrumentation.bjwzds;

import io.opentelemetry.sdk.trace.IdGenerator;

public class MineIdGenerator implements IdGenerator {
  @Override
  public String generateSpanId() {
    return String.valueOf(System.currentTimeMillis());
  }

  @Override
  public String generateTraceId() {
    return String.valueOf(System.currentTimeMillis());
  }
}
