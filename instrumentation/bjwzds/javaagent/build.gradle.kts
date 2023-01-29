plugins {
  id("otel.javaagent-instrumentation")
}

dependencies {
  implementation("io.github.openfeign:feign-core:10.12")
  implementation("io.opentelemetry:opentelemetry-sdk-trace:1.21.0")
}
