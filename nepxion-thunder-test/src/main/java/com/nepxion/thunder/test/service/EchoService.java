package com.nepxion.thunder.test.service;

/**
 * <p>Title: Nepxion Thunder</p>
 * <p>Description: Nepxion Thunder For Distribution</p>
 * <p>Copyright: Copyright (c) 2015</p>
 * <p>Company: Nepxion</p>
 * @author Haojun Ren
 * @email 1394997@qq.com
 * @version 1.0
 */

import java.util.List;

import com.nepxion.thunder.test.entity.Teacher;

public interface EchoService {
    String test250Byte(String string);
    
    Teacher test2496Byte(Teacher teacher);
    
    List<Teacher> test53518Byte(List<Teacher> tlist);
    
    List<Teacher> test210748Byte(List<Teacher> tlist);
    
    List<Teacher> test1041538Byte(List<Teacher> tlist);
}