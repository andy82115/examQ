package com.andyyeh.examQ

import org.junit.Assert
import org.junit.Test
import java.net.URLEncoder

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    @Test
    fun test(){
        val encode = URLEncoder.encode("臺北市", "utf-8")
        print(encode)
        Assert.assertEquals("1","1")
    }
}
