package io.pragra.framework.pages;

import io.pragra.framework.data.ExcelReader;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Iterator;

public class DemoPage {

    @Test(dataProvider = "userProvider")
    public void test(Object [] arr) {
        System.out.println(Arrays.toString(arr));
    }

    @DataProvider
    public Iterator<Object[]> userProvider(){
       return ExcelReader.getDataFromSheet("Payment", true).iterator();
    }
}
