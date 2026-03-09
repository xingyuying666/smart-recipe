<div align="center">

<h1>🍳 Smart Recipe - 智能食谱系统</h1>

<p>
  <strong>Java 后端智能食谱管理与推荐平台</strong><br>
  基于 Spring Boot + PostgreSQL 的智能食谱应用，根据食材生成对应食谱
</p>

<p>
  <img src="https://img.shields.io/badge/Java-17-orange?style=flat-square&logo=java&logoColor=white" alt="Java"/>
  <img src="https://img.shields.io/badge/Spring_Boot-3.x-green?style=flat-square&logo=spring-boot&logoColor=white" alt="Spring Boot"/>
  <img src="https://img.shields.io/badge/Maven-3.x-blue?style=flat-square&logo=apache-maven&logoColor=white" alt="Maven"/>
  <img src="https://img.shields.io/badge/PostgreSQL-16-blue?style=flat-square&logo=postgresql&logoColor=white" alt="PostgreSQL"/>
  <img src="https://img.shields.io/badge/License-Apache%202.0-blue?style=flat-square" alt="License"/>
</p>

</div>

## ✨ 项目介绍

**Smart Recipe** 是一个智能食谱管理系统，旨在帮助用户高效管理个人食谱、根据现有食材快速推荐可做的菜品，并接入 AI 实现“输入食材 → 智能生成菜谱”的功能。

当前核心功能：
- 食谱的增删改查（Recipe CRUD）
- 食材与菜谱的关联管理
- 基于食材推荐食谱
- 数据库持久化



这是我的个人/课程作业项目，同时也是练习 Java 后端 + AI 结合的实战项目。

## 🛠️ 技术栈

| 分类       | 技术/工具                          | 说明                              |
|------------|------------------------------------|-----------------------------------|
| 后端       | Java 17 / Spring Boot 3.x          | 核心框架                          |
| 构建工具   | Maven                              | 依赖管理与构建                    |
| 数据库     | PostgreSQL                         | 主数据库（含 PL/pgSQL 存储过程）  |
| ORM        | MyBatis / Spring Data JPA（待确认）| 数据访问层                        |
| AI（规划） | LangChain4j + Ollama / OpenAI      | 未来 RAG 智能生成食谱             |
| 部署       | Docker（规划）                     | 容器化部署                        |

## 🚀 快速开始

### 1. 环境要求
- JDK 17+
- Maven 3.8+
- PostgreSQL 14+（推荐 Docker 启动）

### 2. 克隆项目
```bash
git clone https://github.com/xingyuying666/smart-recipe.git
cd smart-recipe
