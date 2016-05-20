package com.nepxion.thunder.test.entity;

/**
 * <p>Title: Nepxion Thunder</p>
 * <p>Description: Nepxion Thunder For Distribution</p>
 * <p>Copyright: Copyright (c) 2015</p>
 * <p>Company: Nepxion</p>
 * @author Neptune
 * @email 1394997@qq.com
 * @version 1.0
 */

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class Teacher implements Serializable {
    private static final long serialVersionUID = 5938022706758013674L;

    private String id;
    private String name;
    private String email;
    private String address;
    private String phone;
    private Date birthday;
    private double pay;
    private List<Student> studentList;
    private List<Student> studentSet;
    private int age;
    private GenderEnum gender;

    private int int1;
    private int int2;
    private int int3;
    private int int4;
    private int int5;

    private long long1;
    private long long2;
    private long long3;
    private long long4;
    private long long5;

    private float float1;
    private float float2;
    private float float3;
    private float float4;
    private float float5;

    private double double1;
    private double double2;
    private double double3;
    private double double4;
    private double double5;

    private String str1;
    private String str2;
    private String str3;
    private String str4;
    private String str5;
    private String str6;
    private String str7;
    private String str8;
    private String str9;
    private String str10;

    private boolean bool1;
    private boolean bool2;
    private boolean bool3;
    private boolean bool4;
    private boolean bool5;

    private byte byte1;
    private byte byte2;
    private byte byte3;
    private byte byte4;
    private byte byte5;

    private List<ObjectSub> subList1;
    private List<ObjectSub> subList2;
    private List<ObjectSub> subList3;
    private List<ObjectSub> subList4;
    private List<ObjectSub> subList5;

    private Set<ObjectSub> subSet1;
    private Set<ObjectSub> subSet2;
    private Set<ObjectSub> subSet3;
    private Set<ObjectSub> subSet4;
    private Set<ObjectSub> subSet5;
    
    private List<GenderEnum> genderEnum1;
    private List<GenderEnum> genderEnum2;
    private List<GenderEnum> genderEnum3;
    private List<GenderEnum> genderEnum4;
    private List<GenderEnum> genderEnum5;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public double getPay() {
        return pay;
    }

    public void setPay(double pay) {
        this.pay = pay;
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }

    public List<Student> getStudentSet() {
        return studentSet;
    }

    public void setStudentSet(List<Student> studentSet) {
        this.studentSet = studentSet;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public GenderEnum getGender() {
        return gender;
    }

    public void setGender(GenderEnum gender) {
        this.gender = gender;
    }

    public int getInt1() {
        return int1;
    }

    public void setInt1(int int1) {
        this.int1 = int1;
    }

    public int getInt2() {
        return int2;
    }

    public void setInt2(int int2) {
        this.int2 = int2;
    }

    public int getInt3() {
        return int3;
    }

    public void setInt3(int int3) {
        this.int3 = int3;
    }

    public int getInt4() {
        return int4;
    }

    public void setInt4(int int4) {
        this.int4 = int4;
    }

    public int getInt5() {
        return int5;
    }

    public void setInt5(int int5) {
        this.int5 = int5;
    }

    public long getLong1() {
        return long1;
    }

    public void setLong1(long long1) {
        this.long1 = long1;
    }

    public long getLong2() {
        return long2;
    }

    public void setLong2(long long2) {
        this.long2 = long2;
    }

    public long getLong3() {
        return long3;
    }

    public void setLong3(long long3) {
        this.long3 = long3;
    }

    public long getLong4() {
        return long4;
    }

    public void setLong4(long long4) {
        this.long4 = long4;
    }

    public long getLong5() {
        return long5;
    }

    public void setLong5(long long5) {
        this.long5 = long5;
    }

    public float getFloat1() {
        return float1;
    }

    public void setFloat1(float float1) {
        this.float1 = float1;
    }

    public float getFloat2() {
        return float2;
    }

    public void setFloat2(float float2) {
        this.float2 = float2;
    }

    public float getFloat3() {
        return float3;
    }

    public void setFloat3(float float3) {
        this.float3 = float3;
    }

    public float getFloat4() {
        return float4;
    }

    public void setFloat4(float float4) {
        this.float4 = float4;
    }

    public float getFloat5() {
        return float5;
    }

    public void setFloat5(float float5) {
        this.float5 = float5;
    }

    public double getDouble1() {
        return double1;
    }

    public void setDouble1(double double1) {
        this.double1 = double1;
    }

    public double getDouble2() {
        return double2;
    }

    public void setDouble2(double double2) {
        this.double2 = double2;
    }

    public double getDouble3() {
        return double3;
    }

    public void setDouble3(double double3) {
        this.double3 = double3;
    }

    public double getDouble4() {
        return double4;
    }

    public void setDouble4(double double4) {
        this.double4 = double4;
    }

    public double getDouble5() {
        return double5;
    }

    public void setDouble5(double double5) {
        this.double5 = double5;
    }

    public String getStr1() {
        return str1;
    }

    public void setStr1(String str1) {
        this.str1 = str1;
    }

    public String getStr2() {
        return str2;
    }

    public void setStr2(String str2) {
        this.str2 = str2;
    }

    public String getStr3() {
        return str3;
    }

    public void setStr3(String str3) {
        this.str3 = str3;
    }

    public String getStr4() {
        return str4;
    }

    public void setStr4(String str4) {
        this.str4 = str4;
    }

    public String getStr5() {
        return str5;
    }

    public void setStr5(String str5) {
        this.str5 = str5;
    }

    public String getStr6() {
        return str6;
    }

    public void setStr6(String str6) {
        this.str6 = str6;
    }

    public String getStr7() {
        return str7;
    }

    public void setStr7(String str7) {
        this.str7 = str7;
    }

    public String getStr8() {
        return str8;
    }

    public void setStr8(String str8) {
        this.str8 = str8;
    }

    public String getStr9() {
        return str9;
    }

    public void setStr9(String str9) {
        this.str9 = str9;
    }

    public String getStr10() {
        return str10;
    }

    public void setStr10(String str10) {
        this.str10 = str10;
    }

    public boolean isBool1() {
        return bool1;
    }

    public void setBool1(boolean bool1) {
        this.bool1 = bool1;
    }

    public boolean isBool2() {
        return bool2;
    }

    public void setBool2(boolean bool2) {
        this.bool2 = bool2;
    }

    public boolean isBool3() {
        return bool3;
    }

    public void setBool3(boolean bool3) {
        this.bool3 = bool3;
    }

    public boolean isBool4() {
        return bool4;
    }

    public void setBool4(boolean bool4) {
        this.bool4 = bool4;
    }

    public boolean isBool5() {
        return bool5;
    }

    public void setBool5(boolean bool5) {
        this.bool5 = bool5;
    }

    public byte getByte1() {
        return byte1;
    }

    public void setByte1(byte byte1) {
        this.byte1 = byte1;
    }

    public byte getByte2() {
        return byte2;
    }

    public void setByte2(byte byte2) {
        this.byte2 = byte2;
    }

    public byte getByte3() {
        return byte3;
    }

    public void setByte3(byte byte3) {
        this.byte3 = byte3;
    }

    public byte getByte4() {
        return byte4;
    }

    public void setByte4(byte byte4) {
        this.byte4 = byte4;
    }

    public byte getByte5() {
        return byte5;
    }

    public void setByte5(byte byte5) {
        this.byte5 = byte5;
    }

    public List<ObjectSub> getSubList1() {
        return subList1;
    }

    public void setSubList1(List<ObjectSub> subList1) {
        this.subList1 = subList1;
    }

    public List<ObjectSub> getSubList2() {
        return subList2;
    }

    public void setSubList2(List<ObjectSub> subList2) {
        this.subList2 = subList2;
    }

    public List<ObjectSub> getSubList3() {
        return subList3;
    }

    public void setSubList3(List<ObjectSub> subList3) {
        this.subList3 = subList3;
    }

    public List<ObjectSub> getSubList4() {
        return subList4;
    }

    public void setSubList4(List<ObjectSub> subList4) {
        this.subList4 = subList4;
    }

    public List<ObjectSub> getSubList5() {
        return subList5;
    }

    public void setSubList5(List<ObjectSub> subList5) {
        this.subList5 = subList5;
    }

    public Set<ObjectSub> getSubSet1() {
        return subSet1;
    }

    public void setSubSet1(Set<ObjectSub> subSet1) {
        this.subSet1 = subSet1;
    }

    public Set<ObjectSub> getSubSet2() {
        return subSet2;
    }

    public void setSubSet2(Set<ObjectSub> subSet2) {
        this.subSet2 = subSet2;
    }

    public Set<ObjectSub> getSubSet3() {
        return subSet3;
    }

    public void setSubSet3(Set<ObjectSub> subSet3) {
        this.subSet3 = subSet3;
    }

    public Set<ObjectSub> getSubSet4() {
        return subSet4;
    }

    public void setSubSet4(Set<ObjectSub> subSet4) {
        this.subSet4 = subSet4;
    }

    public Set<ObjectSub> getSubSet5() {
        return subSet5;
    }

    public void setSubSet5(Set<ObjectSub> subSet5) {
        this.subSet5 = subSet5;
    }

    public List<GenderEnum> getGenderEnum1() {
        return genderEnum1;
    }

    public void setGenderEnum1(List<GenderEnum> genderEnum1) {
        this.genderEnum1 = genderEnum1;
    }

    public List<GenderEnum> getGenderEnum2() {
        return genderEnum2;
    }

    public void setGenderEnum2(List<GenderEnum> genderEnum2) {
        this.genderEnum2 = genderEnum2;
    }

    public List<GenderEnum> getGenderEnum3() {
        return genderEnum3;
    }

    public void setGenderEnum3(List<GenderEnum> genderEnum3) {
        this.genderEnum3 = genderEnum3;
    }

    public List<GenderEnum> getGenderEnum4() {
        return genderEnum4;
    }

    public void setGenderEnum4(List<GenderEnum> genderEnum4) {
        this.genderEnum4 = genderEnum4;
    }

    public List<GenderEnum> getGenderEnum5() {
        return genderEnum5;
    }

    public void setGenderEnum5(List<GenderEnum> genderEnum5) {
        this.genderEnum5 = genderEnum5;
    }
}