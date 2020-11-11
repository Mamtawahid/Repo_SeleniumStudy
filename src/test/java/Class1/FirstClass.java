package Class1;

import org.testng.Assert;
import org.testng.annotations.Test;

public class FirstClass {

    //Annotation
    @Test

    public void method1 (){
        System.out.println("Hello");

        int a = 10;
        int b = 20;

        boolean res = b<a;
       // Assert.assertTrue(res);
       // Assert.assertTrue(res, "result is not as expected");
       // Assert.assertEquals(a, b,"a and b are not equal");
        Assert.assertNotEquals(a, b, "a and b are equal");

    }



    @Test
    public void method2 (){
        System.out.println("Inside method 2");
    }
}
