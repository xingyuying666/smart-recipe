# Smart Recipe

上传一张成品菜照片，服务会使用视觉模型识别菜品并生成可编辑的中文菜谱。返回包含食材、用量、步骤、烹饪提示及识别不确定性的说明。

## 启动

需要 JDK 17+ 和 Maven。API 密钥只从环境变量读取：

```powershell
$env:OPENAI_API_KEY = "你的密钥"
mvn spring-boot:run
```

在浏览器打开 `http://localhost:8080`，或向 `POST /api/recipes/from-image` 发送 `multipart/form-data`：

- `image`：必填，JPG、PNG、WebP，最大 10MB。
- `preferences`：可选，最多 500 字符，例如“少油、两人份”。

示例：

```powershell
curl.exe -X POST http://localhost:8080/api/recipes/from-image -F "image=@dish.jpg" -F "preferences=少油，两人份"
```

可选环境变量：`OPENAI_MODEL`（默认 `gpt-5.6-luna`）。图片仅作为本次请求的 data URL 发送，服务端不落盘；请求设置为不存储。请在实际烹饪前确认食材、过敏原和熟度。
