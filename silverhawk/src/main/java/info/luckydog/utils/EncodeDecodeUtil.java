package info.luckydog.utils;

import org.junit.Test;
import org.python.core.PyFunction;
import org.python.core.PyObject;
import org.python.core.PyString;
import org.python.util.PythonInterpreter;

import java.io.UnsupportedEncodingException;

/**
 * EncodeDecodeUtil
 *
 * @author eric
 * @since 2019/9/18
 */
public class EncodeDecodeUtil {


    @Test
    public void test() throws UnsupportedEncodingException {
//        String xData = "\\xe5\\xa7\\x9a\\xe6\\x80\\xbb";
        String xData = "\\xE5\\x85\\x84\\xE5\\xBC\\x9F\\xE9\\x9A\\xBE\\xE5\\xBD\\x93 \\xE6\\x9D\\x9C\\xE6\\xAD\\x8C";
        System.out.println(new String(xData.getBytes("latin1"), "utf8"));
        System.out.println(decodeXData(xData));
    }

    public static String decodeXData(String xData) {

//        PythonInterpreter interpreter = new PythonInterpreter();
//
//        interpreter.execfile(ClassLoader.getSystemResource("decodeXData.py").getFile());
//
//        // 第一个参数为期望获得的函数（变量）的名字，第二个参数为期望返回的对象类型
//        PyFunction pyFunction = interpreter.get("decode_x_data", PyFunction.class);
//
//        //调用函数，如果函数需要参数，在Java中必须先将参数转化为对应的“Python类型”
//        PyObject pyObj = pyFunction.__call__(new PyString(xData));
//        System.out.println("the answer is: " + pyObj);
//
//        return pyObj.toString();
        return "";
    }
}
