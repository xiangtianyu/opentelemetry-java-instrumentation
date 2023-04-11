package io.opentelemetry.javaagent.instrumentation.demo;

import com.example.otel.MineHeader;
import io.opentelemetry.context.Context;
import io.opentelemetry.javaagent.extension.instrumentation.TypeInstrumentation;
import io.opentelemetry.javaagent.extension.instrumentation.TypeTransformer;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.matcher.ElementMatcher;

import static net.bytebuddy.matcher.ElementMatchers.named;

public class MineHeaderInstrumentation implements TypeInstrumentation {
  @Override
  public ElementMatcher<TypeDescription> typeMatcher() {
    return named("com.example.otel.MineUtils");
  }

  @Override
  public void transform(TypeTransformer transformer) {
    transformer.applyAdviceToMethod(
        named("getMineHeader"),
        MineHeaderInstrumentation.class.getName() + "$MineAdvice");
  }

  @SuppressWarnings("unused")
  public static class MineAdvice {
    @Advice.OnMethodExit(onThrowable = Throwable.class, suppress = Throwable.class)
    public static void onExit() {
      MineHeader.fromContext(Context.current());
    }
  }
}
