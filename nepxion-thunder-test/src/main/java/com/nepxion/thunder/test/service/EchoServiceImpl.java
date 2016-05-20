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

public class EchoServiceImpl implements EchoService {

    @Override
    public String test250Byte(String string) {
        return EchoFactory.create250Byte();
    }

    @Override
    public Teacher test2496Byte(Teacher teacher) {
        return EchoFactory.create2496Byte();
    }

    @Override
    public List<Teacher> test53518Byte(List<Teacher> tlist) {
        return EchoFactory.create53518Byte();
    }

    @Override
    public List<Teacher> test210748Byte(List<Teacher> tlist) {
        return EchoFactory.create210748Byte();
    }

    @Override
    public List<Teacher> test1041538Byte(List<Teacher> tlist) {
        return EchoFactory.create1041538Byte();
    }
}