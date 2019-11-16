package info.luckydog.javacore.javassist;

import javassist.*;
import org.junit.Test;

/**
 * AgentMain
 *
 * @author eric
 * @since 2019/9/24
 */
public class AgentMain {

    @Test
    public void test() throws NotFoundException, CannotCompileException, IllegalAccessException, InstantiationException {
        ClassPool cp = ClassPool.getDefault();
        CtClass cc = cp.get("info.luckydog.javacore.javassist.Hello");
        CtMethod cm = cc.getDeclaredMethod("say");
        cm.insertBefore("{ System.out.println(\"Hello.say() start\");}");
        cm.insertAfter("{ System.out.println(\"Hello.say() end\");}");
        Class c = cc.toClass();
        Hello h = (Hello) c.newInstance();
        h.say("javassist");
    }
}
