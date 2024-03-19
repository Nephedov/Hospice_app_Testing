<h1>Дипломный проект по профессии "QA Engineer"</h1>
<h2>Запуск автотестов</h2>
<details><summary>Предусловия</summary>
    <ul>
        <li>Установлен JDK 11 локально</li>
        <li>JAVA_HOME определена на установленную JAVA 11</li>
        <li>Установлена Android Studio</li>
        <li>Установлена последняя стабильная версия Android SDK</li>
        <li>Android эмулятор с API 29, с русским языком системы</li>
        <li>Установлен Allure</li>
    </ul>
</details>

<ol>
    <li>Склонировать репозиторий с проектом
        <a href="https://github.com/Nephedov/Hospice_app_Testing">Nephedov/Hospice_app_Testing</a>.
    </li>
    <li>Открыть репозиторий 
        <a href="/fmh_android_15_03_24/">fmh_android_15_03_24</a> 
        в AndroidStudio.</li>
    <li>Переопределить JAVA используемую в проекте на JAVA_HOME. По пути "File/Settings/Build, Execution, Deployment/Build Tools/Gradle -> Gradle JDK".</li>
    <li>Скомпилировать проект.</li>
    <li>Запустить Android эмулятор с API 29.</li>
    <li>Запустить выполнение тестов командной "./gradlew connectedAndroidTest" из терминала AndroidStudio. Либо командой "gradle connectedAndroidTest" из другого терминала, находясь в репозитории "fmh_android_15_03_24".</li>
    <li>По окончании прогона автотестов, выгрузить папку "allure-results". По пути "Device Explorer/sdcard/googletests/test_outputfiles/allure-results"</li>
</ol>
