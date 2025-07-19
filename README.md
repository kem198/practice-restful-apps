<!-- omit in toc -->
# Practice - Restful apps

This project is designed to practice the following:

- Building a **REST API** with **Spring Boot** .
- Applying **Test-Driven Development** (TDD) .
- Implementing **Continuous Integration** (CI) for Java applications.

<!-- omit in toc -->
## TOC

- [Apps](#apps)
- [Requirements](#requirements)
    - [Required](#required)
    - [Recommended](#recommended)
- [Setup](#setup)
    - [Setup environment](#setup-environment)
    - [Run DB Server on Docker Container](#run-db-server-on-docker-container)
    - [Run API Server](#run-api-server)
- [Demo APIs](#demo-apis)
    - [`/v1/greeting`](#v1greeting)
    - [`/v1/error`](#v1error)
    - [`/v1/fizzbuzz`](#v1fizzbuzz)
    - [`/v1/todos`](#v1todos)
- [References](#references)
    - [Architecture](#architecture)
    - [Environment](#environment)
    - [TDD](#tdd)
    - [Spring Boot](#spring-boot)
    - [Doma](#doma)
    - [Others](#others)

## Apps

- [./src/web/todos-web](./src/web/todos-web/)
- [./src/api/todos-api](./src/api/todos-api/)

## Requirements

### Required

- [JDK 21](https://openjdk.org/projects/jdk/21/)
- [Docker](https://www.docker.com/)

### Recommended

- [SDKMAN!](https://sdkman.io/)
- [Visual Studio Code](https://azure.microsoft.com/ja-jp/products/visual-studio-code)

## Setup

### Setup environment

```shell
# Example Environment: Ubuntu 24.04 on WSL
$ lsb_release -a
No LSB modules are available.
Distributor ID: Ubuntu
Description:    Ubuntu 24.04.2 LTS
Release:        24.04
Codename:       noble

# Install SDKMAN!
$ curl -s "https://get.sdkman.io" | bash
$ sdk version
SDKMAN!
script: 5.19.0
native: 0.7.4 (linux x86_64)

# Install OpenJDK 21 (Eclipse Temurin Java 21.0.6)
$ sdk install java 21.0.6-tem
$ java --version
openjdk 21.0.6 2025-01-21 LTS
OpenJDK Runtime Environment Temurin-21.0.6+7 (build 21.0.6+7-LTS)
OpenJDK 64-Bit Server VM Temurin-21.0.6+7 (build 21.0.6+7-LTS, mixed mode, sharing)

# Clone This repository
$ cd /path/to/your/repo/
$ git clone https://github.com/kem198/practice-restful-apps.git

# Moving api apps directory
$ cd practice-restful-apps
```

### Run DB Server on Docker Container

```sh
# Current directory is the repository root
$ pwd
/path/to/your/repo/todos-api

# Copy and rename .env.example to .env
$ cp .env.example .env

# (Optional) Edit .env for your settings
$ vim .env

# Start the database service
$ docker compose up -d

# (Optional) Check running the Docker service
$ docker compose ls
NAME                    STATUS              CONFIG FILES
practice-restful-apps   running(1)          /home/kenkenpa198/works/repos/dev/test/practice-restful-apps/docker-compose.yml

# (Optional) Connect to the database and execute queries
$ docker compose exec db psql -U postgres -d todos_db
psql (17.4 (Debian 17.4-1.pgdg120+2))
Type "help" for help.

todos_db=# \d
               List of relations
 Schema |      Name       |   Type   |  Owner
--------+-----------------+----------+----------
 public | examples        | table    | postgres
 public | examples_id_seq | sequence | postgres
(2 rows)

todos_db=# SELECT * FROM examples;
 id |   name    |         created_at
----+-----------+----------------------------
  1 | Example 1 | 2025-05-01 21:24:38.143514
  2 | Example 2 | 2025-05-01 21:24:38.143514
  3 | Example 3 | 2025-05-01 21:24:38.143514
(3 rows)

todos_db=# exit
```

### Run API Server

```shell
# Move to api application root
$ cd src/api/todos-api
$ pwd
/path/to/your/repo/practice-restful-apps/src/api/todos-api

# Build and Run application
$ ./gradlew build
$ java -jar build/libs/todos-api-0.0.1-SNAPSHOT.jar

# Open new terminal and request to API
$ curl 'http://localhost:8080/v1/greeting/hello'
{"id":1,"content":"Hello, World!"}%
```

## Demo APIs

### `/v1/greeting`

```sh
$ curl -i -X GET 'http://localhost:8080/v1/greeting/hello'
HTTP/1.1 200
Content-Type: application/json
Transfer-Encoding: chunked
Date: Mon, 28 Apr 2025 05:32:49 GMT

{"id":1,"content":"Hello, World!"}%

$ curl -i -X GET 'http://localhost:8080/v1/greeting/hello?name=kem198'
HTTP/1.1 200
Content-Type: application/json
Transfer-Encoding: chunked
Date: Mon, 28 Apr 2025 05:33:03 GMT

{"id":2,"content":"Hello, kem198!"}%
```

### `/v1/error`

```sh
$ curl -s -D /dev/stderr -X GET 'http://localhost:8080/v1/error' | jq
HTTP/1.1 500
Content-Type: application/problem+json
Transfer-Encoding: chunked
Date: Tue, 06 May 2025 06:09:11 GMT
Connection: close

{
  "type": "about:blank",
  "title": "Internal Server Error",
  "status": 500,
  "instance": "/v1/error"
}

$ curl -s -D /dev/stderr -X GET 'http://localhost:8080/v1/error/detail' | jq
HTTP/1.1 500
Content-Type: application/problem+json
Transfer-Encoding: chunked
Date: Tue, 06 May 2025 06:19:59 GMT
Connection: close

{
  "type": "https://example.com",
  "title": "Internal Server Error",
  "status": 500,
  "detail": "Customized error details",
  "instance": "/v1/error/detail"
}

$ curl -s -D /dev/stderr -X GET 'http://localhost:8080/v1/error/throw-system-error' | jq
HTTP/1.1 500
Content-Type: application/problem+json
Transfer-Encoding: chunked
Date: Tue, 06 May 2025 10:02:16 GMT
Connection: close

{
  "type": "about:blank",
  "title": "Internal Server Error",
  "status": 500,
  "detail": "An unexpected error occurred. Please contact support if the problem persists.",
  "instance": "/v1/error/throw-system-error"
}
```

### `/v1/fizzbuzz`

```sh
$ curl -s -D /dev/stderr -X GET 'http://localhost:8080/v1/fizzbuzz/convert?num=3' | jq
HTTP/1.1 200
Content-Type: application/json
Transfer-Encoding: chunked
Date: Mon, 28 Apr 2025 05:43:50 GMT

{
  "result": "Fizz"
}

$ curl -s -D /dev/stderr -X GET 'http://localhost:8080/v1/fizzbuzz/convert' | jq
HTTP/1.1 400
Content-Type: application/problem+json
Transfer-Encoding: chunked
Date: Mon, 28 Apr 2025 05:44:28 GMT
Connection: close

{
  "type": "about:blank",
  "title": "Missing Parameter",
  "status": 400,
  "detail": "The 'num' query parameter is required.",
  "instance": "/v1/fizzbuzz"
}

$ curl -s -D /dev/stderr -X GET 'http://localhost:8080/v1/fizzbuzz/convert?num=abc' | jq
HTTP/1.1 400
Content-Type: application/problem+json
Transfer-Encoding: chunked
Date: Mon, 28 Apr 2025 05:44:49 GMT
Connection: close

{
  "type": "about:blank",
  "title": "Invalid Number Format",
  "status": 400,
  "detail": "The 'num' query parameter must be a valid integer.",
  "instance": "/v1/fizzbuzz"
}
```

### `/v1/todos`

```sh
$ curl -s -D /dev/stderr -X GET 'http://localhost:8080/v1/todos' | jq
HTTP/1.1 200
Content-Type: application/json
Transfer-Encoding: chunked
Date: Sun, 04 May 2025 09:31:59 GMT

[]

$ curl -s -D /dev/stderr -X POST http://localhost:8080/v1/todos \
-H 'Content-Type: application/json' \
-d '{"todoTitle": "Hello World!"}' | jq
HTTP/1.1 201
Content-Type: application/json
Transfer-Encoding: chunked
Date: Sun, 04 May 2025 09:30:59 GMT

{
  "todoId": "a9502e35-b177-43ea-9639-ba529360e2cc",
  "todoTitle": "Hello World!",
  "finished": false,
  "createdAt": "2025-05-04T09:30:59.728+00:00"
}

$ curl -s -D /dev/stderr -X GET 'http://localhost:8080/v1/todos' | jq
HTTP/1.1 200
Content-Type: application/json
Transfer-Encoding: chunked
Date: Sun, 04 May 2025 09:31:26 GMT

[
  {
    "todoId": "a9502e35-b177-43ea-9639-ba529360e2cc",
    "todoTitle": "Hello World!",
    "finished": false,
    "createdAt": "2025-05-04T09:30:59.728+00:00"
  }
]

$ curl -s -D /dev/stderr -X GET 'http://localhost:8080/v1/todos/a9502e35-b177-43ea-9639-ba529360e2cc' | jq
HTTP/1.1 200
Content-Type: application/json
Transfer-Encoding: chunked
Date: Sun, 04 May 2025 09:40:29 GMT

{
  "todoId": "a9502e35-b177-43ea-9639-ba529360e2cc",
  "todoTitle": "Hello World!",
  "finished": false,
  "createdAt": "2025-05-04T09:40:06.785+00:00"
}

$ curl -s -D /dev/stderr -X PUT 'http://localhost:8080/v1/todos/a9502e35-b177-43ea-9639-ba529360e2cc' | jq
HTTP/1.1 200
Content-Type: application/json
Transfer-Encoding: chunked
Date: Sun, 04 May 2025 09:47:41 GMT

{
  "todoId": "a9502e35-b177-43ea-9639-ba529360e2cc",
  "todoTitle": "Hello World!",
  "finished": true,
  "createdAt": "2025-05-04T09:46:24.529+00:00"
}

$ curl -s -D /dev/stderr -X DELETE 'http://localhost:8080/v1/todos/a9502e35-b177-43ea-9639-ba529360e2cc' | jq
HTTP/1.1 204 Date: Sun, 04 May 2025 09:51:12 GMT

$ curl -s -D /dev/stderr -X GET 'http://localhost:8080/v1/todos' | jq
HTTP/1.1 200
Content-Type: application/json
Transfer-Encoding: chunked
Date: Sun, 04 May 2025 09:51:28 GMT

[]
```

## References

### Architecture

- [Macchinetta Server Framework (1.x) Development Guideline](https://macchinetta.github.io/server-guideline-thymeleaf/current/ja/index.html)
    - [2.4. アプリケーションのレイヤ化](https://macchinetta.github.io/server-guideline-thymeleaf/current/ja/Overview/ApplicationLayering.html)
    - [5.1. RESTful Web Service](https://macchinetta.github.io/server-guideline-thymeleaf/current/ja/ArchitectureInDetail/WebServiceDetail/REST.html)
    - [11.1. チュートリアル(Todoアプリケーション)](https://macchinetta.github.io/server-guideline-thymeleaf/current/ja/Tutorial/TutorialTodo.html)
    - [11.2. チュートリアル(Todoアプリケーション REST編)](https://macchinetta.github.io/server-guideline-thymeleaf/current/ja/Tutorial/TutorialREST.html)
- [ちょうぜつソフトウェア設計入門 \| 技術評論社](https://gihyo.jp/book/2022/978-4-297-13234-7)
- [Web API: The Good Parts - O'Reilly Japan](https://www.oreilly.co.jp/books/9784873116860/)

### Environment

- [SDK Installation Candidates \| SDKMAN! the Software Development Kit Manager](https://sdkman.io/sdks/)

### TDD

- [テスト駆動開発 \| Ohmsha](https://www.ohmsha.co.jp/book/9784274217883/)
- [TDD Boot Camp 2020 Online \#1 基調講演/ライブコーディング - YouTube](https://www.youtube.com/watch?v=Q-FJ3XmFlT8)

### Spring Boot

- [Spring Boot in Visual Studio Code](https://code.visualstudio.com/docs/java/java-spring-boot)
- [Spring Boot REST API の作成 - 公式サンプルコード](https://spring.pleiades.io/guides/gs/rest-service)

### Doma

- [Doma へようこそ — Doma ドキュメント](https://docs.domaframework.org/ja/stable/)
    - [Doma CodeGen プラグイン — Doma ドキュメント](https://docs.domaframework.org/ja/stable/codegen/)
    - [domaframework/getting-started: Getting-started project for Doma](https://github.com/domaframework/getting-started)
    - [domaframework/simple-examples: Doma Simple Examples](https://github.com/domaframework/simple-examples)

### Others

- [エラーレスポンス :: Spring Framework - リファレンス](https://spring.pleiades.io/spring-framework/reference/web/webmvc/mvc-ann-rest-exceptions.html)
    - [RFC 9457 - Problem Details for HTTP APIs](https://datatracker.ietf.org/doc/html/rfc9457)
    - [RFC 9457 - Problem Details for HTTP APIs 日本語訳](https://tex2e.github.io/rfc-translater/html/rfc9457.html)
    - [REST API Common Spec としての HTTP Status Code と Error の提案 - Affamative Way](https://cos31.hatenablog.jp/entry/2023/12/14/093435)
    - [Spring Boot 3.0 で入った RFC7807 サポートを色々試す \#Java - Qiita](https://qiita.com/koji-cw/items/422140bd7752e4a82baf)
    - [Spring Boot で Problem Details に対応するための kotlin での実装](https://zenn.dev/bm_sms/articles/aec9aeca1fee34)
- [curl -s -f -D /dev/stderr が優勝 – 株式会社ルーター](https://rooter.jp/web-crawling/curl-s-f-d-dev-stderr-is-the-winner/)
