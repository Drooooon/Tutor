# Tutor 项目接口文档

## 概述

该项目是一个基于 Spring Boot 的教育管理系统，提供了用户管理、学生管理、考试管理等功能。本文档详细描述了各个接口的功能、请求方法、请求路径、请求参数和响应格式。

## 目录

1. 用户管理
    - 添加用户
    - 用户登录
2. 学生管理
    - 添加学生
    - 添加学生科目
    - 删除学生科目
    - 根据客户ID获取学生
3. 考试管理
    - 获取考试成绩
4. 科目管理
    - 获取所有科目

## 用户管理

### 添加用户

- **请求方法**: POST
- **请求路径**: `/account/add`
- **请求参数**:
    - `name` (String): 用户名
    - `password` (String): 密码
    - `email` (String): 邮箱
    - `userType` (String): 用户类型 (customer, teacher, admin)
- **请求示例**:
    ```json
    {
        "name": "John Doe",
        "password": "password123",
        "email": "john.doe@example.com",
        "userType": "customer"
    }
    ```
- **响应示例**:
    ```json
    {
        "code": 200,
        "message": "success",
        "data": {
            "id": 1,
            "name": "John Doe",
            "password": "password123",
            "userType": "customer"
        }
    }
    ```

### 用户登录

- **请求方法**: POST
- **请求路径**: `/account/login`
- **请求参数**:
    - `id` (int): 用户ID
    - `password` (String): 密码
- **请求示例**:
    ```json
    {
        "id": 1,
        "password": "password123"
    }
    ```
- **响应示例**:
    ```json
    {
        "code": 200,
        "message": "success",
        "data": {
            "id": 1,
            "name": "John Doe",
            "password": "password123",
            "userType": "customer"
        }
    }
    ```

## 学生管理

### 添加学生

- **请求方法**: POST
- **请求路径**: `/customer/addStudent`
- **请求参数**:
    - `name` (String): 学生姓名
    - `age` (int): 学生年龄
    - `subjects` (List<String>): 学生科目
    - `grade` (String): 学生年级
    - `customerId` (int): 客户ID
- **请求示例**:
    ```json
    {
        "name": "Jane Doe",
        "age": 16,
        "subjects": ["Math", "Science"],
        "grade": "高一",
        "customerId": 1
    }
    ```
- **响应示例**:
    ```json
    {
        "code": 200,
        "message": "success",
        "data": {
            "id": 1,
            "name": "Jane Doe",
            "age": 16,
            "grade": "高一",
            "customerId": 1
        }
    }
    ```

### 添加学生科目

- **请求方法**: PUT
- **请求路径**: `/customer/addSubject`
- **请求参数**:
    - `subjects` (List<String>): 科目列表
    - `stuId` (int): 学生ID
- **请求示例**:
    ```json
    {
        "subjects": ["Math", "Science"],
        "stuId": 1
    }
    ```
- **响应示例**:
    ```json
    {
        "code": 200,
        "message": "success",
        "data": ["Math", "Science"]
    }
    ```

### 删除学生科目

- **请求方法**: PUT
- **请求路径**: `/customer/deleteSubject`
- **请求参数**:
    - `subjects` (List<String>): 科目列表
    - `stuId` (int): 学生ID
- **请求示例**:
    ```json
    {
        "subjects": ["Math"],
        "stuId": 1
    }
    ```
- **响应示例**:
    ```json
    {
        "code": 200,
        "message": "success",
        "data": ["Science"]
    }
    ```

### 根据客户ID获取学生

- **请求方法**: GET
- **请求路径**: `/customer/getStudentsByCustomerId`
- **请求参数**:
    - `id` (int): 客户ID
- **请求示例**:
    ```json
    {
        "id": 1
    }
    ```
- **响应示例**:
    ```json
    {
        "code": 200,
        "message": "success",
        "data": [
            {
                "id": 1,
                "name": "Jane Doe",
                "age": 16,
                "grade": "高一",
                "customerId": 1
            }
        ]
    }
    ```

## 考试管理

### 获取考试成绩

- **请求方法**: GET
- **请求路径**: `/customer/getCharts`
- **请求参数**:
    - `studentId` (int): 学生ID
    - `subjectName` (String): 科目名称
- **请求示例**:
    ```json
    {
        "studentId": 1,
        "subjectName": "Math"
    }
    ```
- **响应示例**:
    ```json
    {
        "code": 200,
        "message": "success",
        "data": [
            [95.0, 88.0, 92.0],
            [85.0, 80.0, 90.0]
        ]
    }
    ```

## 科目管理

### 获取所有科目

- **请求方法**: GET
- **请求路径**: `/customer/getAllSubject`
- **请求参数**: 无
- **请求示例**:
    ```json
    {}
    ```
- **响应示例**:
    ```json
    {
        "code": 200,
        "message": "success",
        "data": ["Math", "Science", "English"]
    }
    ```

## AI Assistant
### 接口文档: 根据学生ID获取所选科目列表

#### 接口描述
此接口通过提供的学生ID获取该学生所选的所有科目名称。

---

#### 请求说明

- **请求类型**: `POST`
- **URL**: `/customer/getSubjectsbyStudentId`

---

#### 请求参数

| 参数名      | 类型    | 是否必填 | 描述                     |
|-------------|---------|----------|--------------------------|
| id          | `int`   | 是       | 学生ID，用于标识具体学生 |

**示例请求体**:
```json
{
  "id": 123
}
```

---

#### 响应说明

| 字段名       | 类型              | 描述                       |
|--------------|-------------------|----------------------------|
| success      | `boolean`         | 响应是否成功               |
| message      | `String`          | 响应的消息                 |
| data         | `List<String>`    | 返回的学生所选科目名称列表 |

**示例响应**:
```json
{
  "success": true,
  "message": "Operation successful",
  "data": [
    "Mathematics",
    "Physics",
    "Chemistry"
  ]
}
```

---

#### 错误案例

以下是可能的响应错误情况及解释：

1. **请求参数错误**:
  - **HTTP状态码**: `400`
  - **示例响应**:
```json
{
          "success": false,
          "message": "Invalid request data",
          "data": null
      }
```

2. **学生不存在或无科目数据**:
  - **HTTP状态码**: `404`
  - **示例响应**:
```json
{
          "success": false,
          "message": "No subjects found for the given student ID",
          "data": null
      }
```

---

#### 调用逻辑

1. 根据学生ID，查询学生所选科目的ID (`stuSubjectDao.getSubjectIdsByStudentId`)。
2. 使用查询到的科目ID列表，从数据库中获取科目名称 (`subjectDao.findSubjectNamesByIds`)。
3. 返回查询到的科目名称列表。

---

#### 注意事项

- 确保请求中提供的学生ID为有效值，且该ID已存在。
- 如果学生未选择任何科目，将返回空列表。

---

### 接口总结

- 此接口设计用于快速检索与学生关联的科目名称，适合在选课系统或学生管理系统中使用。
- 返回的科目名称列表为按需过滤的结果，详见 `StuSubjectDao` 和 `SubjectDao` 的逻辑实现。
