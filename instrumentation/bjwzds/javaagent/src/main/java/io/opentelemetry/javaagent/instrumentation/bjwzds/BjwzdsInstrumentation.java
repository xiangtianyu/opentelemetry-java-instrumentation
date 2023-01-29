package io.opentelemetry.javaagent.instrumentation.bjwzds;

import static net.bytebuddy.matcher.ElementMatchers.isMethod;
import static net.bytebuddy.matcher.ElementMatchers.isPublic;
import static net.bytebuddy.matcher.ElementMatchers.named;

import io.opentelemetry.javaagent.extension.instrumentation.TypeInstrumentation;
import io.opentelemetry.javaagent.extension.instrumentation.TypeTransformer;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.matcher.ElementMatcher;

public class BjwzdsInstrumentation implements TypeInstrumentation {
  @Override
  public ElementMatcher<TypeDescription> typeMatcher() {
    return named("org.example.bjwzds.AgentTest");
  }

  @Override
  public void transform(TypeTransformer transformer) {
    transformer.applyAdviceToMethod(
        isMethod()
            .and(isPublic())
            .and(named("test")),
        this.getClass().getName() + "$BjwzdsAdvice");
  }

  @SuppressWarnings("unused")
  public static class BjwzdsAdvice {

    @Advice.OnMethodEnter(suppress = Throwable.class)
    public static void methodEnter() {
      System.out.println("enter method");
    }

    @Advice.OnMethodExit(suppress = Throwable.class)
    public static void methodExit() {
      System.out.println("exit method");
    }
  }
}
