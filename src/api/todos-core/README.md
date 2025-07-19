<!-- omit in toc -->
# Restful apps - API Layer Application

<!-- omit in toc -->
## TOC

- [Commands](#commands)
    - [Test](#test)
    - [Run for Development](#run-for-development)
    - [Build](#build)
    - [Generate Dao, Entity, and SQL files by Doma CodeGen](#generate-dao-entity-and-sql-files-by-doma-codegen)
    - [Run](#run)

## Commands

### Test

```shell
./gradlew test
```

### Run for Development

```shell
./gradlew bootRun
```

### Build

```shell
./gradlew build
```

### Generate Dao, Entity, and SQL files by [Doma CodeGen](https://docs.domaframework.org/ja/stable/codegen/)

```shell
./gradlew domaCodeGenPostgresqlAll
```

### Run

```shell
java -jar build/libs/todos-core-0.0.1-SNAPSHOT.jar
```
