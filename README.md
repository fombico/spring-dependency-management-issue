# Spring Dependency Management 1.1.6 Sample

This repo illustrates an issue that appears with the `io.spring.dependency-management` plugin, version `1.1.6`.

We have a gradle module, `:core`, which is published to maven local. Another module, `:app` pulls the `:core` from maven local as a dependency.

When using version 1.1.5 (configured in `gradle.properties`), running the following command will return a successful build:
```
./gradlew :app:compileJava
```

This will also publish a package `com.example.foo:core` app in maven local:
```
/Users/fombico/.m2/repository/com/example/foo
└── core
    ├── 0.0.0
    │   ├── core-0.0.0.jar
    │   └── core-0.0.0.pom
    └── maven-metadata-local.xml
```

When this folder is removed, and we run with version 1.1.6,
```
./gradlew :app:compileJava -PdependencyManagementVersion=1.1.6
```

a build failure is encountered:
```
> Task :app:compileJava FAILED

FAILURE: Build failed with an exception.

* What went wrong:
Execution failed for task ':app:compileJava'.
> Could not resolve all dependencies for configuration ':app:compileClasspath'.
   > Could not find com.example.foo:core:0.0.0.
     Required by:
         project :app
```

However, if you check the maven repo, the package was actually published.
In fact, if we run the same command again, the build succeeds.

On a side note, if we update the `org.springframework.boot` plugin to version `3.3.2`, it will also exhibit the same behavior even if the `io.spring.dependency-management` plugin is `1.1.5`.