[![](https://jitpack.io/v/jobtools/android-app-instance.svg)](https://jitpack.io/#jobtools/android-app-instance)

# android-app-instance
Just type "AppInstance.get()" to get Application object anywhere.

## Setup
Add the JitPack repository in your build.gradle (top level module):
```gradle
allprojects {
    repositories {
        jcenter()
        maven { url "https://jitpack.io" }
    }
}
```

And add next dependencies in the build.gradle of the module:
```gradle
dependencies {
    implementation 'com.github.jobtools:android-app-instance:1.0.1'
}
```
