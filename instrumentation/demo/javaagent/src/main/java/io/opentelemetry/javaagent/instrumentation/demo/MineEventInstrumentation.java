package io.opentelemetry.javaagent.instrumentation.demo;

import io.opentelemetry.javaagent.extension.instrumentation.TypeInstrumentation;
import io.opentelemetry.javaagent.extension.instrumentation.TypeTransformer;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.matcher.ElementMatcher;

import static net.bytebuddy.matcher.ElementMatchers.named;

public class MineEventInstrumentation implements TypeInstrumentation {
  @Override
  public ElementMatcher<TypeDescription> typeMatcher() {
    return named("com.example.springdemo.SpringDemoApplication");
  }

  @Override
  public void transform(TypeTransformer transformer) {
    transformer.applyAdviceToMethod(
        named("main"),
        MineEventInstrumentation.class.getName() + "$EventAdvice");
  }

  public static class EventAdvice {
    @Advice.OnMethodEnter(suppress = Throwable.class)
    public static void onEnter(
    ) {
      System.out.println("1212121212121");
    }
  }
}
