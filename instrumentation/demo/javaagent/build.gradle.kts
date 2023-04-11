plugins {
  id("otel.javaagent-instrumentation")
}

dependencies {
  compileOnly("com.example:otel-agent-sdk:1.0.0")
}
