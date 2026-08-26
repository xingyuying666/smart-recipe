# Smart Recipe

一个基于 AI 视觉能力的智能菜谱应用：上传成品菜照片，系统会推测菜品、食材与做法，并给出一份可编辑的中文菜谱。

## 功能

- 上传 JPG、PNG 或 WebP 格式的菜品图片（最大 10MB）
- 根据图片识别菜品并生成菜名、食材用量、步骤与烹饪贴士
- 支持“少油、两人份、不放香菜”等自然语言偏好
- 返回识别不确定性说明，方便在烹饪前人工确认
- 提供开箱即用的单页上传界面与 REST API

## 快速开始

### 运行要求

- JDK 17 或更高版本
- Maven 3.9 或更高版本
- OpenAI API Key

### 启动服务

在 PowerShell 中设置密钥并启动：

```powershell
$env:OPENAI_API_KEY = "你的 OpenAI API Key"
mvn spring-boot:run
```

服务启动后，打开 [http://localhost:8080](http://localhost:8080) 上传图片生成菜谱。

> 不要将 API Key 写入 `application.yml` 或提交到 Git 仓库。

## API

### 从图片生成菜谱

`POST /api/recipes/from-image`

请求类型：`multipart/form-data`

| 字段 | 必填 | 说明 |
| --- | --- | --- |
| `image` | 是 | JPG、PNG、WebP 菜品图片，最大 10MB |
| `preferences` | 否 | 口味、人数或忌口等偏好，最多 500 个字符 |

```powershell
curl.exe -X POST http://localhost:8080/api/recipes/from-image `
  -F "image=@dish.jpg" `
  -F "preferences=少油，两人份，不要香菜"
```

响应示例：

```json
{
  "title": "番茄炒蛋",
  "description": "酸甜开胃的家常快炒。",
  "servings": 2,
  "prepTimeMinutes": 5,
  "cookTimeMinutes": 8,
  "ingredients": [
    { "name": "鸡蛋", "quantity": "3 个", "note": "打散" }
  ],
  "steps": ["热锅下油", "炒香番茄后倒入蛋液"],
  "cookingTips": ["按食材实际状态调整火候"],
  "recognitionNotes": ["图片识别结果仅供参考，请确认食材与过敏原" ]
}
```

## 配置

| 环境变量 | 是否必填 | 默认值 | 说明 |
| --- | --- | --- | --- |
| `OPENAI_API_KEY` | 是 | — | OpenAI API 密钥 |
| `OPENAI_MODEL` | 否 | `gpt-5.6-luna` | 用于图像理解与菜谱生成的模型 |

图片仅在本次请求中转换为 data URL 并发送给 AI 服务；应用不会将原图写入本地磁盘。服务端请求设置为不存储。

## 项目结构

```text
src/
├── main/
│   ├── java/com/smartrecipe/
│   │   ├── recipe/                 # 图片校验、AI 调用与 API
│   │   └── SmartRecipeApplication.java
│   └── resources/
│       ├── application.yml         # 上传限制与模型配置
│       └── static/index.html       # 上传页面
└── test/                           # Web 层测试
```

## 测试

```powershell
mvn test
```

## 使用提示

AI 根据照片推测食材与用量，不能保证还原餐厅的准确配方。实际烹饪前请确认食材、过敏原、保存状态和加热熟度。
