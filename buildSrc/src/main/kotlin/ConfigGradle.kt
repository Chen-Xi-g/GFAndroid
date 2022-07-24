/**
 * <h3> 作用类描述：全局Gradle管理</h3>
 *
 * @Package :        com.alvin.build_src
 * @Date :           2022/3/3
 * @author 高国峰
 */

/**
 * 发布插件
 */
object JetpackMaven {

    /*   发布插件的相关属性     */

    const val mavenGroup = "com.alvin.gf_android"
    const val mavenVersion = "0.0.1"

}

/**
 * Gradle基础配置
 */
object AndroidConfig {
    const val compileSdk = 31
    const val applicationId = "com.alvin.gf_android"
    const val minSdk = 21
    const val targetSdk = 31
    const val versionCode = 1
    const val versionName = "1.0"
    const val minifyEnabled = false
}

/**
 * 依赖配置
 */
object Dependencies {

    private const val kotlinVersion = "1.5.30"
    private const val coreVersion = "1.7.0"
    private const val appcompatVersion = "1.3.0"
    private const val materialVersion = "1.4.0"
    private const val constraintLayoutVersion = "2.0.4"

    /* Jetpack */
    private const val lifecycleVersion = "2.2.0"
    private const val viewModelVersion = "2.3.0"
    private const val liveDataVersion = "2.2.0"

    /* 第三方 */
    private const val immersionBarVersion = "3.2.2"


    /*       Android  基础组件库         */
    const val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:$kotlinVersion"
    const val core = "androidx.core:core-ktx:${coreVersion}"
    const val appcompat = "androidx.appcompat:appcompat:${appcompatVersion}"
    const val material = "com.google.android.material:material:${materialVersion}"
    const val constraintLayout =
        "androidx.constraintlayout:constraintlayout:${constraintLayoutVersion}"

    /*       Jetpack组件               */
    // lifecycle
    const val lifecycleRuntime = "androidx.lifecycle:lifecycle-runtime-ktx:${lifecycleVersion}"
    const val lifecycleCommon = "androidx.lifecycle:lifecycle-common-java8:${lifecycleVersion}"
    const val lifecycleExtensions = "androidx.lifecycle:lifecycle-extensions:${lifecycleVersion}"

    // viewModel
    const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${viewModelVersion}"

    // liveData
    const val liveData = "androidx.lifecycle:lifecycle-livedata-ktx:${liveDataVersion}"

    /*       第三方组件库               */

    // 透明状态栏  基础依赖包，必须要依赖
    const val immersionBar = "com.geyifeng.immersionbar:immersionbar:${immersionBarVersion}"

    // 透明状态栏  kotlin扩展（可选）
    const val immersionBarKt = "com.geyifeng.immersionbar:immersionbar-ktx:${immersionBarVersion}"
}